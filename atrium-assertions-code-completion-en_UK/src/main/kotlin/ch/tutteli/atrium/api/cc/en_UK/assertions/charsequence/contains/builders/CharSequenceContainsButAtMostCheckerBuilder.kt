package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsButAtMostCheckerBuilderBase

open class CharSequenceContainsButAtMostCheckerBuilder<T : CharSequence, D : IDecorator>(
    times: Int,
    atLeastBuilder: CharSequenceContainsAtLeastCheckerBuilder<T, D>,
    containsBuilder: CharSequenceContainsBuilder<T, D>
) : CharSequenceContainsButAtMostCheckerBuilderBase<T, D>(
    times,
    atLeastBuilder,
    containsBuilder,
    containsBuilder.plant::containsNot.name,
    containsBuilder::atMost.name,
    containsBuilder::atLeast.name,
    atLeastBuilder::butAtMost.name,
    containsBuilder::exactly.name
)
