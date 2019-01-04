package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl


/**
 * Makes the assertion that [AssertionPlant.subject] contains the given [key].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <K> Assert<Map<K, *>>.containsKey(key: K)
    = addAssertion(AssertImpl.map.containsKey(this, key))

/**
 * Makes the assertion that [AssertionPlant.subject] contains the given [key] and that the corresponding value
 * holds all assertions the given [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K, V: Any, T: Map<K, V>> Assert<T>.getExisting(key: K, assertionCreator: Assert<V>.() -> Unit)
    = addAssertion(AssertImpl.map.getExisting(this, key, assertionCreator))

/**
 * Makes the assertion that [AssertionPlant.subject] contains the given [key] and that the corresponding value
 * holds all assertions the given [assertionCreator] might create for it.
 *
 * Notice, that the corresponding value of the given [key] can be `null` even if the key exists as the [Map] has a
 * nullable value type.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K, V: Any, T: Map<K, V?>> Assert<T>.getExistingNullable(key: K, assertionCreator: AssertionPlantNullable<V?>.() -> Unit)
    = addAssertion(AssertImpl.map.getExistingNullable(this, key, assertionCreator))

/**
 * Makes the assertion that [AssertionPlant.subject]'s [Map.size] is [size].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Map<*, *>> Assert<T>.hasSize(size: Int)
    = addAssertion(AssertImpl.map.hasSize(this, size))

/**
 * Makes the assertion that [AssertionPlant.subject] is an empty [Map].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Map<*, *>> Assert<T>.isEmpty()
    = addAssertion(AssertImpl.map.isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] is not an empty [Map].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Map<*, *>> Assert<T>.isNotEmpty()
    = addAssertion(AssertImpl.map.isNotEmpty(this))


/**
 * Creates an [AssertionPlant] for the [subject][AssertionPlant.subject]'s property [keys][Map.keys] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <K> Assert<Map<K, *>>.keys get() : Assert<Set<K>> = property(Map<K, *>::keys)

/**
 * Makes the assertion that [AssertionPlant.subject]'s property [keys][Map.keys] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K, V, T: Map<K, V>> Assert<T>.keys(assertionCreator: Assert<Set<K>>.() -> Unit)
    = addAssertion(AssertImpl.map.keys(this, assertionCreator))

/**
 * Creates an [AssertionPlant] for the [subject][AssertionPlant.subject]'s property [values][Map.values] so that further
 * fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
val <V> Assert<Map<*, V>>.values get() : Assert<Collection<V>> = property(Map<*, V>::values)

/**
 * Makes the assertion that [AssertionPlant.subject]'s property [values][Map.values] holds all assertions the given
 * [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
fun <K, V, T: Map<K, V>> Assert<T>.values(assertionCreator: Assert<Collection<V>>.() -> Unit)
    = addAssertion(AssertImpl.map.values(this, assertionCreator))

/**
 * Turns `Assert<Map<K, V>>` into `Assert<Set<Map.Entry<K, V>>>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `property(subject::entries)` if you want to show the transformation in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
fun <K, V> Assert<Map<K, V>>.asEntries(): Assert<Set<Map.Entry<K, V>>>
    = AssertImpl.changeSubject(this) { subject.entries }
