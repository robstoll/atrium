package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.path
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Path
import java.nio.file.Paths

class PathAssertionsSpec : ch.tutteli.atrium.specs.integration.PathAssertionsSpec(
    fun1<Path, exist>(Expect<Path>::to).name to Companion::exists,
    fun1<Path, exist>(Expect<Path>::notTo).name to Companion::existsNot,
    fun1(Expect<Path>::startsWith),
    fun1(Expect<Path>::startsNotWith),
    fun1(Expect<Path>::endsWith),
    fun1(Expect<Path>::endsNotWith),
    fun1<Path, readable>(Expect<Path>::toBe).name to Companion::isReadable,
    fun1<Path, writable>(Expect<Path>::toBe).name to Companion::isWritable,
    fun1<Path, aRegularFile>(Expect<Path>::toBe).name to Companion::isRegularFile,
    fun1<Path, aDirectory>(Expect<Path>::toBe).name to Companion::isDirectory,
    "not supported in this API - hasSameBinaryContentAs" to Companion::hasSameBinaryContentAs,
    "not supported in this API - hasSameTextualContentAs" to Companion::hasSameTextualContentAs
) {
    companion object : WithAsciiReporter() {

        private fun exists(expect: Expect<Path>) = expect to exist
        private fun existsNot(expect: Expect<Path>) = expect notTo exist
        private fun isReadable(expect: Expect<Path>) = expect toBe readable
        private fun isWritable(expect: Expect<Path>) = expect toBe writable
        private fun isRegularFile(expect: Expect<Path>) = expect toBe aRegularFile
        private fun isDirectory(expect: Expect<Path>) = expect toBe aDirectory
        private fun hasSameTextualContentAs(expect: Expect<Path>, targetPath: Path, sourceCharset: Charset, targetCharset: Charset): Expect<Path> {
            return expect
        }
        private fun hasSameBinaryContentAs(expect: Expect<Path>, targetPath: Path): Expect<Path> {
            return expect
        }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<DummyPath> = notImplemented()

        a1 to exist
        a1 notTo exist
        a1 startsWith Paths.get("a")
        a1 startsNotWith Paths.get("a")
        a1 endsWith Paths.get("a")
        a1 endsNotWith Paths.get("a")
        a1 toBe readable
        a1 toBe writable
        a1 toBe aRegularFile
        a1 toBe aDirectory
    }
}

class DummyPath(path: Path) : Path by path
