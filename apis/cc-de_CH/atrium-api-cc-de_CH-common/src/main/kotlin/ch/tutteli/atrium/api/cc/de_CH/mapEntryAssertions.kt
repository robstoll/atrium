package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Creates an [AssertionPlant] for the [AssertionPlant.subject]'s property [key][Map.Entry.key] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <K : Any> Assert<Map.Entry<K, *>>.key get() : Assert<K> = property(Map.Entry<K, *>::key)

/**
 * Makes the assertion that [AssertionPlant.subject]'s property [key][Map.Entry.key] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K : Any, V> Assert<Map.Entry<K, V>>.key(assertionCreator: Assert<K>.() -> Unit)
    = addAssertion(AssertImpl.map.entry.key(this, assertionCreator))

/**
 * Creates an [AssertionPlant] for the [AssertionPlant.subject]'s property [value][Map.Entry.value] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <V : Any> Assert<Map.Entry<*, V>>.value get() : Assert<V> = property(Map.Entry<*, V>::value)

/**
 * Makes the assertion that [AssertionPlant.subject]'s property [value][Map.Entry.value] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K, V: Any> Assert<Map.Entry<K, V>>.value(assertionCreator: Assert<V>.() -> Unit)
    = addAssertion(AssertImpl.map.entry.value(this, assertionCreator))
