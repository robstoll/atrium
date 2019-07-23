package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.path
import java.nio.file.Path

/**
 * Expects that the subject of the assertion (a [Path]) exists.
 *
 * Shortcut for more or less something like `feature(Files::exists, arrayOf()) { toBe(true) }`
 * depends on the underlying implementation though.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Path> Expect<T>.exists(): Expect<T> = addAssertion(ExpectImpl.path.exists(this))

