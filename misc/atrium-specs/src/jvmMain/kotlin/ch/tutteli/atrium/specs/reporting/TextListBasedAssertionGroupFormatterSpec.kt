package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.reporting.text.TextAssertionPairFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFallbackAssertionFormatter
import ch.tutteli.atrium.reporting.text.impl.TextFeatureAssertionGroupFormatter
import ch.tutteli.atrium.specs.DummyTranslatables
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeparator
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class TextListBasedAssertionGroupFormatterSpec<T : AssertionGroupType>(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter) -> AssertionFormatter,
    assertionGroupClass: Class<T>,
    assertionGroupType: T,
    anonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assertions = listOf(
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(DummyTranslatables.EXPECT, 1).build(),
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(DummyTranslatables.EXPECT_THROWN, 2).build()
    )
    val listAssertionGroup = assertionBuilder.customType(anonymousAssertionGroupType)
        .withDescriptionAndRepresentation(TestTranslatable.PLACEHOLDER, 2)
        .withAssertions(assertions)
        .build()

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(
            bulletPoints, DefaultAssertionFormatterController(),
            ToStringObjectFormatter
        )
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${assertionGroupClass.simpleName}") {
            //TODO 1.3.0 replace whole spec for proof
            @Suppress("DEPRECATION")
            val result = testee.canFormat(
                assertionBuilder.customType(anonymousAssertionGroupType)
                    .withDescriptionAndRepresentation(ch.tutteli.atrium.reporting.translating.Untranslatable.EMPTY, 1)
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
                    TextAssertionPairFormatter.newSameLine(ToStringObjectFormatter)
                facade.register {
                    testeeFactory(
                        bulletPoints, it,
                        ToStringObjectFormatter
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
                                    + "$listBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 1$lineSeparator"
                                    + "$listBulletPoint ${DummyTranslatables.EXPECT_THROWN.getDefault()}: 2"
                            )
                        }
                    }

                    context("in an ${AssertionGroup::class.simpleName} of type ${DefaultFeatureAssertionGroupType::class.simpleName}") {
                        val featureAssertions = listOf(
                            listAssertionGroup,
                            assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                DummyTranslatables.EXPECT,
                                20
                            ).build()
                        )
                        val featureAssertionGroup = assertionBuilder.feature
                            .withDescriptionAndRepresentation(DummyTranslatables.EXPECT, 10)
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
                                    + "$arrow ${DummyTranslatables.EXPECT.getDefault()}: 10$lineSeparator"
                                    + "$indentArrow$bulletPoint placeholder %s: 2$lineSeparator"
                                    + "$indentArrow$indent$listBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 1$lineSeparator"
                                    + "$indentArrow$indent$listBulletPoint ${DummyTranslatables.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                                    + "$indentArrow$bulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 20"
                            )
                        }
                        context("in another ${AssertionGroup::class.simpleName} of type ${assertionGroupType::class.simpleName}") {
                            it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                                val listAssertions = listOf(
                                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                        DummyTranslatables.EXPECT,
                                        5
                                    ).build(), featureAssertionGroup,
                                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                        DummyTranslatables.EXPECT,
                                        30
                                    ).build()
                                )
                                val listAssertionGroup2 = assertionBuilder
                                    .customType(assertionGroupType)
                                    .withDescriptionAndRepresentation(DummyTranslatables.EXPECT_THROWN, 10)
                                    .withAssertions(listAssertions)
                                    .build()
                                facade.format(
                                    listAssertionGroup2,
                                    sb,
                                    alwaysTrueAssertionFilter
                                )
                                expect(sb.toString()).toEqual(
                                    lineSeparator
                                        + "${DummyTranslatables.EXPECT_THROWN.getDefault()}: 10$lineSeparator"
                                        + "$listBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 5$lineSeparator"
                                        + "$listBulletPoint $arrow ${DummyTranslatables.EXPECT.getDefault()}: 10$lineSeparator"
                                        + "$listIndent$indentArrow$bulletPoint placeholder %s: 2$lineSeparator"
                                        + "$listIndent$indentArrow$indent$listBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 1$lineSeparator"
                                        + "$listIndent$indentArrow$indent$listBulletPoint ${DummyTranslatables.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                                        + "$listIndent$indentArrow$bulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 20$lineSeparator"
                                        + "$listBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 30"
                                )
                            }
                        }
                    }
                    context("in another ${AssertionGroup::class.simpleName} of type ${assertionGroupClass.simpleName}") {
                        it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                            val listAssertions = listOf(
                                assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                    DummyTranslatables.EXPECT,
                                    5
                                ).build(),
                                listAssertionGroup,
                                assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                    DummyTranslatables.EXPECT,
                                    30
                                ).build()
                            )
                            val listAssertionGroup2 = assertionBuilder.customType(anonymousAssertionGroupType)
                                .withDescriptionAndRepresentation(DummyTranslatables.EXPECT_THROWN, 10)
                                .withAssertions(listAssertions)
                                .build()
                            facade.format(
                                listAssertionGroup2,
                                sb,
                                alwaysTrueAssertionFilter
                            )
                            expect(sb.toString()).toEqual(
                                lineSeparator
                                    + "${DummyTranslatables.EXPECT_THROWN.getDefault()}: 10$lineSeparator"
                                    + "$listBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 5$lineSeparator"
                                    + "$listBulletPoint placeholder %s: 2$lineSeparator"
                                    + "$listIndent$listBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 1$lineSeparator"
                                    + "$listIndent$listBulletPoint ${DummyTranslatables.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                                    + "$listBulletPoint ${DummyTranslatables.EXPECT.getDefault()}: 30"
                            )
                        }
                    }
                }
            }
        }
    }
})
