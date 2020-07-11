//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.nio.charset.Charset
import java.nio.file.Path
import java.util.*

fun <T : Path> AssertionContainer<T>.startsWith(expected: Path): Assertion = _pathImpl.startsWith(this, expected)
fun <T : Path> AssertionContainer<T>.startsNotWith(expected: Path): Assertion = _pathImpl.startsNotWith(this, expected)
fun <T : Path> AssertionContainer<T>.endsWith(expected: Path): Assertion = _pathImpl.endsWith(this, expected)
fun <T : Path> AssertionContainer<T>.endsNotWith(expected: Path): Assertion = _pathImpl.endsNotWith(this, expected)

fun <T : Path> AssertionContainer<T>.exists(): Assertion = _pathImpl.exists(this)
fun <T : Path> AssertionContainer<T>.existsNot(): Assertion = _pathImpl.existsNot(this)

fun <T : Path> AssertionContainer<T>.isReadable(): Assertion = _pathImpl.isReadable(this)
fun <T : Path> AssertionContainer<T>.isWritable(): Assertion = _pathImpl.isWritable(this)
fun <T : Path> AssertionContainer<T>.isRegularFile(): Assertion = _pathImpl.isRegularFile(this)
fun <T : Path> AssertionContainer<T>.isDirectory(): Assertion = _pathImpl.isDirectory(this)

fun <T : Path> AssertionContainer<T>.hasSameTextualContentAs(targetPath: Path, sourceCharset: Charset, targetCharset: Charset): Assertion =
    _pathImpl.hasSameTextualContentAs(this, targetPath, sourceCharset, targetCharset)

fun <T : Path> AssertionContainer<T>.hasSameBinaryContentAs(targetPath: Path): Assertion = _pathImpl.hasSameBinaryContentAs(this, targetPath)

fun <T : Path> AssertionContainer<T>.fileName(): ExtractedFeaturePostStep<T, String> = _pathImpl.fileName(this)
fun <T : Path> AssertionContainer<T>.extension(): ExtractedFeaturePostStep<T, String> = _pathImpl.extension(this)
fun <T : Path> AssertionContainer<T>.fileNameWithoutExtension(): ExtractedFeaturePostStep<T, String> = _pathImpl.fileNameWithoutExtension(this)
fun <T : Path> AssertionContainer<T>.parent(): ExtractedFeaturePostStep<T, Path> = _pathImpl.parent(this)
fun <T : Path> AssertionContainer<T>.resolve(other: String): ExtractedFeaturePostStep<T, Path> = _pathImpl.resolve(this, other)
