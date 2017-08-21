package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsButAtMostCheckerBuilder
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsExactlyCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder

fun <T : CharSequence, D: IDecorator> CharSequenceContainsBuilder<T, D>.zumindest(times: Int): CharSequenceContainsAtLeastCheckerBuilder<T, D>
    = CharSequenceContainsAtLeastCheckerBuilder(times, this)

fun <T : CharSequence, D: IDecorator> CharSequenceContainsAtLeastCheckerBuilder<T, D>.aberHoechstens(times: Int): CharSequenceContainsButAtMostCheckerBuilder<T, D>
    = CharSequenceContainsButAtMostCheckerBuilder(times, this, containsBuilder)

fun <T : CharSequence, D: IDecorator> CharSequenceContainsBuilder<T, D>.genau(times: Int): CharSequenceContainsExactlyCheckerBuilder<T, D>
    = CharSequenceContainsExactlyCheckerBuilder(times, this)

fun <T : CharSequence, D: IDecorator> CharSequenceContainsBuilder<T, D>.hoechstens(times: Int): CharSequenceContainsAtMostCheckerBuilder<T, D>
    = CharSequenceContainsAtMostCheckerBuilder(times, this)
