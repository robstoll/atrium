package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerOption
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerOptionInternal
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerOptionLogic
import ch.tutteli.atrium.reporting.BUG_REPORT_URL

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [CharSequence].
 */
inline val <T : CharSequence, S : CharSequenceContains.SearchBehaviour>
    CharSequenceContains.Builder<T, S>._logic: CharSequenceContains.BuilderLogic<T, S>
    get() = when (this) {
        is CharSequenceContains.BuilderInternal<T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported CharSequenceContains.Builder: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20CharSequenceContains.Builder._logic")
    }

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [CharSequence].
 */
inline val <T : CharSequence, S : CharSequenceContains.SearchBehaviour>
    WithTimesCheckerOption<T, S>._logic: WithTimesCheckerOptionLogic<T, S>
    get() = when (this) {
        is WithTimesCheckerOptionInternal<T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported WithTimesCheckerOption: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20WithTimesCheckerOption._logic")
    }

inline fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour>
    CharSequenceContains.CheckerOption<T, S>._logicAppend(factory: CharSequenceContains.CheckerOptionLogic<T, S>.() -> Assertion): Expect<T> {
    val l = _logic
    return l.containsBuilder.container.toExpect().addAssertion(l.factory())
}

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [CharSequence].
 */
inline val <T : CharSequence, S : CharSequenceContains.SearchBehaviour>
    CharSequenceContains.CheckerOption<T, S>._logic: CharSequenceContains.CheckerOptionLogic<T, S>
    get() = when (this) {
        is CharSequenceContains.CheckerOptionInternal<T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported CharSequenceContains.CheckerOption: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20CharSequenceContains.CheckerOption._logic")
    }
