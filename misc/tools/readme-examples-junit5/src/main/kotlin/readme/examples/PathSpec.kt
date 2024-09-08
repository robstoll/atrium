package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.toBeARegularFile
import ch.tutteli.atrium.api.fluent.en_GB.toBeWritable
import ch.tutteli.atrium.api.fluent.en_GB.toExist
import ch.tutteli.niok.deleteRecursively
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import readme.examples.utils.expect
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*


/**
 * The tests and error message are written here and automatically placed into the README via generation.
 * The generation is done during the project built. To trigger it manually, you have to run:
 * ```
 * ./gradlew :readme-examples:build
 * ```
 *
 * There are currently three kind of tags supported:
 * - <ex-xy> => places code and output into the tag
 * - <exs-xy> => places code into the tag, output will be put into a tag named <exs-xy-output>
 * - <code> => is not supposed to fail and only the code is put into the code
 *
 * Moreover, all tags can reuse snippets defined in this file with corresponding markers
 */

class PathSpec {
    //    afterGroup {
    //        toDelete.forEach { it.deleteRecursively() }
    //    }
    companion object {
        val toDelete: MutableSet<Path> = HashSet()
        val tmpdir = Paths.get(System.getProperty("java.io.tmpdir"))

        @AfterAll @JvmStatic
        fun deletePaths() { // TODO verify whether this method works or not
            toDelete.add(tmpdir.resolve("atrium-path"))
            toDelete.forEach { it.deleteRecursively() }
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
        val directory = Files.createDirectory(tmpdir.resolve("atrium-path"))
        val file = Files.createFile(directory.resolve("file"))
        val filePointer = Files.createSymbolicLink(directory.resolve("directory"), file)

        expect(filePointer.resolve("subfolder/file")).toBeARegularFile()
    }
}
