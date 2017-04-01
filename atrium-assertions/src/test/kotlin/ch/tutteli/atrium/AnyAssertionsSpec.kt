package ch.tutteli.atrium


import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class AnyAssertionsSpec : Spek({
    describe("genericCheck for properties") {
        data class Test(val isDead: Boolean)
        context("evaluates to true") {
            it("does not throw an exception") {
                assert(Test(true)) { genericCheck(subject::isDead) }
            }
        }

        context("evaluates to false") {

            it("throws an AssertionError and the message contains the name of the property") {
                expect {
                    assert(Test(false)) { genericCheck(subject::isDead) }
                }.toThrow<AssertionError>().and.message.contains(Test::isDead.name)
            }
        }
    }
})
