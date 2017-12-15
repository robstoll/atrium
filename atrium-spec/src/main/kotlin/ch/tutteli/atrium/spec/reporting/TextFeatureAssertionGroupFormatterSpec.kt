package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.isTrue
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierIntSpec
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class TextFeatureAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Map<Class<out IBulletPointIdentifier>, String>, IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val assertions = listOf(
        BasicAssertion(AssertionVerb.ASSERT, 1, true),
        BasicAssertion(AssertionVerb.EXPECT_THROWN, 2, true)
    )
    val featureAssertionGroup = AssertionGroup(object : IFeatureAssertionGroupType {}, TranslationSupplierIntSpec.TestTranslatable.PLACEHOLDER, 2, assertions)

    prefixedDescribe("fun ${IAssertionFormatter::canFormat.name}") {
        val testee = testeeFactory(bulletPoints, AtriumFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())
        it("returns true for an ${IAssertionGroup::class.simpleName} with type object: ${IFeatureAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(AssertionGroup(object : IFeatureAssertionGroupType {}, Untranslatable.EMPTY, 1, listOf()))
            verbs.checkImmediately(result).isTrue()
        }
    }

    prefixedDescribe("fun ${IAssertionFormatter::formatGroup.name}") {

        val facade = createFacade()
        facade.register({ testeeFactory(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) })
        facade.register { AtriumFactory.newTextListAssertionGroupFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
        facade.register { AtriumFactory.newTextFallbackAssertionFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }

        context("${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
            context("format directly the group") {

                it("starts feature ${IAssertionGroup::name.name} with '$arrow' followed by the feature ${IAssertionGroup::subject.name} and prepends the ${IAssertionGroup::assertions.name} all prefixed with a `$featureBulletPoint`") {
                    facade.format(featureAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "$arrow placeholder %s: 2$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                }
            }

            context("in an ${IAssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(featureAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                val listAssertionGroup = AssertionGroup(ListAssertionGroupType, AssertionVerb.ASSERT, 10, listAssertions)
                it("does only indent the assertions but not the feature") {
                    facade.format(listAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$listBulletPoint $arrow placeholder %s: 2$separator"
                        + "$indentListBulletPoint$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentListBulletPoint$indentArrow$featureBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }
            context("in another ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
                it("indents the group ${IAssertionGroup::name.name} as well as the ${IAssertionGroup::assertions.name} accordingly - uses `$featureBulletPoint` for each assertion and `$listBulletPoint` for each element in the list group") {
                    val featureAssertions = listOf(BasicAssertion(AssertionVerb.ASSERT, 5, false), featureAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 30, false))
                    val featureAssertionGroup2 = AssertionGroup(object : IFeatureAssertionGroupType {}, AssertionVerb.EXPECT_THROWN, 10, featureAssertions)
                    facade.format(featureAssertionGroup2, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                        + "$indentArrow$featureBulletPoint $arrow placeholder %s: 2$separator"
                        + "$indentArrow$indentFeatureBulletPoint$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentArrow$indentFeatureBulletPoint$indentArrow$featureBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30")
                }
            }
        }
    }

})
