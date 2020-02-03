package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import java.nio.file.Path
import java.nio.file.Paths

class PathAssertionsSpec : ch.tutteli.atrium.specs.integration.PathAssertionsSpec(
    fun1<Path, exist>(Expect<Path>::does).name to ::exists,
    fun1<Path, exist>(Expect<Path>::doesNot).name to ::existsNot,
    fun1(Expect<Path>::startsWith),
    fun1(Expect<Path>::startsNotWith),
    fun1(Expect<Path>::endsWith),
    fun1(Expect<Path>::endsNotWith),
    fun1<Path, readable>(Expect<Path>::toBe).name to ::isReadable,
    fun1<Path, writable>(Expect<Path>::toBe).name to ::isWritable,
    fun1<Path, aRegularFile>(Expect<Path>::toBe).name to ::isRegularFile,
    fun1<Path, aDirectory>(Expect<Path>::toBe).name to ::isDirectory
) {
    companion object {
        fun exists(expect: Expect<Path>) = expect does exist
        fun existsNot(expect: Expect<Path>) = expect doesNot exist
        fun isReadable(expect: Expect<Path>) = expect toBe readable
        fun isWritable(expect: Expect<Path>) = expect toBe writable
        fun isRegularFile(expect: Expect<Path>) = expect toBe aRegularFile
        fun isDirectory(expect: Expect<Path>) = expect toBe aDirectory
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Path> = notImplemented()

        a1 does exist
        a1 doesNot exist
        a1 startsWith Paths.get("a")
        a1 startsNotWith Paths.get("a")
        a1 endsWith Paths.get("a")
        a1 endsNotWith Paths.get("a")
        a1.fileName
        a1 fileName {}
        a1.fileNameWithoutExtension
        a1 fileNameWithoutExtension {}
        a1.extension
        a1 extension {}
        a1.parent
        a1 parent {}
        a1 toBe readable
        a1 toBe writable
        a1 toBe aRegularFile
        a1 toBe aDirectory
    }
}
