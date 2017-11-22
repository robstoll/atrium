package ch.tutteli.atrium.assertions.basic.contains.builders

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.basic.contains.IContains
import ch.tutteli.atrium.assertions.charsequence.contains.ICharSequenceContains.IChecker

abstract class ContainsCheckerBuilder<out T : Any, D : IContains.IDecorator, out C : IContains.IChecker, out B : ContainsBuilder<T, D>>(
    val containsBuilder: B
) {
    /**
     * Contains all [IChecker]s which should be applied to the search result.
     *
     * It typically contains the [IChecker] this builder created and might contain other [IChecker]s which builders,
     * precedent to this builder within the fluent API, created already.
     */
    abstract val checkers: List<C>

    /**
     * Helper method to simplify adding assertions to the plant which itself is stored in [containsBuilder].
     *
     * @return The plant to support a fluent API.
     */
    fun addAssertion(assertion: IAssertion)
        = containsBuilder.plant.addAssertion(assertion)
}
