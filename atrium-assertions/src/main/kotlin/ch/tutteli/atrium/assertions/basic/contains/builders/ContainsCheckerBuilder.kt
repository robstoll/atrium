package ch.tutteli.atrium.assertions.basic.contains.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.basic.contains.Contains
import ch.tutteli.atrium.assertions.basic.contains.Contains.Checker
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Represents the base class for builders representing a checking step in the process of building a sophisticated
 * `contains` assertion.
 *
 * @param T The type of the [AssertionPlant.subject].
 * @param S The type of the current [Contains.SearchBehaviour].
 * @param C The type of the checkers in use (typically a sub interface of [Contains.Checker]).
 * @param B The concrete type of the builder representing the entry point of the process of building a sophisticated
 *   `contains` assertion.
 *
 * @property containsBuilder The builder representing the entry point of the process of building a sophisticated
 *   `contains` assertion.
 *
 * @constructor Represents the base class for builders representing a checking step in the process of creating a
 *   sophisticated `contains` assertion.
 * @param containsBuilder The builder representing the entry point of the process of building a sophisticated
 *   `contains` assertion.
 */
abstract class ContainsCheckerBuilder<out T : Any, out S : Contains.SearchBehaviour, out C : Contains.Checker, out B : ContainsBuilder<T, S>>(
    val containsBuilder: B
) {
    /**
     * Contains all [Checker]s which should be applied to the search result.
     *
     * It typically contains the [Checker] this builder created and might contain other [Checker]s which builders,
     * precedent to this builder within the fluent API, created already.
     */
    abstract val checkers: List<C>

    /**
     * Helper method to simplify adding assertions to the plant which itself is stored in [containsBuilder].
     *
     * @return The plant to support a fluent API.
     */
    fun addAssertion(assertion: Assertion)
        = containsBuilder.plant.addAssertion(assertion)
}
