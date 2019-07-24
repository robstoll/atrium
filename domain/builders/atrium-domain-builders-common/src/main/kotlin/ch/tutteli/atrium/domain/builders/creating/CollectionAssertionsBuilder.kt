@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.CollectionAssertions
import ch.tutteli.atrium.domain.creating.collectionAssertions

/**
 * Delegates inter alia to the implementation of [CollectionAssertions].
 * In detail, it implements [CollectionAssertions] by delegating to [collectionAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object CollectionAssertionsBuilder : CollectionAssertions {

    override inline fun isEmpty(subjectProvider: SubjectProvider<Collection<*>>) =
        collectionAssertions.isEmpty(subjectProvider)

    override inline fun isNotEmpty(subjectProvider: SubjectProvider<Collection<*>>) =
        collectionAssertions.isNotEmpty(subjectProvider)

    override inline fun <T: Collection<*>> size(assertionContainer: Expect<T>)
        = collectionAssertions.size(assertionContainer)


    override inline fun hasSize(plant: AssertionPlant<Collection<*>>, size: Int) =
        collectionAssertions.hasSize(plant, size)

    override inline fun size(plant: AssertionPlant<Collection<*>>, noinline assertionCreator: Assert<Int>.() -> Unit) =
        collectionAssertions.size(plant, assertionCreator)
}
