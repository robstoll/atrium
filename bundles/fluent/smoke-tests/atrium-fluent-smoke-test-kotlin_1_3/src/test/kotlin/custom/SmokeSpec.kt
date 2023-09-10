@file:Suppress("DEPRECATION")

package custom

import ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.toBeASuccess
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import org.spekframework.spek2.Spek

object SmokeSpec : Spek({
    test("see if `toEqual` can be used") {
        expect(1).toEqual(1)
    }

    test("see if `Result.toBeASuccess` can be used") {
        expect(Result.success(1)).toBeASuccess { toEqual(1) }
    }

    test("see if own assertion function without i18n can be used") {
        expect(2).toBeEven()
        expect(1).toBeOdd()
    }

    test("see if own assertion function with i18n can be used") {
        expect(4).toBeMultipleOf(2)
    }
})

fun Expect<Int>.toBeEven() =
    _logic.createAndAppend("to be", Text("an even number")) { it % 2 == 0 }

fun Expect<Int>.toBeOdd() =
    _logic.append(_logic.createDescriptiveAssertion(DescriptionBasic.TO_BE, Text("an odd number")) { it % 2 == 1 })

fun Expect<Int>.toBeMultipleOf(base: Int): Expect<Int> = _logicAppend { toBeMultipleOf(base) }

private fun AssertionContainer<Int>.toBeMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.TO_BE_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    TO_BE_MULTIPLE_OF("to be multiple of")
}
