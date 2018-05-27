import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.verbs.assertThat
import org.jetbrains.spek.api.Spek

object SmokeSpec : Spek({
    test("see if `toBe` can be used") {
        assertThat(1).toBe(1)
    }

    test("see if own assertion function without i18n can be used"){
        assertThat(2).isEven()
    }

    test("see if own assertion function with i18n can be used") {
        assertThat(4).isMultipleOf(2)
    }
})

fun Assert<Int>.isEven()
    = createAndAddAssertion(DescriptionBasic.IS, RawString.create("an even number"), { subject % 2 == 0 })

fun Assert<Int>.isMultipleOf(base: Int)
    = addAssertion(_isMultipleOf(this, base))

fun _isMultipleOf(plant: AssertionPlant<Int>, base: Int): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject % base == 0 }
        .withDescriptionAndRepresentation(DescriptionIntAssertions.IS_MULTIPLE_OF, base)
        .build()

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
