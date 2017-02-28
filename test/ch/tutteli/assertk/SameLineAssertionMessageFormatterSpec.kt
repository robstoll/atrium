package ch.tutteli.assertk

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class SameLineAssertionMessageFormatterSpec : Spek({
    val testee = SameLineAssertionMessageFormatter(ToStringObjectFormatter())

    val alwaysTrueAssertionFilter: (IAssertion) -> Boolean = { true }
    val alwaysTrueMessageFilter: (Message) -> Boolean = { true }

    describe("format") {
        var sb = StringBuilder()
        afterEachTest {
            sb = StringBuilder()
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

        it("includes the group name and its representation in case of a ${IAssertionGroup::class.java.name}") {
            testee.format(sb, AssertionGroup("assert", "subject", listOf(
                OneMessageAssertion("bla", "bli", false),
                OneMessageAssertion("hello", "bye", false)
            )), alwaysTrueAssertionFilter, alwaysTrueMessageFilter)
            assert(sb.toString()).startsWith("assert: subject${separator}bla: bli${separator}hello: bye")
        }

    }
})

