package ch.tutteli.atrium.assertions.charsequence.contains.decorators

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

object CharSequenceContainsIgnoringCaseDecorator : CharSequenceContainsAssertionCreator.IDecorator {

    override fun decorateDescription(description: ITranslatable): ITranslatable
        = TranslatableWithArgs(DescriptionCharSequenceAssertion.IGNORING_CASE, description)
}
