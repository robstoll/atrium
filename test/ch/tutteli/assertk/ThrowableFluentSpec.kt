package ch.tutteli.assertk

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class ThrowableFluentSpec : Spek({


    describe("immediate evaluation") {
        it("throws an AssertionError when no exception occurs") {
            expect {
                expect {
                    /* no exception occurs */
                }.toThrow<IllegalArgumentException>()
            }.toThrow<AssertionError> {
                //TODO message contains 'exception was not thrown'
            }
        }

        it("throws an AssertionError when the wrong exception occurs") {
            expect {
                expect {
                    throw UnsupportedOperationException()
                }.toThrow<IllegalArgumentException>()
            }.toThrow<AssertionError> {
                //TODO message contains 'exception was: Unsupported'
            }
        }
        it("does not throw if the correct exception is thrown") {
            expect {
                throw IllegalArgumentException()
            }.toThrow<IllegalArgumentException>()
        }
    }
})
