package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import kotlin.reflect.KClass

//TODO remove with 1.0.0 - no need to migrate to spek2
@Deprecated("So far indentation was achieved by grouping (which is the solution to go). Will be removed with 1.0.0")
abstract class TextIndentBasedAssertionGroupFormatterSpec<T : AssertionGroupType>(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    assertionGroupTypeClass: KClass<T>,
    anonymousAssertionGroupType: T,
    groupFactory: (List<Assertion>) -> AssertionGroup,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val indentBulletPoint = " +"
    val indentIndentBulletPoint = " ".repeat(indentBulletPoint.length + 1)

    val facade =
        createFacade(assertionGroupTypeClass to "$indentBulletPoint ") { bulletPoints, controller, _, _ ->
            testeeFactory(bulletPoints, controller)
        }

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(bulletPoints, coreFactory.newAssertionFormatterController())
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${assertionGroupTypeClass.simpleName}") {
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
        context("${AssertionGroup::class.simpleName} of type ${assertionGroupTypeClass.simpleName}") {
            val assertions = listOf(
                assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(
                    AssertionVerb.ASSERT,
                    1
                ).build(),
                assertionBuilder.descriptive.holding.withDescriptionAndRepresentation(
                    AssertionVerb.EXPECT_THROWN,
                    2
                ).build()
            )
            val indentAssertionGroup = groupFactory(assertions)

            context("format directly the group (no prefix given)") {
                it("puts the assertions one under the other and indents the second one including a prefix") {
                    facade.format(
                        indentAssertionGroup,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        separator
                            + "$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2"
                    )
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the other and indents the second one including a prefix") {
                    val featureAssertions = listOf(
                        indentAssertionGroup,
                        assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                            AssertionVerb.ASSERT,
                            20
                        ).build()
                    )
                    val featureAssertionGroup = assertionBuilder.feature
                        .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 10)
                        .withAssertions(featureAssertions)
                        .build()
                    facade.format(
                        featureAssertionGroup,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        separator
                            + "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$indentArrow$indentFeatureBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$indentArrow$indentFeatureBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20"
                    )
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(
                    indentAssertionGroup,
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        AssertionVerb.ASSERT,
                        20
                    ).build()
                )
                val listAssertionGroup = assertionBuilder.list
                    .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 10)
                    .withAssertions(listAssertions)
                    .build()

                it("puts the assertions one under the other and indents the second one including a prefix") {
                    facade.format(
                        listAssertionGroup,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        separator
                            + "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$indentListBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$indentListBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20"
                    )
                }

                context("in another ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                    it("puts the assertions one under the other and indents as the other assertions but adds an extra indent to the second assertion including a prefix") {
                        val listAssertions2 = listOf(
                            listAssertionGroup,
                            assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                                AssertionVerb.EXPECT_THROWN,
                                30
                            ).build()
                        )
                        val listAssertionGroup2 = assertionBuilder.list
                            .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 5)
                            .withAssertions(listAssertions2)
                            .build()
                        facade.format(
                            listAssertionGroup2,
                            sb,
                            alwaysTrueAssertionFilter
                        )
                        expect(sb.toString()).toBe(
                            separator
                                + "${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                                + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                                + "$indentListBulletPoint$indentListBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                + "$indentListBulletPoint$indentListBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                                + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 30"
                        )
                    }
                }
            }

            context("in another ${AssertionGroup::class.simpleName} of type object: ${assertionGroupTypeClass::class.simpleName}") {
                val indentAssertions = listOf(
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        AssertionVerb.ASSERT,
                        21
                    ).build(), indentAssertionGroup,
                    assertionBuilder.descriptive.failing.withDescriptionAndRepresentation(
                        AssertionVerb.ASSERT,
                        20
                    ).build()
                )
                val indentAssertionGroup2 = assertionBuilder.customType(anonymousAssertionGroupType)
                    .withDescriptionAndRepresentation(AssertionVerb.ASSERT, 10)
                    .withAssertions(indentAssertions)
                    .build()

                it("puts the assertions one under the other and adds an extra indent to the second one") {
                    facade.format(
                        indentAssertionGroup2,
                        sb,
                        alwaysTrueAssertionFilter
                    )
                    expect(sb.toString()).toBe(
                        separator
                            + "$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 21$separator"
                            + "$indentIndentBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$indentIndentBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20"
                    )
                }
            }
        }
    }
})
