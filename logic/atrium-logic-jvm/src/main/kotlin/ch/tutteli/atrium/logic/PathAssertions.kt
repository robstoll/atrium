@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.nio.charset.Charset
import java.nio.file.Path
import java.util.*

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Path] type.
 */
interface PathAssertions {
    fun <T : Path> startsWith(container: AssertionContainer<T>, expected: Path): Assertion
    fun <T : Path> startsNotWith(container: AssertionContainer<T>, expected: Path): Assertion
    fun <T : Path> endsWith(container: AssertionContainer<T>, expected: Path): Assertion
    fun <T : Path> endsNotWith(container: AssertionContainer<T>, expected: Path): Assertion

    fun <T : Path> exists(container: AssertionContainer<T>): Assertion
    fun <T : Path> existsNot(container: AssertionContainer<T>): Assertion

    fun <T : Path> isReadable(container: AssertionContainer<T>): Assertion
    fun <T : Path> isWritable(container: AssertionContainer<T>): Assertion
    fun <T : Path> isRegularFile(container: AssertionContainer<T>): Assertion
    fun <T : Path> isDirectory(container: AssertionContainer<T>): Assertion

    fun <T : Path> hasSameTextualContentAs(
        container: AssertionContainer<T>,
        targetPath: Path,
        sourceCharset: Charset,
        targetCharset: Charset
    ): Assertion

    fun <T : Path> hasSameBinaryContentAs(container: AssertionContainer<T>, targetPath: Path): Assertion

    fun <T : Path> fileName(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, String>
    fun <T : Path> extension(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, String>
    fun <T : Path> fileNameWithoutExtension(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, String>
    fun <T : Path> parent(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, Path>
    fun <T : Path> resolve(container: AssertionContainer<T>, other: String): ExtractedFeaturePostStep<T, Path>
}
