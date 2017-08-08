package ch.tutteli.atrium.assertions.charsequence.contains.decorators

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.reporting.translating.ITranslatable

object CharSequenceContainsNoOpDecorator : CharSequenceContainsAssertionCreator.IDecorator {
    override fun decorateDescription(description: ITranslatable) = description
}
