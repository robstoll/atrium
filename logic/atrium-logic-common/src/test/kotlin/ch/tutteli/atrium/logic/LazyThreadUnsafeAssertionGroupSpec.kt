package ch.tutteli.atrium.logic

import ch.tutteli.atrium.api.fluent.en_GB.containsExactly
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.logic.impl.assertions.LazyThreadUnsafeAssertionGroup
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object LazyThreadUnsafeAssertionGroupSpec : Spek({

    describe("creating it") {
        var callingCount = 0
        val assertion = assertionBuilder.descriptive
            .failing
            .withDescriptionAndRepresentation("b", 3)
            .build()

        val testee = LazyThreadUnsafeAssertionGroup {
            ++callingCount
            assertionBuilder.feature
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
