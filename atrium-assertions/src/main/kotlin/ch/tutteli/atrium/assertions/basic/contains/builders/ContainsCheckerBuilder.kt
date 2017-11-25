package ch.tutteli.atrium.assertions.basic.contains.builders

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.basic.contains.IContains
import ch.tutteli.atrium.assertions.basic.contains.IContains.IChecker
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Represents the base class for builders representing a checking step in the process of building a sophisticated
 * `contains` assertion.
 *
 * @param T The type of the [IAssertionPlant.subject].
 * @param S The type of the current [IContains.ISearchBehaviour].
 * @param C The type of the checkers in use (typically a sub interface of [IContains.IChecker]).
 * @param B The concrete type of the builder representing the entry point of the process of building a sophisticated
 *        `contains` assertion.
 *
 * @property containsBuilder The builder representing the entry point of the process of building a sophisticated
 *           `contains` assertion.
 *
 * @constructor Represents the base class for builders representing a checking step in the process of creating a
 *              sophisticated `contains` assertion.
 * @param containsBuilder The builder representing the entry point of the process of building a sophisticated
 *        `contains` assertion.
 */
abstract class ContainsCheckerBuilder<out T : Any, S : IContains.ISearchBehaviour, out C : IContains.IChecker, out B : ContainsBuilder<T, S>>(
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
