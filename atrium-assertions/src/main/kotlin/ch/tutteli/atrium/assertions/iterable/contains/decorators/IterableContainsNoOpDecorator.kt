package ch.tutteli.atrium.assertions.iterable.contains.decorators

import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.reporting.translating.ITranslatable

object IterableContainsNoOpDecorator : IIterableContains.IDecorator {
    override fun decorateDescription(description: ITranslatable) = description
}
