package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeBasicAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object LazyThreadUnsafeBasicAssertionSpec : Spek({

    describe("creating it") {
        var callingCount = 0
        val testee = LazyThreadUnsafeBasicAssertion {
            ++callingCount
            AssertImpl.builder.descriptive.failing.withDescriptionAndRepresentation("a", 2).build()
        }
        test("does not evaluate anything") {
            expect(callingCount).toBe(0)
        }
        test("adding it to a list does not evaluate anything") {
            listOf(testee)
            expect(callingCount).toBe(0)
        }
        on("invoking ${testee::holds.name}") {
            val resultHolds = testee.holds()

            it("evaluates it") {
                expect(callingCount).toBe(1)
            }

            it("returns holds() of the underlying ${DescriptiveAssertion::class.simpleName}") {
                expect(resultHolds).toBe(false)
            }
        }

        on("invoking ${testee::holds.name} and then ${testee::representation.name}") {
            val resultHolds = testee.holds()
            val resultExpected = testee.representation

            it("evaluates it only once") {
                expect(callingCount).toBe(1)
            }

            it("returns ${DescriptiveAssertion::holds.name}() of the underlying ${DescriptiveAssertion::class.simpleName}") {
                expect(resultHolds).toBe(false)
            }

            it("returns ${DescriptiveAssertion::representation.name} of the underlying ${DescriptiveAssertion::class.simpleName}") {
                expect(resultExpected).toBe(2)
            }
        }
    }
})
