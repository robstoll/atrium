package custom

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import org.jetbrains.spek.api.Spek

object SmokeSpec : Spek({
    test("see if `toBe` can be used") {
        expect(1).toBe(1)
    }

    test("see if `Result.isSuccess` can be used") {
        //TODO activate after isSuccess is implemented
        //expect(Result.success(1)).isSuccess { toBe(1) }
    }

    test("see if own assertion function without i18n can be used") {
        expect(2).isEven()
    }

    test("see if own assertion function with i18n can be used") {
        expect(4).isMultipleOf(2)
    }
})

fun Expect<Int>.isEven(): Expect<Int> =
    createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) { it % 2 == 0 }

fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> = addAssertion(_isMultipleOf(this, base))

fun _isMultipleOf(assertionContainer: Expect<Int>, base: Int): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, DescriptionIntAssertions.IS_MULTIPLE_OF, base) {
        it % base == 0
    }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
