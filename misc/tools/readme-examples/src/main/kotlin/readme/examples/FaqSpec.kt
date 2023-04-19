package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.asIterable
import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import org.spekframework.spek2.Spek
import readme.examples.utils.expect

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
object FaqSpec : Spek({
    test("code-faq-1") {
        expect(sequenceOf(1, 2, 3)).asIterable().toContain(2)
    }
    test("code-faq-2") {
        expect(sequenceOf(1, 2, 3)).feature { f(it::asIterable) }.toContain(2)
    }
})
