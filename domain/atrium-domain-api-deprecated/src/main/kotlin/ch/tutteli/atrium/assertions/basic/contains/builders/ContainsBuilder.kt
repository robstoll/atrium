package ch.tutteli.atrium.assertions.basic.contains.builders

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.basic.contains.Contains

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
    "use the interface Contains.Builder instead, will be removed with 1.0.0",
    ReplaceWith(
        "Contains.Builder",
        "ch.tutteli.atrium.domain.creating.basic.contains.Contains"
    )
)
abstract class ContainsBuilder<out T : Any, out S : Contains.SearchBehaviour>(
    override val plant: AssertionPlant<T>,
    override val searchBehaviour: S
) : Contains.Builder<T, S>
