package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class TextFeatureAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assertions = listOf(
        AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 1, true),
        AssertImpl.builder.descriptive.create(AssertionVerb.EXPECT_THROWN, 2, true)
    )
    val featureAssertionGroup = AssertImpl.builder
        .withType(object : FeatureAssertionGroupType {}, TranslatorIntSpec.TestTranslatable.PLACEHOLDER, 2)
        .create(assertions)

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(bulletPoints, coreFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${FeatureAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(AssertImpl.builder
                .withType(object : FeatureAssertionGroupType {}, Untranslatable.EMPTY, 1)
                .create(listOf())
            )
            verbs.checkImmediately(result).toBe(true)
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {

        val facade = createFacade()
        facade.register({ testeeFactory(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) })
        facade.register { coreFactory.newTextListAssertionGroupFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
        facade.register { coreFactory.newTextFallbackAssertionFormatter(bulletPoints, it, ToStringObjectFormatter, UsingDefaultTranslator()) }

        context("${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
            context("format directly the group") {

                it("starts feature ${AssertionGroup::name.name} with '$arrow' followed by the feature ${AssertionGroup::representation.name} and prepends the ${AssertionGroup::assertions.name} all prefixed with a `$featureBulletPoint`") {
                    facade.format(featureAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "$arrow placeholder %s: 2$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${DefaultListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(featureAssertionGroup,
                    AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 20, false)
                )
                val listAssertionGroup = AssertImpl.builder.list(AssertionVerb.ASSERT, 10).create(listAssertions)
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
            context("in another ${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("indents the group ${AssertionGroup::name.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$featureBulletPoint` for each assertion and `$listBulletPoint` for each element in the list group") {
                    val featureAssertions = listOf(
                        AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 5, false), featureAssertionGroup,
                        AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 30, false)
                    )
                    val featureAssertionGroup2 = AssertImpl.builder
                        .withType(object : FeatureAssertionGroupType {}, AssertionVerb.EXPECT_THROWN, 10)
                        .create(featureAssertions)
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
