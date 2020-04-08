package custom

import ch.tutteli.atrium.api.infix.en_GB.jdk8.notTo
import ch.tutteli.atrium.api.infix.en_GB.exist
import ch.tutteli.atrium.api.infix.en_GB.toBe
import ch.tutteli.atrium.api.verbs.assert
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.nio.file.Paths

object SmokeSpec : Spek({
    test("see if `toBe` can be used") {
        assert(1) toBe 1
    }

    test("see if `Path.existsNot` can be used") {
        assert(Paths.get("nonExisting")) notTo exist
    }

    test("see if own assertion function without i18n can be used") {
        assert(2) tobe even
    }

    test("see if own assertion function with i18n can be used") {
        assert(4) isMultipleOf 2
    }
})

@Suppress("ClassName")
object even

infix fun Expect<Int>.tobe(@Suppress("UNUSED_PARAMETER") even: even) =
    createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) { it % 2 == 0 }

infix fun Expect<Int>.isMultipleOf(base: Int): Expect<Int> = addAssertion(_isMultipleOf(this, base))

fun _isMultipleOf(expect: Expect<Int>, base: Int): Assertion =
    ExpectImpl.builder.createDescriptive(expect, DescriptionIntAssertions.IS_MULTIPLE_OF, base) {
        it % base == 0
    }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
