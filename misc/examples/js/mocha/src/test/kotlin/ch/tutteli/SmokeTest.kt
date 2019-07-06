package ch.tutteli

import ch.tutteli.atrium.api.cc.en_GB.isLessThan
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.verbs.assertThat
import kotlin.test.Test

class SmokeTest {
    @Test
    fun toBe_canBeUsed(){
        assertThat(1).isLessThan(2)
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

fun Assert<Int>.isEven()
    = createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number")) {
    @Suppress("DEPRECATION")
    subject % 2 == 0
}

fun Assert<Int>.isMultipleOf(base: Int)
    = addAssertion(_isMultipleOf(this, base))

fun _isMultipleOf(plant: AssertionPlant<Int>, base: Int): Assertion
    = AssertImpl.builder.createDescriptive(plant, DescriptionIntAssertions.IS_MULTIPLE_OF, base) { it % base == 0 }

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
