package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import java.nio.file.Path
import java.nio.file.Paths

class PathAssertionsSpec : ch.tutteli.atrium.specs.integration.PathAssertionsSpec(
    fun0(Expect<Path>::exists),
    fun0(Expect<Path>::existsNot),
    fun1(Expect<Path>::endsWith)
){
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<Path> = notImplemented()
        val a2: Expect<out Path> = notImplemented()

        a1.exists()
        a2.exists()

        a2.existsNot()
        a2.existsNot()

        a1.endsWith(Paths.get("a"))
        a2.endsWith(Paths.get("a"))
    }
}
