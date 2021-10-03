@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.fluent.en_GB

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
 * @since 0.9.0
 */
@Deprecated("Use toStartWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.toStartWith<T>(expected)"))
fun <T : Path> Expect<T>.startsWith(expected: Path): Expect<T> =
    _logicAppend { startsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not start with the [expected] [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use notToStartWith; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToStartWith<T>(expected)")
)
fun <T : Path> Expect<T>.startsNotWith(expected: Path): Expect<T> =
    _logicAppend { startsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) ends with the expected [Path].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated("Use toEndWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.toEndWith<T>(expected)"))
fun <T : Path> Expect<T>.endsWith(expected: Path): Expect<T> =
    _logicAppend { endsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not end with the expected [Path];
 *
 * @param expected The [Path] provided to the assertion
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated("Use notToEndWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToEndWith<T>(expected)"))
fun <T : Path> Expect<T>.endsNotWith(expected: Path): Expect<T> =
    _logicAppend { endsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [Path]) exists;
 * meaning that there is a file system entry at the location the [Path] points to.
 *
 * This assertion _resolves_ symbolic links. Therefore, if a symbolic link exists at the location the subject points to,
 * then the search will continue at that location.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated("Use toExist; will be removed with 1.0.0 at the latest", ReplaceWith("this.toExist<T>()"))
fun <T : Path> Expect<T>.exists(): Expect<T> =
    _logicAppend { exists() }

/**
 * Expects that the subject of `this` expectation (a [Path]) does not exist;
 * meaning that there is no file system entry at the location the [Path] points to.
 *
 * This assertion _resolves_ symbolic links. Therefore, if a symbolic link exists at the location the subject points to,
 * then the search will continue at that location.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated("Use notToExist; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToExist<T>()"))
fun <T : Path> Expect<T>.existsNot(): Expect<T> =
    _logicAppend { existsNot() }

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Creates an [Expect] for the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.pathExtensionFeature
 *
 * @since 0.9.0
 */
val <T : Path> Expect<T>.extension: Expect<String>
    get() = _logic.extension().transform()

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Expects that the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.pathExtension
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.extension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.extension().collectAndAppend(assertionCreator)

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Creates an [Expect] for the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.fileNameFeature
 *
 * @since 0.9.0
 */
val <T : Path> Expect<T>.fileName: Expect<String>
    get() = _logic.fileName().transform()

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Expects that the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.fileName
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.fileName(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.fileName().collectAndAppend(assertionCreator)

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Creates an [Expect] for the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.fileNameWithoutExtensionFeature
 *
 * @since 0.9.0
 */
val <T : Path> Expect<T>.fileNameWithoutExtension: Expect<String>
    get() = _logic.fileNameWithoutExtension().transform()

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Expects that the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok))
 * of the subject of `this` expectation holds all assertions the given [assertionCreator] creates for it
 * and returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.fileNameWithoutExtension
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.fileNameWithoutExtension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.fileNameWithoutExtension().collectAndAppend(assertionCreator)

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Expects that this [Path] has a [parent][Path.getParent] and creates an [Expect] for it,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.parentFeature
 *
 * @since 0.9.0
 */
val <T : Path> Expect<T>.parent: Expect<Path>
    get() = _logic.parent().transform()

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Expects that this [Path] has a [parent][Path.getParent], that the parent holds all assertions the
 * given [assertionCreator] creates for it and returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.parent
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.parent(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    _logic.parent().collectAndAppend(assertionCreator)

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Expects that [other] resolves against this [Path] and creates an [Expect] for the resolved [Path]
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.resolveFeature
 *
 * @since 0.10.0
 */
fun <T : Path> Expect<T>.resolve(other: String): Expect<Path> =
    _logic.resolve(other).transform()

//TODO move to pathExpectations.kt with 0.18.0
/**
 * Expects that [other] resolves against this [Path], that the resolved [Path] holds all assertions the
 * given [assertionCreator] creates for it and returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathExpectationSamples.resolve
 *
 * @since 0.10.0
 */
fun <T : Path> Expect<T>.resolve(other: String, assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    _logic.resolve(other).collectAndAppend(assertionCreator)

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
 * @since 0.9.0
 */
@Deprecated("Use toBeReadable; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeReadable<T>()"))
fun <T : Path> Expect<T>.isReadable(): Expect<T> =
    _logicAppend { isReadable() }

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
 * @since 0.9.0
 */
@Deprecated("Use toBeWritable; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeWritable<T>()"))
fun <T : Path> Expect<T>.isWritable(): Expect<T> =
    _logicAppend { isWritable() }


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
 * @since 0.14.0
 */
