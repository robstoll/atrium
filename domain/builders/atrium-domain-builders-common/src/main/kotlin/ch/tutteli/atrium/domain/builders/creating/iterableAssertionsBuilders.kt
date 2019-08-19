@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.IterableAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.IterableContainsAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.iterableContainsAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.domain.creating.iterableAssertions

/**
 * Delegates inter alia to the implementation of [IterableAssertions].
 * In detail, it implements [IterableAssertions] by delegating to [iterableAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object IterableAssertionsBuilder : IterableAssertions {

    override inline fun <E, T : Iterable<E>> containsBuilder(subjectProvider: SubjectProvider<T>) =
        iterableAssertions.containsBuilder(subjectProvider)

    override inline fun <E, T : Iterable<E>> containsNotBuilder(subjectProvider: SubjectProvider<T>) =
        iterableAssertions.containsNotBuilder(subjectProvider)


    override inline fun <E : Any, T : Iterable<E?>> all(
        assertionContainer: Expect<T>,
        noinline assertionCreator: (Expect<E>.() -> Unit)?
    ): Assertion = iterableAssertions.all(assertionContainer, assertionCreator)

    /**
     * Returns [IterableContainsAssertionsBuilder]
     * which inter alia delegates to the implementation of [IterableContainsAssertions].
     */
    inline val contains get() = IterableContainsAssertionsBuilder

    // everything below is deprecated functionality and will be removed with 1.0.0

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <E : Any> all(
        plant: AssertionPlant<Iterable<E?>>,
        noinline assertionCreator: (AssertionPlant<E>.() -> Unit)?
    ): Assertion = iterableAssertions.all(plant, assertionCreator)
}

/**
 * Delegates inter alia to the implementation of [IterableContainsAssertions].
 * In detail, it implements [IterableContainsAssertions] by delegating to [iterableContainsAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object IterableContainsAssertionsBuilder : IterableContainsAssertions {

    override inline fun <E, T : Iterable<E>> valuesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
        expected: List<E>
    ) = iterableContainsAssertions.valuesInAnyOrder(checkerOption, expected)

    override inline fun <E : Any, T : Iterable<E?>> entriesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ) = iterableContainsAssertions.entriesInAnyOrder(checkerOption, assertionCreators)

    override inline fun <E, T : Iterable<E>> valuesInAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
        expected: List<E>
    ) = iterableContainsAssertions.valuesInAnyOrderOnly(builder, expected)

    override inline fun <E : Any, T : Iterable<E?>> entriesInAnyOrderOnly(
        builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ) = iterableContainsAssertions.entriesInAnyOrderOnly(builder, assertionCreators)

    override inline fun <E, T : Iterable<E>> valuesInOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
        expected: List<E>
    ) = iterableContainsAssertions.valuesInOrderOnly(builder, expected)

    override inline fun <E : Any, T : Iterable<E?>> entriesInOrderOnly(
        builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ) = iterableContainsAssertions.entriesInOrderOnly(builder, assertionCreators)

    override inline fun <E, T : Iterable<E>> valuesInOrderOnlyGrouped(
        builder: IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<E>>
    ): Assertion = iterableContainsAssertions.valuesInOrderOnlyGrouped(builder, groups)

    override inline fun <E : Any, T : Iterable<E?>> entriesInOrderOnlyGrouped(
        builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<(Expect<E>.() -> Unit)?>>
    ): Assertion = iterableContainsAssertions.entriesInOrderOnlyGrouped(builder, groups)


    /**
     * Returns [IterableContainsSearchBehaviourFactoryBuilder]
     * which inter alia delegates to the implementation of [SearchBehaviourFactory].
     */
    inline val searchBehaviours get() = IterableContainsSearchBehaviourFactoryBuilder

    // everything below is deprecated functionality and will be removed with 1.0.0

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <E : Any, T : Iterable<E?>> entriesInAnyOrderWithAssert(
        checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ) = iterableContainsAssertions.entriesInAnyOrderWithAssert(checkerOption, assertionCreators)


    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <E : Any, T : Iterable<E?>> entriesInAnyOrderOnlyWithAssert(
        builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ) = iterableContainsAssertions.entriesInAnyOrderOnlyWithAssert(builder, assertionCreators)

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <E : Any, T : Iterable<E?>> entriesInOrderOnlyWithAssert(
        builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ) = iterableContainsAssertions.entriesInOrderOnlyWithAssert(builder, assertionCreators)

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
    override inline fun <E : Any, T : Iterable<E?>> entriesInOrderOnlyGroupedWithAssert(
        builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<(AssertionPlant<E>.() -> Unit)?>>
    ): Assertion = iterableContainsAssertions.entriesInOrderOnlyGroupedWithAssert(builder, groups)
}

/**
 * Delegates inter alia to the implementation of [SearchBehaviourFactory].
 * In detail, it implements [SearchBehaviourFactory] by delegating to [searchBehaviourFactory]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object IterableContainsSearchBehaviourFactoryBuilder : SearchBehaviourFactory {

    override inline fun <E, T : Iterable<E>> inAnyOrder(builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>) =
        searchBehaviourFactory.inAnyOrder(builder)

    override inline fun <E, T : Iterable<E>> inAnyOrderOnly(builder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>) =
        searchBehaviourFactory.inAnyOrderOnly(builder)

    override inline fun <E, T : Iterable<E>> inOrder(builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>) =
        searchBehaviourFactory.inOrder(builder)

    override inline fun <E, T : Iterable<E>> inOrderOnly(builder: IterableContains.Builder<E, T, InOrderSearchBehaviour>) =
        searchBehaviourFactory.inOrderOnly(builder)

    override inline fun <E, T : Iterable<E>> inOrderOnlyGrouped(builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>) =
        searchBehaviourFactory.inOrderOnlyGrouped(builder)

    override inline fun <E, T : Iterable<E>> inOrderOnlyGroupedWithin(builder: IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>) =
        searchBehaviourFactory.inOrderOnlyGroupedWithin(builder)

}
