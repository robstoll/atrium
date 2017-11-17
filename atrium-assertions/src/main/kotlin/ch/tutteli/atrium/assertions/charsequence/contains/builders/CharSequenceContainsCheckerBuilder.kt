package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IChecker
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator

/**
 * The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 * `contains` assertion which was started with the given [containsBuilder].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied for the input of the search.
 *
 * @constructor The base class for builders which create [IChecker]s within the fluent API of a sophisticated
 *              `contains` assertion which was started with the given [containsBuilder].
 * @param containsBuilder The previously used [CharSequenceContainsBuilder].
 */
abstract class CharSequenceContainsCheckerBuilder<T : CharSequence, D : IDecorator>(
    val containsBuilder: CharSequenceContainsBuilder<T, D>
) {
    /**
     * Contains all [IChecker]s which should be applied to the search result.
     *
     * It typically contains the [IChecker] this builder created and might contain other [IChecker]s which builders,
     * precedent to this builder within the fluent API, created already.
     */
    abstract val checkers: List<IChecker>

    /**
     * Helper method to simplify adding assertions to the plant which itself is stored in [containsBuilder].
     *
     * @return The plant to support a fluent API.
     */
    fun addAssertion(assertion: IAssertion)
        = containsBuilder.plant.addAssertion(assertion)
}
