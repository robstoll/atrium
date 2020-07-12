package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeperator
import ch.tutteli.atrium.specs.reporting.translating.TranslatorIntSpec
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class TextFeatureAssertionGroupFormatterSpec(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assertions = listOf(
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(AssertionVerb.ASSERT, 1).build(),
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(AssertionVerb.EXPECT_THROWN, 2).build()
    )
    val featureAssertionGroup = assertionBuilder
        .customType(object : FeatureAssertionGroupType {})
        .withDescriptionAndRepresentation(TranslatorIntSpec.TestTranslatable.PLACEHOLDER, 2)
        .withAssertions(assertions)
        .build()

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(
            bulletPoints, coreFactory.newAssertionFormatterController(),
            ToStringObjectFormatter, UsingDefaultTranslator()
        )
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${FeatureAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(
                assertionBuilder
                    .customType(object : FeatureAssertionGroupType {})
                    .withDescriptionAndRepresentation(Untranslatable.EMPTY, 1)
                    .withAssertions(listOf())
                    .build()
            )
            expect(result).toBe(true)
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {

        val facade = createFacade()
        facade.register {
            testeeFactory(
                bulletPoints, it,
                ToStringObjectFormatter, UsingDefaultTranslator()
            )
        }
        facade.register {
            coreFactory.newTextListAssertionGroupFormatter(
                bulletPoints, it,
                ToStringObjectFormatter, UsingDefaultTranslator()
            )
        }
        facade.register {
            coreFactory.newTextFallbackAssertionFormatter(
                bulletPoints, it,
                ToStringObjectFormatter, UsingDefaultTranslator()
            )
        }

        context("${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
            context("format directly the group") {

                it("starts feature ${AssertionGroup::description.name} with '$arrow' followed by the feature ${AssertionGroup::representation.name} and prepends the ${AssertionGroup::assertions.name} all prefixed with a `$featureBulletPoint`") {
                    facade.format(
                        featureAssertionGroup,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        lineSeperator
                            + "$arrow placeholder %s: 2$lineSeperator"
                            + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeperator"
                            + "$indentArrow$featureBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2"
                    )
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${DefaultListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(
                    featureAssertionGroup,
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        AssertionVerb.ASSERT,
                        20
                    ).build()
                )
                val listAssertionGroup = assertionBuilder.list
                    .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 10)
                    .withAssertions(listAssertions)
                    .build()
                it("does only indent the assertions but not the feature") {
                    facade.format(
                        listAssertionGroup,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        lineSeperator
                            + "${AssertionVerb.ASSERT.getDefault()}: 10$lineSeperator"
                            + "$listBulletPoint $arrow placeholder %s: 2$lineSeperator"
                            + "$indentListBulletPoint$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeperator"
                            + "$indentListBulletPoint$indentArrow$featureBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeperator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20"
                    )
                }
            }
            context("in another ${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$featureBulletPoint` for each assertion and `$listBulletPoint` for each element in the list group") {
                    val featureAssertions = listOf(
                        assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                            AssertionVerb.ASSERT,
                            5
                        ).build(), featureAssertionGroup,
                        assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                            AssertionVerb.ASSERT,
                            30
                        ).build()
                    )
                    val featureAssertionGroup2 = assertionBuilder
                        .customType(object : FeatureAssertionGroupType {})
                        .withDescriptionAndRepresentation(AssertionVerb.EXPECT_THROWN, 10)
                        .withAssertions(featureAssertions)
                        .build()
                    facade.format(
                        featureAssertionGroup2,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        lineSeperator
                            + "$arrow ${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$lineSeperator"
                            + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 5$lineSeperator"
                            + "$indentArrow$featureBulletPoint $arrow placeholder %s: 2$lineSeperator"
                            + "$indentArrow$indentFeatureBulletPoint$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeperator"
                            + "$indentArrow$indentFeatureBulletPoint$indentArrow$featureBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeperator"
                            + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30"
                    )
                }
            }
        }
    }

})
