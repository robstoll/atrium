package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.nio.file.Path

/**
 * The access point to an implementation of [PathAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val pathAssertions by lazy { loadSingleService(PathAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [Path],
 * which an implementation of the domain of Atrium has to provide.
 */
interface PathAssertions {
    fun <T : Path> endsWith(assertionContainer: Expect<T>, expected: Path): Assertion

    fun <T : Path> exists(assertionContainer: Expect<T>): Assertion
    fun <T : Path> existsNot(assertionContainer: Expect<T>): Assertion

    fun <T : Path> fileNameWithoutExtension(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, String>

    fun <T : Path> parent(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Path>

    fun <T : Path> isReadable(assertionContainer: Expect<T>): Assertion
    fun <T : Path> isWritable(assertionContainer: Expect<T>): Assertion
    fun <T : Path> isRegularFile(assertionContainer: Expect<T>): Assertion
    fun <T : Path> isDirectory(assertionContainer: Expect<T>): Assertion
}
