package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.fileSystemSupportsCreatingSymlinks
import ch.tutteli.atrium.specs.fileSystemSupportsPosixPermissions
import ch.tutteli.atrium.specs.integration.AbstractPathExpectationsTest
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermissions
import kotlin.test.Test

class PathExpectationsTest : AbstractPathExpectationsTest(
    fun0(Expect<Path>::toExist),
    fun0(Expect<Path>::notToExist),
    fun1(Expect<Path>::toStartWith),
    fun1(Expect<Path>::notToStartWith),
    fun1(Expect<Path>::toEndWith),
    fun1(Expect<Path>::notToEndWith),
    fun0(Expect<Path>::toBeReadable),
    fun0(Expect<Path>::notToBeReadable),
    fun0(Expect<Path>::toBeWritable),
    fun0(Expect<Path>::notToBeWritable),
    fun0(Expect<Path>::toBeExecutable),
    fun0(Expect<Path>::notToBeExecutable),
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
    fun2<Path, String, Expect<Path>.() -> Unit>(Expect<Path>::resolve),
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

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE", "unused")
    @Test
    fun ambiguityTest() {
        val tempDir = Files.createTempDirectory("PathExpectationsTest")
        val file = Files.createTempFile(tempDir, "file", ".txt")

        var a1: Expect<Path> = expect(file)
        a1.toExist()
        a1.toBeReadable()
        a1.toBeWritable()
        a1.toBeARegularFile()
        a1.toBeAbsolute()
        a1.toStartWith(tempDir)
        a1.notToStartWith(Paths.get("a"))
        a1.toEndWith(file.fileName)
        a1.notToEndWith(Paths.get("a"))

        a1 = expect(tempDir.resolve("does-not-exist"))
        a1.notToExist()
        if (fileSystemSupportsPosixPermissions()) {
            val notReadable = Files.createTempFile(tempDir, "not-readable", ".txt")
            Files.setPosixFilePermissions(notReadable, PosixFilePermissions.fromString("-w-------"))
            a1 = expect(notReadable)
            a1.notToBeReadable()
            a1.notToBeExecutable()

            val notWritable = Files.createTempFile(tempDir, "not-writable", ".txt")
            Files.setPosixFilePermissions(notWritable, PosixFilePermissions.fromString("r--------"))
            a1 = expect(notWritable)
            a1.notToBeWritable()
        }

        a1 = expect(tempDir)
        a1.toBeADirectory()
        a1.toBeExecutable()

        if (fileSystemSupportsCreatingSymlinks()) {
            val link = Files.createSymbolicLink(tempDir.resolve("linkToMyFile"), file)
            a1 = expect(link)
            a1.toBeASymbolicLink()
        }

        val source = Files.createTempFile(tempDir, "source", ".txt")
        Files.write(source, byteArrayOf(1, 2, 3))
        val target = Files.createTempFile(tempDir, "target", ".txt")
        Files.write(target, byteArrayOf(1, 2, 3))
        a1 = expect(source)
        a1.toHaveTheSameBinaryContentAs(target)
        a1.toHaveTheSameTextualContentAs(target)

        a1 = expect(Paths.get("a/my.txt"))
        a1.parent
        a1 = a1.parent { toEqual(Paths.get("a")) }

        a1.fileName
        a1 = a1.fileName { toEqual("my.txt") }

        a1.fileNameWithoutExtension
        a1 = a1.fileNameWithoutExtension { toEqual("my") }

        a1.extension
        a1 = a1.extension { toEqual("txt") }

        a1.resolve("test")
        a1 = a1.resolve("test") { toEqual(Paths.get("a/my.txt/test")) }
    }
}
