package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.niok.*
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.Test
import kotlin.test.fail

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
        val dir = tempDir.newDirectory(TEST_DIR)
        expect(dir).toBeAnEmptyDirectory()

        dir.newFile("test.txt")
        fails {
            expect(dir).toBeAnEmptyDirectory()
        }
    }

    @Test
    fun toStartWithTestDir() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir.last()).toStartWith(Paths.get(TEST_DIR))

        fails {
            expect(dir.last()).toStartWith(Paths.get(INVALID_DIR))
        }
    }

    @Test
    fun notToStartWithTestDir() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir.last()).notToStartWith(Paths.get(INVALID_DIR))

        fails {
            expect(dir.last()).notToStartWith(Paths.get(TEST_DIR))
        }
    }

    @Test
    fun toEndWithTestDir() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).toEndWith(Paths.get(TEST_DIR))

        fails {
            expect(dir).toEndWith(Paths.get(INVALID_DIR))
        }
    }

    @Test
    fun notToEndWithTestDir() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).notToEndWith(Paths.get(INVALID_DIR))

        fails {
            expect(dir).notToEndWith(Paths.get(TEST_DIR))
        }
    }

    @Test
    fun toExistTestDir() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).toExist()

        fails {
            expect(Paths.get(INVALID_DIR)).toExist()
        }
    }

    @Test
    fun notToExistTestDir() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(Paths.get(INVALID_DIR)).notToExist()

        fails {
            expect(dir).notToExist()
        }

    }

    @Test
    fun toBeReadable() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).toBeReadable()

        fails {
            expect(Paths.get(INVALID_DIR)).toBeReadable()
        }
    }

    @Test
    fun toBeWritable() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).toBeWritable()

        fails {
            expect(Paths.get(INVALID_DIR)).toBeWritable()
        }
    }

    @Test
    fun toBeExecutable() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).toBeExecutable()

        fails {
            expect(Paths.get(INVALID_DIR)).toBeExecutable()
        }
    }

    @Test
    fun toBeARegularFile() {
        val file = tempDir.newFile(TEST_FILE)
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(file).toBeARegularFile()

        fails {
            expect(dir).toBeARegularFile()
        }
    }


    @Test
    fun toBeADirectory() {
        val file = tempDir.newFile(TEST_FILE)
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).toBeADirectory()

        fails {
            expect(file).toBeADirectory()
        }
    }

    @Test
    fun toBeAbsolute() {
        val dir = tempDir.newDirectory(ABSOLUTE_DIR)

        expect(dir).toBeAbsolute()

        fails {
            expect(Paths.get(RELATIVE_DIR)).toBeAbsolute()
        }
    }

    @Test
    fun toBeRelative() {
        expect(Paths.get(RELATIVE_DIR)).toBeRelative()

        fails {
            expect(tempDir.newDirectory(ABSOLUTE_DIR)).toBeRelative()
        }
    }

    @Test
    fun toHaveDirectoryEntries() {
        val dir = tempDir.newDirectory(TEST_DIR)
        val file = dir.newFile(TEST_FILE)

        expect(dir).toHaveTheDirectoryEntries(TEST_FILE)

        file.delete()

        fails {
            expect(dir).toHaveTheDirectoryEntries(TEST_FILE)
        }

    }

    @Test
    fun toHaveSameTextualContentAs() {
        val notEmptyFilePath = tempDir.newFile("${TEST_FILE}_1")
        notEmptyFilePath.toFile().writeText("test_test")

        val expectedFilePath = tempDir.newFile("${TEST_FILE}_2")
        expectedFilePath.toFile().writeText("test_test")

        val emptyFilePath = tempDir.newFile("${TEST_FILE}_3")

        expect(notEmptyFilePath).toHaveTheSameTextualContentAs(expectedFilePath)

        fails {
            expect(emptyFilePath).toHaveTheSameTextualContentAs(expectedFilePath)
        }
    }

    @Test
    fun toHaveTheSameBinaryContentAs() {
        val notEmptyFilePath = tempDir.newFile("${TEST_FILE}_1")
        notEmptyFilePath.toFile().writeBytes(byteArrayOf(1, 2, 3))

        val expectedFilePath = tempDir.newFile("${TEST_FILE}_2")
        expectedFilePath.toFile().writeBytes(byteArrayOf(1, 2, 3))

        val emptyFilePath = tempDir.newFile("${TEST_FILE}_3")

        expect(notEmptyFilePath).toHaveTheSameBinaryContentAs(expectedFilePath)

        fails {
            expect(emptyFilePath).toHaveTheSameBinaryContentAs(expectedFilePath)
        }
    }

    @Test
    fun testPathExtensionFeature() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).extension.notToBeEmpty().toEndWith(TEST_DIR)

        fails {
            expect(dir).extension.toBeEmpty().toEndWith(INVALID_DIR)
        }
    }

    @Test
    fun testPathExtension() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).extension {
            notToBeEmpty()
            toEndWith(TEST_DIR)
        }

        fails {
            expect(dir).extension {
                toBeEmpty()
                toEndWith(INVALID_DIR)
            }
        }
    }

    @Test
    fun testFileNameFeature() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).fileName.toEndWith(TEST_DIR).notToStartWith(INVALID_DIR).notToBeBlank()

        fails {
            expect(dir).fileName.toEndWith(INVALID_DIR).toStartWith(TEST_DIR)
        }
    }

    @Test
    fun testFileName() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).fileName {
            toEndWith(TEST_DIR)
            notToStartWith(INVALID_DIR)
            notToBeBlank()
        }

        fails {
            expect(dir).fileName {
                toEndWith(INVALID_DIR)
                toStartWith(TEST_DIR)
            }
        }
    }

    @Test
    fun testFileNameWithoutExtensionFeature() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).fileNameWithoutExtension.notToBeEmpty().toEqual(TEST_DIR)

        fails {
            expect(dir).fileNameWithoutExtension.toBeEmpty().notToEqual(TEST_DIR)
        }
    }

    @Test
    fun testFileNameWithoutExtension() {
        val dir = tempDir.newDirectory(TEST_DIR)

        expect(dir).fileNameWithoutExtension {
            notToBeEmpty()
            toEqual(TEST_DIR)
        }

        fails {
            expect(dir).fileNameWithoutExtension {
                toBeEmpty()
                notToEqual(TEST_DIR)
            }
        }
    }

    @Test
    fun testParentFeature() {
        val dir = tempDir.newFile("${TEST_DIR}_1")
        val dir2 = tempDir.newFile("${TEST_DIR}_2")

        expect(dir).parent.toEqual(dir2.parent)

        fails {
            expect(dir).parent.toEqual(dir2.newFile(TEST_DIR))
        }
    }

    @Test
    fun testParent() {
        val dir = tempDir.newFile("${TEST_DIR}_1")
        val dir2 = tempDir.newFile("${TEST_DIR}_2")

        expect(dir).parent {
            toEqual(dir2.parent)
        }

        fails {
            expect(dir).parent {
                toEqual(dir2.newFile(TEST_DIR))
            }
        }
    }

    private companion object {
        private const val TEST_DIR = "test_dir"
        private const val TEST_FILE = "test_file"
        private const val INVALID_DIR = "invalid_dir"

        private const val RELATIVE_DIR = "relative_dir"
        private const val ABSOLUTE_DIR = "absolute_dir"
    }

}
