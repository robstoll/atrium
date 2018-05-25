package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeBasicAssertion
import ch.tutteli.atrium.reporting.translating.Untranslatable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object LazyThreadUnsafeBasicAssertionSpec : Spek({

    describe("creating it") {
        var callingCount = 0
        val testee = LazyThreadUnsafeBasicAssertion {
            ++callingCount
            AssertImpl.builder.descriptive.failing.create(Untranslatable("a"), 2)
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

            it("returns holds() of the underlying ${DescriptiveAssertion::class.simpleName}") {
                assert(resultHolds).toBe(false)
            }
        }

        on("invoking ${testee::holds.name} and then ${testee::representation.name}") {
            val resultHolds = testee.holds()
            val resultExpected = testee.representation

            it("evaluates it only once") {
                assert(callingCount).toBe(1)
            }

            it("returns ${DescriptiveAssertion::holds.name}() of the underlying ${DescriptiveAssertion::class.simpleName}") {
                assert(resultHolds).toBe(false)
            }

            it("returns ${DescriptiveAssertion::representation.name} of the underlying ${DescriptiveAssertion::class.simpleName}") {
                assert(resultExpected).toBe(2)
            }
        }
    }
})
