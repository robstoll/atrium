package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.iterable.contains.IterableContainsAssertionCreator


abstract class IterableContainsCheckerBuilder<E, T : Iterable<E>, D : IterableContainsAssertionCreator.IDecorator>(
    val containsBuilder: IterableContainsBuilder<E, T, D>
) {

    abstract val checkers: List<IterableContainsAssertionCreator.IChecker>

    /**
     * Helper method to simplify adding assertions to the plant which itself is stored in [containsBuilder].
     *
     * @return The plant to support a fluent API.
     */
    fun addAssertion(assertion: IAssertion)
        = containsBuilder.plant.addAssertion(assertion)
}
