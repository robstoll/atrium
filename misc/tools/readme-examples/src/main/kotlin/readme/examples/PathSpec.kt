package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.toBeARegularFile
import ch.tutteli.atrium.api.fluent.en_GB.toBeWritable
import ch.tutteli.atrium.api.fluent.en_GB.toExist
import ch.tutteli.niok.deleteRecursively
import org.spekframework.spek2.Spek
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

object PathSpec : Spek({
    val toDelete: MutableSet<Path> = HashSet()

    afterGroup {
        toDelete.forEach { it.deleteRecursively() }
    }

    test("ex-path-exists") {
        expect(Paths.get("/usr/bin/noprogram")).toExist()
    }

    test("ex-path-writable") {
        expect(Paths.get("/root/.ssh/config")).toBeWritable()
    }

    val tmpdir = Paths.get(System.getProperty("java.io.tmpdir"))
    test("ex-path-symlink-and-parent-not-folder") {
        val directory = Files.createDirectory(tmpdir.resolve("atrium-path"))
        val file = Files.createFile(directory.resolve("file"))
        val filePointer = Files.createSymbolicLink(directory.resolve("directory"), file)

        expect(filePointer.resolve("subfolder/file")).toBeARegularFile()
    }
    toDelete.add(tmpdir.resolve("atrium-path"))
})
