package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.api.cc.en_UK.containsStrictly
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assert
import ch.tutteli.atrium.reporting.translating.Untranslatable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object LazyThreadUnsafeAssertionGroupSpec : Spek({

    describe("creating it") {
        var count = 0
        val assertion = BasicAssertion(Untranslatable("b"), 3, false)
        val testee = LazyThreadUnsafeAssertionGroup {
            ++count
            AssertionGroup(FeatureAssertionGroupType, Untranslatable("a"), 2, listOf(assertion))
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

        on("invoking ${testee::holds.name} and then ${testee::assertions.name}") {
            val resultHolds = testee.holds()
            val resultAssertions = testee.assertions

            it("evaluates it only once") {
                assert(count).toBe(1)
            }

            it("returns holds() of the underlying ${BasicAssertion::class.simpleName}") {
                assert(resultHolds).toBe(false)
            }

            it("returns expected of the underlying ${BasicAssertion::class.simpleName}") {
                assert(resultAssertions).containsStrictly(assertion)
            }
        }
    }
})
