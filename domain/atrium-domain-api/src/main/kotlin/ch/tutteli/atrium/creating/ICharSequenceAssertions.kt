package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour

/**
 * Defines the minimum set of assertion functions and builders applicable to [CharSequence] type,
 * which an implementation of the domain of Atrium has to provide.
 */
interface ICharSequenceAssertions {
    fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>): CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>
    fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>): CharSequenceContainsBuilder<T, CharSequenceContainsNotSearchBehaviour>

    fun <T : CharSequence> startsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> startsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> endsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> endsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> isEmpty(plant: AssertionPlant<T>): Assertion
    fun <T : CharSequence> isNotEmpty(plant: AssertionPlant<T>): Assertion
}
