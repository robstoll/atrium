package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import java.nio.file.Path
import java.nio.file.Paths

class PathExpectationsSpec : ch.tutteli.atrium.specs.integration.PathExpectationsSpec(
    fun0(Expect<Path>::toExist),
    fun0(Expect<Path>::notToExist),
    fun1(Expect<Path>::toStartWith),
    fun1(Expect<Path>::notToStartWith),
    fun1(Expect<Path>::toEndWith),
    fun1(Expect<Path>::notToEndWith),
    fun0(Expect<Path>::toBeReadable),
    fun0(Expect<Path>::toBeWritable),
    fun0(Expect<Path>::notToBeWritable),
    fun0(Expect<Path>::toBeExecutable),
    fun0(Expect<Path>::toBeARegularFile),
    fun0(Expect<Path>::toBeADirectory),
    fun0(Expect<Path>::toBeASymbolicLink),
    fun0(Expect<Path>::toBeAbsolute),
    fun0(Expect<Path>::toBeRelative),
    fun0(Expect<Path>::toBeAnEmptyDirectory),
    Expect<Path>::toHaveTheDirectoryEntries.name to Companion::toHaveTheDirectoryEntry,
    fun2<Path, String, Array<out String>>(Expect<Path>::toHaveTheDirectoryEntries),
    fun1(Expect<Path>::toHaveTheSameBinaryContentAs),
    fun3(Expect<Path>::toHaveTheSameTextualContentAs),
    Expect<Path>::toHaveTheSameTextualContentAs.name to Companion::toHaveTheSameTextualContentAsDefaultArgs,
    property<Path, Path>(Expect<Path>::parent),
    fun1<Path, Expect<Path>.() -> Unit>(Expect<Path>::parent),
    feature1<Path, String, Path>(Expect<Path>::resolve),
    fun2<Path, String,  Expect<Path>.() -> Unit>(Expect<Path>::resolve),
    property<Path, String>(Expect<Path>::fileName),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::fileName),
    property<Path, String>(Expect<Path>::fileNameWithoutExtension),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::fileNameWithoutExtension),
    property<Path, String>(Expect<Path>::extension),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::extension)
) {

    companion object {
        private fun toHaveTheSameTextualContentAsDefaultArgs(expect: Expect<Path>, targetPath: Path): Expect<Path> =
            expect.toHaveTheSameTextualContentAs(targetPath)

        private fun toHaveTheDirectoryEntry(expect: Expect<Path>, entry: String) =
            expect.toHaveTheDirectoryEntries(entry)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<DummyPath> = notImplemented()

        a1.toExist()
        a1.notToExist()
        a1.toStartWith(Paths.get("a"))
        a1.notToStartWith(Paths.get("a"))
        a1.toEndWith(Paths.get("a"))
        a1.notToEndWith(Paths.get("a"))
        a1.toBeReadable()
        a1.toBeWritable()
        a1.notToBeWritable()
        a1.toBeExecutable()
        a1.toBeARegularFile()
        a1.toBeADirectory()
        a1.toBeASymbolicLink()
        a1.toHaveTheSameBinaryContentAs(Paths.get("a"))
        a1.toHaveTheSameTextualContentAs(Paths.get("a"))

        a1.parent
        a1 = a1.parent { }

        a1.fileName
        a1 = a1.fileName { }

        a1.fileNameWithoutExtension
        a1 = a1.fileNameWithoutExtension { }

        a1.extension
        a1 = a1.extension { }

        a1.resolve("test")
        a1.resolve("test") {}
    }
}

class DummyPath(path: Path) : Path by path
