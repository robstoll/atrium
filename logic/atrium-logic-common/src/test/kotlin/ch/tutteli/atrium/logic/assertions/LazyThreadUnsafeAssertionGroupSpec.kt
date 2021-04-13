package ch.tutteli.atrium.logic.assertions

import ch.tutteli.atrium.api.fluent.en_GB.containsExactly
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.logic.assertions.impl.LazyThreadUnsafeAssertionGroup
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
            expect(callingCount).toEqual(0)
        }
        it("adding it to a list does not evaluate anything") {
            listOf(testee)
            expect(callingCount).toEqual(0)
        }
        context("invoking ${testee::holds.name}") {
            var resultHolds = true

            it("execute it") {
                resultHolds = testee.holds()
            }

            it("evaluates it") {
                expect(callingCount).toEqual(1)
            }

            it("returns ${AssertionGroup::holds.name}() of the underlying ${AssertionGroup::class.simpleName}") {
                expect(resultHolds).toEqual(false)
            }
        }

        context("invoking ${testee::holds.name} and then ${testee::assertions.name}") {
            var resultHolds = true

            it("execute it") {
                resultHolds = testee.holds()
            }

            it("evaluates it only once") {
                expect(callingCount).toEqual(1)
            }

            it("returns ${AssertionGroup::holds.name}() of the underlying ${AssertionGroup::class.simpleName}") {
                expect(resultHolds).toEqual(false)
            }

            it("returns the ${AssertionGroup::assertions.name} of the underlying ${AssertionGroup::class.simpleName}") {
                expect(testee.assertions).containsExactly(assertion)
            }
        }
    }
})
