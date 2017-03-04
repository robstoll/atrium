package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class SameLineAssertionMessageFormatterSpec : Spek({
    val testee = SameLineAssertionMessageFormatter(ToStringObjectFormatter())

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }
    val alwaysTrueMessageFilter: (Message) -> Boolean = { true }


    var sb = StringBuilder()
    afterEachTest {
        sb = StringBuilder()
    }

    context("unsupported ${IAssertion::class.java.simpleName}") {
        it("writes whether the assertion holds including a message telling the type is unsupported") {
            val assertion: IAssertion = object : IAssertion {
                override fun holds() = false
            }
            testee.format(sb, assertion, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).contains("Unsupported ${assertion::class.java.name}").and.contains("false")
        }
    }

    it("writes ${Message::description.name} and ${Message::representation.name} on the same line separated by colon and space") {
        testee.format(sb, OneMessageAssertion("bla", "bli", false), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
        assert(sb.toString()).toBe("bla: bli")
    }

    val separator = System.getProperty("line.separator")!!
    it("uses the system line separator if there are multiple messages") {
        testee.format(sb, object : IMultiMessageAssertion {
            override val messages = listOf(Message("a", "b", false), Message("c", "d", false))
        }, alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
        assert(sb.toString()).contains("a: b${separator}c: d")
    }

    it("includes the group name and its representation in case of a ${IAssertionGroup::class.java.simpleName}") {
        testee.format(sb, AssertionGroup("assert", "subject", listOf(
            OneMessageAssertion("bla", "bli", false),
            OneMessageAssertion("hello", "bye", false)
        )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
        assert(sb.toString()).startsWith("assert: subject${separator}bla: bli${separator}hello: bye")
    }

    context(IFeatureAssertionGroup::class.java.simpleName) {
        val arrow = "-> "
        it("starts feature name with '$arrow' followed by representation") {
            testee.format(sb, FeatureAssertionGroup("name", "robert", listOf(
                OneMessageAssertion("starts with", "ro", true),
                OneMessageAssertion("ends with", "bert", true)
            )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).startsWith("${arrow}name: robert")
        }

        it("indents assertions by ${SameLineAssertionMessageFormatter.INDENT} spaces") {
            testee.format(sb, FeatureAssertionGroup("name", "robert", listOf(
                OneMessageAssertion("starts with", "ro", true),
                OneMessageAssertion("ends with", "bert", true)
            )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).contains("   starts with: ro$separator   ends with: bert")
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
                    + "   starts with: a" + separator
                    + "   ends with: description" + separator
                    + "-> representation: whatever" + separator
                    + "   to be: whatever"
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
                        + "   starts with: a" + separator
                        + "   -> toString: a description" + separator
                        + "      to be: a description"
                    )
                }
            }
        }


    }

})

