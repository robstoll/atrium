package ch.tutteli.atrium.creating.iterable.contains.builders

import ch.tutteli.atrium.creating.iterable.contains.IterableContains

interface IterableContainsWithTimesCheckerBuilder<out E, out T : Iterable<E>, out S : IterableContains.SearchBehaviour>
    : IterableContains.CheckerBuilder<E, T, S> {
    val times: Int
}
