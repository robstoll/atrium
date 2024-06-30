//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import java.io.File
import java.nio.file.Path

/**
 * Turns `Expect<File>` into `Expect<Path>`.
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature File::toPath` if you want to show the transformation in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.12.0
 */
infix fun <T : File> Expect<T>.asPath(@Suppress("UNUSED_PARAMETER") o: o): Expect<Path> =
    _logic.changeSubject.unreported { it.toPath() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [Path].
 *
 * The transformation as such is not reflected in reporting.
 * Use `feature of(File::toPath, assertionCreator)` if you want to show the transformation in reporting.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.12.0
 */
infix fun <T : File> Expect<T>.asPath(assertionCreator: Expect<Path>.() -> Unit): Expect<T> =
    apply { asPath(o)._logic.appendAsGroup(assertionCreator) }
