package ch.tutteli.atrium.creating.basic.contains.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.basic.contains.Contains

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
@Deprecated("do not rely on this class, will be made internal with 1.0.0")
abstract class ContainsCheckerBuilder<out T : Any, out S : Contains.SearchBehaviour, out C : Contains.Checker, out B : Contains.Builder<T, S>>(
    override val containsBuilder: B
): Contains.CheckerBuilder<T, S, C, B> {
    /**
     * Helper method to simplify adding assertions to the plant which itself is stored in [containsBuilder].
     *
     * @return The plant to support a fluent API.
     */
    fun addAssertion(assertion: Assertion)
        = containsBuilder.plant.addAssertion(assertion)
}
