package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.core.polyfills.stack
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.verbs.internal.AssertionVerb
import ch.tutteli.atrium.verbs.internal.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class AdjustStackSpec : Spek({

    describe("no-op adjuster") {
        fun <T : Any> assertNoOp(subject: T) = createAssert(
            subject, AssertImpl.coreFactory.newNoOpAtriumErrorAdjuster()
        )

        it("contains spek, junit, atrium.creating and atrium.reporting") {
            expect {
                assertNoOp(1).toBe(2)
            }.toThrow<AssertionError> {
                property(subject::stack).contains(
                    { startsWith("org.jetbrains.spek") },
                    { startsWith("org.junit") },
                    { startsWith("ch.tutteli.atrium") }
                )
            }
        }
    }
    describe("remove test runner adjuster") {
        fun <T : Any> assertRemoveRunner(subject: T) = createAssert(
            subject, AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster()
        )

        it("does not contain spek nor junit in stack but atrium") {
            expect {
                assertRemoveRunner(1).toBe(2)
            }.toThrow<AssertionError> {
                property(subject::stack)
                    .containsNot.entries({ contains("spek") }, { contains("junit") })
                    .contains { startsWith("ch.tutteli.atrium") }
            }
        }
    }

    describe("remove atrium adjuster") {
        fun <T : Any> assertRemoveAtrium(subject: T) = createAssert(
            subject, AssertImpl.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster()
        )

        it("does not contain atrium but spek and junit in stack") {
            expect {
                assertRemoveAtrium(1).toBe(2)
            }.toThrow<AssertionError> {
                property(subject::stack)
                    .contains({ contains("spek") }, { contains("junit") })
                    .containsNot.entry { startsWith("ch.tutteli.atrium") }
            }
        }
    }
})

private fun <T : Any> createAssert(subject: T, adjuster: AtriumErrorAdjuster) =
    AssertImpl.coreFactory.newReportingPlant(
        AssertionVerb.ASSERT, { subject },
        AssertImpl.coreFactory.newThrowingAssertionChecker(
            reporter,
            adjuster
        )
    )
