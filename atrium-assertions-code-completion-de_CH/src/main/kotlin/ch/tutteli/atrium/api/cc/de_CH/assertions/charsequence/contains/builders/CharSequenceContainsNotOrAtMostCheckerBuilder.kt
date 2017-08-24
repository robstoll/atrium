package ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders

import ch.tutteli.atrium.api.cc.de_CH.enthaeltNicht
import ch.tutteli.atrium.api.cc.de_CH.nichtOderHoechstens
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsNotOrAtMostCheckerBuilderBase

open class CharSequenceContainsNotOrAtMostCheckerBuilder<T : CharSequence, D : CharSequenceContainsAssertionCreator.IDecorator>(
    times: Int,
    containsBuilder: CharSequenceContainsBuilder<T, D>
) : CharSequenceContainsNotOrAtMostCheckerBuilderBase<T, D>(
    times,
    containsBuilder,
    containsBuilder.plant::enthaeltNicht.name,
    containsBuilder::nichtOderHoechstens.name
)
