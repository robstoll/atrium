package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.toBeARegularFile
import ch.tutteli.atrium.api.fluent.en_GB.toBeWritable
import ch.tutteli.atrium.api.fluent.en_GB.toExist
import ch.tutteli.niok.deleteRecursively
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest
import readme.examples.utils.expect
import java.nio.file.Files
import java.nio.file.Paths


/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class PathExamples : ReadmeTest {

    companion object {
        val tmpDir = Paths.get(System.getProperty("java.io.tmpdir")).resolve("atrium-path")

        @AfterAll
        @JvmStatic
        fun deletePaths() {
            tmpDir.deleteRecursively()
        }
    }

    @Test
    fun `ex-path-exists`() {
        expect(Paths.get("/usr/bin/noprogram")).toExist()
    }

    @Test
    fun `ex-path-writable`() {
        expect(Paths.get("/root/.ssh/config")).toBeWritable()
    }

    @Test
    fun `ex-path-symlink-and-parent-not-folder`() {
        val directory = Files.createDirectory(tmpDir)
        val file = Files.createFile(directory.resolve("file"))
        val filePointer = Files.createSymbolicLink(directory.resolve("directory"), file)

        expect(filePointer.resolve("subfolder/file")).toBeARegularFile()
    }
}
