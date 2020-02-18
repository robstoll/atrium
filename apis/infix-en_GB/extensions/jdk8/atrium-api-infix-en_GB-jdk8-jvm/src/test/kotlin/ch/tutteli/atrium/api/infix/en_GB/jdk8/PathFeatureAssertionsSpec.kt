package ch.tutteli.atrium.api.infix.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import java.nio.file.Path

class PathFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.PathFeatureAssertionsSpec(
    property<Path, Path>(Expect<Path>::parent),
    fun1<Path, Expect<Path>.() -> Unit>(Expect<Path>::parent),
    feature1<Path, String, Path>(Expect<Path>::resolve),
    "resolve with assertionCreator not implemented in this API" to ::resolve,
    property<Path, String>(Expect<Path>::fileName),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::fileName),
    property<Path, String>(Expect<Path>::fileNameWithoutExtension),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::fileNameWithoutExtension),
    property<Path, String>(Expect<Path>::extension),
    fun1<Path, Expect<String>.() -> Unit>(Expect<Path>::extension)
) {
    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val a1: Expect<DummyPath> = notImplemented()

        a1.fileName
        a1 fileName {}

        a1.fileNameWithoutExtension
        a1 fileNameWithoutExtension {}

        a1.extension
        a1 extension {}

        a1.parent
        a1 parent {}

        a1 resolve "test"
    }
}

private fun resolve(expect: Expect<Path>, other: String, assertionCreator: Expect<Path>.() -> Unit): Expect<Path> =
    (expect resolve other).addAssertionsCreatedBy(assertionCreator)
