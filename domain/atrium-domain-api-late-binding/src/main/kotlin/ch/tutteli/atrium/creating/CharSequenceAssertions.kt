package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour

/**
 * A dummy implementation of [ICharSequenceAssertions] which should be replaced by an actual implementation.
 */
object CharSequenceAssertions: ICharSequenceAssertions {
    override fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>): CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>
        = throwUnsupportedOperationException()
    override fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>): CharSequenceContainsBuilder<T, CharSequenceContainsNotSearchBehaviour>
        = throwUnsupportedOperationException()

    override fun <T : CharSequence> startsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
        = throwUnsupportedOperationException()
    override fun <T : CharSequence> startsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
        = throwUnsupportedOperationException()
    override fun <T : CharSequence> endsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
        = throwUnsupportedOperationException()
    override fun <T : CharSequence> endsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
        = throwUnsupportedOperationException()
    override fun <T : CharSequence> isEmpty(plant: AssertionPlant<T>): Assertion
        = throwUnsupportedOperationException()
    override fun <T : CharSequence> isNotEmpty(plant: AssertionPlant<T>): Assertion
        = throwUnsupportedOperationException()
}
