package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractFileAsPathExpectationsTest
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.Test

class FileAsPathExpectationsTest : AbstractFileAsPathExpectationsTest(
    fun1(Expect<File>::asPath)
) {

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE", "unused")
    @Test
    fun ambiguityTest() {
        var a1: Expect<File> = expect(File("/foo/bar"))

        val p1: Expect<Path> = a1.asPath()
        a1 = a1.asPath { toEqual(Paths.get("/foo/bar")) }
    }
}
