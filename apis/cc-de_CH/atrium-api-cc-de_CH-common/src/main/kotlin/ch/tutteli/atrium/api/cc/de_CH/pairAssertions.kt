@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property [first][Pair.first] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <K : Any> Assert<Pair<K, *>>.first get() : Assert<K> = property(Pair<K, *>::first)

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property [first][Pair.first] (which could be `null`)
 * so that further fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <K> Assert<Pair<K, *>>.first get() : AssertionPlantNullable<K> = property(Pair<K, *>::first)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject]'s property [first][Pair.first] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
@Suppress("DEPRECATION")
fun <K : Any, V> Assert<Pair<K, V>>.first(assertionCreator: Assert<K>.() -> Unit)
    = addAssertion(AssertImpl.pair.first(this, assertionCreator))

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property [second][Pair.second] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <V : Any> Assert<Pair<*, V>>.second get() : Assert<V> = property(Pair<*, V>::second)

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property [second][Pair.second]
 * (which could be `null`) so that further fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <V> Assert<Pair<*, V>>.second get() : AssertionPlantNullable<V> = property(Pair<*, V>::second)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject]'s property [second][Pair.second] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
@Suppress("DEPRECATION")
fun <K, V: Any> Assert<Pair<K, V>>.second(assertionCreator: Assert<V>.() -> Unit)
    = addAssertion(AssertImpl.pair.second(this, assertionCreator))
