//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.path.PathWithCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import java.nio.file.Path

/**
 * Creates an [Expect] for the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.extensionFeature
 *
 * @since 0.12.0
 */
val <T : Path> Expect<T>.extension: Expect<String>
    get() = _logic.extension().transform()

/**
 * Expects that the property [Path.extension][ch.tutteli.niok.extension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.extension
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.extension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.extension().collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.fileNameFeature
 *
 * @since 0.12.0
 */
val <T : Path> Expect<T>.fileName: Expect<String>
    get() = _logic.fileName().transform()

/**
 * Expects that the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation
 * holds all assertions the given [assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.fileName
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.fileName(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.fileName().collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.fileNameWithoutExtensionFeature
 *
 * @since 0.12.0
 */
val <T : Path> Expect<T>.fileNameWithoutExtension: Expect<String>
    get() = _logic.fileNameWithoutExtension().transform()

/**
 * Expects that the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok))
 * of the subject of `this` expectation holds all assertions the given [assertionCreator] creates for it
 * and returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.fileNameWithoutExtension
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.fileNameWithoutExtension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.fileNameWithoutExtension().collectAndAppend(assertionCreator)

/**
 * Expects that this [Path] has a [parent][Path.getParent] and creates an [Expect] for it,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.parentFeature
 *
 * @since 0.12.0
 */
val <T : Path> Expect<T>.parent: Expect<Path>
    get() = _logic.parent().transform()

/**
 * Expects that this [Path] has a [parent][Path.getParent], that the parent holds all assertions the
 * given [assertionCreator] creates for it and returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.parent
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.parent(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    _logic.parent().collectAndAppend(assertionCreator)

/**
 * Expects that [other] resolves against this [Path] and creates an [Expect] for the resolved [Path]
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.resolveFeature
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.resolve(other: String): Expect<Path> =
    _logic.resolve(other).transform()

/**
 * Expects that [PathWithCreator.path] resolves against this [Path], that the resolved [Path] holds all assertions the
 * given [PathWithCreator.assertionCreator] creates for it and
 * returns an [Expect] for the current subject of `this` expectation.
 *
 *  Use the function `path(String) { ... }` to create a [PathWithCreator].
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.PathExpectationSamples.resolve
 *
 * @since 0.12.0
 */
infix fun <T : Path> Expect<T>.resolve(path: PathWithCreator<Path>): Expect<T> =
    _logic.resolve(path.path).collectAndAppend(path.assertionCreator)

/**
 * Helper function to create a [PathWithCreator] based on the given [path] and [assertionCreator].
 */
fun <E> path(path: String, assertionCreator: Expect<E>.() -> Unit): PathWithCreator<E> =
    PathWithCreator(path, assertionCreator)
