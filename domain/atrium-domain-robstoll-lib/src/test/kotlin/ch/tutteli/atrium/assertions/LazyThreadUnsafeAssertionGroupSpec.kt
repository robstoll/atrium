package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.api.cc.en_UK.containsStrictly
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assert
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.translating.Untranslatable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object LazyThreadUnsafeAssertionGroupSpec : Spek({

    describe("creating it") {
        var callingCount = 0
        val assertion = AssertionBuilder.descriptive.create(Untranslatable("b"), 3, false)
        val testee = LazyThreadUnsafeAssertionGroup {
            ++callingCount
            AssertionBuilder.feature.create(Untranslatable("a"), 2, assertion)
        }
        test("does not evaluate anything") {
            assert(callingCount).toBe(0)
        }
        test("adding it to a list does not evaluate anything") {
            listOf(testee)
            assert(callingCount).toBe(0)
        }
        on("invoking ${testee::holds.name}") {
            val resultHolds = testee.holds()

            it("evaluates it") {
                assert(callingCount).toBe(1)
            }

            it("returns ${AssertionGroup::holds.name}() of the underlying ${AssertionGroup::class.simpleName}") {
                assert(resultHolds).toBe(false)
            }
        }

        on("invoking ${testee::holds.name} and then ${testee::assertions.name}") {
            val resultHolds = testee.holds()
            val resultAssertions = testee.assertions

            it("evaluates it only once") {
                assert(callingCount).toBe(1)
            }

            it("returns ${AssertionGroup::holds.name}() of the underlying ${AssertionGroup::class.simpleName}") {
                assert(resultHolds).toBe(false)
            }

            it("returns the ${AssertionGroup::assertions.name} of the underlying ${AssertionGroup::class.simpleName}") {
                assert(resultAssertions).containsStrictly(assertion)
            }
        }
    }
})
