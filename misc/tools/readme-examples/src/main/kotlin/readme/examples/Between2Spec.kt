package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.creating.Expect
import org.spekframework.spek2.Spek
import java.util.*

//snippet-own-compose-import-start
import ch.tutteli.atrium.logic._logic
//snippet-own-compose-import-end

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

object Between2Spec : Spek({
    test("code-own-compose-2") {
        //snippet-own-compose-import-insert

        fun <T : Date> Expect<T>.toBeBetween(lowerBoundInclusive: T, upperBoundExclusive: T) =
            _logic.appendAsGroup {
                toBeGreaterThanOrEqualTo(lowerBoundInclusive)
                toBeLessThan(upperBoundExclusive)
            }
    }
})
