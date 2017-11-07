package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.text.SimpleDateFormat

/**
 * If you use this Spec then your reporter needs to use a translator which uses the following translations
 * which should be in
 *
 * the primary local: de_CH
 * ch.tutteli.atrium.assertions.DescriptionAnyAssertion-TO_BE = ist
 * ch.tutteli.atrium.creating.IAssertionPlantNullable.AssertionDescription-TO_BE = ist
 *
 * the primary Locale's natural first fallback: de
 * ch.tutteli.atrium.assertions.DescriptionAnyAssertion-NOT_TO_BE = ist nicht
 *
 * the Locale.ROOT
 * ch.tutteli.atrium.assertions.DescriptionAnyAssertion-IS_SAME = ist dieselbe Instanz wie
 *
 * the fallback Locale: fr
 * ch.tutteli.atrium.spec.AssertionVerb-ASSERT = il applique que
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-DATE_KNOWN = %tD était %<tA
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-PLACEHOLDER = Caractère de remplacement %s
 *
 * the Locale it:
 * ch.tutteli.atrium.assertions.DescriptionNumberAssertion-IS_LESS_THAN = è meno di
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-DATE_UNKNOWN = solo %tA!!
 */
abstract class TranslationSupplierSpec(
    verbs: IAssertionVerbFactory,
    reporter: IReporter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    fun <T : Any> assert(subject: T)
        = AtriumFactory.newReportingPlantCheckImmediately(AssertionVerb.ASSERT, subject, reporter)

    fun <T : Any?> assert(subject: T)
        = AtriumFactory.newReportingPlantNullable(AssertionVerb.ASSERT, subject, reporter)

    prefixedDescribe("primary locale is 'de_CH' and fallback is 'fr'") {

        describe("translation for ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE} is provided for 'de_CH'") {
            it("a failing assertion contains 'ist' instead of 'to be' in the error message") {
                verbs.checkException {
                    assert(1).toBe(2)
                }.toThrow<AssertionError> { message { contains("ist: 2") } }
            }
        }

        describe("translation for ${IAssertionPlantNullable.AssertionDescription::class.simpleName}.${IAssertionPlantNullable.AssertionDescription.name} is provided for 'de_CH'") {
            it("a failing assertion contains 'ist' instead of 'to be' in the error message") {
                verbs.checkException {
                    val a: Int? = 1
                    assert(a).isNull()
                }.toThrow<AssertionError> { message { contains("ist: ${RawString.NULL.string}") } }
            }
        }

        describe("translation for ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.NOT_TO_BE} is provided for 'de'") {
            val text = "ist nicht"
            it("a failing assertion contains '$text' instead of 'not to be' in the error message") {
                verbs.checkException {
                    assert(1).notToBe(1)
                }.toThrow<AssertionError> { message { contains("$text: 1") } }
            }
        }

        describe("translation for ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.IS_SAME} is provided for 'Locale.ROOT'") {
            val text = "ist dieselbe Instanz wie"
            it("a failing assertion contains '$text' instead of 'is same as' in the error message") {
                verbs.checkException {
                    assert(1).isSame(2)
                }.toThrow<AssertionError> { message { contains("$text: 2") } }
            }
        }

        describe("translation for ${AssertionVerb::class.simpleName}.${AssertionVerb.ASSERT} is provided for 'fr'") {
            val text = "il applique que"
            it("a failing assertion contains '$text' instead of 'assert' in the error message") {
                verbs.checkException {
                    assert(1).toBe(2)
                }.toThrow<AssertionError> { message { contains("$text: 1") } }
            }
        }

        describe("translation for ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_LESS_THAN} is provided for 'it'") {
            it("throws an AssertionError which message contains the default of ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_LESS_THAN}") {
                verbs.checkException {
                    assert(1).isLessThan(1)
                }.toThrow<AssertionError> { message { contains("${DescriptionNumberAssertion.IS_LESS_THAN.getDefault()}: 1") } }
            }
        }

        val firstOfFeb2017 = SimpleDateFormat("dd.MM.yyyy").parse("01.02.2017")
        describe("translation for ${TestTranslatable::class.simpleName}.${TestTranslatable.DATE_KNOWN} (with a date as parameter) is provided for 'fr'") {
            it("uses the translation form 'fr' but the primary Locale to format the date") {
                verbs.checkException {
                    assert(1).createAndAddAssertion(TranslatableWithArgs(TestTranslatable.DATE_KNOWN, firstOfFeb2017), 1, { false })
                }.toThrow<AssertionError> { message { contains("02/01/17 était Mittwoch!!") } }
            }
        }

        describe("translation for ${TestTranslatable::class.simpleName}.${TestTranslatable.DATE_UNKNOWN} (with a date as parameter) is provided for 'it'") {
            it("uses default translation but the primary Locale to format the date") {
                verbs.checkException {
                    assert(1).createAndAddAssertion(TranslatableWithArgs(TestTranslatable.DATE_UNKNOWN, firstOfFeb2017), 1, { false })
                }.toThrow<AssertionError> { message { contains("only Mittwoch") } }
            }
        }

        describe("translation for ${TestTranslatable::class.simpleName}.${TestTranslatable.PLACEHOLDER} "
            + "with ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE} as Placeholder") {
            it("uses the translation from 'fr' for ${TestTranslatable::class.simpleName}.${TestTranslatable.PLACEHOLDER} "
                + "and the translation from 'ch' for ${DescriptionAnyAssertion::class.simpleName}.${DescriptionAnyAssertion.TO_BE}") {
                verbs.checkException {
                    assert(1).createAndAddAssertion(TranslatableWithArgs(TestTranslatable.PLACEHOLDER, DescriptionAnyAssertion.TO_BE), 1, { false })
                }.toThrow<AssertionError> { message { contains("Caractère de remplacement ist") } }
            }
        }
    }

}) {
    /**
     * Contains [ISimpleTranslatable]s which are used in [TranslationSupplierSpec].
     */
    enum class TestTranslatable(override val value: String) : ISimpleTranslatable {
        DATE_KNOWN("%tD is a %<tA"),
        DATE_UNKNOWN("only %tA"),
        PLACEHOLDER("placeholder %s")
    }
}
