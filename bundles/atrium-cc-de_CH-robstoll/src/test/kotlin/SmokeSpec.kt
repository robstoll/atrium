import ch.tutteli.atrium.api.cc.de_CH.ist
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString.Companion.create
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.verbs.assert
import ch.tutteli.atrium.verbs.assertThat
import org.jetbrains.spek.api.Spek

object SmokeSpec : Spek({
    test("see if `ist` can be used") {
        assert(1).ist(1)
    }

    test("see if own assertion function without i18n can be used") {
        assertThat(2).istGerade()
    }

    test("see if own assertion function with i18n can be used") {
        assertThat(4).istVielfachesVon(2)
    }
})

fun Assert<Int>.istGerade()
    = createAndAddAssertion(DescriptionBasic.IS, create("an even number"), { subject % 2 == 0 })

fun Assert<Int>.istVielfachesVon(base: Int)
    = addAssertion(_istVielfachesVon(this, base))

fun _istVielfachesVon(plant: AssertionPlant<Int>, base: Int): Assertion
    = AssertImpl.builder.descriptive
        .withTest { plant.subject % base == 0 }
        .withDescriptionAndRepresentation(DescriptionIntAssertions.IS_MULTIPLE_OF, base)
        .build()

enum class DescriptionIntAssertions(override val value: String) : StringBasedTranslatable {
    IS_MULTIPLE_OF("is multiple of")
}
