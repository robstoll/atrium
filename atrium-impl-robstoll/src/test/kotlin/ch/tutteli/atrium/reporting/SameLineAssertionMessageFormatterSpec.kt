package ch.tutteli.atrium.reporting


import ch.tutteli.atrium.*
import ch.tutteli.atrium.AssertionVerb.ASSERT
import ch.tutteli.atrium.DescriptionAnyAssertion.*
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

object SameLineAssertionMessageFormatterSpec : Spek({
    include(ch.tutteli.atrium.spec.reporting.SameLineAssertionMessageFormatterSpec(
        AssertionVerbFactory, ::SameLineAssertionFormatter))

    val testee = SameLineAssertionFormatter(ToStringObjectFormatter(), UsingDefaultTranslator)

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }
    val alwaysTrueMessageFilter: (Message) -> Boolean = { true }


    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }
    val separator = System.getProperty("line.separator")!!


    it("includes the group name and its representation in case of a ${IAssertionGroup::class.java.simpleName}") {
        testee.format(sb, AssertionGroup(ASSERT, "subject", listOf(
            OneMessageAssertion(TO_BE, "bli", false),
            OneMessageAssertion(NOT_TO_BE, "bye", false)
        )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
        assert(sb.toString()).startsWith("assert: subject$separator" +
            "${TO_BE.getDefault()}: bli$separator${NOT_TO_BE.getDefault()}: bye")
    }

    context("a ${IFeatureAssertionGroup::class.java.simpleName}") {
        val arrow = "-> "
        it("starts feature name with '$arrow' followed by representation") {
            testee.format(sb, FeatureAssertionGroup(Untranslatable("name"), "robert", listOf(
                OneMessageAssertion(TO_BE, "robert", true),
                OneMessageAssertion(NOT_TO_BE, "bert", true)
            )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).startsWith("${arrow}name: robert")
        }

        val indent = " ".repeat(SameLineAssertionFormatter.NUMBER_OF_INDENT_SPACES)

        it("indents assertions by ${SameLineAssertionFormatter.NUMBER_OF_INDENT_SPACES} spaces") {
            testee.format(sb, FeatureAssertionGroup(Untranslatable("name"), "robert", listOf(
                OneMessageAssertion(TO_BE, "robert", true),
                OneMessageAssertion(NOT_TO_BE, "bert", true)
            )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).contains("$indent${TO_BE.getDefault()}: robert$separator"
                + "$indent${NOT_TO_BE.getDefault()}: bert")
        }

        context("in an ${IAssertionGroup::class.java.simpleName}") {
            it("does only indent the assertions but not the feature name") {
                val message = Message(IS_SAME, "whatever", false)
                testee.format(sb, AssertionGroup(ASSERT, message, listOf(
                    FeatureAssertionGroup(Untranslatable(message::description.name), message.description, listOf(
                        OneMessageAssertion(TO_BE, "a", true),
                        OneMessageAssertion(NOT_TO_BE, "description", true)
                    )),
                    FeatureAssertionGroup(Untranslatable(message::representation.name), message.representation, listOf(
                        OneMessageAssertion(TO_BE, "whatever", true)
                    ))
                )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
                assert(sb.toString()).toBe("assert: " + message + separator
                    + "-> description: $IS_SAME$separator"
                    + "$indent${TO_BE.getDefault()}: a$separator"
                    + "$indent${NOT_TO_BE.getDefault()}: description$separator"
                    + "-> representation: whatever$separator"
                    + "$indent${TO_BE.getDefault()}: whatever"
                )
            }

            context("in another ${IFeatureAssertionGroup::class.java.simpleName}") {
                it("does indent the feature and double-intends its assertions") {
                    val message = Message(IS_SAME, "whatever", false)
                    testee.format(sb, AssertionGroup(ASSERT, message, listOf(
                        FeatureAssertionGroup(Untranslatable(message::description.name), message.description, listOf(
                            OneMessageAssertion(TO_BE, "a", true),
                            FeatureAssertionGroup(Untranslatable(message::description::toString.name), message.description, listOf(
                                OneMessageAssertion(NOT_TO_BE, "a description", true)
                            ))
                        ))
                    )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
                    assert(sb.toString()).toBe("assert: " + message + separator
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


