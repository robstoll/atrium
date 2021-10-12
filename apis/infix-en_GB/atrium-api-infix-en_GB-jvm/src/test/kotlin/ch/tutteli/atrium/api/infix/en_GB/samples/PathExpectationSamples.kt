package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.creating.path.DirectoryEntries
import ch.tutteli.atrium.api.infix.en_GB.creating.path.PathWithEncoding
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
}
