package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.AssertionVerb
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.creating.ComponentFactoryContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.logic.utils.expectLambda
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@ExperimentalNewExpectTypes
@ExperimentalComponentFactoryContainer
class AdjustStackSpec : Spek({

    describe("no-op adjuster") {
        fun <T : Any> assertNoOp(subject: T) =
            createExpect(subject) { _ -> NoOpAtriumErrorAdjuster }

        it("contains spek, junit, atrium.creating and atrium.reporting") {
            expect {
                assertNoOp(1) toEqual 2
            }.toThrow<AssertionError> {
                feature { f(it::stackBacktrace) } toContain entries(
                    { it toStartWith "org.spekframework.spek2" },
                    { it toStartWith "ch.tutteli.atrium.creating" },
                    { it toStartWith "ch.tutteli.atrium.reporting" }
                )
            }
        }
    }

    fun mapStartsWith(list: List<String>): Pair<Expect<String>.() -> Unit, Array<out Expect<String>.() -> Unit>> {
        val asserts = list.map { c -> expectLambda<String> { toStartWith(c) } }
        return asserts.first() to asserts.drop(1).toTypedArray()
    }

    mapOf<String, Triple<(ComponentFactoryContainer) -> AtriumErrorAdjuster, List<String>, List<String>>>(
        "remove test runner adjuster" to Triple(
            { c -> c.build<RemoveRunnerFromAtriumError>() },
            listOf("org.spekframework.spek2", "kotlin.coroutines", "kotlinx.coroutines"),
            listOf("ch.tutteli.atrium")
        ),
        "remove atrium adjuster" to Triple(
            { c -> c.build<RemoveAtriumFromAtriumError>() },
            listOf("ch.tutteli.atrium"),
            listOf("org.spekframework.spek2")
        )
    ).forEach { (description, triple) ->
        val (factory, containsNot, contains) = triple
        val (containsNotFirst, containsNotRest) = mapStartsWith(containsNot)
        val (containsFirst, containsRest) = mapStartsWith(contains)
        describe(description) {
            it("does not contain $containsNot in stackBacktrace but $contains") {
                expect {
                    createExpect(1, factory) toEqual 2
                }.toThrow<AssertionError> {
                    feature { f(it::stackBacktrace) } and {
                        it notToContain o the entries(containsNotFirst, *containsNotRest)
                        it toContain entries(containsFirst, *containsRest)
                    }
                }
            }


            it("does not contain $containsNot in stackBacktrace of cause, but $contains") {
                val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val adjuster = createExpect(1, factory)._logic.components.build<AtriumErrorAdjuster>()
                adjuster.adjust(throwable)
                expect(throwable.cause!!.stackBacktrace) {
                    it notToContain o the entries(containsNotFirst, *containsNotRest)
                    it toContain entries(containsFirst, *containsRest)
                }
            }

            it("does not contain $containsNot in stackBacktrace of cause of cause, but $contains") {
                val throwable = IllegalArgumentException(
                    "hello",
                    UnsupportedOperationException("world", IllegalStateException("and good night"))
                )
                val adjuster = createExpect(1, factory)._logic.components.build<AtriumErrorAdjuster>()
                adjuster.adjust(throwable)
                expect(throwable.cause!!.cause!!.stackBacktrace) {
                    it notToContain o the entries(containsNotFirst, *containsNotRest)
                    it toContain entries(containsFirst, *containsRest)
                }
            }

            it("does not contain $containsNot in stackBacktrace of suppressed exception, but $contains") {
                val throwable1 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable2 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable = IllegalStateException("with suppressed")
                throwable.addSuppressed(throwable1)
                throwable.addSuppressed(throwable2)
                val adjuster = createExpect(1, factory)._logic.components.build<AtriumErrorAdjuster>()
                adjuster.adjust(throwable)
                (expect(throwable.suppressed) asList o).toHaveElementsAndAll(fun Expect<Throwable>.() {
                    feature { f(it::stackBacktrace) } and {
                        it notToContain o the entries(containsNotFirst, *containsNotRest)
                        it toContain entries(containsFirst, *containsRest)
                    }
                })
            }

            it("does not contain $containsNot in stackBacktrace of cause of suppressed exception, but $contains") {
                val throwable1 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable2 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable = IllegalStateException("with suppressed")
                throwable.addSuppressed(throwable1)
                throwable.addSuppressed(throwable2)
                val adjuster = createExpect(1, factory)._logic.components.build<AtriumErrorAdjuster>()
                adjuster.adjust(throwable)
                (expect(throwable.suppressed) asList o).toHaveElementsAndAll(fun Expect<Throwable>.() {
                    cause<UnsupportedOperationException> {
                        feature { f(it::stackBacktrace) } and {
                            it notToContain o the entries(containsNotFirst, *containsNotRest)
                            it toContain entries(containsFirst, *containsRest)
                        }
                    }
                })
            }
        }
    }

    mapOf<String, (ComponentFactoryContainer) -> AtriumErrorAdjuster>(
        "combine remove runner adjuster and remove atrium adjuster" to
            { c ->
                MultiAtriumErrorAdjuster(
                    c.build<RemoveRunnerFromAtriumError>(),
                    c.build<RemoveAtriumFromAtriumError>(),
                    listOf()
                )
            },
        "combine remove atrium adjuster and remove runner adjuster" to
            { c ->
                MultiAtriumErrorAdjuster(
                    c.build<RemoveAtriumFromAtriumError>(),
                    c.build<RemoveRunnerFromAtriumError>(),
                    listOf()
                )
            },
        "combine noop adjust, remove atrium adjuster and remove runner adjuster" to
            { c ->
                MultiAtriumErrorAdjuster(
                    NoOpAtriumErrorAdjuster,
                    c.build<RemoveAtriumFromAtriumError>(),
                    listOf(c.build<RemoveRunnerFromAtriumError>())
                )
            },
        "combine remove atrium adjuster, remove runner adjuster and noop adjuster several times" to
            { c ->
                MultiAtriumErrorAdjuster(
                    c.build<RemoveAtriumFromAtriumError>(),
                    c.build<RemoveRunnerFromAtriumError>(),
                    listOf(
                        NoOpAtriumErrorAdjuster,
                        c.build<RemoveRunnerFromAtriumError>(),
                        NoOpAtriumErrorAdjuster
                    )
                )
            }
    ).forEach { (description, factory) ->
        describe(description) {
            it("stackBacktrace is empty as we filter out everything") {
                expect {
                    createExpect(1, factory) toEqual 2
                }.toThrow<AssertionError> {
                    it feature { f(it::stackBacktrace) } toBe empty
                }
            }

            it("stackBacktrace of cause is empty as we filter out everything") {
                val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val adjuster = createExpect(1, factory)._logic.components.build<AtriumErrorAdjuster>()
                adjuster.adjust(throwable)
                expect(throwable.cause!!.stackBacktrace) toBe empty
            }

            it("stackBacktrace of suppressed is empty as we filter out everything") {
                val throwable1 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable2 = IllegalArgumentException("hello", UnsupportedOperationException("world"))
                val throwable = IllegalStateException("with suppressed")
                throwable.addSuppressed(throwable1)
                throwable.addSuppressed(throwable2)
                val adjuster = createExpect(1, factory)._logic.components.build<AtriumErrorAdjuster>()
                adjuster.adjust(throwable)
                (expect(throwable.suppressed) asList o).toHaveElementsAndAll(fun Expect<Throwable>.() {
                    it feature { f(it::stackBacktrace) } toBe empty
                })
            }
        }
    }
})

@ExperimentalNewExpectTypes
@ExperimentalComponentFactoryContainer
private fun <T : Any> createExpect(subject: T, factory: (ComponentFactoryContainer) -> AtriumErrorAdjuster) =
    RootExpectBuilder.forSubject(subject)
        .withVerb(AssertionVerb.EXPECT)
        .withOptions {
            withComponent(AtriumErrorAdjuster::class, factory)
        }
        .build()
