package ch.tutteli.atrium.assertions.iterable.contains.decorators

import ch.tutteli.atrium.assertions.iterable.contains.IterableContainsAssertionCreator
import ch.tutteli.atrium.reporting.translating.ITranslatable

object IterableContainsNoOpDecorator : IterableContainsAssertionCreator.IDecorator {
    override fun decorateDescription(description: ITranslatable) = description
}
