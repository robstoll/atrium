package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator

val <T : CharSequence> CharSequenceContainsBuilder<T, CharSequenceContainsNoOpDecorator>.ignoringCase
    get() : CharSequenceContainsBuilder<T, CharSequenceContainsIgnoringCaseDecorator>
    = CharSequenceContainsBuilder(plant, CharSequenceContainsIgnoringCaseDecorator)
