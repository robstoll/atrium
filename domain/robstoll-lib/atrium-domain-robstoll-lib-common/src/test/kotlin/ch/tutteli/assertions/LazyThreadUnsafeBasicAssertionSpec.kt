package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeBasicAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object LazyThreadUnsafeBasicAssertionSpec : Spek({

    describe("creating it") {
        var callingCount = 0
        val testee = LazyThreadUnsafeBasicAssertion {
            ++callingCount
            AssertImpl.builder.descriptive.failing.withDescriptionAndRepresentation("a", 2).build()
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

            it("returns holds() of the underlying ${DescriptiveAssertion::class.simpleName}") {
                expect(resultHolds).toBe(false)
            }
        }

        context("invoking ${testee::holds.name} and then ${testee::representation.name}") {
            var resultHolds = true

            it("execute it") {
                resultHolds = testee.holds()
            }

            it("evaluates it only once") {
                expect(callingCount).toBe(1)
            }

            it("returns ${DescriptiveAssertion::holds.name}() of the underlying ${DescriptiveAssertion::class.simpleName}") {
                expect(resultHolds).toBe(false)
            }

            it("returns ${DescriptiveAssertion::representation.name} of the underlying ${DescriptiveAssertion::class.simpleName}") {
                expect(testee.representation).toBe(2)
            }
        }
    }
})
