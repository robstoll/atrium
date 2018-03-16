@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.IterableAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.IterableContainsAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.iterableContainsAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.domain.creating.iterableAssertions
import java.util.*

/**
 * Delegates inter alia to the implementation of [IterableAssertions].
 * In detail, it implements [IterableAssertions] by delegating to [iterableAssertions]
 * which in turn delegates to the implementation via [ServiceLoader].
 */
object IterableAssertionsBuilder : IterableAssertions {

    override inline fun <E, T : Iterable<E>> containsBuilder(plant: AssertionPlant<T>)
        = iterableAssertions.containsBuilder(plant)

    override inline fun <E, T : Iterable<E>> containsNotBuilder(plant: AssertionPlant<T>)
        = iterableAssertions.containsNotBuilder(plant)

    /**
     * Returns [IterableContainsAssertionsBuilder]
     * which inter alia delegates to the implementation of [IterableContainsAssertions].
     */
    inline val contains get() = IterableContainsAssertionsBuilder
}

/**
 * Delegates inter alia to the implementation of [IterableContainsAssertions].
 * In detail, it implements [IterableContainsAssertions] by delegating to [iterableContainsAssertions]
 * which in turn delegates to the implementation via [ServiceLoader].
 */
object IterableContainsAssertionsBuilder: IterableContainsAssertions {

    override inline fun <E, T : Iterable<E>> objectsInAnyOrder(checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>, expected: E, otherExpected: Array<out E>)
        = iterableContainsAssertions.objectsInAnyOrder(checkerOption, expected, otherExpected)

    override inline fun <E : Any, T : Iterable<E>> entriesInAnyOrder(checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>, noinline assertionCreator: AssertionPlant<E>.() -> Unit, otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>)
        = iterableContainsAssertions.entriesInAnyOrder(checkerOption, assertionCreator, otherAssertionCreators)

    override inline fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrder(checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>, noinline assertionCreator: (AssertionPlant<E>.() -> Unit)?, otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>)
        = iterableContainsAssertions.nullableEntriesInAnyOrder(checkerOption, assertionCreator, otherAssertionCreators)

    override inline fun <E, T : Iterable<E>> objectsInAnyOrderOnly(builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E, otherExpected: Array<out E>)
        = iterableContainsAssertions.objectsInAnyOrderOnly(builder, expected, otherExpected)

    override inline fun <E : Any, T : Iterable<E>> entriesInAnyOrderOnly(builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>, noinline assertionCreator: AssertionPlant<E>.() -> Unit, otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>)
        = iterableContainsAssertions.entriesInAnyOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override inline fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrderOnly(builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>, noinline assertionCreator: (AssertionPlant<E>.() -> Unit)?, otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>)
        = iterableContainsAssertions.nullableEntriesInAnyOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override inline fun <E, T : Iterable<E>> objectsInOrderOnly(builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>, expected: E, otherExpected: Array<out E>)
        = iterableContainsAssertions.objectsInOrderOnly(builder, expected, otherExpected)

    override inline fun <E : Any, T : Iterable<E>> entriesInOrderOnly(builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>, noinline assertionCreator: AssertionPlant<E>.() -> Unit, otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>)
        = iterableContainsAssertions.entriesInOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override inline fun <E : Any, T : Iterable<E?>> nullableEntriesInOrderOnly(builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>, noinline assertionCreator: (AssertionPlant<E>.() -> Unit)?, otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>)
        = iterableContainsAssertions.nullableEntriesInOrderOnly(builder, assertionCreator, otherAssertionCreators)

    /**
     * Returns [IterableContainsSearchBehaviourFactoryBuilder]
     * which inter alia delegates to the implementation of [SearchBehaviourFactory].
     */
    inline val searchBehaviours get() = IterableContainsSearchBehaviourFactoryBuilder
}

/**
 * Delegates inter alia to the implementation of [SearchBehaviourFactory].
 * In detail, it implements [SearchBehaviourFactory] by delegating to [searchBehaviourFactory]
 * which in turn delegates to the implementation via [ServiceLoader].
 */
object IterableContainsSearchBehaviourFactoryBuilder : SearchBehaviourFactory {

    override inline fun <E, T : Iterable<E>> inAnyOrder(builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>)
        = searchBehaviourFactory.inAnyOrder(builder)

    override inline fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>)
        = searchBehaviourFactory.inAnyOrderOnly(builder)

    override inline fun <E, T : Iterable<E>> inOrder(containsBuilder: IterableContains.Builder<E, T, NoOpSearchBehaviour>)
        = searchBehaviourFactory.inOrder(containsBuilder)

    override inline fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContains.Builder<E, T, InOrderSearchBehaviour>)
        = searchBehaviourFactory.inOrderOnly(builder)

}
