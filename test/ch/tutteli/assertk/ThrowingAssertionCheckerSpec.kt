package ch.tutteli.assertk

import com.nhaarman.mockito_kotlin.*
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
                testee.fail(assertionVerb, 1, DescriptionExpectedAssertion("description", "1", { true }))
            }.toThrow<IllegalArgumentException> {
                assert(subject.message).isNotNull().startsWith("the given assertion should fail:")
            }
        }

        it("throws an AssertionError with the message formatted by the reporter") {
            expect {
                testee.fail(assertionVerb, "1", DescriptionExpectedAssertion("to be", "0", { false }))
            }.toThrow<AssertionError> {
                assert(subject.message).isNotNull().toBe(reporterResponse)
            }
        }
    }

    describe("check") {
        it("does not throw an AssertionError if all assertions hold") {
            testee.check(assertionVerb, 1, listOf(
                DescriptionExpectedAssertion("a", "a", { true }),
                DescriptionExpectedAssertion("a", "b", { true })
            ))
        }

        it("throws an AssertionError with the message formatted by the reporter if one assertion does not hold") {
            expect {
                testee.check(assertionVerb, 1, listOf(
                    DescriptionExpectedAssertion("a", "a", { true }),
                    DescriptionExpectedAssertion("a", "b", { false })
                ))
            }.toThrow<AssertionError> {
                assert(subject.message).isNotNull().toBe(reporterResponse)
            }
        }
    }
})
