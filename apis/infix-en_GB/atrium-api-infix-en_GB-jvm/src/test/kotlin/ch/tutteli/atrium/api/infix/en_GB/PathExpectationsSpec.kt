package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import java.nio.charset.Charset
import java.nio.file.Path
import java.nio.file.Paths

class PathExpectationsSpec : ch.tutteli.atrium.specs.integration.PathExpectationsSpec(
    "toBe ${existing::class.simpleName}" to Companion::toExist,
    "notToBe ${existing::class.simpleName}" to Companion::notToExist,
    fun1(Expect<Path>::toStartWith),
    fun1(Expect<Path>::notToStartWith),
    fun1(Expect<Path>::toEndWith),
    fun1(Expect<Path>::notToEndWith),
    "toBe ${readable::class.simpleName}" to Companion::toBeReadable,
    "toBe ${writable::class.simpleName}" to Companion::toBeWritable,
    "notToBe ${writable::class.simpleName}" to Companion::notToBeWritable,
    "toBe ${executable::class.simpleName}" to Companion::toBeExecutable,
    "toBe ${aRegularFile::class.simpleName}" to Companion::toBeRegularFile,
    "toBe ${aDirectory::class.simpleName}" to Companion::toBeADirectory,
    "toBe ${aSymbolicLink::class.simpleName}" to Companion::toBeASymbolicLink,
    "toBe ${relative::class.simpleName}" to Companion::toBeAbsolute,
    "toBe ${relative::class.simpleName}" to Companion::toBeRelative,
    "toBe ${relative::class.simpleName}" to Companion::toBeAnEmptyDirectory,
    "toHave ${::directoryEntries.name}" to Companion::toHaveTheDirectoryEntry,
    "toHave ${::directoryEntries.name}" to Companion::toHaveTheDirectoryEntries,
    fun1(Expect<Path>::toHaveTheSameBinaryContentAs),
    fun3(Companion::toHaveTheSameTextualContentAs),
    fun1(Companion::toHaveTheSameTextualContentAsDefaultArgs),
    property<Path, Path>(Expect<Path>::parent),
    fun1<Path, Expect<Path>.() -> Unit>(Expect<Path>::parent),
    feature1<Path, String, Path>(Expect<Path>::resolve),
    fun2(PathExpectationsSpec.Companion::resolve),
    property<Path, String>(Expect<Path>::fileName),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::fileName),
    property<Path, String>(Expect<Path>::fileNameWithoutExtension),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::fileNameWithoutExtension),
    property<Path, String>(Expect<Path>::extension),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::extension)
) {

    companion object {

        private fun toExist(expect: Expect<Path>) = expect toBe existing
        private fun notToExist(expect: Expect<Path>) = expect notToBe existing
        private fun toBeReadable(expect: Expect<Path>) = expect toBe readable
        private fun toBeWritable(expect: Expect<Path>) = expect toBe writable
        private fun notToBeWritable(expect: Expect<Path>) = expect notToBe writable
        private fun toBeExecutable(expect: Expect<Path>) = expect toBe executable
        private fun toBeRegularFile(expect: Expect<Path>) = expect toBe aRegularFile
        private fun toBeADirectory(expect: Expect<Path>) = expect toBe aDirectory
        private fun toBeASymbolicLink(expect: Expect<Path>) = expect toBe aSymbolicLink
        private fun toBeAbsolute(expect: Expect<Path>) = expect toBe absolute
        private fun toBeRelative(expect: Expect<Path>) = expect toBe relative
        private fun toBeAnEmptyDirectory(expect: Expect<Path>) = expect toBe anEmptyDirectory

        private fun toHaveTheDirectoryEntry(expect: Expect<Path>, entry: String) =
            expect toHave directoryEntries(entry)

        private fun toHaveTheDirectoryEntries(expect: Expect<Path>, entry: String, vararg otherEntries: String) =
            expect toHave directoryEntries(entry, *otherEntries)

        private fun toHaveTheSameTextualContentAs(
            expect: Expect<Path>,
            targetPath: Path,
            sourceCharset: Charset,
            targetCharset: Charset
        ): Expect<Path> = expect toHaveTheSameTextualContentAs withEncoding(targetPath, sourceCharset, targetCharset)

        private fun toHaveTheSameTextualContentAsDefaultArgs(
            expect: Expect<Path>,
            targetPath: Path
        ): Expect<Path> = expect toHaveTheSameTextualContentAs targetPath

        private fun resolve(
            expect: Expect<Path>,
            other: String,
            assertionCreator: Expect<Path>.() -> Unit
        ): Expect<Path> = expect resolve path(other) { assertionCreator() }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<DummyPath> = notImplemented()

        a1 toBe existing
        a1 notToBe existing
        a1 toStartWith Paths.get("a")
        a1 notToStartWith Paths.get("a")
        a1 toEndWith Paths.get("a")
        a1 notToEndWith Paths.get("a")
        a1 toBe readable
        a1 toBe writable
        a1 notToBe writable
        a1 toBe executable
        a1 toBe aRegularFile
        a1 toBe aDirectory
        a1 toBe absolute
        a1 toBe relative
        a1 toHaveTheSameTextualContentAs withEncoding(Paths.get("a"))
        a1 toHaveTheSameTextualContentAs Paths.get("a")
        a1 toHaveTheSameBinaryContentAs  Paths.get("a")
        a1 resolve "a"
        a1 toHave directoryEntries("a")
        a1 toHave directoryEntries("a", "b", "c")

        a1.fileName
        a1 fileName {}

        a1.fileNameWithoutExtension
        a1 fileNameWithoutExtension {}

        a1.extension
        a1 extension {}

        a1.parent
        a1 parent {}

        a1 resolve "test"
        a1 resolve path("test") {}
    }
}

class DummyPath(path: Path) : Path by path
