package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import java.io.File

class FileAsPathAssertionsSpec : ch.tutteli.atrium.specs.integration.FileAsPathAssertionsSpec(
    Expect<File>::asPath
) {
    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<File> = notImplemented()

        a1 asPath o
        a1 = a1 asPath { }
    }
}
