package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.isEmpty
import ch.tutteli.atrium.api.cc.en_UK.isTrue
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it


abstract class TextSummaryAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, IAssertionFormatterController) -> IAssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val successBulletPoint = "(/)"
    val indentSuccessBulletPoint = " ".repeat(successBulletPoint.length + 1)
    val failingBulletPoint = "(x)"
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length + 1)

    val facade = createFacade(mapOf(
        PrefixSuccessfulSummaryAssertion::class.java to "$successBulletPoint ",
        PrefixFailingSummaryAssertion::class.java to "$failingBulletPoint "
    )) { bulletPoints, controller, _, _ ->
        testeeFactory(bulletPoints, controller)
    }

    val onlyFailingAssertionFilter: (IAssertion) -> Boolean = { !it.holds() }

    prefixedDescribe("fun ${IAssertionFormatter::canFormat.name}") {
        val testee = testeeFactory(bulletPoints, AtriumFactory.newAssertionFormatterController())
        it("returns true for an ${IAssertionGroup::class.simpleName} with type object: ${ISummaryAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(AssertionGroup(object : ISummaryAssertionGroupType {}, Untranslatable(""), 1, listOf()))
            verbs.checkImmediately(result).isTrue()
        }
    }

    prefixedDescribe("fun ${IAssertionFormatter::formatGroup.name}") {
        context("${IAssertionGroup::class.simpleName} of ${SummaryAssertionGroupType::class.simpleName} and does not hold") {
            val assertions = listOf(
                BasicAssertion(AssertionVerb.ASSERT, 1, true),
                BasicAssertion(AssertionVerb.EXPECT_THROWN, 2, false)
            )
            val summaryAssertionGroup = AssertionGroup(SummaryAssertionGroupType, AssertionVerb.ASSERT, 22, assertions)

            context("format directly the group (no prefix given)") {
                it("puts the assertions one under the other, does not filter out successful ones and indicates whether they hold or not") {
                    facade.format(summaryAssertionGroup, sb, onlyFailingAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                        + "$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                }
            }

            context("in an ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the other and indents the second one including a prefix") {
                    val featureAssertions = listOf(summaryAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                    val featureAssertionGroup = AssertionGroup(FeatureAssertionGroupType, AssertionVerb.ASSERT, 10, featureAssertions)
                    facade.format(featureAssertionGroup, sb, onlyFailingAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                        + "$indentArrow$indentFeatureBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentArrow$indentFeatureBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }

            context("in an ${IAssertionGroup::class.simpleName} of type ${IListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(summaryAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                val listAssertionGroup = AssertionGroup(ListAssertionGroupType, AssertionVerb.ASSERT, 10, listAssertions)

                it("puts the assertions one under the other and indents the second one including a prefix") {
                    facade.format(listAssertionGroup, sb, onlyFailingAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                        + "$indentListBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentListBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }

                context("in another ${IAssertionGroup::class.simpleName} of type ${IListAssertionGroupType::class.simpleName}") {
                    it("puts the assertions one under the other and indents as the other assertions but adds an extra indent to the second assertion including a prefix") {
                        val listAssertions2 = listOf(listAssertionGroup, BasicAssertion(AssertionVerb.EXPECT_THROWN, 30, false))
                        val listAssertionGroup2 = AssertionGroup(ListAssertionGroupType, AssertionVerb.ASSERT, 5, listAssertions2)
                        facade.format(listAssertionGroup2, sb, onlyFailingAssertionFilter)
                        verbs.checkImmediately(sb.toString()).toBe(separator
                            + "${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                            + "$indentListBulletPoint$indentListBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$indentListBulletPoint$indentListBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                            + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 30")
                    }
                }
            }

            context("in another ${IAssertionGroup::class.simpleName} of type object: ${ISummaryAssertionGroupType::class.simpleName}") {
                val summaryAssertions = listOf(
                    BasicAssertion(AssertionVerb.ASSERT, 21, false),
                    summaryAssertionGroup,
                    AssertionGroup(SummaryAssertionGroupType, AssertionVerb.EXPECT_THROWN, 14, listOf(
                        BasicAssertion(AssertionVerb.ASSERT, 30, true),
                        BasicAssertion(AssertionVerb.ASSERT, 31, true)
                    ))
                )
                val summaryAssertionGroup2 = AssertionGroup(object : ISummaryAssertionGroupType {}, AssertionVerb.ASSERT, 10, summaryAssertions)

                it("puts the assertions one under the other and adds an extra indent to the second one") {
                    facade.format(summaryAssertionGroup2, sb, onlyFailingAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$failingBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 21$separator"
                        + "$failingBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                        + "$indentFailingBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentFailingBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$successBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 14$separator"
                        + "$indentSuccessBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30$separator"
                        + "$indentSuccessBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 31"
                    )
                }
            }
        }
        context("${IAssertionGroup::class.simpleName} of ${SummaryAssertionGroupType::class.simpleName} and group holds") {
            test("The group is not formatted since it is filtered out") {
                val assertions = listOf(
                    BasicAssertion(AssertionVerb.ASSERT, 1, true)
                )
                val summaryAssertionGroup = AssertionGroup(SummaryAssertionGroupType, AssertionVerb.ASSERT, 22, assertions)
                facade.format(summaryAssertionGroup, sb, onlyFailingAssertionFilter)
                verbs.checkImmediately(sb.toString()).isEmpty()
            }
        }
    }
})
