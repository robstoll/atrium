package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.AssertionVerb
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.creating.ComponentFactoryContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import kotlin.test.Test

class AdjustStackTest {

    @Test
    fun noOp_containsMochaAndAtrium() {
        expect {
            assertNoOp(1) toEqual 2
        }.toThrow<AssertionError> {
            feature(AssertionError::stackBacktrace) contains entries(
                { toContain("mocha") },
                { toContain("atrium-core-js.js") }
            )
        }
    }

    @Test
    fun removeRunner_containsAtriumButNotMocha() {
        expect {
            assertRemoveRunner(1) toEqual 2
        }.toThrow<AssertionError> {
            it feature of(AssertionError::stackBacktrace) {
                it containsNot o entry { it toContain "mocha" }
                it contains { it toContain "atrium-core-js.js" }
            }
        }
    }

    @ExperimentalComponentFactoryContainer
    @Test
    fun removeRunner_containsAtriumButNotMochaInCause() {
        val adjuster = assertRemoveRunner(1)._logic.components.build<AtriumErrorAdjuster>()
        expect(adjuster).toBeAnInstanceOf<RemoveRunnerFromAtriumError>()
        val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world"))
        adjuster.adjust(throwable)
        expect(throwable.cause!!.stackBacktrace) {
            it containsNot o entry { it toContain "mocha" }
            it contains { it toContain "atrium-core-js" }
        }
    }

    @Test
    fun removeAtrium_containsMochaButNotAtrium() {
        expect {
            assertRemoveAtrium(1) toEqual 2
        }.toThrow<AssertionError> {
            it feature of(AssertionError::stackBacktrace) {
                it contains { it toContain "mocha" }
                it containsNot o entry { it toContain "atrium-core-js.js" }
            }
        }
    }

    @ExperimentalComponentFactoryContainer
    @Test
    fun removeAtrium_containsMochaButNotAtriumInCause() {
        val adjuster = assertRemoveAtrium(1)._logic.components.build<AtriumErrorAdjuster>()
        expect(adjuster).toBeAnInstanceOf<RemoveAtriumFromAtriumError>()
        val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world"))
        adjuster.adjust(throwable)
        expect(throwable.cause!!.stackBacktrace) {
            it contains { it toContain "mocha" }
            it containsNot o entry { it toContain "atrium-core-js" }
        }
    }

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    private fun <T : Any> assertNoOp(subject: T) = createExpect(subject) { _ -> NoOpAtriumErrorAdjuster }

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    private fun <T : Any> assertRemoveRunner(subject: T) =
        createExpect(subject) { c -> c.build<RemoveRunnerFromAtriumError>() }

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    private fun <T : Any> assertRemoveAtrium(subject: T) =
        createExpect(subject) { c -> c.build<RemoveAtriumFromAtriumError>() }


    @ExperimentalNewExpectTypes
    @ExperimentalComponentFactoryContainer
    private fun <T : Any> createExpect(subject: T, factory: (ComponentFactoryContainer) -> AtriumErrorAdjuster) =
        RootExpectBuilder.forSubject(subject)
            .withVerb(AssertionVerb.EXPECT)
            .withOptions {
                withComponent(AtriumErrorAdjuster::class, factory)
            }
            .build()
}
