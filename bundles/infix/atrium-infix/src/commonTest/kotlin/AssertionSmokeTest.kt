//TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

import ch.tutteli.atrium._coreAppend
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
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import kotlin.test.Test

//TODO 1.3.0 SmokeTest with Proof
class AssertionSmokeTest {

    @Test
    fun toEqual_canBeUsed() {
        expect(1) toEqual 1
    }

    @Test
    fun expectationFunctionWithoutI18nCanBeUsed() {
        expect(2) toBeDeprecated even
        expect(1) toBeDeprecated odd
    }

    @Test
    fun expectationFunctionWithI18nCanBeUsed() {
        expect(4) toBeAMultipleOfDeprecated 2
    }

    @Test
    fun expectAnExceptionOccurred() {
        expect {
            throw IllegalArgumentException()
        }.toThrow<IllegalArgumentException>()
    }


    @Test
    fun expectNotToThrow() {
        expect {

        }.notToThrow()
    }
}


infix fun Expect<Int>.toBeDeprecated(@Suppress("UNUSED_PARAMETER") even: even) =
    _logic.append(_logic.createDescriptiveAssertion(TO_BE, Text("an even number")) { it % 2 == 0 })

infix fun Expect<Int>.toBeDeprecated(@Suppress("UNUSED_PARAMETER") odd: odd) =
    _logic.append(_logic.createDescriptiveAssertion(TO_BE, Text("an odd number")) { it % 2 == 1})

infix fun Expect<Int>.toBeAMultipleOfDeprecated(base: Int): Expect<Int> = _coreAppend { toBeAMultipleOfDeprecated(base) }

private fun AssertionContainer<Int>.toBeAMultipleOfDeprecated(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.TO_BE_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    TO_BE_MULTIPLE_OF("to be multiple of")
}

