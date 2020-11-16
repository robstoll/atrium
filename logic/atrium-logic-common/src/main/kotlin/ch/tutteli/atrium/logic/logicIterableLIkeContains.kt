@file:Suppress("ObjectPropertyName", "FunctionName")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.WithTimesCheckerStep
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.WithTimesCheckerStepInternal
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.WithTimesCheckerStepLogic
//import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.WithTimesCheckerStep
//import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.WithTimesCheckerStepInternal
//import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.WithTimesCheckerStepLogic
import ch.tutteli.atrium.reporting.BUG_REPORT_URL

/**
 * Appends the [Assertion] the given [factory] creates based on this [IterableLikeContains.EntryPointStep].
 *
 * Use [_logic] for more sophisticated scenarios.
 */
inline fun <E, T : Any, S : IterableLikeContains.SearchBehaviour>
    IterableLikeContains.EntryPointStep<E, T, S>._logicAppend(factory: IterableLikeContains.EntryPointStepLogic<E, T, S>.() -> Assertion): Expect<T> {
    val l = _logic
    return l.container.toExpect().addAssertion(l.factory())
}

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [Iterable].
 */
inline val <E, T : Any, S : IterableLikeContains.SearchBehaviour>
    IterableLikeContains.EntryPointStep<E, T, S>._logic: IterableLikeContains.EntryPointStepLogic<E, T, S>
    get() = when (this) {
        is IterableLikeContains.EntryPointStepInternal<E, T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported IterableLikeContains.Builder: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20IterableLikeContains.EntryPointStep._logic")
    }

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [Iterable].
 *
 * Use [_logicAppend] in case you want to create and append an [Assertion] to the initial [Expect].
 */
inline val <E, T : Any, S : IterableLikeContains.SearchBehaviour>
    WithTimesCheckerStep<E, T, S>._logic: WithTimesCheckerStepLogic<E, T, S>
    get() = when (this) {
        is WithTimesCheckerStepInternal<E, T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported WithTimesCheckerStep: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20IterableLikeContains%20WithTimesCheckerStep._logic")
    }

/**
 * Appends the [Assertion] the given [factory] creates based on this [IterableLikeContains.CheckerStep].
 *
 * Use [_logic] for more sophisticated scenarios.
 */
inline fun <E, T : Any, S : IterableLikeContains.SearchBehaviour>
    IterableLikeContains.CheckerStep<E, T, S>._logicAppend(factory: IterableLikeContains.CheckerStepLogic<E, T, S>.() -> Assertion): Expect<T> {
    val l = _logic
    return l.entryPointStepLogic.container.toExpect().addAssertion(l.factory())
}

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [Iterable].
 *
 * Use [_logicAppend] in case you want to create and append an [Assertion] to the initial [Expect].
 */
inline val <E, T : Any, S : IterableLikeContains.SearchBehaviour>
    IterableLikeContains.CheckerStep<E, T, S>._logic: IterableLikeContains.CheckerStepLogic<E, T, S>
    get() = when (this) {
        is IterableLikeContains.CheckerStepInternal<E, T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported IterableLikeContains.CheckerStep: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20IterableLikeContains.CheckerStep._logic")
    }
