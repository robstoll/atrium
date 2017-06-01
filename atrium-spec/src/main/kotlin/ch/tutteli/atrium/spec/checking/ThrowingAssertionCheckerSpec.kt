package ch.tutteli.atrium.spec.checking

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.message
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.startsWith
import ch.tutteli.atrium.toBe
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

open class ThrowingAssertionCheckerSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IReporter) -> IAssertionChecker
) : Spek({

    val assertionVerb = AssertionVerb.VERB
    val reporterResponse = "hello"
    val reporter = mock<IReporter> {
        on { format(any<StringBuilder>(), any<IAssertion>()) }.thenAnswer {
            (it.arguments[0] as StringBuilder).append(reporterResponse)
        }
    }
    val testee = testeeFactory(reporter)
    val assertionWhichHolds = object : IAssertion {
        override fun holds() = true
    }
    val assertionWhichFails = object : IAssertion {
        override fun holds() = false
    }

    describe("fail") {
        it("throws an IllegalArgumentException if the given assertion holds") {
            verbs.checkException {
                testee.fail(assertionVerb, 1, assertionWhichHolds)
            }.toThrow<IllegalArgumentException>().and.message.startsWith("the given assertion should fail:")
        }

        it("throws an AssertionError with the message formatted by the reporter") {
            verbs.checkException {
                testee.fail(assertionVerb, "1", assertionWhichFails)
            }.toThrow<AssertionError>().and.message.toBe(reporterResponse)
        }
    }

    describe("check") {
        it("does not throw an AssertionError if all assertions hold") {
            testee.check(assertionVerb, 1, listOf(
                assertionWhichHolds,
                assertionWhichHolds
            ))
        }

        it("throws an AssertionError with the message formatted by the reporter if first assertion fails") {
            verbs.checkException {
                testee.check(assertionVerb, 1, listOf(
                    assertionWhichFails,
                    assertionWhichHolds,
                    assertionWhichHolds
                ))
            }.toThrow<AssertionError>().and.message.toBe(reporterResponse)
        }

        it("throws an AssertionError with the message formatted by the reporter if the middle assertion fails") {
            verbs.checkException {
                testee.check(assertionVerb, 1, listOf(
                    assertionWhichHolds,
                    assertionWhichFails,
                    assertionWhichHolds
                ))
            }.toThrow<AssertionError>().and.message.toBe(reporterResponse)
        }

        it("throws an AssertionError with the message formatted by the reporter if last assertion fails") {
            verbs.checkException {
                testee.check(assertionVerb, 1, listOf(
                    assertionWhichHolds,
                    assertionWhichHolds,
                    assertionWhichFails
                ))
            }.toThrow<AssertionError>().and.message.toBe(reporterResponse)
        }
    }
})
