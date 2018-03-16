package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.CharSequenceAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*

/**
 * Robstoll's implementation of [CharSequenceAssertions].
 */
class CharSequenceAssertionsImpl: CharSequenceAssertions {

    override fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>)
        = _containsBuilder(plant)

    override fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>)
        = _containsNotBuilder(plant)


    override fun <T : CharSequence> startsWith(plant: AssertionPlant<T>, expected: CharSequence)
        = _startsWith(plant, expected)

    override fun <T : CharSequence> startsNotWith(plant: AssertionPlant<T>, expected: CharSequence)
        = _startsNotWith(plant, expected)

    override fun <T : CharSequence> endsWith(plant: AssertionPlant<T>, expected: CharSequence)
        = _endsWith(plant, expected)

    override fun <T : CharSequence> endsNotWith(plant: AssertionPlant<T>, expected: CharSequence)
        = _endsNotWith(plant, expected)

    override fun <T : CharSequence> isEmpty(plant: AssertionPlant<T>)
        = _isEmpty(plant)

    override fun <T : CharSequence> isNotEmpty(plant: AssertionPlant<T>)
        = _isNotEmpty(plant)
}
