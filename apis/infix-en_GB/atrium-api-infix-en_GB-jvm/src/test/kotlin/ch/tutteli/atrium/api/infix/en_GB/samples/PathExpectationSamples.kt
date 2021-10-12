package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.creating.path.DirectoryEntries
import ch.tutteli.atrium.api.infix.en_GB.creating.path.PathWithEncoding
import ch.tutteli.atrium.api.infix.en_GB.fileName
import ch.tutteli.atrium.api.infix.en_GB.fileNameWithoutExtension
import ch.tutteli.atrium.api.infix.en_GB.notToEqual
import ch.tutteli.atrium.api.infix.en_GB.parent
import ch.tutteli.atrium.api.infix.en_GB.toEndWith
import ch.tutteli.atrium.api.infix.en_GB.toStartWith
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.niok.*
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.swing.filechooser.FileSystemView
import kotlin.test.Test

class PathExpectationSamples {

    private val tempDir = Files.createTempDirectory("PathAssertionSamples")

    @Test
    fun toBeASymbolicLink() {
        val target = tempDir.newFile("target")
        val link = Files.createSymbolicLink(tempDir.resolve("link"), target)

        // Passes, because subject `link` is a symbolic link
        expect(link) toBe aSymbolicLink

        val file = tempDir.newFile("somePath")

        fails { // because subject `path` is a not a symbolic link
            expect(file) toBe aSymbolicLink
        }
    }

    @Test
    fun toBeAnEmptyDirectory() {
        val dir = tempDir.newDirectory("test_dir")
        expect(dir) toBe anEmptyDirectory

        dir.newFile("test_file.txt")
        fails {
            expect(dir) toBe anEmptyDirectory
        }
    }

