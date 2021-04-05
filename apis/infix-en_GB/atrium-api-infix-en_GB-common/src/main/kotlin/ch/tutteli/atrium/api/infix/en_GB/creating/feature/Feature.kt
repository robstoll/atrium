package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import ch.tutteli.atrium.creating.ComponentFactoryContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import kotlin.reflect.KProperty1

/**
 * Parameter object which contains a [description] of a feature along with an [extractor]
 * which actually extracts the feature out of a subject of an assertion.
 *
 * Use `of(K..., ...) { ... }` to create this representation where the first argument is the extractor in form of a
 * [KProperty1] or a `KFunctionX` and the remaining arguments are the required arguments in case of a `KFunctionX`
 * where `X` > 1.
 *
 * @property descriptionProvider Provides the description of the feature.
 * @property extractor The extractor which extracts the feature out of the subject of the expectation.
 *
 * @since 0.12.0
 */
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalComponentFactoryContainer::class)
data class Feature<T, R> internal constructor(
    val descriptionProvider: (ComponentFactoryContainer) -> String,
    val extractor: (T) -> R
) {
    internal constructor(description: String, extractor: (T) -> R) :
        this({ description }, extractor)
}
