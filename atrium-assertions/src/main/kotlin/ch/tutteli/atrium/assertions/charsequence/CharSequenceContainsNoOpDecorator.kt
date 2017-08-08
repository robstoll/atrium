package ch.tutteli.atrium.assertions.charsequence

import ch.tutteli.atrium.reporting.translating.ITranslatable

object CharSequenceContainsNoOpDecorator : CharSequenceContainsAssertionCreator.IDecorator {
    override fun decorateDescription(description: ITranslatable) = description
}
