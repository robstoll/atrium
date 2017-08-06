package ch.tutteli.atrium.builders.charsequence.contains

import ch.tutteli.atrium.assertions.charsequence.*
import ch.tutteli.atrium.creating.IAssertionPlant

class CharSequenceContainsBuilder<T : CharSequence>(
    val plant: IAssertionPlant<T>,
    val decorator: CharSequenceContainsAssertionCreator.IDecorator<T>
) {
    constructor(plant: IAssertionPlant<T>) : this(plant, CharSequenceContainsAssertionCreator.NothingDecorator())
}
