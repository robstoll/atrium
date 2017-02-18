package ch.tutteli.assertk

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class SameLineAssertionMessageFormatterSpec : Spek({
    val testee = SameLineAssertionMessageFormatter(ToStringObjectFormatter())

    val alwaysTrueFilter: (Message) -> Boolean = { true }

    describe("format") {
        var sb = StringBuilder()
        afterEachTest {
            sb = StringBuilder()
        }

        it("writes ${Message::description.name} and ${Message::representation.name} on the same line separated by colon and space") {
            testee.format(sb, DescriptionExpectedAssertion("bla", "bli", { false }), alwaysTrueFilter)
            assert(sb.toString()).toBe("bla: bli")
        }

        val separator = System.getProperty("line.separator")!!
        it("uses the system line separator if there are multiple messages") {
            testee.format(sb, object : IAssertion {
                override fun messages() = listOf(Message("a", "b", false), Message("c", "d", false))
            }, alwaysTrueFilter)
            assert(sb.toString()).contains("a: b${separator}c: d")
        }

        it("includes the group name and its representation in case of a ${IAssertionGroup::class.java.name}") {
            testee.format(sb, AssertionGroup("assert", "subject", listOf(
                DescriptionExpectedAssertion("bla", "bli", { false }),
                DescriptionExpectedAssertion("hello", "bye", { false })
            )), alwaysTrueFilter)
            assert(sb.toString()).startsWith("assert: subject${separator}bla: bli${separator}hello: bye")
        }

    }
})

