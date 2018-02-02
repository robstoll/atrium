package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour

/**
 * Robstoll's implementation of [ICharSequenceAssertions].
 */
object CharSequenceAssertions: ICharSequenceAssertions {
    override fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>): CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>
        = _containsBuilder(plant)

    override fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>): CharSequenceContainsBuilder<T, CharSequenceContainsNotSearchBehaviour>
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
