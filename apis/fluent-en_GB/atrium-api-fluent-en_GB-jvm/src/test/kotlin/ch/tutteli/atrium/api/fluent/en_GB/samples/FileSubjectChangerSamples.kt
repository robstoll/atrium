package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.niok.newFile
import java.nio.file.Files
import kotlin.test.Test

class FileSubjectChangerSamples {

    private val tempDir = Files.createTempDirectory("FileAssertionSamples")

    @Test
    fun asPathFeature() {
        val file = tempDir.newFile("target").toFile()

        expect(file)
            .asPath() // subject is now of type Path
            .toBeARegularFile()

        fails {
            expect(file)
                .asPath()                // subject is now of type Path
                .toBeADirectory()        // fails
                .notToStartWith(tempDir) // not reported because `cause` already fails
            //                              use `.asPath { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun asPath() {
        val file = tempDir.newFile("target").toFile()

        expect(file).asPath { // subject within this block is of type Path
            toBeARegularFile()
            toStartWith(tempDir)
        } // subject here is back to type File

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(file).asPath {
                toBeADirectory()        // fails
                notToStartWith(tempDir) // still evaluated even though `toBeADirectory` already fails
                //                         use `.asPath().` if you want a fail fast behaviour
            }
        }
    }
}
