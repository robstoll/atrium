package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.impl.DefaultAssertionFormatterController
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeparator
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class TextIndentBasedAssertionGroupFormatterSpec<T : AssertionGroupType>(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    assertionGroupTypeClass: KClass<T>,
    anonymousAssertionGroupType: T,
    groupFactory: (List<Assertion>) -> AssertionGroup,
    describePrefix: String = "[Atrium] ",
    withIndent: Boolean = true
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val indentBulletPoint = " +"
    val indentIndentBulletPoint = " ".repeat(indentBulletPoint.length + 1)

    val facade = createFacade(assertionGroupTypeClass to "$indentBulletPoint ") { bulletPoints, controller, _, _ ->
        testeeFactory(bulletPoints, controller)
    }

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(bulletPoints, DefaultAssertionFormatterController())
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${assertionGroupTypeClass.simpleName}") {
            expect(testee).feature {
                f(
                    it::canFormat, assertionBuilder.customType(anonymousAssertionGroupType)
                        .withDescriptionAndRepresentation(Untranslatable.EMPTY, 1)
                        .withAssertions(listOf())
                        .build()
                )
            }.toEqual(true)
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {
        context("${AssertionGroup::class.simpleName} of type ${assertionGroupTypeClass.simpleName}") {
            val assertions = listOf(
                assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(
                    AssertionVerb.EXPECT,
                    1
                ).build(),
                assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(
                    AssertionVerb.EXPECT_THROWN,
                    2
                ).build()
            )
            val indentAssertionGroup = groupFactory(assertions)

            context("format directly the group (no prefix given)") {
                it("puts the assertions one under the other including a prefix") {
                    facade.format(
                        indentAssertionGroup,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toEqual(
                        lineSeparator
                                + "$indentBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 1$lineSeparator"
                                + "$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2"
                    )
                }
            }



            context("in an ${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the other including a prefix ${if(withIndent) "and indent" else "but without indent"}") {
                    val featureAssertions = listOf(
                        indentAssertionGroup,
                        assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                            AssertionVerb.EXPECT,
                            20
                        ).build()
                    )
                    val featureAssertionGroup = assertionBuilder.feature
                        .withDescriptionAndRepresentation(AssertionVerb.EXPECT, 10)
                        .withAssertions(featureAssertions)
                        .build()
                    facade.format(
                        featureAssertionGroup,
                        sb,
                        alwaysTrueAssertionFilter
                    )

                    val indent = if(withIndent) indentFeatureBulletPoint else ""

                    expect(sb.toString()).toEqual(
                        lineSeparator
                            + "$arrow ${AssertionVerb.EXPECT.getDefault()}: 10$lineSeparator"
                            + "$indentArrow$indent$indentBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 1$lineSeparator"
                            + "$indentArrow$indent$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                            + "$indentArrow$featureBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 20"
                    )
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(
                    indentAssertionGroup,
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        AssertionVerb.EXPECT,
                        20
                    ).build()
                )
                val listAssertionGroup = assertionBuilder.list
                    .withDescriptionAndRepresentation(AssertionVerb.EXPECT, 10)
                    .withAssertions(listAssertions)
                    .build()

                val indent = if(withIndent) indentListBulletPoint else ""

                it("puts the assertions one under the other including a prefix ${if(withIndent) "and indent" else "but without indent"}") {
                    facade.format(
                        listAssertionGroup,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toEqual(
                        lineSeparator
                            + "${AssertionVerb.EXPECT.getDefault()}: 10$lineSeparator"
                            + "$indent$indentBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 1$lineSeparator"
                            + "$indent$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                            + "$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 20"
                    )
                }

                context("in another ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                    it("puts the assertions one under the other and indents as the other assertions but ${if(withIndent) "adds an extra indent including a prefix" else "uses a different prefix"} ") {
                        val listAssertions2 = listOf(
                            listAssertionGroup,
                            assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                AssertionVerb.EXPECT_THROWN,
                                30
                            ).build()
                        )
                        val listAssertionGroup2 = assertionBuilder.list
                            .withDescriptionAndRepresentation(AssertionVerb.EXPECT, 5)
                            .withAssertions(listAssertions2)
                            .build()
                        facade.format(
                            listAssertionGroup2,
                            sb,
                            alwaysTrueAssertionFilter
                        )
                        expect(sb.toString()).toEqual(
                            lineSeparator
                                + "${AssertionVerb.EXPECT.getDefault()}: 5$lineSeparator"
                                + "$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 10$lineSeparator"
                                + "$indentListBulletPoint$indent$indentBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 1$lineSeparator"
                                + "$indentListBulletPoint$indent$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                                + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 20$lineSeparator"
                                + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 30"
                        )
                    }
                }
            }

            context("in another ${AssertionGroup::class.simpleName} of type object: ${assertionGroupTypeClass::class.simpleName}") {
                val indentAssertions = listOf(
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        AssertionVerb.EXPECT,
                        21
                    ).build(), indentAssertionGroup,
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        AssertionVerb.EXPECT,
                        20
                    ).build()
                )
                val indentAssertionGroup2 = assertionBuilder.customType(anonymousAssertionGroupType)
                    .withDescriptionAndRepresentation(AssertionVerb.EXPECT, 10)
                    .withAssertions(indentAssertions)
                    .build()

                val indent = if(withIndent) indentIndentBulletPoint else ""

                it("puts the assertions one under the other ${if(withIndent) "but adds an extra indent including" else ""}") {
                    facade.format(
                        indentAssertionGroup2,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toEqual(
                        lineSeparator
                            + "$indentBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 21$lineSeparator"
                            + "$indent$indentBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 1$lineSeparator"
                            + "$indent$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeparator"
                            + "$indentBulletPoint ${AssertionVerb.EXPECT.getDefault()}: 20"
                    )
                }
            }
        }
    }
})
