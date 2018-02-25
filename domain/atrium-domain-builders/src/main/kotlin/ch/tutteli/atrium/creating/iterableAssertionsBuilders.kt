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

    override fun <E, T : Iterable<E>> objectsInAnyOrder(checkerBuilder: IterableContains.CheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>, expected: E, otherExpected: Array<out E>)
        = IterableContainsAssertions.objectsInAnyOrder(checkerBuilder, expected, otherExpected)

    override fun <E : Any, T : Iterable<E>> entriesInAnyOrder(checkerBuilder: IterableContains.CheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>, assertionCreator: AssertionPlant<E>.() -> Unit, otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>)
        = IterableContainsAssertions.entriesInAnyOrder(checkerBuilder, assertionCreator, otherAssertionCreators)

    override fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrder(checkerBuilder: IterableContains.CheckerBuilder<E?, T, IterableContainsInAnyOrderSearchBehaviour>, assertionCreator: (AssertionPlant<E>.() -> Unit)?, otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>)
        = IterableContainsAssertions.nullableEntriesInAnyOrder(checkerBuilder, assertionCreator, otherAssertionCreators)

    override fun <E, T : Iterable<E>> objectsInAnyOrderOnly(builder: IterableContains.Builder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>, expected: E, otherExpected: Array<out E>)
        = IterableContainsAssertions.objectsInAnyOrderOnly(builder, expected, otherExpected)

    override fun <E : Any, T : Iterable<E>> entriesInAnyOrderOnly(builder: IterableContains.Builder<E, T, IterableContainsInAnyOrderOnlySearchBehaviour>, assertionCreator: AssertionPlant<E>.() -> Unit, otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>)
        = IterableContainsAssertions.entriesInAnyOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override fun <E : Any, T : Iterable<E?>> nullableEntriesInAnyOrderOnly(builder: IterableContains.Builder<E?, T, IterableContainsInAnyOrderOnlySearchBehaviour>, assertionCreator: (AssertionPlant<E>.() -> Unit)?, otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>)
        = IterableContainsAssertions.nullableEntriesInAnyOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override fun <E, T : Iterable<E>> objectsInOrderOnly(builder: IterableContains.Builder<E, T, IterableContainsInOrderOnlySearchBehaviour>, expected: E, otherExpected: Array<out E>)
        = IterableContainsAssertions.objectsInOrderOnly(builder, expected, otherExpected)

    override fun <E : Any, T : Iterable<E>> entriesInOrderOnly(builder: IterableContains.Builder<E, T, IterableContainsInOrderOnlySearchBehaviour>, assertionCreator: AssertionPlant<E>.() -> Unit, otherAssertionCreators: Array<out AssertionPlant<E>.() -> Unit>)
        = IterableContainsAssertions.entriesInOrderOnly(builder, assertionCreator, otherAssertionCreators)

    override fun <E : Any, T : Iterable<E?>> nullableEntriesInOrderOnly(builder: IterableContains.Builder<E?, T, IterableContainsInOrderOnlySearchBehaviour>, assertionCreator: (AssertionPlant<E>.() -> Unit)?, otherAssertionCreators: Array<out (AssertionPlant<E>.() -> Unit)?>)
        = IterableContainsAssertions.nullableEntriesInOrderOnly(builder, assertionCreator, otherAssertionCreators)

    /**
     * Delegates to [IterableContainsSearchBehaviours].
     */
    inline val searchBehaviours get() = IterableContainsSearchBehavioursBuilder
}


object IterableContainsSearchBehavioursBuilder: IIterableContainsSearchBehaviours {

    override fun <E, T : Iterable<E>> inAnyOrder(builder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>)
        = IterableContainsSearchBehaviours.inAnyOrder(builder)

    override fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>)
        = IterableContainsSearchBehaviours.inAnyOrderOnly(builder)

    override fun <E, T : Iterable<E>> inOrder(containsBuilder: IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>)
        = IterableContainsSearchBehaviours.inOrder(containsBuilder)

    override fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContains.Builder<E, T, IterableContainsInOrderSearchBehaviour>)
        = IterableContainsSearchBehaviours.inOrderOnly(builder)

}
