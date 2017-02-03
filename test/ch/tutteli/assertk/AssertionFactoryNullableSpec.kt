package ch.tutteli.assertk

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class AssertionFactoryNullableSpec : Spek({
    describe("subject is null") {
        val i: Int? = null
        it("isNull() does not throw an Exception") {
            assert(i).isNull()
        }
        it("isNotNull() throws an AssertionError") {
            expect {
                assert(i).isNotNull()
            }.toThrow<AssertionError>()
        }
    }

    describe("subject is not null") {
        val i: Int? = 1
        it("isNull() throws an AssertionError") {
            expect {
                assert(i).isNull()
            }.toThrow<AssertionError>()
        }
        it("isNotNull() does not throw an Exception") {
            assert(i).isNotNull()
        }
    }
})
