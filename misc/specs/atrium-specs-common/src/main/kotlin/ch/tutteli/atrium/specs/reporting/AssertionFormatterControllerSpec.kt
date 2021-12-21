package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.assertions.builders.root
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterParameterObject
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.text.TextAssertionPairFormatter
import ch.tutteli.atrium.reporting.text.impl.TextExplanatoryAssertionGroupFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFallbackAssertionFormatter
import ch.tutteli.atrium.reporting.text.impl.TextListAssertionGroupFormatter
import ch.tutteli.atrium.reporting.text.impl.TextSummaryAssertionGroupFormatter
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class AssertionFormatterControllerSpec(
    testeeFactory: () -> AssertionFormatterController,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val testee = testeeFactory()
    val arrow = "  >>"
    val warning = "  !!"
    val information = "ℹ"
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

    testee.register(TextExplanatoryAssertionGroupFormatter(bulletPoints, testee))
    val sameLineTextAssertionPairFormatter =
        TextAssertionPairFormatter.newSameLine(ToStringObjectFormatter, UsingDefaultTranslator())
    testee.register(
        TextListAssertionGroupFormatter(
            bulletPoints,
            testee,
            sameLineTextAssertionPairFormatter
        )
    )
    testee.register(TextSummaryAssertionGroupFormatter(bulletPoints, testee, sameLineTextAssertionPairFormatter))
    testee.register(
        TextFallbackAssertionFormatter(
            bulletPoints,
            testee,
            sameLineTextAssertionPairFormatter,
            ToStringObjectFormatter
        )
    )

    val holdingAssertion = assertionBuilder.descriptive
        .holding
        .withDescriptionAndRepresentation(TO_BE_GREATER_THAN_OR_EQUAL_TO, 1)
        .build()

    val failingAssertion = assertionBuilder.descriptive
        .failing
        .withDescriptionAndRepresentation(TO_BE_LESS_THAN_OR_EQUAL_TO, 2)
        .build()

    describeFun(testee::format.name) {

        context("assertionFilter which always returns `false`") {
            var sb = StringBuilder()
            var parameterObject = AssertionFormatterParameterObject.new(
                sb,
                alwaysFalseAssertionFilter
            )
            afterEachTest {
                sb = StringBuilder()
                parameterObject = AssertionFormatterParameterObject.new(
                    sb,
                    alwaysFalseAssertionFilter
                )
            }

            val anonymousType = object : ExplanatoryAssertionGroupType {}

            listOf<Pair<String, (ExplanatoryAssertionGroupType, List<Assertion>) -> AssertionGroup>>(
                "${AssertionBuilder::class.simpleName}.${AssertionBuilder::explanatoryGroup.name}.customType(t)" to { t, a ->
                    assertionBuilder.explanatoryGroup
                        .withType(t)
                        .withAssertions(a)
                        .build()
                },
                "${AssertionBuilder::class.simpleName}.customType(t, ..)" to { t, a ->
                    assertionBuilder.customType(t)
                        .withDescriptionAndRepresentation(AssertionVerb.VERB, 1)
                        .withAssertions(a)
                        .build()
                },
                "${AssertionBuilder::class.simpleName}.${AssertionBuilder::fixedClaimGroup.name}" to { t, a ->
                    assertionBuilder.fixedClaimGroup
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
                        factory(anonymousType, listOf(holdingAssertion)) to factory(
                            anonymousType,
                            listOf(failingAssertion)
                        ),
                        arrow
                    ),
                    Triple(
                        "$groupName with type ${DefaultExplanatoryAssertionGroupType::class.simpleName}",
                        factory(DefaultExplanatoryAssertionGroupType, listOf(holdingAssertion)) to factory(
                            DefaultExplanatoryAssertionGroupType,
                            listOf(failingAssertion)
                        ),
                        arrow
                    ),
                    Triple(
                        "$groupName with type ${WarningAssertionGroupType::class.simpleName}",
                        factory(WarningAssertionGroupType, listOf(holdingAssertion)) to factory(
                            WarningAssertionGroupType,
                            listOf(failingAssertion)
                        ),
                        warning
                    ),
                    Triple(
                        "$groupName with type ${InformationAssertionGroupType::class.simpleName} and withIndent = true",
                        factory(InformationAssertionGroupType(true), listOf(holdingAssertion)) to factory(
                            InformationAssertionGroupType(true),
                            listOf(failingAssertion)
                        ),
                        information
                    ),
                    Triple(
                        "$groupName with type ${InformationAssertionGroupType::class.simpleName} and withIndent = false",
                        factory(InformationAssertionGroupType(false), listOf(holdingAssertion)) to factory(
                            InformationAssertionGroupType(false),
                            listOf(failingAssertion)
                        ),
                        information
                    )
                ).forEach { (description, factories, prefix) ->
                    val (holdingGroup, failingGroup) = factories
                    context(description) {
                        it("appends the assertions without group header, if the assertion group holds") {
                            testee.format(holdingGroup, parameterObject)
                            expect(sb.toString()).toEqual(
                                lineSeparator +
                                        "$prefix ${TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault()}: 1"
                            )
                        }

                        it("appends the assertions without group header, if the assertion group does not hold") {
                            testee.format(failingGroup, parameterObject)
                            expect(sb.toString()).toEqual(
                                lineSeparator +
                                    "$prefix ${TO_BE_LESS_THAN_OR_EQUAL_TO.getDefault()}: 2"
                            )
                        }
                    }
                }
            }
        }

        context("assertionFilter which returns `false` except for the RootAssertionGroup") {
            val onlyRootAssertionGroup: (Assertion) -> Boolean =
                { it is AssertionGroup && it.type == RootAssertionGroupType }
            var sb = StringBuilder()
            var parameterObject = AssertionFormatterParameterObject.new(sb, onlyRootAssertionGroup)
            afterEachTest {
                sb = StringBuilder()
                parameterObject = AssertionFormatterParameterObject.new(sb, onlyRootAssertionGroup)
            }

            context("first an ${ExplanatoryAssertionGroupType::class.simpleName} and then a regular assertion") {
                it("appends only the explanatory assertion group") {
                    val rootGroup = assertionBuilder.root
                        .withDescriptionAndRepresentation(AssertionVerb.EXPECT, 5)
                        .withAssertions(
                            assertionBuilder.explanatoryGroup.withDefaultType.withAssertion(holdingAssertion).build(),
                            holdingAssertion
                        )
                        .build()
                    testee.format(rootGroup, parameterObject)
                    expect(sb.toString()).toEqual(
                        "${AssertionVerb.EXPECT.getDefault()}: 5$lineSeparator" +
                            "$indentBulletPoint$arrow ${TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault()}: 1"
                    )
                }
            }

            context("first a regular assertion, then an ${ExplanatoryAssertionGroupType::class.simpleName} and finally a regular assertion again") {
                it("appends only the explanatory assertion group") {
                    val rootGroup = assertionBuilder.root
                        .withDescriptionAndRepresentation(AssertionVerb.EXPECT, 5)
                        .withAssertions(
                            holdingAssertion,
                            assertionBuilder.explanatoryGroup.withWarningType.withAssertion(holdingAssertion).build(),
                            holdingAssertion
                        )
                        .build()
                    testee.format(rootGroup, parameterObject)
                    expect(sb.toString()).toEqual(
                        "${AssertionVerb.EXPECT.getDefault()}: 5$lineSeparator" +
                            "$indentBulletPoint$warning ${TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault()}: 1"
                    )
                }
            }

            context("an assertion group with assertions within an ${ExplanatoryAssertionGroupType::class.simpleName}") {
                val assertionGroup = assertionBuilder.list
                    .withDescriptionAndRepresentation(AssertionVerb.EXPECT_THROWN, 2)
                    .withAssertions(holdingAssertion, failingAssertion)
                    .build()
                val explanatoryAssertionGroup = assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withAssertions(listOf(assertionGroup, holdingAssertion))
                    .build()

                it("appends the explanatory assertion group including all its assertions") {
                    val rootGroup = assertionBuilder.root
                        .withDescriptionAndRepresentation(AssertionVerb.EXPECT, 5)
                        .withAssertion(explanatoryAssertionGroup)
                        .build()
                    testee.format(rootGroup, parameterObject)
                    expect(sb.toString()).toEqual(
                        "${AssertionVerb.EXPECT.getDefault()}: 5$lineSeparator" +
                            "$indentBulletPoint$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator" +
                            "$indentBulletPoint$indentArrow$listBulletPoint ${TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault()}: 1$lineSeparator" +
                            "$indentBulletPoint$indentArrow$listBulletPoint ${TO_BE_LESS_THAN_OR_EQUAL_TO.getDefault()}: 2$lineSeparator" +
                            "$indentBulletPoint$arrow ${TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault()}: 1"
                    )
                }

                context("within another ${ExplanatoryAssertionGroupType::class.simpleName} which is preceded and followed by a regular assertion ") {
                    it("appends the explanatory assertion group including all its assertions") {
                        val explanatoryAssertionGroup2 = assertionBuilder.explanatoryGroup
                            .withWarningType
                            .withAssertion(explanatoryAssertionGroup)
                            .build()
                        val rootGroup2 = assertionBuilder.root
                            .withDescriptionAndRepresentation(TO_BE_LESS_THAN, 10)
                            .withAssertions(failingAssertion, explanatoryAssertionGroup2, holdingAssertion)
                            .build()
                        testee.format(rootGroup2, parameterObject)
                        expect(sb.toString()).toEqual(
                            "${TO_BE_LESS_THAN.getDefault()}: 10$lineSeparator" +
                                "$indentBulletPoint$indentArrow$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator" +
                                "$indentBulletPoint$indentArrow$indentArrow$listBulletPoint ${TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault()}: 1$lineSeparator" +
                                "$indentBulletPoint$indentArrow$indentArrow$listBulletPoint ${TO_BE_LESS_THAN_OR_EQUAL_TO.getDefault()}: 2$lineSeparator" +
                                "$indentBulletPoint$indentArrow$arrow ${TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault()}: 1"
                        )
                    }
                }
            }
        }

        context("assertionFilter which always returns `true`") {
            var sb = StringBuilder()
            var parameterObject = AssertionFormatterParameterObject.new(
                sb,
                alwaysTrueAssertionFilter
            )
            afterEachTest {
                sb = StringBuilder()
                parameterObject = AssertionFormatterParameterObject.new(
                    sb,
                    alwaysTrueAssertionFilter
                )
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
                        val summaryGroup = assertionBuilder.summary
                            .withDescription(AssertionVerb.EXPECT)
                            .withAssertion(invisibleGroup)
                            .build()
                        testee.format(summaryGroup, parameterObject)
                        expect(sb.toString()).toEqual(
                            lineSeparator +
                                "${AssertionVerb.EXPECT.getDefault()}: ${Text.EMPTY}$lineSeparator" +
                                "$successfulBulletPoint ${TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault()}: 1$lineSeparator" +
                                "$failingBulletPoint ${TO_BE_LESS_THAN_OR_EQUAL_TO.getDefault()}: 2"
                        )
                    }
                }
            }
        }
    }
})
