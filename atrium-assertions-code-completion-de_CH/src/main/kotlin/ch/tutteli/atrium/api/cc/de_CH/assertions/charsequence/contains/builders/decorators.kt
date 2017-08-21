package ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator

val <T : CharSequence> CharSequenceContainsBuilder<T, CharSequenceContainsNoOpDecorator>.ignoriereGrossKleinschreibung
    get() : CharSequenceContainsBuilder<T, CharSequenceContainsIgnoringCaseDecorator>
    = CharSequenceContainsBuilder(plant, CharSequenceContainsIgnoringCaseDecorator)
