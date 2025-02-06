//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.logic.based.en_GB

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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.extensionFeature
 *
 * @since 0.9.0
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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.extension
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.extension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.extension().collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Path.fileNameAsString][ch.tutteli.niok.fileNameAsString]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.fileNameFeature
 *
 * @since 0.9.0
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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.fileName
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.fileName(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.fileName().collectAndAppend(assertionCreator)

/**
 * Creates an [Expect] for the property [Path.fileNameWithoutExtension][ch.tutteli.niok.fileNameWithoutExtension]
 * (provided via [niok](https://github.com/robstoll/niok)) of the subject of `this` expectation,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.fileNameWithoutExtensionFeature
 *
 * @since 0.9.0
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
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.fileNameWithoutExtension
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.fileNameWithoutExtension(assertionCreator: Expect<String>.() -> Unit): Expect<T> =
    _logic.fileNameWithoutExtension().collectAndAppend(assertionCreator)

/**
 * Expects that this [Path] has a [parent][Path.getParent] and creates an [Expect] for it,
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.parentFeature
 *
 * @since 0.9.0
 */
val <T : Path> Expect<T>.parent: Expect<Path>
    get() = _logic.parent().transform()

/**
 * Expects that this [Path] has a [parent][Path.getParent], that the parent holds all assertions the
 * given [assertionCreator] creates for it and returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.parent
 *
 * @since 0.9.0
 */
fun <T : Path> Expect<T>.parent(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    _logic.parent().collectAndAppend(assertionCreator)

/**
 * Expects that [other] resolves against this [Path] and creates an [Expect] for the resolved [Path]
 * so that further fluent calls are assertions about it.
 *
 * @return The newly created [Expect] for the extracted feature.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.resolveFeature
 *
 * @since 0.10.0
 */
fun <T : Path> Expect<T>.resolve(other: String): Expect<Path> =
    _logic.resolve(other).transform()

/**
 * Expects that [other] resolves against this [Path], that the resolved [Path] holds all assertions the
 * given [assertionCreator] creates for it and returns an [Expect] for the current subject of `this` expectation.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.PathFeatureExtractorSamples.resolve
 *
 * @since 0.10.0
 */
fun <T : Path> Expect<T>.resolve(other: String, assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    _logic.resolve(other).collectAndAppend(assertionCreator)
