//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated

import ch.tutteli.atrium.api.fluent.en_GB.isEmptyDirectory
import ch.tutteli.atrium.api.fluent.en_GB.isSymbolicLink
import ch.tutteli.atrium.api.fluent.en_GB.samples.fails
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
        expect(link).isSymbolicLink()

        val file = tempDir.newFile("somePath")

        // Fails, as subject `path` is a not a symbolic link
        fails {
            expect(file).isSymbolicLink()
        }
    }

    @Test
    fun isEmptyDirectory() {
        val dir = tempDir.newDirectory("dir")
        expect(dir).isEmptyDirectory()

        dir.newFile("test.txt")
        fails {
            expect(dir).isEmptyDirectory()
        }
    }
}
