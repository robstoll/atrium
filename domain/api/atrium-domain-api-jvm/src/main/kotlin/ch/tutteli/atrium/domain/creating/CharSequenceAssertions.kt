package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import java.util.*

/**
 * The access point to an implementation of [CharSequenceAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val charSequenceAssertions by lazy { loadSingleService(CharSequenceAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [CharSequence],
 * which an implementation of the domain of Atrium has to provide.
 */
interface CharSequenceAssertions {
    fun <T : CharSequence> containsBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    fun <T : CharSequence> containsNotBuilder(plant: AssertionPlant<T>): CharSequenceContains.Builder<T, NotSearchBehaviour>

    fun <T : CharSequence> startsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> startsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> endsWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> endsNotWith(plant: AssertionPlant<T>, expected: CharSequence): Assertion
    fun <T : CharSequence> isEmpty(plant: AssertionPlant<T>): Assertion
    fun <T : CharSequence> isNotEmpty(plant: AssertionPlant<T>): Assertion
}
