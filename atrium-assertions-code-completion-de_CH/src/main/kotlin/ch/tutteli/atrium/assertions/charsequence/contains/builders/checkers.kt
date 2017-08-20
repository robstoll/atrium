package ch.tutteli.atrium.assertions.charsequence.contains.builders

import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator.IDecorator

fun <T : CharSequence, D: IDecorator> CharSequenceContainsBuilder<T, D>.atLeast(times: Int): CharSequenceContainsAtLeastCheckerBuilder<T, D>
    = CharSequenceContainsAtLeastCheckerBuilder(times, this)

fun <T : CharSequence, D: IDecorator> CharSequenceContainsAtLeastCheckerBuilder<T, D>.butAtMost(times: Int): CharSequenceContainsButAtMostCheckerBuilder<T, D>
    = CharSequenceContainsButAtMostCheckerBuilder(times, this, containsBuilder)

fun <T : CharSequence, D: IDecorator> CharSequenceContainsBuilder<T, D>.exactly(times: Int): CharSequenceContainsExactlyCheckerBuilder<T, D>
    = CharSequenceContainsExactlyCheckerBuilder(times, this)

fun <T : CharSequence, D: IDecorator> CharSequenceContainsBuilder<T, D>.atMost(times: Int): CharSequenceContainsAtMostCheckerBuilder<T, D>
    = CharSequenceContainsAtMostCheckerBuilder(times, this)
