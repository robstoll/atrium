@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.nio.charset.Charset
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
    fun <T : Path> fileName(expect: Expect<T>): ExtractedFeaturePostStep<T, String>
    fun <T : Path> extension(expect: Expect<T>): ExtractedFeaturePostStep<T, String>
    fun <T : Path> fileNameWithoutExtension(expect: Expect<T>): ExtractedFeaturePostStep<T, String>
    fun <T : Path> parent(expect: Expect<T>): ExtractedFeaturePostStep<T, Path>
    fun <T : Path> resolve(expect: Expect<T>, other: String): ExtractedFeaturePostStep<T, Path>

    fun <T : Path> startsWith(expect: Expect<T>, expected: Path): Assertion
    fun <T : Path> startsNotWith(expect: Expect<T>, expected: Path): Assertion
    fun <T : Path> endsWith(expect: Expect<T>, expected: Path): Assertion
    fun <T : Path> endsNotWith(expect: Expect<T>, expected: Path): Assertion

    fun <T : Path> exists(expect: Expect<T>): Assertion
    fun <T : Path> existsNot(expect: Expect<T>): Assertion

    fun <T : Path> isReadable(expect: Expect<T>): Assertion
    fun <T : Path> isWritable(expect: Expect<T>): Assertion
    fun <T : Path> isRegularFile(expect: Expect<T>): Assertion
    fun <T : Path> isDirectory(expect: Expect<T>): Assertion

    fun <T : Path> hasSameTextualContentAs(expect: Expect<T>, targetPath: Path, sourceCharset: Charset, targetCharset: Charset): Assertion
    fun <T : Path> hasSameBinaryContentAs(expect: Expect<T>, targetPath: Path): Assertion
}
