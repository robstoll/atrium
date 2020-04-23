package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.api.fluent.en_GB.containsExactly
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object LazyThreadUnsafeAssertionGroupSpec : Spek({

    describe("creating it") {
        var callingCount = 0
        val assertion = ExpectImpl.builder.descriptive
            .failing
            .withDescriptionAndRepresentation("b", 3)
            .build()

        val testee = LazyThreadUnsafeAssertionGroup {
            ++callingCount
            ExpectImpl.builder.feature
                .withDescriptionAndRepresentation("a", 2)
                .withAssertion(assertion)
                .build()
        }
        it("does not evaluate anything") {
            expect(callingCount).toBe(0)
        }
        it("adding it to a list does not evaluate anything") {
            listOf(testee)
            expect(callingCount).toBe(0)
        }
        context("invoking ${testee::holds.name}") {
            var resultHolds = true

            it("execute it") {
                resultHolds = testee.holds()
            }

            it("evaluates it") {
                expect(callingCount).toBe(1)
            }

            it("returns ${AssertionGroup::holds.name}() of the underlying ${AssertionGroup::class.simpleName}") {
                expect(resultHolds).toBe(false)
            }
        }

        context("invoking ${testee::holds.name} and then ${testee::assertions.name}") {
            var resultHolds = true

            it("execute it") {
                resultHolds = testee.holds()
            }

            it("evaluates it only once") {
                expect(callingCount).toBe(1)
            }

            it("returns ${AssertionGroup::holds.name}() of the underlying ${AssertionGroup::class.simpleName}") {
                expect(resultHolds).toBe(false)
            }

            it("returns the ${AssertionGroup::assertions.name} of the underlying ${AssertionGroup::class.simpleName}") {
                expect(testee.assertions).containsExactly(assertion)
            }
        }
    }
})
