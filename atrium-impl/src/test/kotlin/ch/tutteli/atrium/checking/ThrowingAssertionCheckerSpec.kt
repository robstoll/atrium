package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.OneMessageAssertion
import ch.tutteli.atrium.describe
import ch.tutteli.atrium.message
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.startsWith
import ch.tutteli.atrium.toBe
import ch.tutteli.atrium.verbs.expect.expect
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class ThrowingAssertionCheckerSpec : Spek({

    val assertionVerb = "assertionVerb"
    val reporterResponse = "hello"
    val reporter = mock<IReporter> {
        on { format(any<StringBuilder>(), any<IAssertion>()) }.thenAnswer {
            (it.arguments[0] as StringBuilder).append(reporterResponse)
        }
    }
    val testee = ThrowingAssertionChecker(reporter)

    describe("fail") {
        it("throws an IllegalArgumentException if the given assertion holds") {
            expect {
                testee.fail(assertionVerb, 1, OneMessageAssertion("description", "1", true))
            }.toThrow<IllegalArgumentException>().and.message.startsWith("the given assertion should fail:")
        }

        it("throws an AssertionError with the message formatted by the reporter") {
            expect {
                testee.fail(assertionVerb, "1", OneMessageAssertion("to be", "0", false))
            }.toThrow<AssertionError>().and.message.toBe(reporterResponse)
        }
    }

    describe("check") {
        it("does not throw an AssertionError if all assertions hold") {
            testee.check(assertionVerb, 1, listOf(
                OneMessageAssertion("a", "a", true),
                OneMessageAssertion("a", "b", true)
            ))
        }

        it("throws an AssertionError with the message formatted by the reporter if one assertion does not hold") {
            expect {
                testee.check(assertionVerb, 1, listOf(
                    OneMessageAssertion("a", "a", true),
                    OneMessageAssertion("a", "b", false)
                ))
            }.toThrow<AssertionError>().and.message.toBe(reporterResponse)
        }
    }
})
