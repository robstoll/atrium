package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.spec.reporting.ToStringObjectFormatter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

object SameLineAssertionMessageFormatterSpec : Spek({
    include(ch.tutteli.atrium.spec.reporting.SameLineAssertionMessageFormatterSpec(
        AssertionVerbFactory, ::SameLineAssertionFormatter))

    val testee = SameLineAssertionFormatter(ToStringObjectFormatter())

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }
    val alwaysTrueMessageFilter: (Message) -> Boolean = { true }


    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }
    val separator = System.getProperty("line.separator")!!

    it("includes the group name and its representation in case of a ${IAssertionGroup::class.java.simpleName}") {
        testee.format(sb, AssertionGroup("assert", "subject", listOf(
            OneMessageAssertion("bla", "bli", false),
            OneMessageAssertion("hello", "bye", false)
        )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
        assert(sb.toString()).startsWith("assert: subject${separator}bla: bli${separator}hello: bye")
    }

    context("a ${IFeatureAssertionGroup::class.java.simpleName}") {
        val arrow = "-> "
        it("starts feature name with '$arrow' followed by representation") {
            testee.format(sb, FeatureAssertionGroup("name", "robert", listOf(
                OneMessageAssertion("starts with", "ro", true),
                OneMessageAssertion("ends with", "bert", true)
            )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).startsWith("${arrow}name: robert")
        }

        val indent = " ".repeat(SameLineAssertionFormatter.NUMBER_OF_INDENT_SPACES)

        it("indents assertions by ${SameLineAssertionFormatter.NUMBER_OF_INDENT_SPACES} spaces") {
            testee.format(sb, FeatureAssertionGroup("name", "robert", listOf(
                OneMessageAssertion("starts with", "ro", true),
                OneMessageAssertion("ends with", "bert", true)
            )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).contains("${indent}starts with: ro$separator${indent}ends with: bert")
        }

        context("in an ${IAssertionGroup::class.java.simpleName}") {
            it("does only indent the assertions but not the feature name") {
                val message = Message("a description", "whatever", false)
                testee.format(sb, AssertionGroup("assert", message, listOf(
                    FeatureAssertionGroup(message::description.name, message.description, listOf(
                        OneMessageAssertion("starts with", "a", true),
                        OneMessageAssertion("ends with", "description", true)
                    )),
                    FeatureAssertionGroup(message::representation.name, message.representation, listOf(
                        OneMessageAssertion("to be", "whatever", true)
                    ))
                )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
                assert(sb.toString()).toBe("assert: " + message + separator
                    + "-> description: a description" + separator
                    + "${indent}starts with: a" + separator
                    + "${indent}ends with: description" + separator
                    + "-> representation: whatever" + separator
                    + "${indent}to be: whatever"
                )
            }

            context("in another ${IFeatureAssertionGroup::class.java.simpleName}") {
                it("does indent the feature and double-intends its assertions") {
                    val message = Message("a description", "whatever", false)
                    testee.format(sb, AssertionGroup("assert", message, listOf(
                        FeatureAssertionGroup(message::description.name, message.description, listOf(
                            OneMessageAssertion("starts with", "a", true),
                            FeatureAssertionGroup(message::description::toString.name, message.description, listOf(
                                OneMessageAssertion("to be", "a description", true)
                            ))
                        ))
                    )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
                    assert(sb.toString()).toBe("assert: " + message + separator
                        + "-> description: a description" + separator
                        + "${indent}starts with: a" + separator
                        + "$indent-> toString: a description" + separator
                        + "$indent${indent}to be: a description"
                    )
                }
            }
        }
    }

})