    @Test
    fun toStartWith() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) toStartWith dir.parent

        fails {
            expect(dir) toStartWith Paths.get("invalid_dir")
        }
    }

    @Test
    fun notToStartWith() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) notToStartWith Paths.get("invalid_dir")

        fails {
            expect(dir) notToStartWith dir.parent
        }
    }

    @Test
    fun toEndWith() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) toEndWith Paths.get("test_dir")

        fails {
            expect(dir) toEndWith Paths.get("invalid_dir")
        }
    }

    @Test
    fun notToEndWith() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) notToEndWith Paths.get("invalid_dir")

        fails {
            expect(dir) notToEndWith Paths.get("test_dir")
        }
    }

    @Test
    fun toExist() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) toBe existing

        fails {
            expect(Paths.get("invalid_dir")) toBe existing
        }
    }

    @Test
    fun notToExist() {
        val dir = tempDir.newDirectory("test_dir")

        expect(Paths.get("invalid_dir")) notToBe existing

        fails {
            expect(dir) notToBe existing
        }

    }

    @Test
    fun toBeReadable() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) toBe readable

        fails {
            expect(Paths.get("invalid_dir")) toBe readable
        }
    }

    @Test
    fun toBeWritable() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) toBe writable

        fails {
            expect(Paths.get("invalid_dir")) toBe writable
        }
    }

    @Test
    fun toBeExecutable() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) toBe executable

        fails {
            expect(Paths.get("invalid_dir")) toBe executable
        }
    }

    @Test
    fun toBeARegularFile() {
        val file = tempDir.newFile("test_file")
        val dir = tempDir.newDirectory("test_dir")

        expect(file) toBe aRegularFile

        fails {
            expect(dir) toBe aRegularFile
        }
    }

    @Test
    fun toBeADirectory() {
        val file = tempDir.newFile("test_file")
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) toBe aDirectory

        fails {
            expect(file) toBe aDirectory
        }
    }

    @Test
    fun toBeAbsolute() {
        val s = FileSystems.getDefault().separator
        val prefix = if (s == "\\") "C:" else "" // if (s == "\\") => true current os is windows
        expect(Paths.get("$prefix${s}absolute${s}path")) toBe absolute

        fails {
            expect(Paths.get("relative/path")) toBe absolute
        }
    }

    @Test
    fun toBeRelative() {
        expect(Paths.get("relative/path")) toBe relative

        fails {
            val s = FileSystems.getDefault().separator
            val prefix = if (s == "\\") "C:" else "" // if (s == "\\") => true current os is windows
            expect(Paths.get("$prefix${s}absolute${s}path")) toBe relative
        }
    }

    @Test
    fun toHaveTheDirectoryEntries() {
        val dir = tempDir.newDirectory("test_dir")

        val file1 = dir.newFile("test_file1")
        val file2 = dir.newFile("test_file2")

        expect(dir) toHave directoryEntries("test_file1", "test_file2")

        file1.delete()
        file2.delete()

        fails {
            expect(dir) toHave directoryEntries("test_file1", "test_file2")
        }

    }

    @Test
    fun toHaveTheSameTextualContentAs() {
        val writtenTextInFile = "test_test"

        val notEmptyFilePath = tempDir.newFile("test_file_1")
        notEmptyFilePath.writeText(writtenTextInFile)

        val expectedFilePath = tempDir.newFile("test_file_2")
        expectedFilePath.writeText(writtenTextInFile)

        val emptyFilePath = tempDir.newFile("test_file_3")

        expect(notEmptyFilePath) toHaveTheSameTextualContentAs expectedFilePath

        fails { // because nothing is written inside of `emptyFilePath`
            expect(emptyFilePath) toHaveTheSameTextualContentAs expectedFilePath
        }
    }

    @Test
    fun toHaveTheSameTextualContentAsPathWithEncoding() {
        val writtenTextInFile = "test_test"

        val notEmptyFilePath = tempDir.newFile("test_file_1")
        notEmptyFilePath.writeText(writtenTextInFile)

        val expectedFilePath = tempDir.newFile("test_file_2")
        expectedFilePath.writeText(writtenTextInFile)

        val emptyFilePath = tempDir.newFile("test_file_3")

        expect(notEmptyFilePath) toHaveTheSameTextualContentAs withEncoding(expectedFilePath, Charsets.UTF_8, Charsets.UTF_8)

        fails { // because nothing is written inside of `emptyFilePath`
            expect(emptyFilePath) toHaveTheSameTextualContentAs withEncoding(expectedFilePath, Charsets.UTF_8, Charsets.UTF_8)
        }
    }

    @Test
    fun toHaveTheSameBinaryContentAs() {
        val notEmptyFilePath = tempDir.newFile("test_file_1")
        notEmptyFilePath.writeBytes(byteArrayOf(1, 2, 3))

        val expectedFilePath = tempDir.newFile("test_file_2")
        expectedFilePath.writeBytes(byteArrayOf(1, 2, 3))

        val emptyFilePath = tempDir.newFile("test_file_3")

        expect(notEmptyFilePath) toHaveTheSameBinaryContentAs expectedFilePath

        fails { // because nothing is written inside of `emptyFilePath`
            expect(emptyFilePath) toHaveTheSameBinaryContentAs expectedFilePath
        }
    }

    @Test
    fun extensionFeature() {
        val extension = "txt"
        val dir = tempDir.newFile("test_file.$extension")

        expect(dir).extension notToBe empty toEqual extension notToEndWith("jpg")
        //          | subject is now of type String (actually "txt")

        fails {
            expect(dir).extension toEqual "txtt" toEndWith "jpg"
            //          | subject is now of type String (actually "txt")
            //          |           | fails because it doesn't equal to "txtt"
            //          |                       | not reported
            //          | use `.extension` if you want that all expectations are evaluated
        }
    }

    @Test
    fun extension() {
        val extension = "txt"
        val dir = tempDir.newDirectory("test.$extension")


        expect(dir) extension { // subject is now of type String (actually "txt")
            it notToBe empty
            it toEqual extension
            it notToEndWith "jpg"
        }

        fails {
            expect(dir) extension { // subject is now of type String (actually "txt")
                it toEqual "txtt"     // fails because it doesn't equal to "txtt"
                it toEndWith "jpg"    // fails because it doesn't end with "jpg"
                //                     use `.extension` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun fileNameFeature() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileName toEndWith "dir" toStartWith "test" notToBe blank
        //          | subject is now of type String (actually "test_dir")

        fails {
            expect(dir).fileName toEndWith "foo" toStartWith "invalid"
            //          |       |               | not reported
            //          |       | fails because it does not end with "foo"
            //          | subject is now of type String (actually "test_dir")
            //          | use `fileName {...}` if you want that all expectations are evaluated
        }
    }

    @Test
    fun fileName() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) fileName { // subject is now of type String (actually "test_dir")
            it toEndWith "dir"
            it toStartWith "test"
            it notToBe blank
        }

        fails {
            expect(dir) fileName {
                it toEndWith "foo"        // fails because it does not end with "foo"
                it toStartWith "invalid"  // still evaluated even though `toEndWith("foo")` already fails
                //                         use `.fileName` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun fileNameWithoutExtensionFeature() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileNameWithoutExtension notToBe empty toEqual "test_dir"

        fails {
            expect(dir).fileNameWithoutExtension toBe empty notToEqual "test_dir"
            //          |                           |       | not reported toBeEmpty already fails
            //          |                           | fails because string is not empty
            //          | subject is now of type String (actually "test_dir")
            // use `.fileNameWithoutExtension { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun fileNameWithoutExtension() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir) fileNameWithoutExtension { // subject is now of type String (actually "test_dir")
            it notToBe empty
            it toEqual "test_dir"
        }

        fails {                                     // because fileNameWithoutExtension equals `test_dir`
            expect(dir) fileNameWithoutExtension {  // subject is now of type String (actually "test_dir")
                it toBe empty                       // fails because string is not empty
                it notToEqual "test_dir"            // still evaluated even though `toBeEmpty()` already fails
                //                                     use `.fileNameWithoutExtension` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun parentFeature() {
        val dir = tempDir.newDirectory("test_dir_1")
        val dir2 = tempDir.newDirectory("test_dir_2")

        expect(dir).parent toEqual dir2.parent toBe existing

        fails {
            val dir3 = dir2.newDirectory("test_dir")
            expect(dir).parent toEqual(dir3) notToBe existing
            //                  |           | not reported `toEqual(dir3)` already fails
            //                  | fails because dir3 and dir does not have same parents
            // use `.parent { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun parent() {
        val dir = tempDir.newDirectory("test_dir_1")
        val dir2 = tempDir.newDirectory("test_dir_2")

        expect(dir) parent {
            it toEqual dir2.parent
            it toBe existing
        }

        fails {
            val dir3 = dir2.newDirectory("test_dir")
            expect(dir) parent {
                it toEqual dir3 // fails because dir3 and dir does not have same parents
                it notToBe existing  // still evaluated even though `toEqual(dir3)` already fails
                //               use `.parent` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun resolveFeature() {
        val dir = tempDir.newDirectory("test_dir")
        val fileInDir = dir.newFile("test_file.txt")

        expect(dir) resolve "test_file.txt" toEqual fileInDir toEndWith Paths.get("test_file.txt")

        fails {
            expect(dir) resolve "test_file.ttt" toEqual fileInDir toEndWith Paths.get("ttt")
            //          |                       |                 |  not reported `toEqual(fileInDir)` already fails
            //          |                       | fails because resolve returns *test_file.ttt and fileInDir equals *test_file.txt
            //          | use `resolve other { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun resolve() {
        val dir = tempDir.newDirectory("test_dir")
        val fileInDir = dir.newFile("test_file.txt")

        expect(dir) resolve path("test_file.txt") {
            it toEqual fileInDir
            it toBe existing
        }

        fails {
            expect(dir) resolve path("test_file.txt") {
                it toEqual fileInDir // fails
                it toBe existing     // still evaluated even though `toEqual(fileInDir)` already fails
                //                      use `.resolve(other).` if you want a fail fast behaviour
            }
        }
    }
}
