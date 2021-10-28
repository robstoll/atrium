package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.fileSystemSupportsPosixPermissions
import ch.tutteli.niok.*
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermissions
import kotlin.test.Test

class PathFeatureExtractorSamples {

    private val tempDir = Files.createTempDirectory("PathAssertionSamples")

    @Test
    fun extensionFeature() {
        val extension = "txt"
        val dir = tempDir.newFile("test_file.$extension")

        expect(dir).extension // subject is now of type String (actually "txt")
            .notToBeEmpty()
            .toEqual(extension)
            .notToEndWith("jpg")

        fails {
            expect(dir).extension // subject is now of type String (actually "txt")
                .toEqual("txtt")  // fails because it doesn't equal to "txtt"
                .toEndWith("jpg") // not reported because toEqual already fails
            //                       use `.extension { ... } ` if you want that all expectations are evaluated
        }
    }

    @Test
    fun extension() {
        val extension = "txt"
        val dir = tempDir.newDirectory("test.$extension")

        expect(dir).extension { // subject inside this block is of type String (actually "txt")
            notToBeEmpty()
            toEqual(extension)
            notToEndWith("jpg")
        } // subject here is back to type Path

        fails {
            expect(dir).extension { // subject inside this block is of type String (actually "txt")
                toEqual("txtt")     // fails because it doesn't equal to "txtt"
                toEndWith("jpg")    // fails because it doesn't end with "jpg"
                //                     use `.extension.` if you want fail fast behaviour
            } // subject here is back to type Path
        }
    }

    @Test
    fun fileNameFeature() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileName // subject is now of type String (actually "test_dir")
            .toEndWith("dir")
            .toStartWith("test")
            .notToBeBlank()

        fails {
            expect(dir).fileName        // subject is now of type String (actually "test_dir")
                .toEndWith("foo")       // fails because it does not end with "foo"
                .toStartWith("invalid") // not reported because toEndWith already fails
            //                             use `fileName {...}` if you want that all expectations are evaluated
        }
    }

    @Test
    fun fileName() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileName { // subject inside this block is of type String (actually "test_dir")
            toEndWith("dir")
            toStartWith("test")
            notToBeBlank()
        } // subject here is back to type Path

        fails {
            expect(dir).fileName {      // subject inside this block is of type String (actually "test_dir")
                toEndWith("foo")        // fails because it does not end with "foo"
                toStartWith("invalid")  // still evaluated even though toEndWith already fails
                //                         use `.fileName.` if you want fail fast behaviour
            }  // subject here is back to type Path
        }
    }

    @Test
    fun fileNameWithoutExtensionFeature() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileNameWithoutExtension.notToBeEmpty().toEqual("test_dir")

        fails {
            expect(dir).fileNameWithoutExtension // subject is now of type String (actually "test_dir")
                .toBeEmpty()                     // fails because string is not empty
                .notToEqual("test_dir")          // not reported because toBeEmpty already fails
            //                                      use `.fileNameWithoutExtension { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun fileNameWithoutExtension() {
        val dir = tempDir.newDirectory("test_dir")

        expect(dir).fileNameWithoutExtension { // subject is now of type String (actually "test_dir")
            notToBeEmpty()
            toEqual("test_dir")
        } // subject here is back to type Path

        fails {
            expect(dir).fileNameWithoutExtension { // subject inside this block is of type String (actually "test_dir")
                toBeEmpty()             // fails because string is not empty
                notToEqual("test_dir")  // still evaluated even though toBeEmpty already fails
                //                         use `.fileNameWithoutExtension.` if you want a fail fast behaviour
            } // subject here is back to type Path
        }
    }

    @Test
    fun parentFeature() {
        val dir = tempDir.newDirectory("test_dir_1")
        val dir2 = tempDir.newDirectory("test_dir_2")

        expect(dir).parent.toEqual(dir2.parent).toExist()

        fails {
            val dir3 = dir2.newDirectory("test_dir")
            expect(dir).parent
                .toEqual(dir3) // fails because dir3 and dir do not have same parents
                .notToExist()  // not reported because toEqual already fails
            //                    use `.parent { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun parent() {
        val dir = tempDir.newDirectory("test_dir_1")
        val dir2 = tempDir.newDirectory("test_dir_2")

        expect(dir).parent { // subject inside this block refers to path corresponding to `test_dir_1/..`
            toEqual(dir2.parent)
            toExist()
        } // subject here is back to `test_dir_1`

        fails {
            val dir3 = dir2.newDirectory("test_dir")
            expect(dir).parent {
                toEqual(dir3) // fails because dir3 and dir do not have same parents
                notToExist()  // still evaluated even though toEqual already fails
                //               use `.parent.` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun resolveFeature() {
        val dir = tempDir.newDirectory("test_dir")
        val fileInDir = dir.newFile("test_file.txt")

        expect(dir).resolve("test_file.txt") // subject changes to `test_dir/test_file.txt`
            .toEqual(fileInDir)
            .toEndWith(Paths.get("test_file.txt"))

        fails {
            expect(dir).resolve("test_file.ttt")
                .toEqual(fileInDir)          // fails because resolve returns *test_file.ttt and fileInDir equals *test_file.txt
                .toEndWith(Paths.get("ttt")) // not reported toEqual already fails
            //                                  use `.resolve(other) { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun resolve() {
        val dir = tempDir.newDirectory("test_dir")
        val fileInDir = dir.newFile("test_file.txt")

        expect(dir).resolve("test_file.txt") { // subject inside this block refers to `test_dir/test_file.txt`
            toEqual(fileInDir)
            toExist()
        } // subject here is back to `test_dir`

        fails {
            expect(dir).resolve("test_file.ttt") {
                toEqual(fileInDir) // fails
                toExist()          // still evaluated even though toEqual already fails
                //                    use `.resolve(other).` if you want a fail fast behaviour
            }
        }
    }

}
