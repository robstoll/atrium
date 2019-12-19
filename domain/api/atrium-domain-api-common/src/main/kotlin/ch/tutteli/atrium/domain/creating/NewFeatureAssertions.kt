@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0 */)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * The access point to an implementation of [FeatureAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 *
 * Will be renamed to featureAssertions with 1.0.0
 */
@Deprecated("Introduce in 0.9.0 because we still have the domain-api separate from the implementation module wise. This will change in 0.10.0 where this interface will be removed again. You should therefore go through `_domain` instead.")
val newFeatureAssertions by lazy { loadSingleService(NewFeatureAssertions::class) }

/**
 * Defines the minimum set of assertion functions -- used to create feature assertions --
 * which an implementation of the domain of Atrium has to provide.
 *
 * Will be renamed to FeatureAssertions with 1.0.0
 */
@Deprecated("Introduce in 0.9.0 because we still have the domain-api separate from the implementation module wise. This will change in 0.10.0 where this interface will be removed again. You should therefore go through `_domain` instead.")
interface NewFeatureAssertions {

    /**
     * Extracts a feature from [assertionContainer] based on the given [MetaFeature] and creates a
     * [ExtractedFeaturePostStep] based on it.
     *
     * @return The newly created [ExtractedFeaturePostStep].
     */
    @Deprecated("Introduce in 0.9.0 because we still have the domain-api separate from the implementation module wise. This will change in 0.10.0 where this interface will be removed again. You should therefore go through `_domain` instead.")
    fun <T, R> genericFeature(
        assertionContainer: Expect<T>,
        metaFeature: MetaFeature<R>
    ): ExtractedFeaturePostStep<T, R>
}

/**
 * Represents an extracted feature of type [T] defined by the given [maybeSubject] including a [description]
 * and a [representation].
 *
 * @property description Will be used in reporting to describe the feature extraction - e.g. the name of a property,
 *   a method call etc.
 * @property representation The representation of the feature, in most cases the value behind the feature.
 * @property maybeSubject The feature as such where it is [Some] in case the extraction was successful or [None] if it
 *   was not.
 */
data class MetaFeature<T>(val description: Translatable, val representation: Any?, val maybeSubject: Option<T>) {
    constructor(description: String, representation: Any?, maybeSubject: Option<T>) :
        this(Untranslatable(description), representation, maybeSubject)

    constructor(description: String, subject: T) : this(description, subject, Some(subject))
}
