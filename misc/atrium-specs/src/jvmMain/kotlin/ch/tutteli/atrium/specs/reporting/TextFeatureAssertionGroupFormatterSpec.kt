//TODO remove with 2.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.assertions.DefaultListAssertionGroupType
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.reporting.text.TextAssertionPairFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFallbackAssertionFormatter
import ch.tutteli.atrium.reporting.text.impl.TextListAssertionGroupFormatter
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.specs.DummyTranslatables
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeparator
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

@OptIn(ExperimentalComponentFactoryContainer::class)
abstract class TextFeatureAssertionGroupFormatterSpec(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assertions = listOf(
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(DummyTranslatables.EXPECT, 1).build(),
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(DummyTranslatables.EXPECT_THROWN, 2).build()
    )
    val featureAssertionGroup = assertionBuilder
        .customType(object : FeatureAssertionGroupType {})
        .withDescriptionAndRepresentation(TestTranslatable.PLACEHOLDER, 2)
        .withAssertions(assertions)
        .build()

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(
            bulletPoints,
            DefaultAssertionFormatterController(),
            ToStringObjectFormatter
        )
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${FeatureAssertionGroupType::class.simpleName}") {
            // TODO 1.3.0 switch to Proof remove suppression
            @Suppress("DEPRECATION")
            val result = testee.canFormat(
                assertionBuilder
                    .customType(object : FeatureAssertionGroupType {})
                    .withDescriptionAndRepresentation(ch.tutteli.atrium.reporting.translating.Untranslatable.EMPTY, 1)
                    .withAssertions(emptyList())
                    .build()
            )
            expect(result).toEqual(true)
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {
        val sameLineTextAssertionPairFormatter =
            TextAssertionPairFormatter.newSameLine(ToStringObjectFormatter)
        val facade = createFacade()
        facade.register {
            testeeFactory(
                bulletPoints, it,
                ToStringObjectFormatter
            )
        }
        facade.register {
            TextListAssertionGroupFormatter(bulletPoints, it, sameLineTextAssertionPairFormatter)

        }
        facade.register {
            TextFallbackAssertionFormatter(
                bulletPoints,
                it,
                sameLineTextAssertionPairFormatter,
                ToStringObjectFormatter
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
                    expect(sb.toString()).toEqual(
                        lineSeparator
                                + "$arrow placeholder %s: 2$lineSeparator"
                                + "$indentArrow$featureBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 1$lineSeparator"
                                + "$indentArrow$featureBulletPoint ${DummyTranslatables.EXPECT_THROWN.getDefault()}: 2"
                    )
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${DefaultListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(
                    featureAssertionGroup,
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        DummyTranslatables.EXPECT,
                        20
                    ).build()
                )
                val listAssertionGroup = assertionBuilder.list
                    .withDescriptionAndRepresentation(DummyTranslatables.EXPECT, 10)
                    .withAssertions(listAssertions)
                    .build()
                it("does only indent the assertions but not the feature") {
                    facade.format(
                        listAssertionGroup,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toEqual(
                        lineSeparator
                            + "${DummyTranslatables.EXPECT.getDefault()}: 10$lineSeparator"
                            + "$listBulletPoint $arrow placeholder %s: 2$lineSeparator"
                            + "$indentListBulletPoint$indentArrow$featureBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 1$lineSeparator"
                            + "$indentListBulletPoint$indentArrow$featureBulletPoint ${DummyTranslatables.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                            + "$listBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 20"
                    )
                }
            }
            context("in another ${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$featureBulletPoint` for each assertion and `$listBulletPoint` for each element in the list group") {
                    val featureAssertions = listOf(
                        assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                            DummyTranslatables.EXPECT,
                            5
                        ).build(), featureAssertionGroup,
                        assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                            DummyTranslatables.EXPECT,
                            30
                        ).build()
                    )
                    val featureAssertionGroup2 = assertionBuilder
                        .customType(object : FeatureAssertionGroupType {})
                        .withDescriptionAndRepresentation(DummyTranslatables.EXPECT_THROWN, 10)
                        .withAssertions(featureAssertions)
                        .build()
                    facade.format(
                        featureAssertionGroup2,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toEqual(
                        lineSeparator
                            + "$arrow ${DummyTranslatables.EXPECT_THROWN.getDefault()}: 10$lineSeparator"
                            + "$indentArrow$featureBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 5$lineSeparator"
                            + "$indentArrow$featureBulletPoint $arrow placeholder %s: 2$lineSeparator"
                            + "$indentArrow$indentFeatureBulletPoint$indentArrow$featureBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 1$lineSeparator"
                            + "$indentArrow$indentFeatureBulletPoint$indentArrow$featureBulletPoint ${DummyTranslatables.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                            + "$indentArrow$featureBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 30"
                    )
                }
            }
        }
    }

})

enum class TestTranslatable(override val value: String) : StringBasedTranslatable {
    DATE_KNOWN("%tD is a %tA"),
    DATE_UNKNOWN("only %tA"),
    PLACEHOLDER("placeholder %s")
}
