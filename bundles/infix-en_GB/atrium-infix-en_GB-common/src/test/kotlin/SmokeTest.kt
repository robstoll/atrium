import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic.IS
import kotlin.test.Test

class SmokeTest {

    @Test
    fun toEqual_canBeUsed() {
        expect(1) toEqual 1
    }

    @Test
    fun expectationFunctionWithoutI18nCanBeUsed() {
        expect(2) toBe even
        expect(1) toBe odd
    }

    @Test
    fun expectationFunctionWithI18nCanBeUsed() {
        expect(4) toBeMultipleOf 2
    }

    @Test
    fun expectAnExceptionOccurred() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }

    @Test
    fun expectAnExceptionWithAMessageOccurred() {
        expect {
            throw IllegalArgumentException("oho... hello btw")
        }.toThrow<IllegalArgumentException> {
            it messageToContain "hello"
        }
    }

    @Test
    fun expectNotToThrow() {
        expect {

        }.notToThrow()
    }
}

@Suppress("ClassName")
object even
@Suppress("ClassName")
object odd

infix fun Expect<Int>.toBe(@Suppress("UNUSED_PARAMETER") even: even) =
    _logic.appendAssertion(_logic.createDescriptiveAssertion(IS, Text("an even number")) { it % 2 == 0 })

infix fun Expect<Int>.toBe(@Suppress("UNUSED_PARAMETER") odd: odd) =
    _logic.appendAssertion(_logic.createDescriptiveAssertion(IS, Text("an odd number")) { it % 2 == 1})

infix fun Expect<Int>.toBeMultipleOf(base: Int): Expect<Int> = _logicAppend { toBeMultipleOf(base) }

private fun AssertionContainer<Int>.toBeMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}

