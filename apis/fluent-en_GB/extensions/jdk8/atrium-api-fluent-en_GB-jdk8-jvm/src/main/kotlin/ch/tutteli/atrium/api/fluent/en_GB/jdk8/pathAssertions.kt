package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.path
import java.nio.file.Path

/**
 * Expects that the subject of the assertion (a [Path]) exists; meaning that there is a file system entry at the location the [Path] points to.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue at the location the symbolic point at.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.exists(): Expect<T> = addAssertion(ExpectImpl.path.exists(this))

/**
 * Expects that the subject of the assertion (a [Path]) does not exist; meaning that there is no file system entry at the location the [Path] points to.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue at the location the symbolic point at.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.existsNot(): Expect<T> = addAssertion(ExpectImpl.path.existsNot(this))

/**
 * Creates an [Expect] for the property [Path.fileName] of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect].
 */
val <T : Path> Expect<T>.fileName get(): Expect<Path> = ExpectImpl.path.fileName(this).getExpectOfFeature()

/**
 * Expects that the property [Path.fileName] of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and returns this assertion container.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : Path> Expect<T>.fileName(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    ExpectImpl.path.fileName(this).addToInitial(assertionCreator)
