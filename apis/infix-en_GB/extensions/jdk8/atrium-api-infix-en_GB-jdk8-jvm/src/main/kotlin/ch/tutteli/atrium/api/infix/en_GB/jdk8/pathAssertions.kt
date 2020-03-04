@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.path
import java.nio.file.Path

/**
 * Expects that the subject of the assertion (a [Path]) starts with the [expected] [Path].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.startsWith(expected: Path): Expect<T> =
    addAssertion(ExpectImpl.path.startsWith(this, expected))

/**
 * Expects that the subject of the assertion (a [Path]) does not start with the [expected] [Path].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.startsNotWith(expected: Path): Expect<T> =
    addAssertion(ExpectImpl.path.startsNotWith(this, expected))

/**
 * Expects that the subject of the assertion (a [Path]) ends with the expected [Path].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.endsWith(expected: Path): Expect<T> =
    addAssertion(ExpectImpl.path.endsWith(this, expected))

/**
 * Expects that the subject of the assertion (a [Path]) does not end with the expected [Path];
 *
 * @param expected The [Path] provided to the assertion
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.endsNotWith(expected: Path): Expect<T> =
    addAssertion(ExpectImpl.path.endsNotWith(this, expected))

/**
 * Expects that the subject of the assertion (a [Path]) exists;
 * meaning that there is a file system entry at the location the [Path] points to.
 *
 * This matcher _resolves_ symbolic links. Therefore, if a symbolic link exists at the location the subject points to,
 * then the search will continue at that location.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.to(@Suppress("UNUSED_PARAMETER") exist: exist): Expect<T> =
    addAssertion(ExpectImpl.path.exists(this))

/**
 * Expects that the subject of the assertion (a [Path]) does not exist;
 * meaning that there is no file system entry at the location the [Path] points to.
 *
 * This matcher _resolves_ symbolic links. Therefore, if a symbolic link exists at the location the subject points to,
 * then the search will continue at that location.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.notTo(@Suppress("UNUSED_PARAMETER") exist: exist): Expect<T> =
    addAssertion(ExpectImpl.path.existsNot(this))

/**
 * Creates an [Expect] for the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
val <T : Path> Expect<T>.fileName: Expect<String>
    get() = ExpectImpl.path.fileName(this).getExpectOfFeature()

/**
 * Expects that the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.fileName(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    ExpectImpl.path.fileName(this).addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
val <T : Path> Expect<T>.fileNameWithoutExtension: Expect<String>
    get() = ExpectImpl.path.fileNameWithoutExtension(this).getExpectOfFeature()

/**
 * Expects that the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok))
 * of the subject of the assertion holds all assertions the given [assertionCreator] creates for it
 * and returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.fileNameWithoutExtension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    ExpectImpl.path.fileNameWithoutExtension(this).addToInitial(assertionCreator)

/**
 * Expects that this [Path] has a [parent][Path.getParent] and creates an [Expect] for it,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
val <T : Path> Expect<T>.parent: Expect<Path>
    get() = ExpectImpl.path.parent(this).getExpectOfFeature()

/**
 * Expects that this [Path] has a [parent][Path.getParent] and that the parent holds all assertions the
 * given [assertionCreator] creates for it and returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.parent(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    ExpectImpl.path.parent(this).addToInitial(assertionCreator)

/**
 * Expects that [other] resolves against this [Path] and creates an [Expect] for the resolved [Path]
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.resolve(other: String): Expect<Path> =
    ExpectImpl.path.resolve(this, other).getExpectOfFeature()

/**
 * Expects that the subject of the assertion (a [Path]) is readable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread has the permission to read from it.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to,
 * search will continue at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") readable: readable): Expect<T> =
    addAssertion(ExpectImpl.path.isReadable(this))

/**
 * Expects that the subject of the assertion (a [Path]) is writable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread has the permission to write to it.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") writable: writable): Expect<T> =
    addAssertion(ExpectImpl.path.isWritable(this))

/**
 * Expects that the subject of the assertion (a [Path]) is a file;
 * meaning that there is a file system entry at the location the [Path] points to and that is a regular file.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") aRegularFile: aRegularFile): Expect<T> =
    addAssertion(ExpectImpl.path.isRegularFile(this))

/**
 * Expects that the subject of the assertion (a [Path]) is a directory;
 * meaning that there is a file system entry at the location the [Path] points to and that is a directory.
 *
 * This matcher _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") aDirectory: aDirectory): Expect<T> =
    addAssertion(ExpectImpl.path.isDirectory(this))

/**
 * Creates an [Expect] for the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.10.0
 */
val <T : Path> Expect<T>.extension: Expect<String>
    get() = ExpectImpl.path.extension(this).getExpectOfFeature()

/**
 * Expects that the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.10.0
 */
infix fun <T : Path> Expect<T>.extension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    ExpectImpl.path.extension(this).addToInitial(assertionCreator)
