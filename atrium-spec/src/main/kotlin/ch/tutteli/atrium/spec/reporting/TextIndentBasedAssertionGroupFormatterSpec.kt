package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.isTrue
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it


abstract class TextIndentBasedAssertionGroupFormatterSpec<T : AssertionGroupType>(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    assertionGroupTypeClass: Class<T>,
    anonymousAssertionGroupType: T,
    groupFactory: (List<Assertion>) -> AssertionGroup,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val indentBulletPoint = " +"
    val indentIndentBulletPoint = " ".repeat(indentBulletPoint.length + 1)

    val facade = createFacade(assertionGroupTypeClass to "$indentBulletPoint ") { bulletPoints, controller, _, _ ->
        testeeFactory(bulletPoints, controller)
    }

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(bulletPoints, AtriumFactory.newAssertionFormatterController())
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${assertionGroupTypeClass.simpleName}") {
            val result = testee.canFormat(AssertionGroup.Builder.withType(anonymousAssertionGroupType).create(Untranslatable.EMPTY, 1, listOf()))
            verbs.checkImmediately(result).isTrue()
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {
        context("${AssertionGroup::class.simpleName} of type ${assertionGroupTypeClass.simpleName}") {
            val assertions = listOf(
                BasicDescriptiveAssertion(AssertionVerb.ASSERT, 1, true),
                BasicDescriptiveAssertion(AssertionVerb.EXPECT_THROWN, 2, true)
            )
            val indentAssertionGroup = groupFactory(assertions)

            context("format directly the group (no prefix given)") {
                it("puts the assertions one under the other and indents the second one including a prefix") {
                    facade.format(indentAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the other and indents the second one including a prefix") {
                    val featureAssertions = listOf(indentAssertionGroup, BasicDescriptiveAssertion(AssertionVerb.ASSERT, 20, false))
                    val featureAssertionGroup = AssertionGroup.Builder.feature.create(AssertionVerb.ASSERT, 10, featureAssertions)
                    facade.format(featureAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$indentArrow$indentFeatureBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentArrow$indentFeatureBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(indentAssertionGroup, BasicDescriptiveAssertion(AssertionVerb.ASSERT, 20, false))
                val listAssertionGroup = AssertionGroup.Builder.list.create(AssertionVerb.ASSERT, 10, listAssertions)

                it("puts the assertions one under the other and indents the second one including a prefix") {
                    facade.format(listAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$indentListBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentListBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }

                context("in another ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                    it("puts the assertions one under the other and indents as the other assertions but adds an extra indent to the second assertion including a prefix") {
                        val listAssertions2 = listOf(listAssertionGroup, BasicDescriptiveAssertion(AssertionVerb.EXPECT_THROWN, 30, false))
                        val listAssertionGroup2 = AssertionGroup.Builder.list.create(AssertionVerb.ASSERT, 5, listAssertions2)
                        facade.format(listAssertionGroup2, sb, alwaysTrueAssertionFilter)
                        verbs.checkImmediately(sb.toString()).toBe(separator
                            + "${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$indentListBulletPoint$indentListBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$indentListBulletPoint$indentListBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                            + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 30")
                    }
                }
            }

            context("in another ${AssertionGroup::class.simpleName} of type object: ${assertionGroupTypeClass::class.simpleName}") {
                val indentAssertions = listOf(BasicDescriptiveAssertion(AssertionVerb.ASSERT, 21, false), indentAssertionGroup, BasicDescriptiveAssertion(AssertionVerb.ASSERT, 20, false))
                val indentAssertionGroup2 = AssertionGroup.Builder.withType(anonymousAssertionGroupType).create(AssertionVerb.ASSERT, 10, indentAssertions)

                it("puts the assertions one under the other and adds an extra indent to the second one") {
                    facade.format(indentAssertionGroup2, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 21$separator"
                        + "$indentIndentBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentIndentBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }
        }
    }
})
