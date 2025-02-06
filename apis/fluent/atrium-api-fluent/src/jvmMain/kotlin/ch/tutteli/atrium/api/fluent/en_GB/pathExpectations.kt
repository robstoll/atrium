//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.kbox.glue
import java.nio.charset.Charset
import java.nio.file.Path

/**
 * Expects that the subject of `this` expectation (a [Path]) starts with the [expected] [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toStartWith
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toStartWith(expected: Path): Expect<T> =
    _coreAppend { startsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not start with the [expected] [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.notToStartWith
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.notToStartWith(expected: Path): Expect<T> =
    _coreAppend { startsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) ends with the expected [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toEndWith
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toEndWith(expected: Path): Expect<T> =
    _coreAppend { endsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not end with the expected [Path];
 *
 * @param expected The [Path] provided to the assertion
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.notToEndWith
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.notToEndWith(expected: Path): Expect<T> =
    _coreAppend { endsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) exists;
 * meaning that there is a file system entry at the location the [Path] points to.
 *
 * This assertion _resolves_ symbolic links. Therefore, if a symbolic link exists at the location the subject points to,
 * then the search will continue at that location.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toExist
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toExist(): Expect<T> =
    _coreAppend { exists() }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not exist;
 * meaning that there is no file system entry at the location the [Path] points to.
 *
 * This assertion _resolves_ symbolic links. Therefore, if a symbolic link exists at the location the subject points to,
 * then the search will continue at that location.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.notToExist
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.notToExist(): Expect<T> =
    _coreAppend { existsNot() }


/**
 * Expects that the subject of `this` expectation (a [Path]) is readable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread has the permission to read from it.
 *
 * This assertion _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to,
 * search will continue at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertion works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toBeReadable
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toBeReadable(): Expect<T> =
    _coreAppend { isReadable() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is not readable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread does not have the permission to read from it.
 *
 * This assertion _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to,
 * search will continue at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertion works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.notToBeReadable
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.notToBeReadable(): Expect<T> =
    _coreAppend { isNotReadable() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is writable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread has the permission to write to it.
 *
 * This assertion _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toBeWritable
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toBeWritable(): Expect<T> =
    _coreAppend { isWritable() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is not writable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread does not have the permission to write to it.
 *
 * This assertion _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.notToBeWritable
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.notToBeWritable(): Expect<T> =
    _coreAppend { isNotWritable() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is executable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread has the permission to execute it.
 *
 * The semantics of “permission to execute it” may differ when checking access to a directory. For example, on UNIX
 * systems, it means that the Java virtual machine has permission to search the directory in order to access file or
 * subdirectories.
 *
 * This assertion _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toBeExecutable
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toBeExecutable(): Expect<T> =
    _coreAppend { isExecutable() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is not executable;
 * meaning that there is a file system entry at the location the [Path] points to and
 * that the current thread does not have the permission to execute it.
 *
 * The semantics of “permission to execute it” may differ when checking access to a directory. For example, on UNIX
 * systems, it means that the Java virtual machine has permission to search the directory in order to access file or
 * subdirectories.
 *
 * This assertion _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.notToBeExecutable
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.notToBeExecutable(): Expect<T> =
    _coreAppend { isNotExecutable() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is a file;
 * meaning that there is a file system entry at the location the [Path] points to and that is a regular file.
 *
 * This assertion _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertion works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toBeARegularFile
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toBeARegularFile(): Expect<T> =
    _coreAppend { isRegularFile() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is a directory;
 * meaning that there is a file system entry at the location the [Path] points to and that is a directory.
 *
 * This assertion _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertion works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toBeADirectory
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toBeADirectory(): Expect<T> =
    _coreAppend { isDirectory() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is an empty directory;
 * meaning that there is a file system entry at the location the [Path] points to and that is an empty directory.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toBeAnEmptyDirectory
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toBeAnEmptyDirectory(): Expect<T> =
    _coreAppend { isEmptyDirectory() }


/**
 * Expects that the subject of `this` expectation (a [Path]) is a symbolic link;
 * meaning that there is a file system entry at the location the [Path] points to and that is a symbolic link.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertion works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toBeASymbolicLink
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toBeASymbolicLink(): Expect<T> =
    _coreAppend { isSymbolicLink() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is an absolute path;
 * meaning that the [Path] specified in this instance starts at the file system root.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toBeAbsolute
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toBeAbsolute(): Expect<T> =
    _coreAppend { isAbsolute() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is a relative path;
 * meaning that the [Path] specified in this instance does not start at the file system root.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toBeRelative
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toBeRelative(): Expect<T> =
    _coreAppend { isRelative() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is a directory having the provided entries.
 * That means that there is a file system entry at the location the [Path] points to and that it is a directory.
 * Furthermore, every argument string resolved against the subject yields an existing file system entry.
 *
 * This assertion _resolves_ symbolic links for the subject, but not for the entries.
 * Therefore, if a symbolic link exists at the location the subject points to, the search will continue at the location
 * the link points at. If a symbolic link exists at one of the entries, this will fulfill the respective assertion and
 * the entry’s symbolic link will not be followed.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertion works on.
 * The result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toHaveTheDirectoryEntries
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toHaveTheDirectoryEntries(entry: String, vararg otherEntries: String): Expect<T> =
    _coreAppend { hasDirectoryEntry(entry glue otherEntries) }


/**
 * Expects that the subject of `this` expectation (a [Path]) has the same textual content
 * as [targetPath] taking the given encodings into account (UTF-8 if none given).
 *
 * @param sourceCharset source file encoding - UTF-8 per default.
 * @param targetCharset target file encoding - UTF-8 per default.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toHaveTheSameTextualContentAs
 *
 * @since 0.17.0
 */
fun <T : Path> Expect<T>.toHaveTheSameTextualContentAs(
    targetPath: Path,
    sourceCharset: Charset = Charsets.UTF_8,
    targetCharset: Charset = Charsets.UTF_8
): Expect<T> = _coreAppend { hasSameTextualContentAs(targetPath, sourceCharset, targetCharset) }

/**
 * Expects that the subject of `this` expectation (a [Path]) has the same binary content
 * as [targetPath].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.toHaveTheSameBinaryContentAs
 *
 * @since 0.17.0
 *
 */
fun <T : Path> Expect<T>.toHaveTheSameBinaryContentAs(targetPath: Path): Expect<T> =
    _coreAppend { hasSameBinaryContentAs(targetPath) }

