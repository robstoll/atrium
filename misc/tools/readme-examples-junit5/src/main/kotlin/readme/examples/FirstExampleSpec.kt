package readme.examples

//@formatter:off
//snippet-import-start
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
//snippet-import-end
//@formatter:on

import ch.tutteli.atrium.creating.Expect
import org.junit.jupiter.api.Test

/**
 * The tests and error message are written here and automatically placed into the README via generation.
 * The generation is done during the project built. To trigger it manually, you have to run:
 * ```
 * ./gradlew :readme-examples:build
 * ```
 *
 * There are currently three kind of tags supported:
 * - <ex-xy> => places code and output into the tag
 * - <exs-xy> => places code into the tag, output will be put into a tag named <exs-xy-output>
 * - <code> => is not supposed to fail and only the code is put into the code
 *
 * Moreover, all tags can reuse snippets defined in this file with corresponding markers
 */

class FirstExampleSpec {

    fun <T> expect(t: T): Expect<T> = readme.examples.utils.expect(t)

    @Test
    fun `ex-first`() {
        //snippet-import-insert

        val x = 10
        expect(x).toEqual(9)
    }
}
