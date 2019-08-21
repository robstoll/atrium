package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PathAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._exists
import ch.tutteli.atrium.domain.robstoll.lib.creating._existsNot
import java.nio.file.Path


class PathAssertionsImpl : PathAssertions {
    override fun <T : Path> exists(assertionContainer: Expect<T>) = _exists(assertionContainer)
    override fun <T : Path> existsNot(subjectProvider: Expect<T>) = _existsNot(subjectProvider)
}
