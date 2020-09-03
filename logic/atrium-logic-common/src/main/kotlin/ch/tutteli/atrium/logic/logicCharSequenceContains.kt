@file:Suppress("ObjectPropertyName", "FunctionName")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerStepInternal
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerStepLogic
import ch.tutteli.atrium.reporting.BUG_REPORT_URL

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [CharSequence].
 */
inline val <T : CharSequence, S : CharSequenceContains.SearchBehaviour>
    CharSequenceContains.EntryPointStep<T, S>._logic: CharSequenceContains.EntryPointStepLogic<T, S>
    get() = when (this) {
        is CharSequenceContains.EntryPointStepInternal<T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported CharSequenceContains.Builder: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20CharSequenceContains.EntryPointStep._logic")
    }

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [CharSequence].
 */
inline val <T : CharSequence, S : CharSequenceContains.SearchBehaviour>
    WithTimesCheckerStep<T, S>._logic: WithTimesCheckerStepLogic<T, S>
    get() = when (this) {
        is WithTimesCheckerStepInternal<T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported WithTimesCheckerStep: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20WithTimesCheckerStep._logic")
    }

inline fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour>
    CharSequenceContains.CheckerStep<T, S>._logicAppend(factory: CharSequenceContains.CheckerStepLogic<T, S>.() -> Assertion): Expect<T> {
    val l = _logic
    return l.entryPointStepLogic.container.toExpect().addAssertion(l.factory())
}

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [CharSequence].
 */
inline val <T : CharSequence, S : CharSequenceContains.SearchBehaviour>
    CharSequenceContains.CheckerStep<T, S>._logic: CharSequenceContains.CheckerStepLogic<T, S>
    get() = when (this) {
        is CharSequenceContains.CheckerStepInternal<T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported CharSequenceContains.CheckerStep: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20CharSequenceContains.CheckerStep._logic")
    }
