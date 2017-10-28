package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.isTrue
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class IndentAssertionGroupFormatterSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (String, IAssertionFormatterController) -> IAssertionFormatter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val bulletPoint = "***"
    val listBulletPoint = "=="
    val indentBulletPoint = "+"
    val arrow = "->"
    val bulletIndent = " ".repeat(bulletPoint.length + 1)
    val listIndent = " ".repeat(listBulletPoint.length + 1)
    val indentIndentBulletPoint = " ".repeat(indentBulletPoint.length + 1)
    val arrowIndent = " ".repeat(arrow.length + 1)

    val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
    facade.register { testeeFactory(indentBulletPoint, it) }
    facade.register { AtriumFactory.newTextListAssertionGroupFormatter(listBulletPoint, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
    facade.register { AtriumFactory.newTextFeatureAssertionGroupFormatter(arrow, bulletPoint, it, ToStringObjectFormatter, UsingDefaultTranslator()) }
    facade.register { AtriumFactory.newTextFallbackAssertionFormatter(bulletPoint, it, ToStringObjectFormatter, UsingDefaultTranslator()) }

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }


    val separator = System.getProperty("line.separator")!!

    prefixedDescribe("fun ${IAssertionFormatter::canFormat.name}") {
        val testee = testeeFactory(indentBulletPoint, AtriumFactory.newAssertionFormatterController())
        it("returns true for an ${IAssertionGroup::class.simpleName} with type object: ${IIndentAssertionGroupType::class.simpleName}") {
            val assertionGroup = object : IIndentAssertionGroupType {
                override val indentIndex = 1
            }
            val result = testee.canFormat(AssertionGroup(assertionGroup, Untranslatable(""), 1, listOf()))
            verbs.checkImmediately(result).isTrue()
        }
    }

    prefixedDescribe("fun ${IAssertionFormatter::formatGroup.name}") {
        context("${IAssertionGroup::class.simpleName} of type ${IIndentAssertionGroupType::class.simpleName} and ${IIndentAssertionGroupType::indentIndex.name} = 1") {
            val assertions = listOf(
                BasicAssertion(AssertionVerb.ASSERT, 1, true),
                BasicAssertion(AssertionVerb.EXPECT_THROWN, 2, true)
            )
            val indentAssertionGroup = IndentAssertionGroup.createWithIndentIndex(assertions, 1)

            context("format directly the group (no prefix given)") {
                it("puts the assertions one under the other and indents the second one including a prefix") {
                    facade.format(indentAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + " $indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                }
            }

            context("in an ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the other and indents the second one including a prefix") {
                    val featureAssertions = listOf(indentAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                    val featureAssertionGroup = AssertionGroup(FeatureAssertionGroupType, AssertionVerb.ASSERT, 10, featureAssertions)
                    facade.format(featureAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(
                        "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$arrowIndent$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$arrowIndent$bulletIndent $indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$arrowIndent$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }

            context("in an ${IAssertionGroup::class.simpleName} of type ${IListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(indentAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                val listAssertionGroup = AssertionGroup(ListAssertionGroupType, AssertionVerb.ASSERT, 10, listAssertions)

                it("puts the assertions one under the other and indents the second one including a prefix") {
                    facade.format(listAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(
                        "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$listIndent $indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }

                context("in another ${IAssertionGroup::class.simpleName} of type ${IListAssertionGroupType::class.simpleName}") {
                    it("puts the assertions one under the other and indents as the other assertions but adds an extra indent to the second assertion including a prefix") {
                        val listAssertions2 = listOf(listAssertionGroup, BasicAssertion(AssertionVerb.EXPECT_THROWN, 30, false))
                        val listAssertionGroup2 = AssertionGroup(ListAssertionGroupType, AssertionVerb.ASSERT, 5, listAssertions2)
                        facade.format(listAssertionGroup2, sb, alwaysTrueAssertionFilter)
                        verbs.checkImmediately(sb.toString()).toBe(
                            "${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                                + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                                + "$listIndent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                + "$listIndent$listIndent $indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                + "$listIndent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                                + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 30")
                    }
                }
            }

            context("in another ${IAssertionGroup::class.simpleName} of type ${IIndentAssertionGroupType::class.simpleName} and ${IIndentAssertionGroupType::indentIndex.name} = 1") {
                val indentAssertions = listOf(BasicAssertion(AssertionVerb.ASSERT, 21, false), indentAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                val indentAssertionGroup2 = AssertionGroup(IndentAssertionGroupType.createWithIndentIndex(1), AssertionVerb.ASSERT, 10, indentAssertions)

                it("puts the assertions one under the other and adds an extra indent to the second one") {
                    facade.format(indentAssertionGroup2, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(
                        "${AssertionVerb.ASSERT.getDefault()}: 21$separator"
                            + " $indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + " $indentIndentBulletPoint $indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + " $indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }
        }

        context("${IAssertionGroup::class.simpleName} of type ${IIndentAssertionGroupType::class.simpleName} and ${IIndentAssertionGroupType::indentIndex.name} = 2") {
            val assertions = listOf(
                BasicAssertion(AssertionVerb.ASSERT, 1, true),
                BasicAssertion(AssertionVerb.EXPECT_THROWN, 2, true),
                BasicAssertion(AssertionVerb.ASSERT, 12, true)
            )
            val indentAssertionGroup = IndentAssertionGroup.createWithIndentIndex(assertions, 2)
            context("format directly the group (no prefix given)") {
                it("puts the assertions one under the other and indents the third one including a prefix") {
                    facade.format(indentAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe("${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + " $indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 12"
                    )
                }
            }


            context("in an ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the other, uses the prefix ($bulletPoint) from the parent for the second one and indents the third one including a new prefix") {
                    val featureAssertions = listOf(indentAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                    val featureAssertionGroup = AssertionGroup(FeatureAssertionGroupType, AssertionVerb.ASSERT, 10, featureAssertions)
                    facade.format(featureAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(
                        "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$arrowIndent$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$arrowIndent$bulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$arrowIndent$bulletIndent $indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 12$separator"
                            + "$arrowIndent$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }

            context("in an ${IAssertionGroup::class.simpleName} of type ${IListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(indentAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                val listAssertionGroup = AssertionGroup(ListAssertionGroupType, AssertionVerb.ASSERT, 10, listAssertions)

                it("puts the assertions one under the other, uses the prefix ($listBulletPoint) from the parent for the second one and indents the third one including a new prefix") {
                    facade.format(listAssertionGroup, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(
                        "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$listIndent $indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 12$separator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }

                context("in another ${IAssertionGroup::class.simpleName} of type ${IListAssertionGroupType::class.simpleName}") {
                    it("puts the assertions one under the other and indents as the other assertions but adds an extra indent to the third assertion including a new prefix") {
                        val listAssertions2 = listOf(listAssertionGroup, BasicAssertion(AssertionVerb.EXPECT_THROWN, 30, false))
                        val listAssertionGroup2 = AssertionGroup(ListAssertionGroupType, AssertionVerb.ASSERT, 5, listAssertions2)
                        facade.format(listAssertionGroup2, sb, alwaysTrueAssertionFilter)
                        verbs.checkImmediately(sb.toString()).toBe(
                            "${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                                + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                                + "$listIndent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                + "$listIndent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                + "$listIndent$listIndent $indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 12$separator"
                                + "$listIndent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                                + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 30")
                    }
                }
            }

            context("in another ${IAssertionGroup::class.simpleName} of type ${IIndentAssertionGroupType::class.simpleName} and ${IIndentAssertionGroupType::indentIndex.name} = 1") {
                val indentAssertions = listOf(BasicAssertion(AssertionVerb.ASSERT, 21, false), indentAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                val indentAssertionGroup2 = AssertionGroup(IndentAssertionGroupType.createWithIndentIndex(1), AssertionVerb.ASSERT, 10, indentAssertions)

                it("puts the assertions one under the other and adds an extra indent to the third one") {
                    facade.format(indentAssertionGroup2, sb, alwaysTrueAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(
                        "${AssertionVerb.ASSERT.getDefault()}: 21$separator"
                            + " $indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + " $indentBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + " $indentIndentBulletPoint $indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 12$separator"
                            + " $indentBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }
        }
    }
})
