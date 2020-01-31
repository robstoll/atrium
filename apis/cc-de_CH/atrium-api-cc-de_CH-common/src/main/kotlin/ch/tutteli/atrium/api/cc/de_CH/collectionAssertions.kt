@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject]'s [Collection.size] is [size].
 *
 * Shortcut for `size.ist(expectedSize)` depends on the underlying implementation though.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
@Suppress("DEPRECATION")
fun <T : Collection<*>> Assert<T>.hatDieGroesse(size: Int)
    = addAssertion(AssertImpl.collection.hasSize(this, size))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : Collection<*>> Assert<T>.istLeer()
    = addAssertion(AssertImpl.collection.isEmpty(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is not an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <T : Collection<*>> Assert<T>.istNichtLeer()
    = addAssertion(AssertImpl.collection.isNotEmpty(this))

/**
 * Creates an [AssertionPlant] for the [Assert.subject][SubjectProvider.subject]'s property
 * [size][Collection.size] so that further fluent calls are assertions about it.
 *
 * Wrap it into Kotlin's [apply] if you want to make subsequent assertions on the current subject or use the overload
 * which expects an assertionCreator lambda where sub assertions are evaluated together (form an assertion group block).
 *
 * @return The newly created [AssertionPlant].
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
val Assert<Collection<*>>.size get(): Assert<Int> = property(Collection<*>::size)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject]'s property [size][Collection.size]
 * holds all assertions the given [assertionCreator] might create for it.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if a created [Assertion]s (by calling [assertionCreator])
 *   does not hold.
 * @throws IllegalArgumentException in case the given [assertionCreator] did not create a single assertion.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
@Suppress("DEPRECATION")
fun <E, T: Collection<E>> Assert<T>.size (assertionCreator: Assert<Int>.() -> Unit): Assert<T>
    = addAssertion(AssertImpl.collection.size(this, assertionCreator))
