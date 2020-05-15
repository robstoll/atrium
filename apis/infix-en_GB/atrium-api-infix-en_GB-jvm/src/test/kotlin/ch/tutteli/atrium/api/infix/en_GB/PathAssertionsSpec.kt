package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.path
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import java.nio.charset.Charset
import java.nio.file.Path
import java.nio.file.Paths

class PathAssertionsSpec : ch.tutteli.atrium.specs.integration.PathAssertionsSpec(
    "toBe ${existing::class.simpleName}" to Companion::exists,
    "notToBe ${existing::class.simpleName}" to Companion::existsNot,
    fun1(Expect<Path>::startsWith),
    fun1(Expect<Path>::startsNotWith),
    fun1(Expect<Path>::endsWith),
    fun1(Expect<Path>::endsNotWith),
    "toBe ${readable::class.simpleName}" to Companion::isReadable,
    "toBe ${writable::class.simpleName}" to Companion::isWritable,
    "toBe ${aRegularFile::class.simpleName}" to Companion::isRegularFile,
    "toBe ${aDirectory::class.simpleName}" to Companion::isDirectory,
    "not supported in this API - hasSameBinaryContentAs" to Companion::hasSameBinaryContentAs,
    "not supported in this API - hasSameTextualContentAs" to Companion::hasSameTextualContentAs
) {
    companion object : WithAsciiReporter() {

        private fun exists(expect: Expect<Path>) = expect toBe existing
        private fun existsNot(expect: Expect<Path>) = expect notToBe existing
        private fun isReadable(expect: Expect<Path>) = expect toBe readable
        private fun isWritable(expect: Expect<Path>) = expect toBe writable
        private fun isRegularFile(expect: Expect<Path>) = expect toBe aRegularFile
        private fun isDirectory(expect: Expect<Path>) = expect toBe aDirectory

        private fun hasSameTextualContentAs(expect: Expect<Path>, targetPath: Path, sourceCharset: Charset, targetCharset: Charset): Expect<Path> =
            expect.addAssertion(ExpectImpl.path.hasSameTextualContentAs(expect, targetPath, sourceCharset, targetCharset))

        private fun hasSameBinaryContentAs(expect: Expect<Path>, targetPath: Path): Expect<Path> =
            expect.addAssertion(ExpectImpl.path.hasSameBinaryContentAs(expect, targetPath))
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<DummyPath> = notImplemented()

        a1 toBe existing
        a1 notToBe existing
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
