@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.creators.IIterableContainsAssertions
import ch.tutteli.atrium.creating.iterable.contains.creators.IterableContainsAssertions
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.*

object IterableAssertionsBuilder : IIterableAssertions{

    override inline fun <E, T : Iterable<E>> containsBuilder(plant: AssertionPlant<T>)
        = IterableAssertions.containsBuilder(plant)

    override inline fun <E, T : Iterable<E>> containsNotBuilder(plant: AssertionPlant<T>)
        = IterableAssertions.containsNotBuilder(plant)

    /**
     * Delegates to [IterableContainsAssertions].
     */
    inline val contains get() = IterableContainsAssertionsBuilder
}


object IterableContainsAssertionsBuilder: IIterableContainsAssertions {

    override inline fun <E, T : Iterable<E>> objectsInAnyOrder(checkerBuilder: IterableContains.CheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E, otherExpected: Array<out E>)
        = IterableContainsAssertions.objectsInAnyOrder(checkerBuilder, expected, otherExpected)

    override inline fun <E : Any, T : Iterable<E>> entriesInAnyOrder(checkerBuilder: IterableContains.CheckerBuilder<E, T, InAnyOrderSearchBehaviour>, noinline assertionCreator: AssertionPlant<E>.() -> Unit, otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>)
        = IterableContainsAssertions.entriesInAnyOrder(checkerBuilder, assertionCreator, otherAssertionCreators)

    override inline fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrder(checkerBuilder: IterableContains.CheckerBuilder<E?, T, InAnyOrderSearchBehaviour>, noinline assertionCreator: (AssertionPlant<E>.() -> Unit)?, otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>)
        = IterableContainsAssertions.nullableEntriesInAnyOrder(checkerBuilder, assertionCreator, otherAssertionCreators)

    override inline fun <E, T : Iterable<E>> objectsInAnyOrderOnly(builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E, otherExpected: Array<out E>)
        = IterableContainsAssertions.objectsInAnyOrderOnly(builder, expected, otherExpected)

    override inline fun <E : Any, T : Iterable<E>> entriesInAnyOrderOnly(builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>, noinline assertionCreator: AssertionPlant<E>.() -> Unit, otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>)
        = IterableContainsAssertions.entriesInAnyOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override inline fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrderOnly(builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>, noinline assertionCreator: (AssertionPlant<E>.() -> Unit)?, otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>)
        = IterableContainsAssertions.nullableEntriesInAnyOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override inline fun <E, T : Iterable<E>> objectsInOrderOnly(builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>, expected: E, otherExpected: Array<out E>)
        = IterableContainsAssertions.objectsInOrderOnly(builder, expected, otherExpected)

    override inline fun <E : Any, T : Iterable<E>> entriesInOrderOnly(builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>, noinline assertionCreator: AssertionPlant<E>.() -> Unit, otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>)
        = IterableContainsAssertions.entriesInOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override inline fun <E : Any, T : Iterable<E?>> nullableEntriesInOrderOnly(builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>, noinline assertionCreator: (AssertionPlant<E>.() -> Unit)?, otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>)
        = IterableContainsAssertions.nullableEntriesInOrderOnly(builder, assertionCreator, otherAssertionCreators)

    /**
     * Delegates to [SearchBehaviourFactory].
     */
    inline val searchBehaviours get() = IterableContainsSearchBehaviourFactoryBuilder
}


object IterableContainsSearchBehaviourFactoryBuilder : ISearchBehaviourFactory {

    override inline fun <E, T : Iterable<E>> inAnyOrder(builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>)
        = SearchBehaviourFactory.inAnyOrder(builder)

    override inline fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>)
        = SearchBehaviourFactory.inAnyOrderOnly(builder)

    override inline fun <E, T : Iterable<E>> inOrder(containsBuilder: IterableContains.Builder<E, T, NoOpSearchBehaviour>)
        = SearchBehaviourFactory.inOrder(containsBuilder)

    override inline fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContains.Builder<E, T, InOrderSearchBehaviour>)
        = SearchBehaviourFactory.inOrderOnly(builder)

}
