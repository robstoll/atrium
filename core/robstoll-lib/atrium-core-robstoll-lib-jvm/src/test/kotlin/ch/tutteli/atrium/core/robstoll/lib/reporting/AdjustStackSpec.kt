package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.ReportingAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.utils.subAssert
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.verbs.internal.AssertionVerb
import ch.tutteli.atrium.verbs.internal.assert
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
                property(subject::stackBacktrace).contains(
                    { startsWith("org.jetbrains.spek") },
                    { startsWith("org.junit") },
                    { startsWith("ch.tutteli.atrium") }
                )
            }
        }
    }

    fun mapStartsWith(list: List<String>): Pair<Assert<String>.() -> Unit, Array<out Assert<String>.() -> Unit>>{
        val asserts = list.map { c -> subAssert<String>{ startsWith(c) }}
        return asserts.first() to asserts.drop(1).toTypedArray()
    }

    mapOf(
        "remove test runner adjuster" to Triple(
            AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster(),
            listOf("org.jetbrains.spek", "org.junit"),
            listOf("ch.tutteli.atrium")
        ),
        "remove atrium adjuster" to Triple(
            AssertImpl.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster(),
            listOf("ch.tutteli.atrium"),
            listOf("org.jetbrains.spek", "org.junit")
        )
    ).forEach { (description, triple) ->
        val (adjuster, containsNot, contains) = triple
        val (containsNotFirst, containsNotRest) = mapStartsWith(containsNot)
        val (containsFirst, containsRest) = mapStartsWith(contains)
        describe(description) {
            it("does not contain $containsNot in stackBacktrace but $contains") {
                expect {
                    createAssert(1, adjuster).toBe(2)
                }.toThrow<AssertionError> {
                    property(subject::stackBacktrace)
                        .containsNot.entries(containsNotFirst, *containsNotRest)
                        .contains(containsFirst, *containsRest)
                }
            }


            it("does not contain $containsNot in stackBacktrace of cause, but $contains") {
                val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                adjuster.adjust(throwable)
                assert(throwable.cause!!.stackBacktrace)
                    .containsNot.entries(containsNotFirst, *containsNotRest)
                    .contains(containsFirst, *containsRest)
            }

            it("does not contain $containsNot in stackBacktrace of cause of cause, but $contains") {
                val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world", IllegalStateException("and good night")))
                adjuster.adjust(throwable)
                assert(throwable.cause!!.cause!!.stackBacktrace)
                    .containsNot.entries(containsNotFirst, *containsNotRest)
                    .contains(containsFirst, *containsRest)
            }

            it("does not contain $containsNot in stackBacktrace of suppressed exception, but $contains") {
                val throwable1 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable2 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable = IllegalStateException("with suppressed")
                throwable.addSuppressed(throwable1)
                throwable.addSuppressed(throwable2)
                adjuster.adjust(throwable)
                assert(throwable.suppressed).asIterable().all{
                    property(subject::stackBacktrace)
                        .containsNot.entries(containsNotFirst, *containsNotRest)
                        .contains(containsFirst, *containsRest)
                }
            }

            it("does not contain $containsNot in stackBacktrace of cause of suppressed exception, but $contains") {
                val throwable1 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable2 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable = IllegalStateException("with suppressed")
                throwable.addSuppressed(throwable1)
                throwable.addSuppressed(throwable2)
                adjuster.adjust(throwable)
                assert(throwable.suppressed).asIterable().all{
                    property(subject::cause).notToBeNull {
                        property(subject::stackBacktrace)
                            .containsNot.entries(containsNotFirst, *containsNotRest)
                            .contains(containsFirst, *containsRest)
                    }
                }
            }
        }
    }

    mapOf(
        "combine remove runner adjuster and remove atrium adjuster" to AssertImpl.coreFactory.newMultiAtriumErrorAdjuster(
            AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster(),
            AssertImpl.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster(),
            listOf()
        ),
        "combine remove atrium adjuster and remove runner adjuster" to AssertImpl.coreFactory.newMultiAtriumErrorAdjuster(
            AssertImpl.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster(),
            AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster(),
            listOf()
        ),
        "combine noop ajdust, remove atrium adjuster and remove runner adjuster" to AssertImpl.coreFactory.newMultiAtriumErrorAdjuster(
            AssertImpl.coreFactory.newNoOpAtriumErrorAdjuster(),
            AssertImpl.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster(),
            listOf(AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster())
        ),
        "combine remove atrium adjuster, remove runner adjuster and noop adjuster several times" to AssertImpl.coreFactory.newMultiAtriumErrorAdjuster(
            AssertImpl.coreFactory.newRemoveAtriumFromAtriumErrorAdjuster(),
            AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster(),
            listOf(
                AssertImpl.coreFactory.newNoOpAtriumErrorAdjuster(),
                AssertImpl.coreFactory.newRemoveRunnerAtriumErrorAdjuster(),
                AssertImpl.coreFactory.newNoOpAtriumErrorAdjuster()
            )
        )
    ).forEach { (description, adjuster) ->
        describe(description) {
            it("does neither contain atrium nor spek nor junit in stackBacktrace") {
                expect {
                    createAssert(1, adjuster).toBe(2)
                }.toThrow<AssertionError> {
                    property(subject::stackBacktrace).isEmpty()
                }
            }

            it("does neither contain atrium nor spek nor junit in stackBacktrace of cause") {
                val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                adjuster.adjust(throwable)
                assert(throwable.cause!!.stackBacktrace).isEmpty()
            }

            it("does neither contain atrium nor spek nor junit in stackBacktrace of suppressed") {
                val throwable1 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable2 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable = IllegalStateException("with suppressed")
                throwable.addSuppressed(throwable1)
                throwable.addSuppressed(throwable2)
                adjuster.adjust(throwable)
                assert(throwable.suppressed).asIterable().all{
                    property(subject::stackBacktrace).isEmpty()
                }
            }
        }
    }
})

private fun <T : Any> createAssert(subject: T, adjuster: AtriumErrorAdjuster) =
    AssertImpl.coreFactory.newReportingPlant(
        AssertionVerb.ASSERT, { subject },
        AssertImpl.coreFactory.newThrowingAssertionChecker(DelegatingReporter(reporter, adjuster))
    )
