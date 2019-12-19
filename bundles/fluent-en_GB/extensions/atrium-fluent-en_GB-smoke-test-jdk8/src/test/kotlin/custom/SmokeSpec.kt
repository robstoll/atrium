package custom

import ch.tutteli.atrium.api.fluent.en_GB.jdk8.existsNot
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.assert
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import org.jetbrains.spek.api.Spek
import java.nio.file.Paths

object SmokeSpec : Spek({
    test("see if `toBe` can be used") {
        assert(1).toBe(1)
    }

    test("see if `Path.existsNot` can be used") {
        assert(Paths.get("nonExisting")).existsNot()
    }

    test("see if own assertion function without i18n can be used") {
        assert(2).isEven()
    }

    test("see if own assertion function with i18n can be used") {
        assert(4).isMultipleOf(2)
    }
})

fun Expect<Int>.isEven(): Expect<Int> =
    createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) { it % 2 == 0 }

fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> = addAssertion(_isMultipleOf(this, base))

fun _isMultipleOf(assertionContainer: Expect<Int>, base: Int): Assertion =
    assertionBuilder.createDescriptive(assertionContainer, DescriptionIntAssertions.IS_MULTIPLE_OF, base) {
        it % base == 0
    }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
