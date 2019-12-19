package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFun
import ch.tutteli.atrium.specs.reporting.translating.TranslatorIntSpec
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KClass

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class TextListBasedAssertionGroupFormatterSpec<T : AssertionGroupType>(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController, ObjectFormatter, Translator) -> AssertionFormatter,
    assertionGroupClass: Class<T>,
    assertionGroupType: T,
    anonymousAssertionGroupType: T,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assertions = listOf(
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(AssertionVerb.ASSERT, 1).build(),
        assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(AssertionVerb.EXPECT_THROWN, 2).build()
    )
    val listAssertionGroup = assertionBuilder.customType(anonymousAssertionGroupType)
        .withDescriptionAndRepresentation(TranslatorIntSpec.TestTranslatable.PLACEHOLDER, 2)
        .withAssertions(assertions)
        .build()

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(
            bulletPoints, coreFactory.newAssertionFormatterController(),
            ToStringObjectFormatter, UsingDefaultTranslator()
        )
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${assertionGroupClass.simpleName}") {
            val result = testee.canFormat(
                assertionBuilder.customType(anonymousAssertionGroupType)
                    .withDescriptionAndRepresentation(Untranslatable.EMPTY, 1)
                    .withAssertions(listOf())
                    .build()
            )
            expect(result).toBe(true)
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
                facade.register {
                    testeeFactory(
                        bulletPoints, it,
                        ToStringObjectFormatter, UsingDefaultTranslator()
                    )
                }
                facade.register {
                    coreFactory.newTextFeatureAssertionGroupFormatter(
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

                context("${AssertionGroup::class.simpleName} of type object: ${assertionGroupClass.simpleName}") {
                    context("format directly the group") {
                        it("includes the group ${AssertionGroup::description.name}, its ${AssertionGroup::representation.name} as well as the ${AssertionGroup::assertions.name} which are prepended with a `$listBulletPoint` as bullet point") {
                            facade.format(
                                listAssertionGroup,
                                sb,
                                alwaysTrueAssertionFilter
                            )
                            expect(sb.toString()).toBe(
                                separator
                                    + "placeholder %s: 2$separator"
                                    + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                    + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2"
                            )
                        }
                    }

                    context("in an ${AssertionGroup::class.simpleName} of type ${DefaultFeatureAssertionGroupType::class.simpleName}") {
                        val featureAssertions = listOf(
                            listAssertionGroup,
                            assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                AssertionVerb.ASSERT,
                                20
                            ).build()
                        )
                        val featureAssertionGroup = assertionBuilder.feature
                            .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 10)
                            .withAssertions(featureAssertions)
                            .build()
                        it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                            facade.format(
                                featureAssertionGroup,
                                sb,
                                alwaysTrueAssertionFilter
                            )
                            expect(sb.toString()).toBe(
                                separator
                                    + "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                                    + "$indentArrow$bulletPoint placeholder %s: 2$separator"
                                    + "$indentArrow$indent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                    + "$indentArrow$indent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                    + "$indentArrow$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20"
                            )
                        }
                        context("in another ${AssertionGroup::class.simpleName} of type ${assertionGroupType::class.simpleName}") {
                            it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                                val listAssertions = listOf(
                                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                        AssertionVerb.ASSERT,
                                        5
                                    ).build(), featureAssertionGroup,
                                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                        AssertionVerb.ASSERT,
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
                                expect(sb.toString()).toBe(
                                    separator
                                        + "${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$separator"
                                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                                        + "$listBulletPoint $arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                                        + "$listIndent$indentArrow$bulletPoint placeholder %s: 2$separator"
                                        + "$listIndent$indentArrow$indent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                        + "$listIndent$indentArrow$indent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                        + "$listIndent$indentArrow$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30"
                                )
                            }
                        }
                    }
                    context("in another ${AssertionGroup::class.simpleName} of type ${assertionGroupClass.simpleName}") {
                        it("indents the group ${AssertionGroup::description.name} as well as the ${AssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                            val listAssertions = listOf(
                                assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                    AssertionVerb.ASSERT,
                                    5
                                ).build(),
                                listAssertionGroup,
                                assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                    AssertionVerb.ASSERT,
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
                            expect(sb.toString()).toBe(
                                separator
                                    + "${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$separator"
                                    + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                                    + "$listBulletPoint placeholder %s: 2$separator"
                                    + "$listIndent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                    + "$listIndent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                    + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30"
                            )
                        }
                    }
                }
            }
        }
    }
})
