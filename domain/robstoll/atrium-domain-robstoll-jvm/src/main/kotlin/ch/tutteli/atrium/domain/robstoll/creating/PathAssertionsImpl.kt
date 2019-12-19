@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.PathAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import java.nio.file.Path

class PathAssertionsImpl : PathAssertions {
    override fun <T : Path> startsWith(assertionContainer: Expect<T>, expected: Path) =
        _startsWith(assertionContainer, expected)

    override fun <T : Path> startsNotWith(assertionContainer: Expect<T>, expected: Path) =
        _startsNotWith(assertionContainer, expected)

    override fun <T : Path> endsWith(assertionContainer: Expect<T>, expected: Path) =
        _endsWith(assertionContainer, expected)

    override fun <T : Path> endsNotWith(assertionContainer: Expect<T>, expected: Path) =
        _endsNotWith(assertionContainer, expected)

    override fun <T : Path> exists(assertionContainer: Expect<T>) = _exists(assertionContainer)

    override fun <T : Path> existsNot(assertionContainer: Expect<T>) = _existsNot(assertionContainer)

    override fun <T : Path> fileName(assertionContainer: Expect<T>) = _fileName(assertionContainer)
    override fun <T : Path> extension(assertionContainer: Expect<T>) = _extension(assertionContainer)
    override fun <T : Path> fileNameWithoutExtension(assertionContainer: Expect<T>) =
        _fileNameWithoutExtension(assertionContainer)

    override fun <T : Path> parent(assertionContainer: Expect<T>) = _parent(assertionContainer)

    override fun <T : Path> isReadable(assertionContainer: Expect<T>) = _isReadable(assertionContainer)
    override fun <T : Path> isWritable(assertionContainer: Expect<T>) = _isWritable(assertionContainer)
    override fun <T : Path> isRegularFile(assertionContainer: Expect<T>) = _isRegularFile(assertionContainer)
    override fun <T : Path> isDirectory(assertionContainer: Expect<T>) = _isDirectory(assertionContainer)
}
