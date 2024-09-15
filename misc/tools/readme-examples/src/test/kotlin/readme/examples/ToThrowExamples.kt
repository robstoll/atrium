package readme.examples

import readme.examples.utils.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest
import java.math.BigDecimal

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class ToThrowExamples : ReadmeTest {

    @Test
    fun `ex-toThrow1`() {
        expect {
            // this lambda does something but eventually...
            throw IllegalArgumentException("name is empty")
        }.toThrow<IllegalStateException>()
    }

    @Test
    fun `ex-toThrow2`() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException> {
            message { toStartWith("firstName") }
        }
    }

    @Test
    fun `ex-toThrow3`() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>().message.toStartWith("firstName")
    }

    @Test
    fun `ex-notToThrow`() {
        expect {
            // this block does something but eventually...
            throw IllegalArgumentException("name is empty", RuntimeException("a cause"))
        }.notToThrow()
    }
}
