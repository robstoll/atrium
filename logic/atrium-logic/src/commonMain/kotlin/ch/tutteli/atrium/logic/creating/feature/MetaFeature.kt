package ch.tutteli.atrium.logic.creating.feature

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some

/**
 * Represents an extracted feature of type [T] defined by the given [maybeSubject] including a [description]
 * and a [representation]
 *
 * @property description Will be used in reporting to describe the feature extraction - e.g. the name of a property,
 *   a method call etc.
 * @property representation The representation of the feature, in most cases the value behind the feature.
 * @property maybeSubject The feature as such where it is [Some] in case the extraction was successful or [None] if it
 *   was not.
 */
data class MetaFeature<T>(
    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    val description: ch.tutteli.atrium.reporting.translating.Translatable,
    val representation: Any?,
    val maybeSubject: Option<T>
) {
    //TODO 1.3.0 remove suppress again, use InlineElement instead
    @Suppress("DEPRECATION")
    constructor(description: String, representation: Any?, maybeSubject: Option<T>) :
        this(ch.tutteli.atrium.reporting.translating.Untranslatable(description), representation, maybeSubject)

    constructor(description: String, subject: T) : this(description, subject, Some(subject))
}
