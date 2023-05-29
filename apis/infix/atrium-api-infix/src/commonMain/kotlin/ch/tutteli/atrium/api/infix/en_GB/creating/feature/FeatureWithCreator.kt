package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import ch.tutteli.atrium.creating.ComponentFactoryContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
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
 * @property descriptionProvider Provides the description of the feature.
 * @property extractor The extractor which extracts the feature out of the subject of  the expectation.
 * @property assertionCreator The `assertionCreator`-lambda which defines assertions for the feature.
 *
 * @since 0.12.0
 */
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalComponentFactoryContainer::class)
data class FeatureWithCreator<T, R> internal constructor(
    val descriptionProvider: (ComponentFactoryContainer) -> String,
    val extractor: (T) -> R,
    val assertionCreator: Expect<R>.() -> Unit
) {
    internal constructor(description: String, extractor: (T) -> R, assertionCreator: Expect<R>.() -> Unit) :
        this({ description }, extractor, assertionCreator)
}
