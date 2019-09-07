package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.path
import java.nio.file.Path

/**
 * Expects that the subject of the assertion (a [Path]) exists; meaning that there is a file system entry at the location the [Path] points to.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations take place.
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
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations take place.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.existsNot(): Expect<T> = addAssertion(ExpectImpl.path.existsNot(this))

/**
 * Expects that the subject of the assertion (a [Path]) is readable; meaning that there is a file system entry at the location the [Path] points to and that the current thread has the permission to read from it.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations take place.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.isReadable(): Expect<T> = addAssertion(ExpectImpl.path.isReadable(this))

/**
 * Expects that the subject of the assertion (a [Path]) is writable; meaning that there is a file system entry at the location the [Path] points to and that the current thread has the permission to write to it.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue at the location the link points at.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.isWritable(): Expect<T> = addAssertion(ExpectImpl.path.isWritable(this))

/**
 * Expects that the subject of the assertion (a [Path]) is a file; meaning that there is a file system entry at the location the [Path] points to and that is a regular file.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations take place.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.isRegularFile(): Expect<T> = addAssertion(ExpectImpl.path.isRegularFile(this))

/**
 * Expects that the subject of the assertion (a [Path]) is a directory; meaning that there is a file system entry at the location the [Path] points to and that is a directory.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations take place.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.isDirectory(): Expect<T> = addAssertion(ExpectImpl.path.isDirectory(this))

