// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import java.io.File

class FileAsPathAssertionsSpec : ch.tutteli.atrium.specs.integration.FileAsPathAssertionsSpec(
    Expect<File>::asPath
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<File> = notImplemented()

        a1.asPath()
        a1 = a1.asPath { }
    }
}
