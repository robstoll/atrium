package ch.tutteli.atrium.creating.proofs.feature

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.InlineElement

/**
 * Represents an extracted feature of type [T] defined by the given [maybeSubject] including a [description]
 * and a [representation]
 *
 * @property description Will be used in reporting to describe the feature extraction - e.g. the name of a property,
 *   a method call etc.
 * @property representation The representation of the feature, in most cases the value behind the feature.
 * @property maybeSubject The feature as such where it is [ch.tutteli.atrium.core.Some] in case the extraction was successful or [ch.tutteli.atrium.core.None] if it
 *   was not.
 */
data class MetaFeature<T>(
    val description: InlineElement,
    val representation: Any?,
    val maybeSubject: Option<T>
) {

    constructor(description: String, representation: Any?, maybeSubject: Option<T>) :
        this(Text.Companion(description), representation, maybeSubject)

    constructor(description: String, subject: T) : this(description, subject, Some(subject))
}
