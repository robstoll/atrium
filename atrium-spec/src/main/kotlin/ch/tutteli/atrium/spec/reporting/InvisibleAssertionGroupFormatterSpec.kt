package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.isTrue
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class InvisibleAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assertions = listOf(
        BasicDescriptiveAssertion(AssertionVerb.ASSERT, 1, true),
        BasicDescriptiveAssertion(AssertionVerb.EXPECT_THROWN, 2, true)
    )
    val invisibleAssertionGroup = AssertionGroup.Builder.invisible.create(assertions)
    val facade = createFacade { _, controller, _, _ -> testeeFactory(controller) }

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(AtriumFactory.newAssertionFormatterController())
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${InvisibleAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(AssertionGroup.Builder.withType(object : InvisibleAssertionGroupType {}).create(Untranslatable.EMPTY, 1, listOf()))
            verbs.checkImmediately(result).isTrue()
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {
        context("${AssertionGroup::class.simpleName} of type ${InvisibleAssertionGroupType::class.simpleName}") {
            context("format directly the group (no prefix given)") {
                it("puts the assertions one under the other without indentation and without prefix") {
                    facade.format(invisibleAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                }
            }

            val arrowIndent = " ".repeat(arrow.length + 1)
            val listIndent = " ".repeat(listBulletPoint.length + 1)
            context("in an ${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the other, indents them and uses the same prefix, which is $bulletPoint") {
                    val featureAssertions = listOf(invisibleAssertionGroup, BasicDescriptiveAssertion(AssertionVerb.ASSERT, 20, false))
                    val featureAssertionGroup = AssertionGroup.Builder.feature.create(AssertionVerb.ASSERT, 10, featureAssertions)
                    facade.format(featureAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$arrowIndent$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$arrowIndent$featureBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$arrowIndent$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(invisibleAssertionGroup, BasicDescriptiveAssertion(AssertionVerb.ASSERT, 20, false))
                val listAssertionGroup = AssertionGroup.Builder.list.create(AssertionVerb.ASSERT, 10, listAssertions)

                it("puts the assertions one under the other, indents them and uses the same prefix, which is $listBulletPoint") {
                    facade.format(listAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }

                context("in another ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                    it("puts the assertions one under the other and indents as the other assertions and puts a prefix as well") {
                        val listAssertions2 = listOf(listAssertionGroup, BasicDescriptiveAssertion(AssertionVerb.EXPECT_THROWN, 30, false))
                        val listAssertionGroup2 = AssertionGroup.Builder.list.create(AssertionVerb.ASSERT, 5, listAssertions2)
                        facade.format(listAssertionGroup2, sb, alwaysTrueAssertionFilter)
                        verbs.checkImmediately(sb.toString()).toBe(separator
                            + "${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$listIndent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$listIndent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$listIndent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                            + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 30")
                    }
                }
            }
        }
    }
})
