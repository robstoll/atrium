import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.assertThat
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
    fun toBe_canBeUsed() {
        assertThat(1) toBe 1
    }

    @Test
    fun assertionFunctionWithoutI18nCanBeUsed() {
        assertThat(2) tobe even
        assertThat(1) tobe odd
    }

    @Test
    fun assertionFunctionWithI18nCanBeUsed() {
        assertThat(4) isMultipleOf 2
    }

    @Test
    fun assertAnExceptionOccurred() {
        assertThat {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }

    @Test
    fun assertAnExceptionWithAMessageOccurred() {
        assertThat {
            throw IllegalArgumentException("oho... hello btw")
        }.toThrow<IllegalArgumentException> {
            it messageContains "hello"
        }
    }

    @Test
    fun assertNotToThrow() {
        assertThat {

        }.notToThrow()
    }
}

@Suppress("ClassName")
object even
@Suppress("ClassName")
object odd

infix fun Expect<Int>.tobe(@Suppress("UNUSED_PARAMETER") even: even) =
    _logic.appendAssertion(_logic.createDescriptiveAssertion(IS, Text("an even number")) { it % 2 == 0 })

infix fun Expect<Int>.tobe(@Suppress("UNUSED_PARAMETER") odd: odd) =
    _logic.appendAssertion(_logic.createDescriptiveAssertion(IS, Text("an odd number")) { it % 2 == 1})

infix fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> = _logicAppend { isMultipleOf(base) }

private fun AssertionContainer<Int>.isMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}

