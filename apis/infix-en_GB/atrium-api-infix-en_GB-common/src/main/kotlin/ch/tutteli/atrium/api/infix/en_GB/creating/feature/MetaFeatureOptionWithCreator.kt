package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.MetaFeatureOption
import ch.tutteli.atrium.domain.creating.MetaFeature

/**
 * Parameter object which combines a lambda with a [MetaFeatureOption] receiver (called [provider])
 * and an [assertionCreator].
 *
 * Use the function `of({ ... }) { ... }` to create this representation where the first
 * argument is a lambda with a [MetaFeatureOption] as receiver which has to create a [MetaFeature]
 * where the subject of the assertion is available via implicit parameter `it`.
 * Usually you use [f][MetaFeatureOption.f] to create a [MetaFeature],
 * e.g. `feature of({ f(it::size) }) { o toBe 3 }`
 *
 * @since 0.11.0
 */
data class MetaFeatureOptionWithCreator<T, R> internal constructor(
    val provider: MetaFeatureOption<T>.(T) -> MetaFeature<R>,
    val assertionCreator: Expect<R>.() -> Unit
)
