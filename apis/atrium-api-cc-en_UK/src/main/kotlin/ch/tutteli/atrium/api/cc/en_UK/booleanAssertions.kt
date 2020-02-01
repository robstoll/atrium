@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.SubjectProvider

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is `true`.
 *
 * Delegates to [toBe] with argument `true`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Will be removed with 1.0.0 because it is redundant with `toBe(true)` without adding enough to be a legit alternative.", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.toBe(true)"))
fun Assert<Boolean>.isTrue() = toBe(true)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] is `false`.
 *
 * Delegates to [toBe] with argument `false`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Will be removed with 1.0.0 because it is redundant with `toBe(false)` without adding enough to be a legit alternative.", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.toBe(false)"))
fun Assert<Boolean>.isFalse() = toBe(false)
