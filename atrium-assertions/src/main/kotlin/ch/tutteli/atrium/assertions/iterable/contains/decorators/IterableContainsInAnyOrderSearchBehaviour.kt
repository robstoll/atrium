package ch.tutteli.atrium.assertions.iterable.contains.decorators

import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

object IterableContainsInAnyOrderSearchBehaviour : IIterableContains.ISearchBehaviour {
    override fun decorateDescription(description: ITranslatable): ITranslatable
        = TranslatableWithArgs(DescriptionIterableAssertion.IN_ANY_ORDER, description)
}
