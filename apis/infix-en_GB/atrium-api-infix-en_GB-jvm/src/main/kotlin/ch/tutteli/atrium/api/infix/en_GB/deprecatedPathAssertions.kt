@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.path.DirectoryEntries
import ch.tutteli.atrium.api.infix.en_GB.creating.path.PathWithCreator
import ch.tutteli.atrium.api.infix.en_GB.creating.path.PathWithEncoding
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.nio.charset.Charset
import java.nio.file.Path

/**
 * Expects that the subject of `this` expectation (a [Path]) starts with the [expected] [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use toStartWith which expects a String; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toStartWith<T>(expected)")
)
infix fun <T : Path> Expect<T>.startsWith(expected: Path): Expect<T> =
    _logicAppend { startsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not start with the [expected] [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use notToStartWith; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToStartWith<T>(expected)")
)
infix fun <T : Path> Expect<T>.startsNotWith(expected: Path): Expect<T> =
    _logicAppend { startsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) ends with the expected [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
@Deprecated("Use toEndWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.toEndWith<T>(expected)"))
infix fun <T : Path> Expect<T>.endsWith(expected: Path): Expect<T> =
    _logicAppend { endsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not end with the expected [Path];
 *
 * @param expected The [Path] provided to the assertion
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
@Deprecated("Use notToEndWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToEndWith<T>(expected)"))
infix fun <T : Path> Expect<T>.endsNotWith(expected: Path): Expect<T> =
    _logicAppend { endsNotWith(expected) }


/**
 * Expects that the subject of `this` expectation (a [Path]) is a directory having the provided [entry].
 * That means that there is a file system entry at the location the [Path] points to and that it is a directory.
 * Furthermore, the argument string resolved against the subject yields an existing file system entry.
 *
 * This assertion _resolves_ symbolic links for the subject, but not for the [entry].
 * Therefore, if a symbolic link exists at the location the subject points to, the search will continue at the location
 * the link points at. If a symbolic link exists at the [entry], this will fulfill the assertion and the entry’s
 * symbolic link will not be followed.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertions work on.
 * The result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @see [has]
 *
 * @since 0.14.0
 */
@Deprecated(
    "Use `toHave directoryEntries(...)`; will be removed with 1.0.0 at the latest",
    ReplaceWith(
        "this.toHave<T>(directoryEntries(entry))",
        "ch.tutteli.atrium.api.infix.en_GB.PathAssertionsKt.directoryEntries"
    )
)
infix fun <T : Path> Expect<T>.hasDirectoryEntry(entry: String) =
    _logicAppend { hasDirectoryEntry(listOf(entry)) }

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
 * @since 0.14.0
 */
@Deprecated(
    "Use `toHave`; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHave<T>(directoryEntries)")
)
infix fun <T : Path> Expect<T>.has(directoryEntries: DirectoryEntries) =
    _logicAppend { hasDirectoryEntry(directoryEntries.toList()) }

/**
 * Expects that the subject of `this` expectation (a [Path]) has the same textual content
 * as [targetPath] (using UTF-8 for encoding)
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.13.0
 */
@Deprecated(
    "Use toHaveTheSameTextualContentAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveTheSameTextualContentAs<T>(targetPath)")
)
infix fun <T : Path> Expect<T>.hasSameTextualContentAs(
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
 * @since 0.13.0
 */
@Deprecated(
    "Use toHaveTheSameTextualContentAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveTheSameTextualContentAs<T>(pathWithEncoding)")
)
infix fun <T : Path> Expect<T>.hasSameTextualContentAs(pathWithEncoding: PathWithEncoding): Expect<T> =
    _logicAppend {
        hasSameTextualContentAs(pathWithEncoding.path, pathWithEncoding.sourceCharset, pathWithEncoding.targetCharset)
    }

/**
 * Expects that the subject of `this` expectation (a [Path]) has the same binary content
 * as [targetPath].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.13.0
 */
@Deprecated(
    "Use toHaveTheSameBinaryContentAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveTheSameBinaryContentAs<T>(targetPath)")
)
infix fun <T : Path> Expect<T>.hasSameBinaryContentAs(targetPath: Path): Expect<T> =
    _logicAppend { hasSameBinaryContentAs(targetPath) }
