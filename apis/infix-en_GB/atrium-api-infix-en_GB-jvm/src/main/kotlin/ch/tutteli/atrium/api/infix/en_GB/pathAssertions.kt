@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.encoding.EncodingWithCreator
import ch.tutteli.atrium.api.infix.en_GB.creating.path.PathWithCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.nio.charset.Charset
import java.nio.file.Path

/**
 * Expects that the subject of the assertion (a [Path]) starts with the [expected] [Path].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.startsWith(expected: Path): Expect<T> =
    _logicAppend { startsWith(expected) }

/**
 * Expects that the subject of the assertion (a [Path]) does not start with the [expected] [Path].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.startsNotWith(expected: Path): Expect<T> =
    _logicAppend { startsNotWith(expected) }

/**
 * Expects that the subject of the assertion (a [Path]) ends with the expected [Path].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.endsWith(expected: Path): Expect<T> =
    _logicAppend { endsWith(expected) }

/**
 * Expects that the subject of the assertion (a [Path]) does not end with the expected [Path];
 *
 * @param expected The [Path] provided to the assertion
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.endsNotWith(expected: Path): Expect<T> =
    _logicAppend { endsNotWith(expected) }

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
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") existing: existing): Expect<T> =
    _logicAppend { exists() }

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
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") existing: existing): Expect<T> =
    _logicAppend { existsNot() }

/**
 * Creates an [Expect] for the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val <T : Path> Expect<T>.fileName: Expect<String>
    get() = _logic.fileName().getExpectOfFeature()

/**
 * Expects that the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.fileName(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.fileName().addToInitial(assertionCreator)

/**
 * Creates an [Expect] for the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
val <T : Path> Expect<T>.fileNameWithoutExtension: Expect<String>
    get() = _logic.fileNameWithoutExtension().getExpectOfFeature()

/**
 * Expects that the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok))
 * of the subject of the assertion holds all assertions the given [assertionCreator] creates for it
 * and returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.fileNameWithoutExtension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.fileNameWithoutExtension().addToInitial(assertionCreator)

/**
 * Expects that this [Path] has a [parent][Path.getParent] and creates an [Expect] for it,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
val <T : Path> Expect<T>.parent: Expect<Path>
    get() = _logic.parent().getExpectOfFeature()

/**
 * Expects that this [Path] has a [parent][Path.getParent], that the parent holds all assertions the
 * given [assertionCreator] creates for it and returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.parent(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    _logic.parent().addToInitial(assertionCreator)

/**
 * Expects that [other] resolves against this [Path] and creates an [Expect] for the resolved [Path]
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.resolve(other: String): Expect<Path> =
    _logic.resolve(other).getExpectOfFeature()

/**
 * Expects that [PathWithCreator.path] resolves against this [Path], that the resolved [Path] holds all assertions the
 * given [PathWithCreator.assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 *  Use the function `path(String) { ... }` to create a [PathWithCreator].
 *
 * @return The newly created [Expect] for the extracted feature.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.resolve(path: PathWithCreator<Path>): Expect<T> =
    _logic.resolve(path.path).addToInitial(path.assertionCreator)

/**
 * Helper function to create a [PathWithCreator] based on the given [path] and [assertionCreator].
 */
fun <E> path(path: String, assertionCreator: Expect<E>.() -> Unit): PathWithCreator<E> =
    PathWithCreator(path, assertionCreator)

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
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") readable: readable): Expect<T> =
    _logicAppend { isReadable() }

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
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") writable: writable): Expect<T> =
    _logicAppend { isWritable() }

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
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") aRegularFile: aRegularFile): Expect<T> =
    _logicAppend { isRegularFile() }

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
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") aDirectory: aDirectory): Expect<T> =
    _logicAppend { isDirectory() }

/**
 * Creates an [Expect] for the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @since 0.12.0
 */
val <T : Path> Expect<T>.extension: Expect<String>
    get() = _logic.extension().getExpectOfFeature()

/**
 * Expects that the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of the assertion
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of the assertion.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.extension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.extension().addToInitial(assertionCreator)

/**
 * Expects that the subject of the assertion (a [Path]) has the same textual content
 * as [targetPath].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 */
infix fun <T : Path> Expect<T>.hasSameTextualContentAs(
    targetPath: Path
): Expect<T> = hasSameTextualContentAs(withEncoding(targetPath, this))

/**
 * Expects that the subject of the assertion (a [Path]) has the same textual content
 * as path in [encodingWithCreator].
 *
 *  Use the function `withEncoding(Path, Charset, Charset) { ... }` to create an [EncodingWithCreator].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 */
infix fun <T : Path> Expect<T>.hasSameTextualContentAs(encodingWithCreator: EncodingWithCreator<T>):
    Expect<T> = _logicAppend {
        hasSameTextualContentAs(
            encodingWithCreator.path, encodingWithCreator.sourceCharset, encodingWithCreator.targetCharset
        )
    }
/**
 * Expects that the subject of the assertion (a [Path]) has the same binary content
 * as [targetPath].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.13.0
 */
infix fun <T : Path> Expect<T>.hasSameBinaryContentAs(targetPath: Path):
    Expect<T> = _logicAppend {
    hasSameBinaryContentAs(targetPath)
}


/**
 * Helper function to create an [EncodingWithCreator] based on the given [path] and [assertionCreator].
 */
fun <T : Path> withEncoding(path: Path, assertionCreator: Expect<T>,
                            sourceCharset: Charset = Charsets.UTF_8, targetCharset: Charset = Charsets.UTF_8):
    EncodingWithCreator<T> = EncodingWithCreator(path, sourceCharset, targetCharset, assertionCreator)
