package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.testfactories.TestFactory
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

@Suppress("FunctionName")
abstract class AbstractFileAsPathExpectationsTest(
    private val asPathSpec: Fun1<File, Expect<Path>.() -> Unit>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun asPath() = testFactory(asPathSpec) { asPathFun ->
        it("transformation can be applied and a subsequent assertion made") {
            val path = "/foo/bar"
            expect(File(path)).asPathFun {
                toEqual(Paths.get(path))
            }
        }
    }
}
