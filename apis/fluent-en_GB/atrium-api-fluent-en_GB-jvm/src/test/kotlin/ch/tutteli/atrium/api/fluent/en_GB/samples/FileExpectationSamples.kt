package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.niok.newFile
import java.nio.file.Files
import kotlin.test.Test

class FileExpectationSamples {

    private val tempDir = Files.createTempDirectory("FileAssertionSamples")

    @Test
    fun asPathFeature() {
        val file = tempDir.newFile("target").toFile()

        expect(file)
          .asPath() // subject is now of type Path
          .toBeARegularFile()

        fails {
            expect(file)
              .asPath()
              .toBeADirectory()  //fails
              .toBe...
        }
    }

    @Test
    fun asPath() {
        val file = tempDir.newFile("target").toFile()

        expect(file).asPath { // subject within this block is of type Path
            toBeARegularFile()
            toBeWritable()
            toStartWith(tempDir)
        } // subject here is back to type File

        fails {
            expect(file).asPath {
                toBeADirectory()
                notToBeWritable()
                notToStartWith(tempDir)
            }
        }
    }
}
