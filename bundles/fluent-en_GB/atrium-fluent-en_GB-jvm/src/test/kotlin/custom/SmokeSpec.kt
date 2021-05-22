package custom

import ch.tutteli.atrium.api.fluent.en_GB.notToExist
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
import java.nio.file.Paths

object SmokeSpec : Spek({
    test("see if `toEqual` can be used") {
        expect(1).toEqual(1)
    }

    test("see if `Path.existsNot` can be used") {
        expect(Paths.get("nonExisting")).notToExist()
    }

    test("see if own expectation function without i18n can be used") {
        expect(2).toBeEven()
        expect(1).toBeOdd()
    }

    test("see if own expectation function with i18n can be used") {
        expect(4).toBeAMultipleOf(2)
    }
})

fun Expect<Int>.toBeEven() =
    _logic.createAndAppendAssertion("is", Text("an even number")) { it % 2 == 0 }

fun Expect<Int>.toBeOdd() =
    _logic.appendAssertion(_logic.createDescriptiveAssertion(DescriptionBasic.IS, Text("an odd number")) { it % 2 == 1 })

fun Expect<Int>.toBeAMultipleOf(base: Int) = _logicAppend { toBeAMultipleOf(base) }

private fun AssertionContainer<Int>.toBeAMultipleOf(base: Int): Assertion =
    createDescriptiveAssertion(DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
