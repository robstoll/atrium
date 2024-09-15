package readme.examples

import ch.tutteli.atrium.api.fluent.en_GB.asIterable
import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest
import readme.examples.utils.expect

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class FaqExamples : ReadmeTest {
    @Test
    fun `code-faq-1`() {
        expect(sequenceOf(1, 2, 3)).asIterable().toContain(2)
    }
    @Test
    fun `code-faq-2`() {
        expect(sequenceOf(1, 2, 3)).feature { f(it::asIterable) }.toContain(2)
    }
}
