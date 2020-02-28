package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.MetaFeatureOption
import ch.tutteli.atrium.domain.builders.utils.VarArgHelper
import ch.tutteli.atrium.domain.creating.MetaFeature
import kotlin.reflect.KProperty1

/**
 * Parameter object to express `T, vararg T`.
 */
class All<out T>(override val expected: T, override vararg val otherExpected: T) : VarArgHelper<T>


/**
 * Parameter object which contains a [description] of a feature along with an [extractor]
 * which actually extracts the feature out of a subject of an assertion.
 *
 * @property description The description of the feature.
 * @property extractor The extractor which extracts the feature out of the subject of the assertion.

 * @since 0.10.0
 */
data class Feature<T, R>(val description: String, val extractor: (T) -> R)

/**
 * Parameter object which contains a [description] of a feature along with an [extractor]
 * which actually extracts the feature out of a subject of an assertion + an [assertionCreator]
 * which defines assertions for the feature.
 *
 * Use `of(K...) { ... }` to create this representation where the first argument is the extractor in form of a
 * [KProperty1] or a `KFunctionX`
 *
 * @property description The description of the feature.
 * @property extractor The extractor which extracts the feature out of the subject of the assertion.
 * @property assertionCreator The `assertionCreator`-lambda which defines assertions for the feature.
 *
 * @since 0.10.0
 */
data class FeatureWithCreator<T, R>(
    val description: String,
    val extractor: (T) -> R,
    val assertionCreator: Expect<R>.() -> Unit
)


/**
 * Wrapper for a single index -- can be used as distinguishable type for an overload where Int is already in use.
 */
data class Index(val index: Int)

data class Key<out K>(val key: K)

/**
 * Parameter object to express a key/value [Pair] whose value type is a lambda with an
 * [Expect] receiver, which means one can either pass a lambda or `null`.
 */
data class KeyValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Expect<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Expect<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull
    override fun toString(): String =
        "KeyValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}

/**
 * Parameter object which combines a lambda with a [MetaFeatureOption] receiver (called [option])
 * and an [assertionCreator].
 *
 * Use the function `of({ ... }) { ... }` to create this representation where the first
 * argument is a lambda with a [MetaFeatureOption] as receiver which has to create a [MetaFeature]
 * where the subject of the assertion is available via implicit parameter `it`.
 * Usually you use [f][MetaFeatureOption.f] to create a [MetaFeature],
 * e.g. `feature of({ f(it::size) }) { o toBe 3 }`
 *
 * @since 0.10.0
 */
data class MetaFeatureOptionWithCreator<T, R>(
    val option: MetaFeatureOption<T>.(T) -> MetaFeature<R>,
    val assertionCreator: Expect<R>.() -> Unit
)

/**
 * Parameter object to express `Pair<K, V>, vararg Pair<K, V>`.
 */
class Pairs<out K, out V>(
    override val expected: Pair<K, V>,
    override vararg val otherExpected: Pair<K, V>
) : VarArgHelper<Pair<K, V>>
