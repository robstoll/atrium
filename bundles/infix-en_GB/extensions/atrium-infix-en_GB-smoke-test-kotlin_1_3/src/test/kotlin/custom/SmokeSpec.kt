@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package custom

import ch.tutteli.atrium.api.infix.en_GB.kotlin_1_3.toBe
import ch.tutteli.atrium.api.infix.en_GB.aSuccess
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
import ch.tutteli.atrium.translations.DescriptionBasic
import org.spekframework.spek2.Spek

object SmokeSpec : Spek({
    test("see if `toEqual` can be used") {
        expect(1) toEqual 1
    }

    test("see if `Result.isSuccess` can be used") {
        expect(Result.success(1)) toBe aSuccess
    }

    test("see if own expectation function without i18n can be used") {
        expect(2) toBe even
        expect(1) toBe odd
    }

    test("see if own expectation function with i18n can be used") {
        expect(4) isMultipleOf 2
    }
})

@Suppress("ClassName")
object even
@Suppress("ClassName")
object odd

infix fun Expect<Int>.toBe(@Suppress("UNUSED_PARAMETER") even: even) =
    _logic.append(_logic.createDescriptiveAssertion(DescriptionBasic.IS, Text("an even number")) { it % 2 == 0 })

infix fun Expect<Int>.toBe(@Suppress("UNUSED_PARAMETER") odd: odd) =
    _logic.append(_logic.createDescriptiveAssertion(DescriptionBasic.IS, Text("an odd number")) { it % 2 == 1 })

infix fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> = _logicAppend { isMultipleOf(base) }

private fun AssertionContainer<Int>.isMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
