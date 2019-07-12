import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.api.verbs.assertThat
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class SmokeTest {
    @Test
    fun toBe_canBeUsed(){
        assertThat(1).toBe(1)
    }

    @Test
    fun assertionFunctionWithoutI18nCanBeUsed(){
        assertThat(2).isEven()
    }

    @Test
    fun assertionFunctionWithI18nCanBeUsed(){
        assertThat(4).isMultipleOf(2)
    }
}


@Suppress("DEPRECATION")
fun Expect<Int>.isEven()
    = createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) { subject % 2 == 0 }

fun Expect<Int>.isMultipleOf(base: Int)
    = addAssertion(_isMultipleOf(this, base))

private fun _isMultipleOf(assertionContainer: Expect<Int>, base: Int): Assertion
    = AssertImpl.builder.createDescriptive(assertionContainer, DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
