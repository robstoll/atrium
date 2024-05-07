package readme.examples//@formatter:off
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
import readme.examples.jupiter.ReadmeTest
import readme.examples.utils.ReadmeObjectFormatter
import readme.examples.expect as expectWithNewLine

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class OwnExpectationVerb: ReadmeTest {
    @Test
    fun `code-own-expectation-verb`() {
        //snippet-own-expectation-verb-import-insert

        //snippet-own-expectation-verb-insert
    }

    @OptIn(ExperimentalWithOptions::class, ExperimentalComponentFactoryContainer::class)
    fun <T> expect(subject: T): Expect<T> =
        expectWithNewLine(subject).withOptions {
            withComponent(ObjectFormatter::class) { _ -> ReadmeObjectFormatter() }
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
        .withVerb("I expected subject")
        .withOptions {
            withComponent(TextAssertionPairFormatter::class) { c ->
                TextAssertionPairFormatter.newNextLine(c.build())
            }
        }
        .build()
//snippet-own-expectation-verb-end
