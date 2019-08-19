package ch.tutteli.atrium.specs.checking

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.AssertionVerbFactory
import ch.tutteli.atrium.specs.describeFun
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class ThrowingAssertionCheckerSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Reporter) -> AssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assertionVerb = AssertionVerb.VERB
    val reporterResponse = "hello"
    //TODO #116 use mockk instead of mockito-kotlin
    val reporter = mock<Reporter> {
        on { format(any(), @Suppress("RemoveExplicitTypeArguments") any<StringBuilder>()) }.thenAnswer {
            (it.arguments[1] as StringBuilder).append(reporterResponse)
        }
        on { atriumErrorAdjuster }.thenReturn(coreFactory.newNoOpAtriumErrorAdjuster())
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
            testee.check(assertionVerb, { 1 }, listOf(
                assertionWhichHolds,
                assertionWhichHolds
            ))
        }

        mapOf(
            "first assertion fails" to listOf(assertionWhichFails, assertionWhichHolds, assertionWhichHolds),
            "middle assertion fails" to listOf(assertionWhichHolds, assertionWhichFails, assertionWhichHolds),
            "last assertion fails" to listOf(assertionWhichHolds, assertionWhichHolds, assertionWhichFails)

        ).forEach { (assertionFails, assertions) ->
            it("throws an AssertionError with the message formatted by the reporter if the $assertionFails") {
                verbs.checkException {
                    testee.check(assertionVerb, { 1 }, assertions)
                }.toThrow<AssertionError> {
                    message {
                        toBe(reporterResponse)
                    }
                }
            }
        }
    }
})
