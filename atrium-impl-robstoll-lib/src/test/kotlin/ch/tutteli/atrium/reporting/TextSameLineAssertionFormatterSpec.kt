package ch.tutteli.atrium.reporting


import ch.tutteli.atrium.AssertionVerb.ASSERT
import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.startsWith
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assert
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.*
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import ch.tutteli.atrium.spec.reporting.alwaysTrueAssertionFilter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

class TextSameLineAssertionFormatterSpec : Spek({

    include(AtriumsTextSameLineAssertionFormatterSpec)
    include(AtriumsAssertionFormatterSpec)

    val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
    facade.register({ TextAssertionFormatter(it, TextSameLineAssertionPairFormatter(ToStringObjectFormatter, UsingDefaultTranslator())) })

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }
    val separator = System.getProperty("line.separator")!!

    fun createRootAssertionGroup(name: ITranslatable, subject: Any, assertions: List<IAssertion>)
        = AssertionGroup(RootAssertionGroupType, name, subject, assertions)

    fun createFeatureAssertionGroup(name: ITranslatable, subject: Any, assertions: List<IAssertion>)
        = AssertionGroup(object : IFeatureAssertionGroupType {}, name, subject, assertions)

    val squarePoint = "▪"

    describe("fun ${TextAssertionFormatter::format.name}") {
        context("a ${IAssertionGroup::class.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
            it("includes the group ${IAssertionGroup::name.name}, its ${IAssertionGroup::subject.name} as well as the ${IAssertionGroup::assertions.name}") {
                facade.format(createRootAssertionGroup(ASSERT, "subject", listOf(
                    BasicAssertion(TO_BE, "bli", false),
                    BasicAssertion(NOT_TO_BE, "bye", false)
                )), sb, alwaysTrueAssertionFilter)
                assert(sb.toString()).startsWith("assert: subject$separator" +
                    "$squarePoint ${TO_BE.getDefault()}: bli$separator" +
                    "$squarePoint ${NOT_TO_BE.getDefault()}: bye")
            }
        }

        context("a ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
            val arrow = "-> "
            val arrowLength = arrow.length
            it("starts feature ${IAssertionGroup::name.name} with '$arrow' followed by the feature ${IAssertionGroup::subject.name}") {
                facade.format(createFeatureAssertionGroup(Untranslatable("name"), "robert", listOf(
                    BasicAssertion(TO_BE, "robert", true),
                    BasicAssertion(NOT_TO_BE, "bert", true)
                )), sb, alwaysTrueAssertionFilter)
                assert(sb.toString()).startsWith("${arrow}name: robert")
            }

            val indent = " ".repeat(arrowLength)

            it("indents assertions by $arrowLength spaces") {
                facade.format(createFeatureAssertionGroup(Untranslatable("name"), "robert", listOf(
                    BasicAssertion(TO_BE, "robert", true),
                    BasicAssertion(NOT_TO_BE, "bert", true)
                )), sb, alwaysTrueAssertionFilter)
                assert(sb.toString()).contains("$indent▪ ${TO_BE.getDefault()}: robert$separator"
                    + "$indent$squarePoint ${NOT_TO_BE.getDefault()}: bert")
            }

            context("in an ${IAssertionGroup::class.java.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
                it("does only indent the assertions but not the feature ${IAssertionGroup::name.name}") {
                    val basicAssertion = BasicAssertion(IS_SAME, "whatever", false)
                    facade.format(createRootAssertionGroup(ASSERT, basicAssertion, listOf(
                        createFeatureAssertionGroup(Untranslatable(basicAssertion::description.name), basicAssertion.description, listOf(
                            BasicAssertion(TO_BE, "a", true),
                            BasicAssertion(NOT_TO_BE, "description", true)
                        )),
                        createFeatureAssertionGroup(Untranslatable(basicAssertion::expected.name), basicAssertion.expected, listOf(
                            BasicAssertion(TO_BE, "whatever", true)
                        ))
                    )), sb, alwaysTrueAssertionFilter)
                    assert(sb.toString()).toBe("assert: " + basicAssertion + separator
                        + "$squarePoint -> ${basicAssertion::description.name}: $IS_SAME$separator"
                        + "$indent$squarePoint ${TO_BE.getDefault()}: a$separator"
                        + "$indent$squarePoint ${NOT_TO_BE.getDefault()}: description$separator"
                        + "$squarePoint -> ${basicAssertion::expected.name}: whatever$separator"
                        + "$indent$squarePoint ${TO_BE.getDefault()}: whatever"
                    )
                }

                context("in another ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
                    it("does indent the feature and double-intends its assertions") {
                        val basicAssertion = BasicAssertion(IS_SAME, "whatever", false)
                        facade.format(createRootAssertionGroup(ASSERT, basicAssertion, listOf(
                            createFeatureAssertionGroup(Untranslatable(basicAssertion::description.name), basicAssertion.description, listOf(
                                BasicAssertion(TO_BE, "a", true),
                                createFeatureAssertionGroup(Untranslatable(basicAssertion::description::toString.name), basicAssertion.description, listOf(
                                    BasicAssertion(NOT_TO_BE, "a description", true)
                                ))
                            ))
                        )), sb, alwaysTrueAssertionFilter)
                        assert(sb.toString()).toBe("assert: " + basicAssertion + separator
                            + "$squarePoint -> ${basicAssertion::description.name}: $IS_SAME$separator"
                            + "$indent$squarePoint ${TO_BE.getDefault()}: a$separator"
                            + "$indent$squarePoint -> toString: $IS_SAME$separator"
                            + "$indent$indent$squarePoint ${NOT_TO_BE.getDefault()}: a description"
                        )
                    }
                }
            }
        }
    }
}) {
    object AtriumsTextSameLineAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.TextSameLineAssertionFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's TextSameLine..Spec] "
    )

    object AtriumsAssertionFormatterSpec : ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, factory(), "[Atrium's AssertionFormatterSpec] "
    )

    companion object {
        internal fun factory() = { assertionFormatterController: IAssertionFormatterController, objectFormatter: IObjectFormatter, translator: ITranslator ->
            TextAssertionFormatter(assertionFormatterController, TextSameLineAssertionPairFormatter(objectFormatter, translator))
        }
    }
}
