@file:Suppress("ObjectPropertyName", "FunctionName")
package ch.tutteli.atrium

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.WithTimesCheckerStep
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.WithTimesCheckerStepCore
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.WithTimesCheckerStepInternal
import ch.tutteli.atrium.reporting.BUG_REPORT_URL

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `toContain` expectation for [CharSequence].
 *
 * @since 1.3.0
 */
inline val <T : CharSequence, S : CharSequenceToContain.SearchBehaviour>
    CharSequenceToContain.EntryPointStep<T, S>._core: CharSequenceToContain.EntryPointStepCore<T, S>
    get() = when (this) {
        is CharSequenceToContain.EntryPointStepInternal<T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported CharSequenceToContain.Builder: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20CharSequenceToContain.EntryPointStep._logic")
    }

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `toContain` expectation for [CharSequence].
 *
 * @since 1.3.0
 */
inline val <T : CharSequence, S : CharSequenceToContain.SearchBehaviour>
    WithTimesCheckerStep<T, S>._core: WithTimesCheckerStepCore<T, S>
    get() = when (this) {
        is WithTimesCheckerStepInternal<T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported WithTimesCheckerStep: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20CharSequenceToContain%20WithTimesCheckerStep._logic")
    }

/**
 * Appends the [Proof] the given [factory] creates based on this [CharSequenceToContain.CheckerStep].
 *
 * Use [_core] for more sophisticated scenarios.
 *
 * @since 1.3.0
 */
inline fun <T : CharSequence, S : CharSequenceToContain.SearchBehaviour>
    CharSequenceToContain.CheckerStep<T, S>._coreAppend(
    factory: CharSequenceToContain.CheckerStepCore<T, S>.() -> Proof
): Expect<T> = _core.let { l -> l.entryPointStepCore.container.append(l.factory()) }

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `toContain` expectation for [CharSequence].
 *
 * @since 1.3.0
 */
inline val <T : CharSequence, S : CharSequenceToContain.SearchBehaviour>
    CharSequenceToContain.CheckerStep<T, S>._core: CharSequenceToContain.CheckerStepCore<T, S>
    get() = when (this) {
        is CharSequenceToContain.CheckerStepInternal<T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported CharSequenceToContain.CheckerStep: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20CharSequenceToContain.CheckerStep._logic")
    }
