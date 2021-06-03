package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.niok.*
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.filechooser.FileSystemView
import kotlin.test.AfterTest
import kotlin.test.Test

class PathExpectationSamples {

    private val tempDir = Files.createTempDirectory("PathAssertionSamples")

    @Test
    fun toBeASymbolicLink() {
        val target = tempDir.newFile("target")
        val link = Files.createSymbolicLink(tempDir.resolve("link"), target)

        // Passes, because subject `link` is a symbolic link
        expect(link).toBeASymbolicLink()

        val file = tempDir.newFile("somePath")

        fails { // because subject `path` is a not a symbolic link
            expect(file).toBeASymbolicLink()
        }
    }

    @Test
    fun toBeAnEmptyDirectory() {
        val dir = tempDir.newDirectory("test_dir")
        expect(dir).toBeAnEmptyDirectory()

        dir.newFile("test_file.txt")
        fails {
            expect(dir).toBeAnEmptyDirectory()
        }
    }

    @Test
    fun toStartWith() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).toStartWith(dir.parent)

        fails {
            expect(dir).toStartWith(Paths.get("invalid_dir"))
        }
    }

    @Test
    fun notToStartWith() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir.last()).notToStartWith(Paths.get("invalid_dir"))

        fails {
            expect(dir.last()).notToStartWith(Paths.get("test_dir"))
        }
    }

    @Test
    fun toEndWith() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).toEndWith(Paths.get("test_dir"))

        fails {
            expect(dir).toEndWith(Paths.get("invalid_dir"))
        }
    }

    @Test
    fun notToEndWith() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).notToEndWith(Paths.get("invalid_dir"))

        fails {
            expect(dir).notToEndWith(Paths.get("test_dir"))
        }
    }

    @Test
    fun toExist() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).toExist()

        fails {
            expect(Paths.get("invalid_dir")).toExist()
        }
    }

    @Test
    fun notToExist() {
        val dir = tempDir.newDirectory("test_dir")

        expect(Paths.get("invalid_dir")).notToExist()

        fails {
            expect(dir).notToExist()
        }

    }

    @Test
    fun toBeReadable() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).toBeReadable()

        fails {
            expect(Paths.get("invalid_dir")).toBeReadable()
        }
    }

    @Test
    fun toBeWritable() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).toBeWritable()

        fails {
            expect(Paths.get("invalid_dir")).toBeWritable()
        }
    }

    @Test
    fun toBeExecutable() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).toBeExecutable()

        fails {
            expect(Paths.get("invalid_dir")).toBeExecutable()
        }
    }

    @Test
    fun toBeARegularFile() {
        val file = tempDir.newFile("test_file")
        val dir = tempDir.newDirectory("test_dir")

        expect(file).toBeARegularFile()

        fails {
            expect(dir).toBeARegularFile()
        }
    }


    @Test
    fun toBeADirectory() {
        val file = tempDir.newFile("test_file")
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).toBeADirectory()

        fails {
            expect(file).toBeADirectory()
        }
    }

    @Test
    fun toBeAbsolute() {
        val fooAbsolutePath = FileSystemView.getFileSystemView().homeDirectory.toPath().resolve("foo")
        expect(fooAbsolutePath).toBeAbsolute()

        fails {
            expect(Paths.get("relative/path")).toBeAbsolute()
        }
    }

    @Test
    fun toBeRelative() {
        expect(Paths.get("relative/path")).toBeRelative()

        fails {
            val fooAbsolutePath = FileSystemView.getFileSystemView().homeDirectory.toPath().resolve("foo")
            expect(fooAbsolutePath).toBeRelative()
        }
    }

    @Test
    fun toHaveTheDirectoryEntries() {
        val dir = tempDir.newDirectory("test_dir")

        val file1 = dir.newFile("test_file1")
        val file2 = dir.newFile("test_file2")

        expect(dir).toHaveTheDirectoryEntries("test_file1", "test_file2")

        file1.delete()
        file2.delete()

        fails {
            expect(dir).toHaveTheDirectoryEntries("test_file1", "test_file2")
        }

    }

    @Test
    fun toHaveTheSameTextualContentAs() {
        val writtenTextInFile = "test_test"

        val notEmptyFilePath = tempDir.newFile("${"test_file"}_1")
        notEmptyFilePath.writeText(writtenTextInFile)

        val expectedFilePath = tempDir.newFile("${"test_file"}_2")
        expectedFilePath.writeText(writtenTextInFile)

        val emptyFilePath = tempDir.newFile("${"test_file"}_3")

        expect(notEmptyFilePath).toHaveTheSameTextualContentAs(expectedFilePath)

        fails {
            expect(emptyFilePath).toHaveTheSameTextualContentAs(expectedFilePath)
        }
    }

    @Test
    fun toHaveTheSameBinaryContentAs() {
        val notEmptyFilePath = tempDir.newFile("${"test_file"}_1")
        notEmptyFilePath.writeBytes(byteArrayOf(1, 2, 3))

        val expectedFilePath = tempDir.newFile("${"test_file"}_2")
        expectedFilePath.writeBytes(byteArrayOf(1, 2, 3))

        val emptyFilePath = tempDir.newFile("${"test_file"}_3")

        expect(notEmptyFilePath).toHaveTheSameBinaryContentAs(expectedFilePath)

        fails {
            expect(emptyFilePath).toHaveTheSameBinaryContentAs(expectedFilePath)
        }
    }

    @Test
    fun pathExtensionFeature() {
        val extension = "txt"
        val dir = tempDir.newFile("test_file.$extension")

        expect(dir).extension.notToBeEmpty().toEqual(extension)

        fails {
            val invalidExtension = "txtt"
            expect(dir).extension.toEqual(invalidExtension)
        }
    }

    @Test
    fun pathExtension() {
        val extension = "txt"
        val dir = tempDir.newDirectory("test.$extension")

        expect(dir).extension {
            notToBeEmpty()
            toEqual(extension)
        }

        fails {
            expect(dir).extension {
                toEqual("txtt")
            }
        }
    }

    @Test
    fun fileNameFeature() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileName.toEndWith("test_dir").notToStartWith("invalid_dir").notToBeBlank()

        fails {
            expect(dir).fileName.toEndWith("invalid_dir").toStartWith("test_dir")
        }
    }

    @Test
    fun fileName() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileName {
            toEndWith("test_dir")
            notToStartWith("invalid_dir")
            notToBeBlank()
        }

        fails {
            expect(dir).fileName {
                toEndWith("invalid_dir")
                toStartWith("test_dir")
            }
        }
    }

    @Test
    fun fileNameWithoutExtensionFeature() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileNameWithoutExtension.notToBeEmpty().toEqual("test_dir")

        fails {
            expect(dir).fileNameWithoutExtension.toBeEmpty().notToEqual("test_dir")
        }
    }

    @Test
    fun fileNameWithoutExtension() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileNameWithoutExtension {
            notToBeEmpty()
            toEqual("test_dir")
        }

        fails {
            expect(dir).fileNameWithoutExtension {
                toBeEmpty()
                notToEqual("test_dir")
            }
        }
    }

    @Test
    fun parentFeature() {
        val dir = tempDir.newDirectory("${"test_dir"}_1")
        val dir2 = tempDir.newDirectory("${"test_dir"}_2")

        expect(dir).parent.toEqual(dir2.parent)

        fails {
            expect(dir).parent.toEqual(dir2.newDirectory("test_dir"))
        }
    }

    @Test
    fun parent() {
        val dir = tempDir.newDirectory("${"test_dir"}_1")
        val dir2 = tempDir.newDirectory("${"test_dir"}_2")

        expect(dir).parent {
            toEqual(dir2.parent)
        }

        fails {
            expect(dir).parent {
                toEqual(dir2.newDirectory("test_dir"))
            }
        }
    }

    @Test
    fun resolveFeature() {
        val dir = tempDir.newDirectory("test_dir")
        val fileInDir = dir.newFile("test_file.txt")

        expect(dir).resolve("test_file.txt").toEqual(fileInDir)

        fails {
            expect(dir).resolve("test_file.ttt").toEqual(fileInDir)
        }
    }

    @Test
    fun resolve() {
        val dir = tempDir.newDirectory("test_dir")
        val fileInDir = dir.newFile("test_file.txt")

        expect(dir).resolve("test_file.txt") {
            toEqual(fileInDir)
            toExist()
        }

        fails {
            expect(dir).resolve("test_file.ttt") {
                toEqual(fileInDir)
            }
        }
    }

}
