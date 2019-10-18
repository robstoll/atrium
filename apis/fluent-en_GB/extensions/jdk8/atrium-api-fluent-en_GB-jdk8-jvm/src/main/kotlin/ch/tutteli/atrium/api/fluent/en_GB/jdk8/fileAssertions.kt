package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
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
 * @since 0.9.0
 */
fun <T : File> Expect<T>.asPath(): Expect<Path> =
    ExpectImpl.changeSubject(this).unreported { it.toPath() }

/**
 * Expects that the subject of the assertion holds all assertions the given [assertionCreator] creates for
 * the subject as [Path].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature(File::toPath, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return This assertion container to support a fluent API.
 *
 * @since 0.9.0
 */
fun <T : File> Expect<T>.asPath(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    apply { asPath().addAssertionsCreatedBy(assertionCreator) }
