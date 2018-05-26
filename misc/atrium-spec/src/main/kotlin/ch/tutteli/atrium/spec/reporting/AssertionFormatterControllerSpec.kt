package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.FixedClaimAssertionGroupTypeOption
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
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
import kotlin.reflect.KFunction3

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
        ExplanatoryAssertionGroupType::class.java to "$arrow ",
        WarningAssertionGroupType::class.java to "$warning ",
        ListAssertionGroupType::class.java to "$listBulletPoint ",
        RootAssertionGroupType::class.java to "$bulletPoint ",
        PrefixSuccessfulSummaryAssertion::class.java to "$successfulBulletPoint ",
        PrefixFailingSummaryAssertion::class.java to "$failingBulletPoint "
    )

    val indentArrow = " ".repeat(arrow.length + 1)
    val indentBulletPoint = " ".repeat(bulletPoint.length + 1)

    testee.register(coreFactory.newTextExplanatoryAssertionGroupFormatter(bulletPoints, testee))
    testee.register(coreFactory.newTextListAssertionGroupFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))
    testee.register(coreFactory.newTextSummaryAssertionGroupFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))
    testee.register(coreFactory.newTextFallbackAssertionFormatter(bulletPoints, testee, ToStringObjectFormatter, UsingDefaultTranslator()))

    val assertion = AssertImpl.builder.descriptive.holding.create(IS_GREATER_OR_EQUALS, 1)
    val failingAssertion = AssertImpl.builder.descriptive.failing.create(IS_LESS_OR_EQUALS, 2)

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

            val fixedClaimGroup : KFunction3<AssertionBuilder, Translatable, Any, FixedClaimAssertionGroupTypeOption>  = AssertionBuilder::fixedClaimGroup

            listOf<Pair<String, (ExplanatoryAssertionGroupType, List<Assertion>) -> AssertionGroup>>(
                "${AssertionBuilder::class.simpleName}.${AssertionBuilder::explanatoryGroup.name}.customType(t)" to { t, a -> AssertImpl.builder.explanatoryGroup.withType(t).create(a) },
                "${AssertionBuilder::class.simpleName}.customType(t, ..)" to { t, a -> AssertImpl.builder.customType(t, AssertionVerb.VERB, 1).create(a) },
                "${AssertionBuilder::class.simpleName}.${fixedClaimGroup.name}" to { t, a -> AssertImpl.builder.fixedClaimGroup(AssertionVerb.VERB, 1).withType(t).failing.create(a) }
            ).forEach { (groupName, factory) ->
                listOf(
                    Triple(
                        "$groupName with type object: ${ExplanatoryAssertionGroupType::class.simpleName}",
                        factory(anonymousType, listOf(assertion)) to factory(anonymousType, listOf(failingAssertion)),
                        arrow
                    ),
                    Triple(
                        "$groupName with type ${DefaultExplanatoryAssertionGroupType::class.simpleName}",
                        factory(DefaultExplanatoryAssertionGroupType, listOf(assertion)) to factory(DefaultExplanatoryAssertionGroupType, listOf(failingAssertion)),
                        arrow
                    ),
                    Triple(
                        "$groupName with type ${WarningAssertionGroupType::class.simpleName}",
                        factory(WarningAssertionGroupType, listOf(assertion)) to factory(WarningAssertionGroupType, listOf(failingAssertion)),
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
                    val rootGroup = AssertImpl.builder.root(AssertionVerb.ASSERT, 5).create(
                        AssertImpl.builder.explanatoryGroup.withDefault.create(listOf(assertion)),
                        assertion
                    )
                    testee.format(rootGroup, parameterObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }
            }

            context("first a regular assertion, then an ${ExplanatoryAssertionGroupType::class.simpleName} and finally a regular assertion again") {
                it("appends only the explanatory assertion group") {
                    val rootGroup = AssertImpl.builder.root(AssertionVerb.ASSERT, 5).create(listOf(
                        assertion,
                        AssertImpl.builder.explanatoryGroup.withWarning.create(assertion),
                        assertion
                    ))
                    testee.format(rootGroup, parameterObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$warning ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }
            }

            context("an assertion group with assertions within an ${ExplanatoryAssertionGroupType::class.simpleName}") {
                val assertionGroup = AssertImpl.builder.list(AssertionVerb.EXPECT_THROWN, 2).create(listOf(assertion, failingAssertion))
                val explanatoryAssertionGroup = AssertImpl.builder.explanatoryGroup.withDefault.create(listOf(assertionGroup, assertion))

                it("appends the explanatory assertion group including all its assertions") {
                    val rootGroup = AssertImpl.builder.root(AssertionVerb.ASSERT, 5).create(explanatoryAssertionGroup)
                    testee.format(rootGroup, parameterObject)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 5$separator" +
                        "$indentBulletPoint$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator" +
                        "$indentBulletPoint$indentArrow$listBulletPoint ${IS_GREATER_OR_EQUALS.getDefault()}: 1$separator" +
                        "$indentBulletPoint$indentArrow$listBulletPoint ${IS_LESS_OR_EQUALS.getDefault()}: 2$separator" +
                        "$indentBulletPoint$arrow ${IS_GREATER_OR_EQUALS.getDefault()}: 1")
                }

                context("within another ${ExplanatoryAssertionGroupType::class.simpleName} which is preceded and followed by a regular assertion ") {
                    it("appends the explanatory assertion group including all its assertions") {
                        val explanatoryAssertionGroup2 = AssertImpl.builder.explanatoryGroup.withWarning.create(explanatoryAssertionGroup)
                        val rootGroup2 = AssertImpl.builder.root(IS_LESS_THAN, 10).create(listOf(failingAssertion, explanatoryAssertionGroup2, assertion))
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
                            override val name = Untranslatable("test")
                            override val type = InvisibleAssertionGroupType
                            override val representation = "representation"
                            override val assertions = listOf(assertion, failingAssertion)
                        }
                        val summaryGroup = AssertImpl.builder.summary(AssertionVerb.ASSERT).create(invisibleGroup)
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
