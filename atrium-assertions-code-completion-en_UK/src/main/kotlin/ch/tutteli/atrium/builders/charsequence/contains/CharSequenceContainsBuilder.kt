package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.creating.IAssertionPlant

class CharSequenceContainsBuilder<out T : CharSequence, D : IDecorator>(
    val plant: IAssertionPlant<T>, val decorator: D
)
