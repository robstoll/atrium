package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class AssertionFormatterControllerSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: () -> AssertionFormatterController,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val testee = testeeFactory()
    val arrow = "  >>"
    val warning = "  !!"
    val bulletPoint = "*"
    val listBulletPoint = "=="
    val successfulBulletPoint = "(check)"
    val failingBulletPoint = "(fail)"
    val bulletPoints = mapOf(
        ExplanatoryAssertionGroupType::class to "$arrow ",
        WarningAssertionGroupType::class to "$warning ",
        ListAssertionGroupType::class to "$listBulletPoint ",
        RootAssertionGroupType::class to "$bulletPoint ",
        PrefixSuccessfulSummaryAssertion::class to "$successfulBulletPoint ",
        PrefixFailingSummaryAssertion::class to "$failingBulletPoint "
    )

    val indentArrow = " ".repeat(arrow.length + 1)
    val indentBulletPoint = " ".repeat(bulletPoint.length + 1)

    testee.register(coreFactory.newTextExplanatoryAssertionGroupFormatter(bulletPoints, testee))
    testee.register(coreFactory.newTextListAssertionGroupFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))
    testee.register(coreFactory.newTextSummaryAssertionGroupFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))
    testee.register(coreFactory.newTextFallbackAssertionFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))

    val holdingAssertion = AssertImpl.builder.descriptive
        .holding
        .withDescriptionAndRepresentation(IS_GREATER_OR_EQUALS, 1)
        .build()

    val failingAssertion = AssertImpl.builder.descriptive
        .failing
        .withDescriptionAndRepresentation(IS_LESS_OR_EQUALS, 2)
        .build()

    val separator = System.getProperty("line.separator")!!

    describeFun(testee::format.name) {

        context("assertionFilter which always returns `false`") {
            var sb = StringBuilder()
            var parameterObject = AssertionFormatterParameterObject.new(sb, alwaysFalseAssertionFilter)
            afterEachTest {
                sb = StringBuilder()
                parameterObject = AssertionFormatterParameterObject.new(sb, alwaysFalseAssertionFilter)
            }

            val anonymousType = object : ExplanatoryAssertionGroupType {}

            listOf<Pair<String, (ExplanatoryAssertionGroupType, List<Assertion>) -> AssertionGroup>>(
                "${AssertionBuilder::class.simpleName}.${AssertionBuilder::explanatoryGroup.name}.customType(t)" to { t, a ->
                    AssertImpl.builder.explanatoryGroup
                        .withType(t)
                        .withAssertions(a)
                        .build()
                },
                "${AssertionBuilder::class.simpleName}.customType(t, ..)" to { t, a ->
                    AssertImpl.builder.customType(t)
                        .withDescriptionAndRepresentation(AssertionVerb.VERB, 1)
                        .withAssertions(a)
                        .build()
                },
                "${AssertionBuilder::class.simpleName}.${AssertionBuilder::fixedClaimGroup.name}" to { t, a ->
                    AssertImpl.builder.fixedClaimGroup
                        .withType(t)
                        .failing
                        .withDescriptionAndRepresentation(AssertionVerb.VERB, 1)
                        .withAssertions(a)
                        .build()
                }
            ).forEach { (groupName, factory) ->
                listOf(
                    Triple(
                        "$groupName with type object: ${ExplanatoryAssertionGroupType::class.simpleName}",
                        factory(anonymousType, listOf(holdingAssertion)) to factory(anonymousType, listOf(failingAssertion)),
                        arrow
                    ),
                    Triple(
                        "$groupName with type ${DefaultExplanatoryAssertionGroupType::class.simpleName}",
                        factory(DefaultExplanatoryAssertionGroupType, listOf(holdingAssertion)) to factory(DefaultExplanatoryAssertionGroupType, listOf(failingAssertion)),
                        arrow
                    ),
                    Triple(
                        "$groupName with type ${WarningAssertionGroupType::class.simpleName}",
                        factory(WarningAssertionGroupType, listOf(holdingAssertion)) to factory(WarningAssertionGroupType, listOf(failingAssertion)),
                        warning
                    )
                ).forEach { (description, factories, prefix) ->
                    val (holdingGroup, failingGroup) = factories
                    context(description) {
                        it("appends the assertions without group header, if the assertion group holds") {
                            testee.format(holdingGroup, parameterObject)
                            verbs.checkImmediately(sb.toString()).toBe(separator +
                                "$prefix ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                        }

                        it("appends the assertions without group header, if the assertion group does not hold") {
                            testee.format(failingGroup, parameterObject)
                            verbs.checkImmediately(sb.toString()).toBe(separator +
                                "$prefix ${IS_LESS_OR_EQUALS.getDefault()}: 2")
                        }
                    }
                }
            }
        }

        context("assertionFilter which returns `false` except for the RootAssertionGroup") {
            val onlyRootAssertionGroup: (Assertion) -> Boolean = { it is AssertionGroup && it.type == RootAssertionGroupType }
            var sb = StringBuilder()
            var parameterObject = AssertionFormatterParameterObject.new(sb, onlyRootAssertionGroup)
            afterEachTest {
                sb = StringBuilder()
                parameterObject = AssertionFormatterParameterObject.new(sb, onlyRootAssertionGroup)
            }

            context("first an ${ExplanatoryAssertionGroupType::class.simpleName} and then a regular assertion") {
                it("appends only the explanatory assertion group") {
                    val rootGroup = AssertImpl.builder.root
                        .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 5)
                        .withAssertions(
                            AssertImpl.builder.explanatoryGroup.withDefaultType.withAssertion(holdingAssertion).build(),
                            holdingAssertion
                        )
                        .build()
                    testee.format(rootGroup, parameterObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }
            }

            context("first a regular assertion, then an ${ExplanatoryAssertionGroupType::class.simpleName} and finally a regular assertion again") {
                it("appends only the explanatory assertion group") {
                    val rootGroup = AssertImpl.builder.root
                        .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 5)
                        .withAssertions(
                            holdingAssertion,
                            AssertImpl.builder.explanatoryGroup.withWarningType.withAssertion(holdingAssertion).build(),
                            holdingAssertion
                        )
                        .build()
                    testee.format(rootGroup, parameterObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$warning ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }
            }

            context("an assertion group with assertions within an ${ExplanatoryAssertionGroupType::class.simpleName}") {
                val assertionGroup = AssertImpl.builder.list
                    .withDescriptionAndRepresentation(AssertionVerb.EXPECT_THROWN, 2)
                    .withAssertions(holdingAssertion, failingAssertion)
                    .build()
                val explanatoryAssertionGroup = AssertImpl.builder.explanatoryGroup
                    .withDefaultType
                    .withAssertions(listOf(assertionGroup, holdingAssertion))
                    .build()

                it("appends the explanatory assertion group including all its assertions") {
                    val rootGroup = AssertImpl.builder.root
                        .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 5)
                        .withAssertion(explanatoryAssertionGroup)
                        .build()
                    testee.format(rootGroup, parameterObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator" +
                        "$indentBulletPoint$indentArrow$listBulletPoint ${IS_GREATER_OR_EQUALS.getDefault()}: 1$separator" +
                        "$indentBulletPoint$indentArrow$listBulletPoint ${IS_LESS_OR_EQUALS.getDefault()}: 2$separator" +
                        "$indentBulletPoint$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }

                context("within another ${ExplanatoryAssertionGroupType::class.simpleName} which is preceded and followed by a regular assertion ") {
                    it("appends the explanatory assertion group including all its assertions") {
                        val explanatoryAssertionGroup2 = AssertImpl.builder.explanatoryGroup
                            .withWarningType
                            .withAssertion(explanatoryAssertionGroup)
                            .build()
                        val rootGroup2 = AssertImpl.builder.root
                            .withDescriptionAndRepresentation(IS_LESS_THAN, 10)
                            .withAssertions(failingAssertion, explanatoryAssertionGroup2, holdingAssertion)
                            .build()
                        testee.format(rootGroup2, parameterObject)
                        verbs.checkImmediately(sb.toString()).toBe("${IS_LESS_THAN.getDefault()}: 10$separator" +
                            "$indentBulletPoint$indentArrow$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator" +
                            "$indentBulletPoint$indentArrow$indentArrow$listBulletPoint ${IS_GREATER_OR_EQUALS.getDefault()}: 1$separator" +
                            "$indentBulletPoint$indentArrow$indentArrow$listBulletPoint ${IS_LESS_OR_EQUALS.getDefault()}: 2$separator" +
                            "$indentBulletPoint$indentArrow$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                    }
                }
            }
        }

        context("assertionFilter which always returns `true`") {
            var sb = StringBuilder()
            var parameterObject = AssertionFormatterParameterObject.new(sb, alwaysTrueAssertionFilter)
            afterEachTest {
                sb = StringBuilder()
                parameterObject = AssertionFormatterParameterObject.new(sb, alwaysTrueAssertionFilter)
            }

            context("an assertion group with type ${SummaryAssertionGroupType::class.simpleName}") {
                context("an assertion group with type ${InvisibleAssertionGroupType::class.simpleName} with two assertions") {
                    it("appends both assertions, no header for group with ${InvisibleAssertionGroupType::class.simpleName}, and prefix holding/failing assertion accordingly") {
                        val invisibleGroup: AssertionGroup = object : AssertionGroup {
                            override val description = Untranslatable("test")
                            override val type = InvisibleAssertionGroupType
                            override val representation = "representation"
                            override val assertions = listOf(holdingAssertion, failingAssertion)
                        }
                        val summaryGroup = AssertImpl.builder.summary
                            .withDescription(AssertionVerb.ASSERT)
                            .withAssertion(invisibleGroup)
                            .build()
                        testee.format(summaryGroup, parameterObject)
                        verbs.checkImmediately(sb.toString()).toBe(separator +
                            "${AssertionVerb.ASSERT.getDefault()}: ${RawString.EMPTY}$separator" +
                            "$successfulBulletPoint ${IS_GREATER_OR_EQUALS.getDefault()}: 1$separator" +
                            "$failingBulletPoint ${IS_LESS_OR_EQUALS.getDefault()}: 2"
                        )
                    }
                }
            }
        }
    }
})
