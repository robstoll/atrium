package ch.tutteli.assertk

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class AssertKCharSequenceSpec : Spek({
    val factory = assert("hello my name is robert")
    describe(factory::isEmpty.name) {
        it("throws an AssertionError if the string is not empty") {
            expect {
                assert("not empty string").isEmpty()
            }.toThrow<AssertionError>().and.message.endsWith("is: empty")
        }

        it("does not throw an AssertionError if it is an empty CharSequence") {
            assert("").isEmpty()
            assert(StringBuilder()).isEmpty()
            assert(StringBuffer()).isEmpty()
        }
    }
})
