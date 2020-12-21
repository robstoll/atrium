@file:Suppress("ObjectPropertyName", "FunctionName")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.reporting.BUG_REPORT_URL

/**
 * Appends the [Assertion] the given [factory] creates based on this [MapLikeContains.EntryPointStep].
 *
 * Use [_logic] for more sophisticated scenarios.
 */
inline fun <K, V, T : Any, S : MapLikeContains.SearchBehaviour>
    MapLikeContains.EntryPointStep<K, V, T, S>._logicAppend(
    factory: MapLikeContains.EntryPointStepLogic<K, V, T, S>.() -> Assertion
): Expect<T> = _logic.let { l -> l.container.toExpect().addAssertion(l.factory()) }

/**
 * Entry point to the logic level of Atrium -- which is one level deeper than the API --
 * within the building process of a sophisticated `contains` assertion for [Iterable].
 */
inline val <K, V, T : Any, S : MapLikeContains.SearchBehaviour>
    MapLikeContains.EntryPointStep<K, V, T, S>._logic: MapLikeContains.EntryPointStepLogic<K, V, T, S>
    get() = when (this) {
        is MapLikeContains.EntryPointStepInternal<K, V, T, S> -> this
        else -> throw UnsupportedOperationException("Unsupported MapLikeContains.Builder: $this -- please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20MapLikeContains.EntryPointStep._logic")
    }
