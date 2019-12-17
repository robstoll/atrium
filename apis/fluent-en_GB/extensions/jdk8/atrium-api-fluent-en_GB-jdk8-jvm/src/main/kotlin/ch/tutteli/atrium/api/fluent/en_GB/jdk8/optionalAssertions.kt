@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.optional
import java.util.*

/**
 * Expects that the subject of the assertion (an [Optional]) is empty (not present).
 *
 * Shortcut for more or less something like `feature(Optional<T>::isEmpty) { toBe(true) }`
 * depends on the underlying implementation though.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : Optional<*>> Expect<T>.isEmpty(): Expect<T> = addAssertion(ExpectImpl.optional.isEmpty(this))
