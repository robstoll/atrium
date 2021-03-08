package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import java.nio.file.Path
import java.nio.file.Paths

class PathExpectationsSpec : ch.tutteli.atrium.specs.integration.PathExpectationsSpec(
    fun0(Expect<Path>::exists),
    fun0(Expect<Path>::existsNot),
    fun1(Expect<Path>::startsWith),
    fun1(Expect<Path>::startsNotWith),
    fun1(Expect<Path>::endsWith),
    fun1(Expect<Path>::endsNotWith),
    fun0(Expect<Path>::isReadable),
    fun0(Expect<Path>::isWritable),
    fun0(Expect<Path>::isExecutable),
    fun0(Expect<Path>::isRegularFile),
    fun0(Expect<Path>::isDirectory),
    fun0(Expect<Path>::toBeASymbolicLink),
    fun0(Expect<Path>::isAbsolute),
    fun0(Expect<Path>::isRelative),
    fun0(Expect<Path>::isEmptyDirectory),
    Expect<Path>::hasDirectoryEntry.name to Companion::hasDirectoryEntrySingle,
    fun2<Path, String, Array<out String>>(Expect<Path>::hasDirectoryEntry),
    fun1(Expect<Path>::hasSameBinaryContentAs),
    fun3(Expect<Path>::hasSameTextualContentAs),
    Expect<Path>::hasSameTextualContentAs.name to Companion::hasSameTextualContentAsDefaultArgs,
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
        private fun hasSameTextualContentAsDefaultArgs(expect: Expect<Path>, targetPath: Path): Expect<Path> =
            expect.hasSameTextualContentAs(targetPath)

        private fun hasDirectoryEntrySingle(expect: Expect<Path>, entry: String) = expect.hasDirectoryEntry(entry)
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<DummyPath> = notImplemented()

        a1.exists()
        a1.existsNot()
        a1.startsWith(Paths.get("a"))
        a1.startsNotWith(Paths.get("a"))
        a1.endsWith(Paths.get("a"))
        a1.endsNotWith(Paths.get("a"))
        a1.isReadable()
        a1.isWritable()
        a1.isRegularFile()
        a1.isDirectory()
        a1.toBeASymbolicLink()
        a1.hasSameBinaryContentAs(Paths.get("a"))
        a1.hasSameTextualContentAs(Paths.get("a"))

        a1.parent
        a1 = a1.parent { }

        a1.fileName
        a1 = a1.fileName { }

        a1.fileNameWithoutExtension
        a1 = a1.fileNameWithoutExtension { }

        a1.extension
        a1 = a1.extension { }

        a1.resolve("test")
        a1.resolve("test", {})
    }
}

class DummyPath(path: Path) : Path by path
