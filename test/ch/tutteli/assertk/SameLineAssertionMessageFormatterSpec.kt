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


    }
})

