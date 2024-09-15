package readme.examples//@formatter:off
//snippet-import-start
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
//snippet-import-end
//@formatter:on

import ch.tutteli.atrium.creating.Expect
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class FirstExample : ReadmeTest {

    fun <T> expect(t: T): Expect<T> = readme.examples.utils.expect(t)

    @Test
    fun `ex-first`() {
        //snippet-import-insert

        val x = 10
        expect(x).toEqual(9)
    }
}
