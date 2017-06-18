package ch.tutteli.atrium.reporting


import ch.tutteli.atrium.*
import ch.tutteli.atrium.AssertionVerb.ASSERT
import ch.tutteli.atrium.DescriptionAnyAssertion.*
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

object SameLineAssertionFormatterSpec : Spek({
    include(ch.tutteli.atrium.spec.reporting.SameLineAssertionFormatterSpec(
        AssertionVerbFactory, ::SameLineAssertionFormatter))
    include(ch.tutteli.atrium.spec.reporting.AssertionFormatterSpec(
        AssertionVerbFactory, ::SameLineAssertionFormatter))

    val facade = AtriumFactory.newAssertionFormatterFacade(AtriumFactory.newAssertionFormatterController())
    facade.register({SameLineAssertionFormatter(it, ToStringObjectFormatter(), UsingDefaultTranslator())})

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }

    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }
    val separator = System.getProperty("line.separator")!!

    fun createRootAssertionGroup(name: ITranslatable, subject: Any, assertions: List<IAssertion>)
        = AssertionGroup(RootAssertionGroupType, name, subject, assertions)

    fun createFeatureAssertionGroup(name: ITranslatable, subject: Any, assertions: List<IAssertion>)
        = AssertionGroup(FeatureAssertionGroupType, name, subject, assertions)

    context("a ${IAssertionGroup::class.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
        it("includes the group name, its representation as well as the assertions") {
            facade.format(createRootAssertionGroup(ASSERT, "subject", listOf(
                BasicAssertion(TO_BE, "bli", false),
                BasicAssertion(NOT_TO_BE, "bye", false)
            )), sb, alwaysTrueAssertionFilter)
            assert(sb.toString()).startsWith("assert: subject$separator" +
                "${TO_BE.getDefault()}: bli$separator${NOT_TO_BE.getDefault()}: bye")
        }
    }

    context("a ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
        val arrow = "-> "
        val arrowLength = arrow.length
        it("starts feature name with '$arrow' followed by representation") {
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
            assert(sb.toString()).contains("$indent${TO_BE.getDefault()}: robert$separator"
                + "$indent${NOT_TO_BE.getDefault()}: bert")
        }

        context("in an ${IAssertionGroup::class.java.simpleName} of type ${RootAssertionGroupType::class.simpleName}") {
            it("does only indent the assertions but not the feature name") {
                val basicAssertion = BasicAssertion(IS_SAME, "whatever", false)
                facade.format(createRootAssertionGroup(ASSERT, basicAssertion, listOf(
                    createFeatureAssertionGroup(Untranslatable(basicAssertion::description.name), basicAssertion.description, listOf(
                        BasicAssertion(TO_BE, "a", true),
                        BasicAssertion(NOT_TO_BE, "description", true)
                    )),
                    createFeatureAssertionGroup(Untranslatable(basicAssertion::representation.name), basicAssertion.representation, listOf(
                        BasicAssertion(TO_BE, "whatever", true)
                    ))
                )), sb, alwaysTrueAssertionFilter)
                assert(sb.toString()).toBe("assert: " + basicAssertion + separator
                    + "-> description: $IS_SAME$separator"
                    + "$indent${TO_BE.getDefault()}: a$separator"
                    + "$indent${NOT_TO_BE.getDefault()}: description$separator"
                    + "-> representation: whatever$separator"
                    + "$indent${TO_BE.getDefault()}: whatever"
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
                        + "-> description: $IS_SAME$separator"
                        + "$indent${TO_BE.getDefault()}: a$separator"
                        + "$indent-> toString: $IS_SAME$separator"
                        + "$indent$indent${NOT_TO_BE.getDefault()}: a description"
                    )
                }
            }
        }
    }

})


