package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator

/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> CharSequenceContainsBuilder<T, CharSequenceContainsNoOpDecorator>.ignoringCase
    get() : CharSequenceContainsBuilder<T, CharSequenceContainsIgnoringCaseDecorator>
    = CharSequenceContainsBuilder(plant, CharSequenceContainsIgnoringCaseDecorator)
