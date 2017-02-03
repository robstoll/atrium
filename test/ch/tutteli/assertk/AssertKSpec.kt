package ch.tutteli.assertk

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class AssertKSpec : Spek({
    describe("assert()") {
        it("fails immediately") {
            expect {
                assert(1).isSmallerThan(0).and.isGreaterThan(2)
            }.toThrow<AssertionError> {
                assert(subject.message).isNotNull {
                    contains("1") //the actual value
                    contains("0") //the expected value
                    //TODO contains not 2
                }
            }
        }
    }

    describe("assert{} (lazy evaluation)") {
        it("evaluates all assertions"){
            expect {
                assert(1) {
                    isSmallerThan(0)
                    isGreaterThan(2)
                }
            }.toThrow<AssertionError> {
                assert(subject.message).isNotNull {
                    contains("1") //the actual value
                    contains("0") //the expected value
                    contains("2") //the second expected value
                }
            }
        }
    }
})
