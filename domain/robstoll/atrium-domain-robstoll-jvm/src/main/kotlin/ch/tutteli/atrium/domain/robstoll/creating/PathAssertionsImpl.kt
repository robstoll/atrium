package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PathAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._exists
import java.nio.file.Path

/**
 * Robstoll's implementation of [PathAssertions].
 */
class PathAssertionsImpl : PathAssertions {

    override fun <T: Path> exists(assertionContainer: Expect<T>) = _exists(assertionContainer)
}
