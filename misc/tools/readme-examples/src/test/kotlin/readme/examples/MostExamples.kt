package readme.examples

import readme.examples.utils.expect
import ch.tutteli.atrium.api.fluent.en_GB.*
import org.junit.jupiter.api.Test
import readme.examples.jupiter.ReadmeTest
import java.math.BigDecimal

/**
 * See [ReadmeTest] on how these tests are written into README.md
 */
class MostExamples : ReadmeTest {

    @Test
    fun `ex-single`() {
        // two single expectations, only first evaluated
        expect(4 + 6).toBeLessThan(5).toBeGreaterThan(10)
    }

    fun codeSingleNotExecutedHenceDoesNotFail() {
        //snippet-code-single-start
        expect(4 + 6).toBeLessThan(5)
        expect(4 + 6).toBeGreaterThan(10)
        //snippet-code-single-end
    }

    @Test
    fun `code-single-explained`() {
        //snippet-code-single-insert
    }

    @Test
    fun `ex-group`() {
        // expectation-group with two expectations, both evaluated
        expect(4 + 6) {
            toBeLessThan(5)
            toBeGreaterThan(10)
        }
    }

    fun codeAndNotExecutedHenceDoesNotFail() {
        //snippet-code-and-start
        expect(5) {
            // ...
        } and { // if the previous block fails, then this one is not evaluated
            // ...
        }
        //snippet-code-and-end
    }

    @Test
    fun `code-and`() {
        expect(5).toBeGreaterThan(2).and.toBeLessThan(10)

        //snippet-code-and-insert
    }

    @Test
    fun `ex-type-expectations-1`() {
        //snippet-type-expectations-insert
        expect(x).toBeAnInstanceOf<SubType2> {
            feature { f(it::word) }.toEqual("goodbye")
            feature { f(it::flag) }.toEqual(false)
        }
    }
    @Test
    fun `ex-type-expectations-2`() {
        expect(x).toBeAnInstanceOf<SubType1>()
            .feature { f(it::number) }
            .toEqual(2)
    }

    @Test
    fun `ex-nullable-1`() {
        val slogan1: String? = "postulating expectations made easy"
        expect(slogan1).toEqual(null)
    }
    @Test
    fun `ex-nullable-2`() {
        val slogan2: String? = null
        expect(slogan2).toEqual("postulating expectations made easy")
    }
    val slogan2: String? = null
    @Test
    fun `ex-nullable-3`() {
        expect(slogan2)        // subject has type String?
            .notToEqualNull()  // subject is narrowed to String
            .toStartWith("atrium")
    }
    @Test
    fun `ex-nullable-4`() {
        expect(slogan2).notToEqualNull { toStartWith("atrium") }
    }

    @Test
    fun `ex-because-1`() {
        expect("filename?")
            .because("? is not allowed in file names on Windows") {
                notToContain("?")
            }
    }

    @Test
    fun `exs-add-info-1`() {
        expect(listOf(1, 2, 3)).toContain.inOrder.only.values(1, 3)
    }
    @Test
    fun `exs-add-info-2`() {
        expect(9.99f).toEqualWithErrorTolerance(10.0f, 0.01f)
    }
    @Test
    fun `ex-add-info-3`() {
        expect {
            try {
                throw UnsupportedOperationException("not supported")
            } catch (t: Throwable) {
                throw IllegalArgumentException("no no no...", t)
            }
        }.toThrow<IllegalStateException> { messageToContain("no no no") }
    }

    @Test
    fun `ex-pitfall-1`() {
        expect(BigDecimal.TEN).toEqualIncludingScale(BigDecimal("10.0"))
    }
    @Test
    fun `ex-pitfall-2`() {
        expect(listOf(1)).get(0) {}
    }
}

//@formatter:off
//snippet-type-expectations-start
interface SuperType

data class SubType1(val number: Int) : SuperType
data class SubType2(val word: String, val flag: Boolean) : SuperType

val x: SuperType = SubType2("hello", flag = true)
//snippet-type-expectations-end
//@formatter:on
