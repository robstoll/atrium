package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.isEmpty
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeparator
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class TextSummaryAssertionGroupFormatterSpec(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val successBulletPoint = "(/)"
    val indentSuccessBulletPoint = " ".repeat(successBulletPoint.length + 1)
    val failingBulletPoint = "(x)"
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length + 1)

    val facade = createFacade(
        mapOf(
            PrefixSuccessfulSummaryAssertion::class to "$successBulletPoint ",
            PrefixFailingSummaryAssertion::class to "$failingBulletPoint "
        )
    ) { bulletPoints, controller, _, _ ->
        testeeFactory(bulletPoints, controller)
    }

    val onlyFailingAssertionFilter: (Assertion) -> Boolean = { !it.holds() }

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(bulletPoints, DefaultAssertionFormatterController())
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${SummaryAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(
                assertionBuilder.customType(object : SummaryAssertionGroupType {})
                    .withDescriptionAndRepresentation(Untranslatable.EMPTY, 1)
                    .withAssertions(listOf())
                    .build()
            )

            expect(result).toBe(true)
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {
        context("${AssertionGroup::class.simpleName} of ${DefaultSummaryAssertionGroupType::class.simpleName} and does not hold") {
            val assertions = listOf(
                assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(
                    AssertionVerb.ASSERT,
                    1
                ).build(),
                assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                    AssertionVerb.EXPECT_THROWN,
                    2
                ).build()
            )
            val summaryAssertionGroup = assertionBuilder.customType(DefaultSummaryAssertionGroupType)
                .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 22)
                .withAssertions(assertions)
                .build()

            context("format directly the group (no prefix given)") {
                it("puts the assertions one under the other, does not filter out successful ones and indicates whether they hold or not") {
                    facade.format(
                        summaryAssertionGroup,
                        sb, onlyFailingAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        lineSeparator
                            + "${AssertionVerb.ASSERT.getDefault()}: 22$lineSeparator"
                            + "$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeparator"
                            + "$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2"
                    )
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the other and indents the second one including a prefix") {
                    val featureAssertions = listOf(
                        summaryAssertionGroup,
                        assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                            AssertionVerb.ASSERT,
                            20
                        ).build()
                    )
                    val featureAssertionGroup = assertionBuilder.feature
                        .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 10)
                        .withAssertions(featureAssertions)
                        .build()
                    facade.format(
                        featureAssertionGroup,
                        sb, onlyFailingAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        lineSeparator
                            + "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$lineSeparator"
                            + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$lineSeparator"
                            + "$indentArrow$indentFeatureBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeparator"
                            + "$indentArrow$indentFeatureBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                            + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20"
                    )
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(
                    summaryAssertionGroup,
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        AssertionVerb.ASSERT,
                        20
                    ).build()
                )
                val listAssertionGroup = assertionBuilder.list
                    .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 10)
                    .withAssertions(listAssertions)
                    .build()

                it("puts the assertions one under the other and indents the second one including a prefix") {
                    facade.format(
                        listAssertionGroup,
                        sb, onlyFailingAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        lineSeparator
                            + "${AssertionVerb.ASSERT.getDefault()}: 10$lineSeparator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$lineSeparator"
                            + "$indentListBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeparator"
                            + "$indentListBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20"
                    )
                }

                context("in another ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                    it("puts the assertions one under the other and indents as the other assertions but adds an extra indent to the second assertion including a prefix") {
                        val listAssertions2 = listOf(
                            listAssertionGroup,
                            assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                AssertionVerb.EXPECT_THROWN,
                                30
                            ).build()
                        )
                        val listAssertionGroup2 = assertionBuilder.list
                            .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 5)
                            .withAssertions(listAssertions2)
                            .build()
                        facade.format(
                            listAssertionGroup2,
                            sb, onlyFailingAssertionFilter
                        )
                        expect(sb.toString()).toBe(
                            lineSeparator
                                + "${AssertionVerb.ASSERT.getDefault()}: 5$lineSeparator"
                                + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 10$lineSeparator"
                                + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$lineSeparator"
                                + "$indentListBulletPoint$indentListBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeparator"
                                + "$indentListBulletPoint$indentListBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                                + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$lineSeparator"
                                + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 30"
                        )
                    }
                }
            }

            context("in another ${AssertionGroup::class.simpleName} of type object: ${SummaryAssertionGroupType::class.simpleName}") {
                val summaryAssertions = listOf(
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        AssertionVerb.ASSERT,
                        21
                    ).build(),
                    summaryAssertionGroup,
                    assertionBuilder.summary
                        .withDescription(AssertionVerb.EXPECT_THROWN)
                        .withAssertions(
                            assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(
                                AssertionVerb.ASSERT,
                                30
                            ).build(),
                            assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(
                                AssertionVerb.ASSERT,
                                31
                            ).build()
                        )
                        .build()
                )
                val summaryAssertionGroup2 = assertionBuilder.customType(object : SummaryAssertionGroupType {})
                    .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 10)
                    .withAssertions(summaryAssertions)
                    .build()

                it("puts the assertions one under the other and adds an extra indent to the second one") {
                    facade.format(
                        summaryAssertionGroup2,
                        sb, onlyFailingAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        lineSeparator
                            + "${AssertionVerb.ASSERT.getDefault()}: 10$lineSeparator"
                            + "$failingBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 21$lineSeparator"
                            + "$failingBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$lineSeparator"
                            + "$indentFailingBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeparator"
                            + "$indentFailingBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                            + "$successBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}:  (Text)$lineSeparator"
                            + "$indentSuccessBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30$lineSeparator"
                            + "$indentSuccessBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 31"
                    )
                }
            }
        }
        context("${AssertionGroup::class.simpleName} of ${DefaultSummaryAssertionGroupType::class.simpleName} and group holds") {
            it("The group is not formatted since it is filtered out") {
                val assertions = listOf(
                    assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(
                        AssertionVerb.ASSERT,
                        1
                    ).build()
                )
                val summaryAssertionGroup = assertionBuilder.summary
                    .withDescription(AssertionVerb.ASSERT)
                    .withAssertions(assertions)
                    .build()
                facade.format(
                    summaryAssertionGroup,
                    sb, onlyFailingAssertionFilter
                )
                expect(sb.toString()).isEmpty()
            }
        }
    }
})
