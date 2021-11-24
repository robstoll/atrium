package custom

import ch.tutteli.atrium.api.infix.en_GB.toEqual
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
import org.spekframework.spek2.Spek

object SmokeSpec : Spek({
    test("see if `toEqual` can be used") {
        expect(1) toEqual 1
    }

    test("see if own expectation function without i18n can be used") {
        expect(2) tobe even
        expect(1) tobe odd
    }

    test("see if own expectation function with i18n can be used") {
        expect(4) isMultipleOf 2
    }
})

@Suppress("ClassName")
object even
@Suppress("ClassName")
object odd

infix fun Expect<Int>.tobe(@Suppress("UNUSED_PARAMETER") even: even) =
    _logic.append(_logic.createDescriptiveAssertion(TO_BE, Text("an even number")) { it % 2 == 0 })

infix fun Expect<Int>.tobe(@Suppress("UNUSED_PARAMETER") odd: odd) =
    _logic.append(_logic.createDescriptiveAssertion(TO_BE, Text("an odd number")) { it % 2 == 1 })

infix fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> = _logicAppend { isMultipleOf(base) }

private fun AssertionContainer<Int>.isMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}

