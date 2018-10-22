package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that [AssertionPlant.subject]'s [Map.size] is [size].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Assert<T>.hasSize(size: Int)
    = addAssertion(AssertImpl.map.hasSize(this, size))

/**
 * Makes the assertion that [AssertionPlant.subject] is an empty [Map].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Assert<T>.toBe(@Suppress("UNUSED_PARAMETER") Empty: Empty)
    = addAssertion(AssertImpl.map.isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] is not an empty [Map].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : Map<*, *>> Assert<T>.notToBe(@Suppress("UNUSED_PARAMETER") Empty: Empty)
    = addAssertion(AssertImpl.map.isNotEmpty(this))

/**
 * Turns `Assert<Map<K, *>>` into `Assert<Iterable<K>>`.
 *
 * The transformation as such is not reflected in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
fun <K> Assert<Map<K, *>>.asKeys(): AssertionPlant<Iterable<K>>
    = AssertImpl.changeSubject(this) { subject.keys }

/**
 * Turns `Assert<Map<*, V>>` into `Assert<Iterable<V>>`.
 *
 * The transformation as such is not reflected in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
fun <V> Assert<Map<*, V>>.asValues(): AssertionPlant<Iterable<V>>
    = AssertImpl.changeSubject(this) { subject.values }

/**
 * Turns `Assert<Map<K, V>>` into `Assert<Iterable<Map.Entry<K, V>>>`.
 *
 * The transformation as such is not reflected in reporting.
 *
 * @return The newly created [AssertionPlant] for the transformed subject.
 */
fun <K, V> Assert<Map<K, V>>.asEntries(): AssertionPlant<Iterable<Map.Entry<K, V>>>
    = AssertImpl.changeSubject(this) { subject.entries }
