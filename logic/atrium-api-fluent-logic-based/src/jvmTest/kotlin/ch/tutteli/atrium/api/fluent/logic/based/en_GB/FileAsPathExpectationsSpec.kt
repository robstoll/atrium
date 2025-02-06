package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import java.io.File

class FileAsPathExpectationsSpec : ch.tutteli.atrium.specs.integration.FileAsPathExpectationsSpec(
    Expect<File>::asPath
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<File> = notImplemented()

        a1.asPath()
        a1 = a1.asPath { }
    }
}
