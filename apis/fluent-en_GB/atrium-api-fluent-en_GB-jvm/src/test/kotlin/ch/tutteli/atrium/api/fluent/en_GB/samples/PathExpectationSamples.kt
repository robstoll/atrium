package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.toBeASymbolicLink
import ch.tutteli.atrium.api.fluent.en_GB.toBeAnEmptyDirectory
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.niok.newDirectory
import ch.tutteli.niok.newFile
import java.nio.file.Files
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
        val dir = tempDir.newDirectory("dir")
        expect(dir).toBeAnEmptyDirectory()

        dir.newFile("test.txt")
        fails {
            expect(dir).toBeAnEmptyDirectory()
        }
    }
}
