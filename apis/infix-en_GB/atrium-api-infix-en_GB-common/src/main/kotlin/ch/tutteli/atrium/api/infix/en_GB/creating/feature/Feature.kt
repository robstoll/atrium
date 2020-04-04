package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import kotlin.reflect.KProperty1

/**
 * Parameter object which contains a [description] of a feature along with an [extractor]
 * which actually extracts the feature out of a subject of an assertion.
 *
 * Use `of(K..., ...) { ... }` to create this representation where the first argument is the extractor in form of a
 * [KProperty1] or a `KFunctionX` and the remaining arguments are the required arguments in case of a `KFunctionX`
 * where `X` > 1.
 *
 * @property description The description of the feature.
 * @property extractor The extractor which extracts the feature out of the subject of the assertion.

 * @since 0.11.0
 */
data class Feature<T, R> internal constructor(val description: String, val extractor: (T) -> R)
