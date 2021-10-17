package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.niok.newFile
import java.nio.file.Files
import kotlin.test.Test

class FileExpectationSamples {

    private val tempDir = Files.createTempDirectory("FileAssertionSamples")

    @Test
    fun asPath() {
        val file = tempDir.newFile("target").toFile()

        expect(file).asPath().toBeARegularFile()

        fails {
            expect(file).asPath().toBeADirectory()
        }
    }

    @Test
    fun asPathWithAssertionCreator() {
        val file = tempDir.newFile("target").toFile()

        expect(file).asPath {
            toBeARegularFile()
            toBeWritable()
            toStartWith(tempDir)
        }

        fails {
            expect(file).asPath {
                toBeADirectory()
                notToBeWritable()
                notToStartWith(tempDir)
            }
        }
    }
}
