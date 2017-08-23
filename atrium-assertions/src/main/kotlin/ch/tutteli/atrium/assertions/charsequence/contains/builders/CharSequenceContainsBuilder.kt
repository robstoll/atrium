package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Represents the entry point of the fluent API of sophisticated `contains` assertions.
 * It contains the [plant] for which the [IAssertion] shall be build as well as the decoration behaviour which shall be
 * applied to the [plant]'s [subject][IAssertionPlant.subject].
 *
 * @param T The input type of the search which is the same as the type of the [subject][IAssertionPlant.subject] of the
 *          [plant].
 * @param D The [IDecorator] behaviour.
 *
 * @constructor Represents the entry point of the fluent API of sophisticated `contains` assertions.
 * @param plant The [IAssertionPlant] for which the sophisticated `contains` assertions shall be built.
 * @param decorator The decoration behaviour which shall be applied to the input of the search.
 */
class CharSequenceContainsBuilder<out T : CharSequence, D : IDecorator>(
    val plant: IAssertionPlant<T>, val decorator: D
)
