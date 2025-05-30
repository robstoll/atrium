//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.nio.charset.Charset
import java.nio.file.LinkOption
import java.nio.file.Path

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Path] type.
 */
//TODO 1.3.0 deprecate
interface PathAssertions {
    fun <T : Path> startsWith(container: AssertionContainer<T>, expected: Path): Assertion
    fun <T : Path> startsNotWith(container: AssertionContainer<T>, expected: Path): Assertion
    fun <T : Path> endsWith(container: AssertionContainer<T>, expected: Path): Assertion
    fun <T : Path> endsNotWith(container: AssertionContainer<T>, expected: Path): Assertion

    fun <T : Path> exists(container: AssertionContainer<T>, linkOption: LinkOption? = null): Assertion
    fun <T : Path> existsNot(container: AssertionContainer<T>, linkOption: LinkOption? = null): Assertion

    fun <T : Path> isReadable(container: AssertionContainer<T>): Assertion
    fun <T : Path> isNotReadable(container: AssertionContainer<T>): Assertion
    fun <T : Path> isWritable(container: AssertionContainer<T>): Assertion
    fun <T : Path> isNotWritable(container: AssertionContainer<T>): Assertion
    fun <T : Path> isExecutable(container: AssertionContainer<T>): Assertion
    fun <T : Path> isNotExecutable(container: AssertionContainer<T>): Assertion
    fun <T : Path> isRegularFile(container: AssertionContainer<T>): Assertion
    fun <T : Path> isDirectory(container: AssertionContainer<T>): Assertion
    fun <T : Path> isSymbolicLink(container: AssertionContainer<T>): Assertion
    fun <T : Path> isAbsolute(container: AssertionContainer<T>): Assertion
    fun <T : Path> isRelative(container: AssertionContainer<T>): Assertion

    fun <T : Path> hasDirectoryEntry(container: AssertionContainer<T>, entries: List<String>): Assertion
    fun <T : Path> isEmptyDirectory(container: AssertionContainer<T>): Assertion

    fun <T : Path> hasSameTextualContentAs(
        container: AssertionContainer<T>,
        targetPath: Path,
        sourceCharset: Charset,
        targetCharset: Charset
    ): Assertion

    fun <T : Path> hasSameBinaryContentAs(container: AssertionContainer<T>, targetPath: Path): Assertion

    fun <T : Path> fileName(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, String>
    fun <T : Path> extension(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, String>
    fun <T : Path> fileNameWithoutExtension(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, String>
    fun <T : Path> parent(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, Path>
    fun <T : Path> resolve(
        container: AssertionContainer<T>,
        other: String
    ): FeatureExtractorBuilder.ExecutionStep<T, Path>
}
