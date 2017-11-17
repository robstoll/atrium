package ch.tutteli.atrium.assertions.charsequence.contains.decorators

import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains
import ch.tutteli.atrium.assertions.charsequence.contains.creators.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents a no operation decoration behaviour which means an [CharSequenceContainsAssertionCreator.ISearcher]
 * should not apply any decoration and search exactly for an expected object.
 */
object CharSequenceContainsNoOpDecorator : ICharSequenceContains.IDecorator {
    override fun decorateDescription(description: ITranslatable) = description
}
