package ch.tutteli.atrium.builders.charsequence.contains

fun <T : CharSequence> CharSequenceContainsBuilder<T>.atLeast(times: Int): CharSequenceContainsAtLeastCheckerBuilder<T>
    = CharSequenceContainsAtLeastCheckerBuilder(times, this)

fun <T : CharSequence> CharSequenceContainsAtLeastCheckerBuilder<T>.butAtMost(times: Int): CharSequenceContainsButAtMostCheckerBuilder<T>
    = CharSequenceContainsButAtMostCheckerBuilder(times, this, containsBuilder)

fun <T : CharSequence> CharSequenceContainsBuilder<T>.exactly(times: Int): CharSequenceContainsExactlyCheckerBuilder<T>
    = CharSequenceContainsExactlyCheckerBuilder(times, this)

fun <T : CharSequence> CharSequenceContainsBuilder<T>.atMost(times: Int): CharSequenceContainsAtMostCheckerBuilder<T>
    = CharSequenceContainsAtMostCheckerBuilder(times, this)
