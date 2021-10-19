//TODO rename file to fileExpectations with 0.18.0
@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import java.io.File
import java.nio.file.Path

/**
 * Turns `Expect<File>` into `Expect<Path>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(File::toPath)` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FileExpectationSamples.asPathFeature
 *
 * @since 0.9.0
 */
fun <T : File> Expect<T>.asPath(): Expect<Path> =
    _logic.changeSubject.unreported { it.toPath() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [Path].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(File::toPath, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.FileExpectationSamples.asPath
 *
 * @since 0.9.0
 */
fun <T : File> Expect<T>.asPath(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    apply { asPath()._logic.appendAsGroup(assertionCreator) }
