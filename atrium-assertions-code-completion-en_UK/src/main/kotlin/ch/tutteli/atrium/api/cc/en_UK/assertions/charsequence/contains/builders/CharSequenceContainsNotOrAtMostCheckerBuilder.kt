package ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.api.cc.en_UK.notOrAtMost
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsNotOrAtMostCheckerBuilderBase

open class CharSequenceContainsNotOrAtMostCheckerBuilder<T : CharSequence, D : CharSequenceContainsAssertionCreator.IDecorator>(
    times: Int,
    containsBuilder: CharSequenceContainsBuilder<T, D>
) : CharSequenceContainsNotOrAtMostCheckerBuilderBase<T, D>(
    times,
    containsBuilder,
    containsBuilder.plant::containsNot.name,
    containsBuilder::notOrAtMost.name
)
