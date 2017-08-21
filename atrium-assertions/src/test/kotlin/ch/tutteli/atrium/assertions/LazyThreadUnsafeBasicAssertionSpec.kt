package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assert
import ch.tutteli.atrium.reporting.translating.Untranslatable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object LazyThreadUnsafeBasicAssertionSpec : Spek({

    describe("creating it") {
        var count = 0
        val testee = LazyThreadUnsafeBasicAssertion {
            ++count
            BasicAssertion(Untranslatable("a"), 2, false)
        }
        test("does not evaluate anything") {
            assert(count).toBe(0)
        }
        test("adding it to a list does not evaluate anything") {
            listOf(testee)
            assert(count).toBe(0)
        }
        on("invoking ${testee::holds.name}") {
            val resultHolds = testee.holds()

            it("evaluates it") {
                assert(count).toBe(1)
            }

            it("returns holds() of the underlying ${BasicAssertion::class.simpleName}") {
                assert(resultHolds).toBe(false)
            }
        }

        on("invoking ${testee::holds.name} and then ${testee::expected.name}") {
            val resultHolds = testee.holds()
            val resultExpected = testee.expected

            it("evaluates it only once") {
                assert(count).toBe(1)
            }

            it("returns holds() of the underlying ${BasicAssertion::class.simpleName}") {
                assert(resultHolds).toBe(false)
            }

            it("returns expected of the underlying ${BasicAssertion::class.simpleName}") {
                assert(resultExpected).toBe(2)
            }
        }
    }
})
