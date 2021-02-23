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
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.reporter
import kotlin.test.Test

class AdjustStackTest {

    @Test
    fun noOp_containsMochaAndAtrium() {
        expect {
            assertNoOp(1).toBe(2)
        }.toThrow<AssertionError> {
            feature(AssertionError::stackBacktrace) contains entries(
                { contains("mocha") },
                { contains("atrium-core-api-js.js") }
            )
        }
    }

    @Test
    fun removeRunner_containsAtriumButNotMocha() {
        expect {
            assertRemoveRunner(1).toBe(2)
        }.toThrow<AssertionError> {
            it feature of(AssertionError::stackBacktrace) {
                it containsNot o entry { it contains "mocha" }
                it contains { it contains "atrium-core-api-js.js" }
            }
        }
    }

    @ExperimentalComponentFactoryContainer
    @Test
    fun removeRunner_containsAtriumButNotMochaInCause() {
        val adjuster = assertRemoveRunner(1)._logic.components.build<AtriumErrorAdjuster>()
        expect(adjuster).isA<RemoveRunnerFromAtriumError>()
        val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world"))
        adjuster.adjust(throwable)
        expect(throwable.cause!!.stackBacktrace) {
            it containsNot o entry { it contains "mocha" }
            it contains { it contains "atrium-core-api-js" }
        }
    }

    @Test
    fun removeAtrium_containsMochaButNotAtrium() {
        expect {
            assertRemoveAtrium(1).toBe(2)
        }.toThrow<AssertionError> {
            it feature of(AssertionError::stackBacktrace) {
                it contains { it contains "mocha" }
                it containsNot o entry { it contains "atrium-core-api-js.js" }
            }
        }
    }

    @ExperimentalComponentFactoryContainer
    @Test
    fun removeAtrium_containsMochaButNotAtriumInCause() {
        val adjuster = assertRemoveAtrium(1)._logic.components.build<AtriumErrorAdjuster>()
        expect(adjuster).isA<RemoveAtriumFromAtriumError>()
        val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world"))
        adjuster.adjust(throwable)
        expect(throwable.cause!!.stackBacktrace) {
            it contains { it contains "mocha" }
            it containsNot o entry { it contains "atrium-core-api-js" }
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
