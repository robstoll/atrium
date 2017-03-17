package ch.tutteli.assertk.creating

import ch.tutteli.assertk.*
import ch.tutteli.assertk.verbs.assert.assert
import ch.tutteli.assertk.verbs.expect.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class AssertionFactoryNullableSpec : Spek({
    describe("subject is null") {
        val i: Int? = null
        check("isNull() does not throw an Exception") {
            assert(i).isNull()
        }
        check("isNotNull() throws an AssertionError") {
            expect {
                assert(i).isNotNull()
            }.toThrow<AssertionError>().and.message.contains("is not", "null")
        }
    }

    describe("subject is not null") {
        val i: Int? = 1
        check("isNull() throws an AssertionError") {
            expect {
                assert(i).isNull()
            }.toThrow<AssertionError>().and.message.contains("to be", "null")
        }
        setUp("isNotNull() does not throw an Exception") {
            val fluent = assert(i).isNotNull()
            it("is possible to continue with the assertion") {
                fluent.toBe(1)
            }
        }
        check("isNotNull{} (with lazy evaluation) does not throw an Exception") {
            assert(i).isNotNull {
                toBe(1)
            }
        }
    }
})
