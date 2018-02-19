package ch.tutteli.atrium.assertions.basic.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.Contains
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Represents the *deprecated* base class for builders representing the entry point of the process of building a sophisticated
 * `contains` assertion.
 *
 * @param T The type of the [AssertionPlant.subject].
 * @param S The type of the current [Contains.SearchBehaviour].
 *
 * @property plant The [AssertionPlant] for which the sophisticated `contains` assertions shall be built.
 * @property searchBehaviour The search behaviour which shall be applied to the input of the search.
 *
 * @constructor Represents the base class for builders representing the entry point of the process of building a
 *   sophisticated `contains` assertion.
 * @param plant The [AssertionPlant] for which the sophisticated `contains` assertions shall be built.
 * @param searchBehaviour The search behaviour which shall be applied to the input of the search.
 */
@Deprecated(
    "use the abstract class from package creating, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.creating.basic.contains.builders.ContainsBuilder")
)
abstract class ContainsBuilder<out T : Any, out S : Contains.SearchBehaviour>(
    override val plant: AssertionPlant<T>,
    override val searchBehaviour: S
) : ch.tutteli.atrium.creating.basic.contains.Contains.Builder<T, S>
