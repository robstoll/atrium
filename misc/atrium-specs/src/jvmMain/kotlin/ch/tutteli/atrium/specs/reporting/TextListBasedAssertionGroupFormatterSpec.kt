package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.reporting.text.TextAssertionPairFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFallbackAssertionFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFeatureAssertionGroupFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.specs.reporting.translating.TranslatorIntSpec
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalComponentFactoryContainer::class)
abstract class TextListBasedAssertionGroupFormatterSpec<T : AssertionGroupType>(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    assertionGroupClass: Class<T>,
    assertionGroupType: T,
    anonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assertions = listOf(
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(AssertionVerb.EXPECT, 1).build(),
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(AssertionVerb.EXPECT_THROWN, 2).build()
    )
    val listAssertionGroup = assertionBuilder.customType(anonymousAssertionGroupType)
        .withDescriptionAndRepresentation(TranslatorIntSpec.TestTranslatable.PLACEHOLDER, 2)
        .withAssertions(assertions)
        .build()

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(
            bulletPoints, DefaultAssertionFormatterController(),
            ToStringObjectFormatter, UsingDefaultTranslator()
        )
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${assertionGroupClass.simpleName}") {
            val result = testee.canFormat(
                assertionBuilder.customType(anonymousAssertionGroupType)
                    .withDescriptionAndRepresentation(Untranslatable.EMPTY, 1)
                    .withAssertions(listOf())
                    .build()
            )
            expect(result).toEqual(true)
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {

        mapOf(
            "•" to "▪",
            "[]" to "{}"
        ).forEach { (listBulletPoint, bulletPoint) ->
            val listIndent = " ".repeat(listBulletPoint.length + 1)
            val indent = " ".repeat(bulletPoint.length + 1)
            context("listBulletPoint: $listBulletPoint, bulletPoint: $bulletPoint") {
                val bulletPoints = mapOf(
                    RootAssertionGroupType::class to "$bulletPoint ",
                    ListAssertionGroupType::class to "$listBulletPoint ",
                    PrefixFeatureAssertionGroupHeader::class to "$arrow ",
                    FeatureAssertionGroupType::class to "$bulletPoint "
                )
                val facade = createFacade()
                val sameLineTextAssertionPairFormatter =
                    TextAssertionPairFormatter.newSameLine(ToStringObjectFormatter, UsingDefaultTranslator())
                facade.register {
                    testeeFactory(
                        bulletPoints, it,
                        ToStringObjectFormatter, UsingDefaultTranslator()
                    )
                }
                facade.register {
                    TextFeatureAssertionGroupFormatter(bulletPoints, it, sameLineTextAssertionPairFormatter)
                }
                facade.register {
                    TextFallbackAssertionFormatter(
                        bulletPoints,
                        it,
                        sameLineTextAssertionPairFormatter,
                        ToStringObjectFormatter
                    )
                }

                context("${AssertionGroup::class.simpleName} of type object: ${assertionGroupClass.simpleName}") {
                    context("format directly the group") {
                        it("includes the group ${AssertionGroup::description.name}, its ${AssertionGroup::representation.name} as well as the ${AssertionGroup::assertions.name} which are prepended with a `$listBulletPoint` as bullet point") {
                            facade.format(
                                listAssertionGroup,
                                sb,
                                alwaysTrueAssertionFilter
                            )
                            expect(sb.toString()).toEqual(
                                lineSeparator
                                        + "placeholder %s: 2$lineSeparator"
                                        + "$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 1$lineSeparator"
                                        + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2"
                            )
                        }
                    }

                    context("in an ${AssertionGroup::class.simpleName} of type ${DefaultFeatureAssertionGroupType::class.simpleName}") {
                        val featureAssertions = listOf(
                            listAssertionGroup,
                            assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                AssertionVerb.EXPECT,
                                20
                            ).build()
                        )
                        val featureAssertionGroup = assertionBuilder.feature
                            .withDescriptionAndRepresentation(AssertionVerb.EXPECT, 10)
                            .withAssertions(featureAssertions)
                            .build()
                        it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                            facade.format(
                                featureAssertionGroup,
                                sb,
                                alwaysTrueAssertionFilter
                            )
                            expect(sb.toString()).toEqual(
                                lineSeparator
                                    + "$arrow ${AssertionVerb.EXPECT.getDefault()}: 10$lineSeparator"
                                    + "$indentArrow$bulletPoint placeholder %s: 2$lineSeparator"
                                    + "$indentArrow$indent$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 1$lineSeparator"
                                    + "$indentArrow$indent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                                    + "$indentArrow$bulletPoint ${AssertionVerb.EXPECT.getDefault()}: 20"
                            )
                        }
                        context("in another ${AssertionGroup::class.simpleName} of type ${assertionGroupType::class.simpleName}") {
                            it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                                val listAssertions = listOf(
                                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                        AssertionVerb.EXPECT,
                                        5
                                    ).build(), featureAssertionGroup,
                                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                        AssertionVerb.EXPECT,
                                        30
                                    ).build()
                                )
                                val listAssertionGroup2 = assertionBuilder
                                    .customType(assertionGroupType)
                                    .withDescriptionAndRepresentation(AssertionVerb.EXPECT_THROWN, 10)
                                    .withAssertions(listAssertions)
                                    .build()
                                facade.format(
                                    listAssertionGroup2,
                                    sb,
                                    alwaysTrueAssertionFilter
                                )
                                expect(sb.toString()).toEqual(
                                    lineSeparator
                                        + "${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$lineSeparator"
                                        + "$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 5$lineSeparator"
                                        + "$listBulletPoint $arrow ${AssertionVerb.EXPECT.getDefault()}: 10$lineSeparator"
                                        + "$listIndent$indentArrow$bulletPoint placeholder %s: 2$lineSeparator"
                                        + "$listIndent$indentArrow$indent$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 1$lineSeparator"
                                        + "$listIndent$indentArrow$indent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                                        + "$listIndent$indentArrow$bulletPoint ${AssertionVerb.EXPECT.getDefault()}: 20$lineSeparator"
                                        + "$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 30"
                                )
                            }
                        }
                    }
                    context("in another ${AssertionGroup::class.simpleName} of type ${assertionGroupClass.simpleName}") {
                        it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                            val listAssertions = listOf(
                                assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                    AssertionVerb.EXPECT,
                                    5
                                ).build(),
                                listAssertionGroup,
                                assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                    AssertionVerb.EXPECT,
                                    30
                                ).build()
                            )
                            val listAssertionGroup2 = assertionBuilder.customType(anonymousAssertionGroupType)
                                .withDescriptionAndRepresentation(AssertionVerb.EXPECT_THROWN, 10)
                                .withAssertions(listAssertions)
                                .build()
                            facade.format(
                                listAssertionGroup2,
                                sb,
                                alwaysTrueAssertionFilter
                            )
                            expect(sb.toString()).toEqual(
                                lineSeparator
                                    + "${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$lineSeparator"
                                    + "$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 5$lineSeparator"
                                    + "$listBulletPoint placeholder %s: 2$lineSeparator"
                                    + "$listIndent$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 1$lineSeparator"
                                    + "$listIndent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                                    + "$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 30"
                            )
                        }
                    }
                }
            }
        }
    }
})
