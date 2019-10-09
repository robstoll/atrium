package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
import java.nio.file.Path
import java.nio.file.Paths

class PathFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.PathFeatureAssertionsSpec(
    property<Path, Path>(Expect<Path>::parent),
    fun1<Path, Expect<Path>.() -> Unit>(Expect<Path>::parent),
    property<Path, String>(Expect<Path>::fileNameWithoutExtension),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::fileNameWithoutExtension)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Path> = notImplemented()
        var a2: Expect<out Path> = notImplemented()

        a1.parent
        a1 = a1.parent { }
        a2.parent
        a2 = a2.parent { }

        a1.fileNameWithoutExtension
        a1 = a1.fileNameWithoutExtension { }
        a2.fileNameWithoutExtension
        a2 = a2.fileNameWithoutExtension {  }

        a1.endsWith(Paths.get("a"))
        a2.endsWith(Paths.get("a"))
    }
}
