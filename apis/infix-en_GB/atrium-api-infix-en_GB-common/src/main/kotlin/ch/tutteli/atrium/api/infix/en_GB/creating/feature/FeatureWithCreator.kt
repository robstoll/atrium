package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KProperty1

/**
 * Parameter object which contains a [description] of a feature along with an [extractor]
 * which actually extracts the feature out of a subject of an assertion + an [assertionCreator]
 * which defines assertions for the feature.
 *
 * Use `of(K..., ...) { ... }` to create this representation where the first argument is the extractor in form of a
 * [KProperty1] or a `KFunctionX`, the last an [assertionCreator]-lambda and the remaining arguments in-between the
 * required arguments in case of a `KFunctionX` where `X` > 1.
 *
 * @property description The description of the feature.
 * @property extractor The extractor which extracts the feature out of the subject of the assertion.
 * @property assertionCreator The `assertionCreator`-lambda which defines assertions for the feature.
 *
 * @since 0.11.0
 */
data class FeatureWithCreator<T, R>(
    val description: String,
    val extractor: (T) -> R,
    val assertionCreator: Expect<R>.() -> Unit
)
