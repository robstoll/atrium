package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import ch.tutteli.atrium.creating.Expect
import kotlin.reflect.KProperty1

/**
 * Parameter object which contains an [extractor] which extracts a feature out of a subject of an assertion +
 * an [assertionCreator] which defines assertions for the feature.
 *
 * Use `feature({ extractorLambda }) { ... }` to create this representation where the first argument is the extractor
 * and the second an [assertionCreator]-lambda.
 *
 * @property extractor The extractor which extracts the feature out of the subject of  the expectation.
 * @property assertionCreator The `assertionCreator`-lambda which defines assertions for the feature.
 *
 * @since 0.16.0
 */
//TODO 2.0.0 remove data?
data class ExtractorWithCreator<T, R> (
    val extractor: (T) -> R,
    val assertionCreator: Expect<R>.() -> Unit
)
