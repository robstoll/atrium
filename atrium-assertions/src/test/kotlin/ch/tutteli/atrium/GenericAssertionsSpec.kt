package ch.tutteli.atrium

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object GenericAssertionsSpec : Spek({
    data class DataClass(val isWhatever: Boolean)

    describe("fun genericCheck and genericNotCheck for properties") {
        context("property evaluates to true") {
            test("genericCheck does not throw an exception") {
                assert(DataClass(true)) { genericCheck(subject::isWhatever) }
            }
            test("genericNotCheck throws AssertionError and the message contains the name of the property together with false") {
                expect {
                    assert(DataClass(true)) { genericNotCheck(subject::isWhatever) }
                }.toThrow<AssertionError>().and.message.contains(DataClass::isWhatever.name + " is: false")
            }
        }

        context("property evaluates to false") {
            it("genericCheck throws an AssertionError and the message contains the name of the property together with true") {
                expect {
                    assert(DataClass(false)) { genericCheck(subject::isWhatever) }
                }.toThrow<AssertionError>().and.message.contains(DataClass::isWhatever.name + " is: true")
            }
            it("genericNotCheck does not throw an exception") {
                assert(DataClass(false)) { genericNotCheck(subject::isWhatever) }
            }
        }
    }
})

