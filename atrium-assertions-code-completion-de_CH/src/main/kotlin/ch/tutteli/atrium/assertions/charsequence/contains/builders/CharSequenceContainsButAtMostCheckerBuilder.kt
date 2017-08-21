package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.enthaeltNicht

open class CharSequenceContainsButAtMostCheckerBuilder<T : CharSequence, D : IDecorator>(
    times: Int,
    atLeastBuilder: CharSequenceContainsAtLeastCheckerBuilder<T, D>,
    containsBuilder: CharSequenceContainsBuilder<T, D>
) : CharSequenceContainsButAtMostCheckerBuilderBase<T, D>(
    times,
    atLeastBuilder,
    containsBuilder,
    containsBuilder.plant::enthaeltNicht.name,
    containsBuilder::hoechstens.name,
    containsBuilder::zumindest.name,
    atLeastBuilder::aberHoechstens.name,
    containsBuilder::genau.name
)
