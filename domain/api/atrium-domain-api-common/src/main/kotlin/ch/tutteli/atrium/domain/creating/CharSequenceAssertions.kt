package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour

/**
 * The access point to an implementation of [CharSequenceAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val charSequenceAssertions by lazy { loadSingleService(CharSequenceAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [CharSequence],
 * which an implementation of the domain of Atrium has to provide.
 */
interface CharSequenceAssertions {
    fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NotSearchBehaviour>

    fun startsWith(plant: AssertionPlant<CharSequence>, expected: CharSequence): Assertion
    fun startsNotWith(plant: AssertionPlant<CharSequence>, expected: CharSequence): Assertion
    fun endsWith(plant: AssertionPlant<CharSequence>, expected: CharSequence): Assertion
    fun endsNotWith(plant: AssertionPlant<CharSequence>, expected: CharSequence): Assertion
    fun isEmpty(plant: AssertionPlant<CharSequence>): Assertion
    fun isNotEmpty(plant: AssertionPlant<CharSequence>): Assertion
    fun isNotBlank(plant: AssertionPlant<CharSequence>): Assertion
}
