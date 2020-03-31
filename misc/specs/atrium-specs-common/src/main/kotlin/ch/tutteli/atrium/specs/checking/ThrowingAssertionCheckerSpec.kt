package ch.tutteli.atrium.specs.checking

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import io.mockk.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ThrowingAssertionCheckerSpec(
    testeeFactory: (Reporter) -> AssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assertionVerb = AssertionVerb.VERB
    val reporterResponse = "hello"
    val strbldSlot = slot<StringBuilder>()

    val reporter = mockk<Reporter> {
        every { format(any(), @Suppress("RemoveExplicitTypeArguments") capture(strbldSlot)) } answers {
            (strbldSlot.captured).append(reporterResponse)
        }
        every { atriumErrorAdjuster } returns (coreFactory.newNoOpAtriumErrorAdjuster())
    }
    val testee = testeeFactory(reporter)
    val assertionWhichHolds = object : Assertion {
        override fun holds() = true
    }
    val assertionWhichFails = object : Assertion {
        override fun holds() = false
    }

    describeFun("check") {
        it("does not throw an AssertionError if all assertions hold") {
            testee.check(assertionVerb, 1, listOf(assertionWhichHolds, assertionWhichHolds))
        }

        mapOf(
            "first assertion fails" to listOf(assertionWhichFails, assertionWhichHolds, assertionWhichHolds),
            "middle assertion fails" to listOf(assertionWhichHolds, assertionWhichFails, assertionWhichHolds),
            "last assertion fails" to listOf(assertionWhichHolds, assertionWhichHolds, assertionWhichFails)

        ).forEach { (assertionFails, assertions) ->
            it("throws an AssertionError with the message formatted by the reporter if the $assertionFails") {
                expect {
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
