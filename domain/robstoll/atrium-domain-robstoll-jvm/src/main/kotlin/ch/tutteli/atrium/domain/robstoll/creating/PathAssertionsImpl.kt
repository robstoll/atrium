package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PathAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import java.nio.file.Path

class PathAssertionsImpl : PathAssertions {
    override fun <T : Path> exists(assertionContainer: Expect<T>) = _exists(assertionContainer)
    override fun <T : Path> existsNot(assertionContainer: Expect<T>) = _existsNot(assertionContainer)
    override fun <T : Path> isReadable(assertionContainer: Expect<T>) = _isReadable(assertionContainer)
    override fun <T : Path> isWritable(assertionContainer: Expect<T>) = _isWritable(assertionContainer)
    override fun <T : Path> isRegularFile(assertionContainer: Expect<T>) = _isRegularFile(assertionContainer)
    override fun <T : Path> isDirectory(assertionContainer: Expect<T>) = _isDirectory(assertionContainer)
}
