package ch.tutteli.atrium.assertions.basic.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.Contains
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Represents the *deprecated* base class for builders representing a checking step in the process of building a sophisticated
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
@Deprecated(
    "use the abstract class from package creating, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.creating.basic.contains.builders.ContainsCheckerBuilder")
)
abstract class ContainsCheckerBuilder<out T : Any, out S : Contains.SearchBehaviour, out C : Contains.Checker, out B : ContainsBuilder<T, S>>(
    override val containsBuilder: B
) : ch.tutteli.atrium.creating.basic.contains.Contains.CheckerBuilder<T, S, C, B>
