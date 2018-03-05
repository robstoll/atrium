package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour

/**
 * A dummy implementation of [ICharSequenceAssertions] which should be replaced by an actual implementation.
 */
object CharSequenceAssertions: ICharSequenceAssertions {
    override fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NoOpSearchBehaviour>
        = throwUnsupportedOperationException()
    override fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NotSearchBehaviour>
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
