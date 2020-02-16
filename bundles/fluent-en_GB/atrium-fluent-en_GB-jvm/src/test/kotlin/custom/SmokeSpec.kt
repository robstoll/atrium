package custom

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.assertThat
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
        assertThat(1).toBe(1)
    }

    test("see if own assertion function without i18n can be used") {
        assertThat(2).isEven()
    }

    test("see if own assertion function with i18n can be used") {
        assertThat(4).isMultipleOf(2)
    }

    test("try out toThrow for normal function and suspend function") {
        expect {
            bar()
        }.toThrow<IllegalStateException>()

        expect {
            foo()
        }.toThrow<IllegalStateException>()
    }
})


suspend fun foo() {
    throw IllegalStateException("asdf")
}

fun bar() {
    throw IllegalStateException("asdf")
}


@Suppress("DEPRECATION")
fun Expect<Int>.isEven() =
    createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) { subject % 2 == 0 }

fun Expect<Int>.isMultipleOf(base: Int) = addAssertion(_isMultipleOf(this, base))

fun _isMultipleOf(expect: Expect<Int>, base: Int): Assertion =
    ExpectImpl.builder.createDescriptive(expect, DescriptionIntAssertions.IS_MULTIPLE_OF, base) {
        it % base == 0
    }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
