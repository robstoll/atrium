package readme.examples

//@formatter:off
//snippet-own-expectation-verb-import-start
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
//snippet-own-expectation-verb-import-end
//@formatter:on

import ch.tutteli.atrium.api.fluent.en_GB.ExperimentalWithOptions
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.fluent.en_GB.withOptions
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.text.TextAssertionPairFormatter
import org.junit.jupiter.api.Test
import readme.examples.utils.ReadmeObjectFormatter
import readme.examples.expect as expectWithNewLine

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

class OwnExpectationVerbSpec {
    @Test
    fun `code-own-expectation-verb`() {
        //snippet-own-expectation-verb-import-insert

        //snippet-own-expectation-verb-insert
    }

    @OptIn(ExperimentalWithOptions::class, ExperimentalComponentFactoryContainer::class)
    fun <T> expect(subject: T): Expect<T> =
        expectWithNewLine(subject).withOptions {
            withComponent(ObjectFormatter::class) { c -> ReadmeObjectFormatter(c.build()) }
        }

    @Test
    fun `ex-own-expectation-verb`() {
        expect(10).toEqual(9)
    }
}

//snippet-own-expectation-verb-start
@OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
fun <T> expect(subject: T): RootExpect<T> =
    RootExpectBuilder.forSubject(subject)
        .withVerb("expected the subject")
        .withOptions {
            withComponent(TextAssertionPairFormatter::class) { c ->
                TextAssertionPairFormatter.newNextLine(c.build(), c.build())
            }
        }
        .build()
//snippet-own-expectation-verb-end
