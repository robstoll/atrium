package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.isTrue
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.IAssertionFormatter
import ch.tutteli.atrium.reporting.IAssertionFormatterController
import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class TextListBasedAssertionGroupFormatterSpec<T : IAssertionGroupType>(
    verbs: IAssertionVerbFactory,
    testeeFactory: (String, IAssertionFormatterController, IObjectFormatter, ITranslator) -> IAssertionFormatter,
    assertionGroupClass: Class<T>,
    anonymousAssertionGroupType: T,
    extraIndent: Int,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    val assertions = listOf(
        BasicAssertion(AssertionVerb.ASSERT, 1, true),
        BasicAssertion(AssertionVerb.EXPECT_THROWN, 2, true)
    )
    val listAssertionGroup = AssertionGroup(anonymousAssertionGroupType, TranslationSupplierSpec.TestTranslatable.PLACEHOLDER, 2, assertions)

    val separator = System.getProperty("line.separator")!!
    val arrow = "->"
    val arrowIndent = " ".repeat(arrow.length + 1)

    prefixedDescribe("fun ${IAssertionFormatter::canFormat.name}") {
        val testee = testeeFactory("*", AtriumFactory.newAssertionFormatterController(), ToStringObjectFormatter, UsingDefaultTranslator())
        it("returns true for an ${IAssertionGroup::class.simpleName} with type object: ${assertionGroupClass.simpleName}") {
            val result = testee.canFormat(AssertionGroup(anonymousAssertionGroupType, Untranslatable(""), 1, listOf()))
            verbs.checkImmediately(result).isTrue()
        }
    }

    prefixedDescribe("fun ${IAssertionFormatter::formatGroup.name}") {

        mapOf(
            "•" to "▪",
            "[]" to "{}").forEach { (listBulletPointBeforeExtraIndent, bulletPoint) ->
            val listBulletPoint = " ".repeat(extraIndent) + listBulletPointBeforeExtraIndent
            val listIndent = " ".repeat(listBulletPoint.length + 1)
            val indent = " ".repeat(bulletPoint.length + 1)
            context("listBulletPoint: $listBulletPoint, bulletPoint: $bulletPoint") {
                val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
                facade.register({ testeeFactory(listBulletPointBeforeExtraIndent, it, ToStringObjectFormatter, UsingDefaultTranslator()) })
                facade.register({ AtriumFactory.newTextFeatureAssertionGroupFormatter(arrow, bulletPoint, it, ToStringObjectFormatter, UsingDefaultTranslator()) })
                facade.register({ AtriumFactory.newTextFallbackAssertionFormatter(bulletPoint, it, ToStringObjectFormatter, UsingDefaultTranslator()) })

                context("${IAssertionGroup::class.simpleName} of type ${assertionGroupClass.simpleName}") {
                    context("format directly the group") {
                        it("includes the group ${IAssertionGroup::name.name}, its ${IAssertionGroup::subject.name} as well as the ${IAssertionGroup::assertions.name} which are prepended with a `$listBulletPoint` as bullet point") {
                            facade.format(listAssertionGroup, sb, alwaysTrueAssertionFilter)
                            verbs.checkImmediately(sb.toString()).toBe(
                                "placeholder %s: 2$separator"
                                    + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                    + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                        }
                    }

                    context("in an ${IAssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                        val featureAssertions = listOf(listAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 20, false))
                        val featureAssertionGroup = AssertionGroup(FeatureAssertionGroupType, AssertionVerb.ASSERT, 10, featureAssertions)
                        it("indents the group ${IAssertionGroup::name.name} as well as the ${IAssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                            facade.format(featureAssertionGroup, sb, alwaysTrueAssertionFilter)
                            verbs.checkImmediately(sb.toString()).toBe(
                                "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                                    + "$arrowIndent$bulletPoint placeholder %s: 2$separator"
                                    + "$arrowIndent$indent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                    + "$arrowIndent$indent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                    + "$arrowIndent$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                        }
                        context("in another ${IAssertionGroup::class.simpleName} of type ${assertionGroupClass.simpleName}") {
                            it("indents the group ${IAssertionGroup::name.name} as well as the ${IAssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                                val listAssertions = listOf(BasicAssertion(AssertionVerb.ASSERT, 5, false), featureAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 30, false))
                                val listAssertionGroup2 = AssertionGroup(anonymousAssertionGroupType, AssertionVerb.EXPECT_THROWN, 10, listAssertions)
                                facade.format(listAssertionGroup2, sb, alwaysTrueAssertionFilter)
                                verbs.checkImmediately(sb.toString()).toBe(
                                    "${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$separator"
                                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                                        + "$listBulletPoint $arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                                        + "$listIndent$arrowIndent$bulletPoint placeholder %s: 2$separator"
                                        + "$listIndent$arrowIndent$indent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                        + "$listIndent$arrowIndent$indent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                        + "$listIndent$arrowIndent$bulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30")
                            }
                        }
                    }
                    context("in another ${IAssertionGroup::class.simpleName} of type ${assertionGroupClass.simpleName}") {
                        it("indents the group ${IAssertionGroup::name.name} as well as the ${IAssertionGroup::assertions.name} accordingly - uses `$listBulletPoint` for each assertion and `$bulletPoint` for each element in the list group") {
                            val listAssertions = listOf(BasicAssertion(AssertionVerb.ASSERT, 5, false), listAssertionGroup, BasicAssertion(AssertionVerb.ASSERT, 30, false))
                            val listAssertionGroup2 = AssertionGroup(anonymousAssertionGroupType, AssertionVerb.EXPECT_THROWN, 10, listAssertions)
                            facade.format(listAssertionGroup2, sb, alwaysTrueAssertionFilter)
                            verbs.checkImmediately(sb.toString()).toBe(
                                "${AssertionVerb.EXPECT_THROWN.getDefault()}: 10$separator"
                                    + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                                    + "$listBulletPoint placeholder %s: 2$separator"
                                    + "$listIndent$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                                    + "$listIndent$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                                    + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30")
                        }
                    }
                }
            }
        }
    }

})
