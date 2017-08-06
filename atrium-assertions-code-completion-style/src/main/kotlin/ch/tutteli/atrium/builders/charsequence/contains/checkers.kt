package ch.tutteli.atrium.builders.charsequence.contains

fun <T : CharSequence> CharSequenceContainsBuilder<T>.atLeast(times: Int): CharSequenceContainsAtLeastCheckerBuilder<T>
    = CharSequenceContainsAtLeastCheckerBuilder(times, this)

fun <T : CharSequence> CharSequenceContainsBuilder<T>.exactly(times: Int): CharSequenceContainsExactlyCheckerBuilder<T>
    = CharSequenceContainsExactlyCheckerBuilder(times, this)
