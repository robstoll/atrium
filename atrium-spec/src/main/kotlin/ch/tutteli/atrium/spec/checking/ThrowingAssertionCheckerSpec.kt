package ch.tutteli.atrium.spec.checking

import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it

abstract class ThrowingAssertionCheckerSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (Reporter) -> AssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assertionVerb = AssertionVerb.VERB
    val reporterResponse = "hello"
    val reporter = mock<Reporter> {
        on { format(any(), any<StringBuilder>()) }.thenAnswer {
            (it.arguments[1] as StringBuilder).append(reporterResponse)
        }
    }
    val testee = testeeFactory(reporter)
    val assertionWhichHolds = object : Assertion {
        override fun holds() = true
    }
    val assertionWhichFails = object : Assertion {
        override fun holds() = false
    }

    describeFun(testee::check.name) {
        it("does not throw an AssertionError if all assertions hold") {
            testee.check(assertionVerb, 1, listOf(
                assertionWhichHolds,
                assertionWhichHolds
            ))
        }

        mapOf(
            "first assertion fails" to listOf(assertionWhichFails, assertionWhichHolds, assertionWhichHolds),
            "middle assertion fails" to listOf(assertionWhichHolds, assertionWhichFails, assertionWhichHolds),
            "last assertion fails" to listOf(assertionWhichHolds, assertionWhichHolds, assertionWhichFails)

        ).forEach { assertionFails, assertions ->
            it("throws an AssertionError with the message formatted by the reporter if the $assertionFails") {
                verbs.checkException {
                    testee.check(assertionVerb, 1, assertions)
                }.toThrow<AssertionError> {
                    message {
                        toBe(reporterResponse)
                    }
                }
            }
        }
    }
})
