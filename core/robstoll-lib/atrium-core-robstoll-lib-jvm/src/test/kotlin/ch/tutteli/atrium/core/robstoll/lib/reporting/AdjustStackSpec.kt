package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.core.polyfills.stack
import ch.tutteli.atrium.creating.ReportingAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.Reporter
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
                    .containsNot.entries({ startsWith("org.jetbrains.spek") }, { startsWith("org.junit") })
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
                    .contains({ startsWith("org.jetbrains.spek") }, { startsWith("org.junit") })
                    .containsNot.entry { startsWith("ch.tutteli.atrium") }
            }
        }
    }

    describe("combine remove runner adjuster and remove atrium adjuster") {
        fun <T : Any> assertRemoveRunnerAndAtrium(subject: T): ReportingAssertionPlant<T> {
            return createMultiAtriumErrorAdjuster(
                subject,
                AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster(),
                AssertImpl.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster()
            )
        }

        it("does neither contain atrium nor spek or junit in stack") {
            expect {
                assertRemoveRunnerAndAtrium(1).toBe(2)
            }.toThrow<AssertionError> {
                property(subject::stack).isEmpty()
            }
        }
    }

    describe("combine remove atrium adjuster and remove runner adjuster") {
        fun <T : Any> assertRemoveAtriumAndRunner(subject: T): ReportingAssertionPlant<T> {
            return createMultiAtriumErrorAdjuster(
                subject,
                AssertImpl.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster(),
                AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster()
            )
        }

        it("does neither contain atrium nor spek or junit in stack") {
            expect {
                assertRemoveAtriumAndRunner(1).toBe(2)
            }.toThrow<AssertionError> {
                property(subject::stack).isEmpty()
            }
        }
    }

    describe("combine remove atrium adjuster, remove runner adjuster and noop adjuster") {
        fun <T : Any> assertRemoveAtriumAndRunner(subject: T): ReportingAssertionPlant<T> {
            return createMultiAtriumErrorAdjuster(
                subject,
                AssertImpl.coreFactory.newNoOpAtriumErrorAdjuster(),
                AssertImpl.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster(),
                listOf(
                    AssertImpl.coreFactory.newNoOpAtriumErrorAdjuster(),
                    AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster(),
                    AssertImpl.coreFactory.newNoOpAtriumErrorAdjuster()
                )
            )
        }

        it("does neither contain atrium nor spek or junit in stack") {
            expect {
                assertRemoveAtriumAndRunner(1).toBe(2)
            }.toThrow<AssertionError> {
                property(subject::stack).isEmpty()
            }
        }
    }
})

private fun <T : Any> createMultiAtriumErrorAdjuster(
    subject: T,
    firstAdjuster: AtriumErrorAdjuster,
    secondAdjuster: AtriumErrorAdjuster,
    otherAdjuster: List<AtriumErrorAdjuster> = listOf()
): ReportingAssertionPlant<T> {
    return createAssert(
        subject, AssertImpl.coreFactory.newMultiAtriumErrorAdjuster(
            firstAdjuster,
            secondAdjuster,
            otherAdjuster
        )
    )
}

private fun <T : Any> createAssert(subject: T, adjuster: AtriumErrorAdjuster) =
    AssertImpl.coreFactory.newReportingPlant(
        AssertionVerb.ASSERT, { subject },
        AssertImpl.coreFactory.newThrowingAssertionChecker(DelegatingReporter(reporter, adjuster))
    )
