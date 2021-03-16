package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.aSymbolicLink
import ch.tutteli.atrium.api.infix.en_GB.anEmptyDirectory
import ch.tutteli.atrium.api.infix.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.niok.newDirectory
import ch.tutteli.niok.newFile
import java.nio.file.Files
import kotlin.test.Test

class PathAssertionSamples {

    private val tempDir = Files.createTempDirectory("PathAssertionSamples")

    @Test
    fun isASymbolicLink() {
        val target = tempDir.newFile("target")
        val link = Files.createSymbolicLink(tempDir.resolve("link"), target)

        // Passes, as subject `link` is a symbolic link
        expect(link) toBe aSymbolicLink
    }

    @Test
    fun isNotASymbolicLink() {
        val path = tempDir.newFile("somePath")

        // Fails, as subject `path` is a not a symbolic link
        fails {
            expect(path) toBe aSymbolicLink
        }
    }

    @Test
    fun isEmptyDirectory() {
        val path = tempDir.newDirectory("dir")
        expect(path) toBe anEmptyDirectory
    }

    @Test
    fun isNotEmptyDirectory() {
        tempDir.newFile("a")

        fails {
            expect(tempDir) toBe anEmptyDirectory
        }
    }
}
