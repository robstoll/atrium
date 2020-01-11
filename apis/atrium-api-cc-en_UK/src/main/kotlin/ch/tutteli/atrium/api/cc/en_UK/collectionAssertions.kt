package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject]'s [Collection.size] is [size].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Suppress("DEPRECATION")
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.hasSize(size)"))
fun <T : Collection<*>> Assert<T>.hasSize(size: Int)
    = addAssertion(AssertImpl.collection.hasSize(this, size))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.isEmpty()"))
fun <T : Collection<*>> Assert<T>.isEmpty()
    = addAssertion(AssertImpl.collection.isEmpty(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is not an empty [Collection].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.isNotEmpty()"))
fun <T : Collection<*>> Assert<T>.isNotEmpty()
    = addAssertion(AssertImpl.collection.isNotEmpty(this))
