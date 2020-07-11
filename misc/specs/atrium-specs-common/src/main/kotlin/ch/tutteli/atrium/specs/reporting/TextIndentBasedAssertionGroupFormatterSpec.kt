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
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.lineSeperator
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KClass

abstract class TextIndentBasedAssertionGroupFormatterSpec<T : AssertionGroupType>(
    testeeFactory: (Map<KClass<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    assertionGroupTypeClass: KClass<T>,
    anonymousAssertionGroupType: T,
    groupFactory: (List<Assertion>) -> AssertionGroup,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

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
                        lineSeperator
                            + "$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeperator"
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
                        lineSeperator
                            + "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$lineSeperator"
                            + "$indentArrow$indentFeatureBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeperator"
                            + "$indentArrow$indentFeatureBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeperator"
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
                        lineSeperator
                            + "${AssertionVerb.ASSERT.getDefault()}: 10$lineSeperator"
                            + "$indentListBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeperator"
                            + "$indentListBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeperator"
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
                            lineSeperator
                                + "${AssertionVerb.ASSERT.getDefault()}: 5$lineSeperator"
                                + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 10$lineSeperator"
                                + "$indentListBulletPoint$indentListBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeperator"
                                + "$indentListBulletPoint$indentListBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeperator"
                                + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$lineSeperator"
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
                        lineSeperator
                            + "$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 21$lineSeperator"
                            + "$indentIndentBulletPoint$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$lineSeperator"
                            + "$indentIndentBulletPoint$indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$lineSeperator"
                            + "$indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20"
                    )
                }
            }
        }
    }
})
