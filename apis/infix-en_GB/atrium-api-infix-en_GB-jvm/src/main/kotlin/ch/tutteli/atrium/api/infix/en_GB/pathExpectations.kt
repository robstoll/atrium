@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.path.DirectoryEntries
import ch.tutteli.atrium.api.infix.en_GB.creating.path.PathWithEncoding
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.nio.file.Path

/**
 * Expects that the subject of `this` expectation (a [Path]) starts with the [expected] [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : Path> Expect<T>.toStartWith(expected: Path): Expect<T> =
    _logicAppend { startsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not start with the [expected] [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : Path> Expect<T>.notToStartWith(expected: Path): Expect<T> =
    _logicAppend { startsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) ends with the expected [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : Path> Expect<T>.toEndWith(expected: Path): Expect<T> =
    _logicAppend { endsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not end with the expected [Path];
 *
 * @param expected The [Path] provided to the assertion
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : Path> Expect<T>.notToEndWith(expected: Path): Expect<T> =
    _logicAppend { endsNotWith(expected) }

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
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions work on.
 * The result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @see [directoryEntries]
 * @see [hasDirectoryEntry]
 *
 * @since 0.17.0
 */
infix fun <T : Path> Expect<T>.toHave(directoryEntries: DirectoryEntries) =
    _logicAppend { hasDirectoryEntry(directoryEntries.toList()) }

/**
 * Expects that the subject of `this` expectation (a [Path]) has the same textual content
 * as [targetPath] (using UTF-8 for encoding)
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : Path> Expect<T>.toHaveTheSameTextualContentAs(
    targetPath: Path
): Expect<T> = toHaveTheSameTextualContentAs(withEncoding(targetPath))

/**
 * Expects that the subject of `this` expectation (a [Path]) has the same textual content
 * as [PathWithEncoding.path] in the given [pathWithEncoding] with the specified encodings.
 *
 *  Use the function `withEncoding(Path, Charset, Charset)` to create a [PathWithEncoding].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : Path> Expect<T>.toHaveTheSameTextualContentAs(pathWithEncoding: PathWithEncoding): Expect<T> =
    _logicAppend {
        hasSameTextualContentAs(pathWithEncoding.path, pathWithEncoding.sourceCharset, pathWithEncoding.targetCharset)
    }

/**
 * Expects that the subject of `this` expectation (a [Path]) has the same binary content
 * as [targetPath].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.17.0
 */
infix fun <T : Path> Expect<T>.toHaveTheSameBinaryContentAs(targetPath: Path): Expect<T> =
    _logicAppend { hasSameBinaryContentAs(targetPath) }
