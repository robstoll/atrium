@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PathAssertions
import ch.tutteli.atrium.domain.creating.pathAssertions
import java.nio.file.Path

/**
 * Delegates inter alia to the implementation of [PathAssertions].
 * In detail, it implements [PathAssertions] by delegating to [pathAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object PathAssertionsBuilder : PathAssertions {
    override inline fun <T : Path> exists(assertionContainer: Expect<T>) =
        pathAssertions.exists(assertionContainer)

    override inline fun <T : Path> existsNot(assertionContainer: Expect<T>) =
        pathAssertions.existsNot(assertionContainer)

    override inline fun <T : Path> parent(assertionContainer: Expect<T>) =
        pathAssertions.parent(assertionContainer)
}