@Deprecated("Use toBeExecutable; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeExecutable<T>()"))
fun <T : Path> Expect<T>.isExecutable(): Expect<T> =
    _logicAppend { isExecutable() }

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
 * @since 0.9.0
 */
@Deprecated("Use toBeARegularFile; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeARegularFile<T>()"))
fun <T : Path> Expect<T>.isRegularFile(): Expect<T> =
    _logicAppend { isRegularFile() }

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
 * @since 0.9.0
 */
@Deprecated("Use toBeADirectory; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeADirectory<T>()"))
fun <T : Path> Expect<T>.isDirectory(): Expect<T> =
    _logicAppend { isDirectory() }

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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.PathAssertionSamples.isASymbolicLink
 *
 * @since 0.16.0
 */
@Deprecated(
    "Use toBeASymbolicLink; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeASymbolicLink<T>()")
)
fun <T : Path> Expect<T>.isSymbolicLink(): Expect<T> =
    _logicAppend { isSymbolicLink() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is an absolute path;
 * meaning that the [Path] specified in this instance starts at the file system root.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0
 */
@Deprecated("Use toBeAbsolute; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeAbsolute<T>()"))
fun <T : Path> Expect<T>.isAbsolute(): Expect<T> =
    _logicAppend { isAbsolute() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is a relative path;
 * meaning that the [Path] specified in this instance does not start at the file system root.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.14.0
 */
@Deprecated("Use toBeRelative; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeRelative<T>()"))
fun <T : Path> Expect<T>.isRelative(): Expect<T> =
    _logicAppend { isRelative() }

/**
 * Expects that the subject of `this` expectation (a [Path]) is an empty directory;
 * meaning that there is a file system entry at the location the [Path] points to and that is an empty directory.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.PathAssertionSamples.isEmptyDirectory
 *
 * @since 0.16.0
 */
@Deprecated(
    "Use toBeAnEmptyDirectory; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toBeAnEmptyDirectory<T>()")
)
fun <T : Path> Expect<T>.isEmptyDirectory(): Expect<T> =
    _logicAppend { isEmptyDirectory() }

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
 * @since 0.14.0
 */
@Deprecated(
    "Use toHaveTheDirectoryEntries; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveTheDirectoryEntries<T>(entry, *otherEntries)")
)
fun <T : Path> Expect<T>.hasDirectoryEntry(entry: String, vararg otherEntries: String): Expect<T> =
    _logicAppend { hasDirectoryEntry(entry glue otherEntries) }


/**
 * Expects that the subject of `this` expectation (a [Path]) has the same textual content
 * as [targetPath] taking the given encodings into account (UTF-8 if none given).
 *
 * @param sourceCharset source file encoding - UTF-8 per default.
 * @param targetCharset target file encoding - UTF-8 per default.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use toHaveTheSameTextualContentAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveTheSameTextualContentAs<T>(targetPath, sourceCharset, targetCharset)")
)
fun <T : Path> Expect<T>.hasSameTextualContentAs(
    targetPath: Path,
    sourceCharset: Charset = Charsets.UTF_8,
    targetCharset: Charset = Charsets.UTF_8
): Expect<T> = _logicAppend { hasSameTextualContentAs(targetPath, sourceCharset, targetCharset) }

/**
 * Expects that the subject of `this` expectation (a [Path]) has the same binary content
 * as [targetPath].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use toHaveTheSameBinaryContentAs; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toHaveTheSameBinaryContentAs<T>(targetPath)")
)
fun <T : Path> Expect<T>.hasSameBinaryContentAs(targetPath: Path): Expect<T> =
    _logicAppend { hasSameBinaryContentAs(targetPath) }
