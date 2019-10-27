package ch.tutteli.atrium.specs

import ch.tutteli.niok.createSymbolicLink
import ch.tutteli.niok.deleteRecursively
import ch.tutteli.niok.getFileAttributeView
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.AclFileAttributeView
import java.nio.file.attribute.PosixFileAttributeView

fun fileSystemSupportsAcls() = Paths.get("test").getFileAttributeView<AclFileAttributeView>() != null
fun fileSystemSupportsPosixPermissions() = Paths.get("test").getFileAttributeView<PosixFileAttributeView>() != null

fun fileSystemSupportsCreatingSymlinks(): Boolean {
    val testDir = Files.createTempDirectory("symlinktest")
    return try {
        testDir.resolve("test").createSymbolicLink(testDir.resolve("testLink"))
        true
    } catch (_: java.nio.file.FileSystemException) {
        false
    } finally {
        testDir.deleteRecursively()
    }
}
