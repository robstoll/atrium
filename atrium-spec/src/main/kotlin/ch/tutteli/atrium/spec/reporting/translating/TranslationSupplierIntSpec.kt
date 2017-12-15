package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.text.SimpleDateFormat

/**
 * If you use this Spec then your reporter needs to use a translator which uses the following translations
 * which should be in
 *
 * the primary local: de_CH
 * ch.tutteli.atrium.assertions.DescriptionAnyAssertion-TO_BE = ist
 *
 * the primary Locale's natural first fallback: de
 * ch.tutteli.atrium.assertions.DescriptionAnyAssertion-NOT_TO_BE = ist nicht
 *
 * the fallback Locale: fr
 * ch.tutteli.atrium.assertions.DescriptionAnyAssertion-IS_NOT_SAME = n'est pas la même instance que
 * ch.tutteli.atrium.spec.AssertionVerb-ASSERT = il applique que
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-DATE_KNOWN = %tD était %<tA
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-PLACEHOLDER = Caractère de remplacement %s
 *
 * the Locale it:
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-DATE_KNOWN = solo %tA!!
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-DATE_UNKNOWN = solo %tA!!
 */
abstract class TranslationSupplierIntSpec(
    verbs: IAssertionVerbFactory,
    reporter: IReporter,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    fun <T : Any> assert(subject: T)
        = AtriumFactory.newReportingPlant(AssertionVerb.ASSERT, subject, reporter)

    val descriptionAnyAssertion = DescriptionAnyAssertion::class.simpleName
    val testTranslatable = TestTranslatable::class.simpleName
    prefixedDescribe("primary locale is 'de_CH' and fallback is 'fr'") {

        context("properties file for ${DescriptionAnyAssertion::class.simpleName} is provided for 'de_CH'") {

            describe("translation for $descriptionAnyAssertion.${DescriptionAnyAssertion.TO_BE} is provided for 'de_CH'") {
                it("a failing assertion contains 'ist' instead of 'to be' in the error message") {
                    verbs.checkException {
                        assert(1).toBe(2)
                    }.toThrow<AssertionError> { message { contains("ist: 2") } }
                }
            }

            describe("translation for $descriptionAnyAssertion.${DescriptionAnyAssertion.NOT_TO_BE} is provided for 'de'") {
                val text = "ist nicht"
                it("a failing assertion contains '$text' instead of 'not to be' in the error message") {
                    verbs.checkException {
                        assert(1).notToBe(1)
                    }.toThrow<AssertionError> { message { contains("$text: 1") } }
                }
            }

            describe("translation for $descriptionAnyAssertion.${DescriptionAnyAssertion.IS_NOT_SAME} is provided 'fr'") {
                val text = "n'est pas la même instance que"
                it("a failing assertion contains '$text' instead of 'assert' in the error message") {
                    verbs.checkException {
                        assert(1).isNotSame(1)
                    }.toThrow<AssertionError> { message { contains("$text: 1") } }
                }
            }
        }

        context("properties file for ${AssertionVerb::class.simpleName} is not provided for 'de_CH' nor one of its parents") {
            describe("translation for ${AssertionVerb::class.simpleName}.${AssertionVerb.ASSERT} is provided for 'fr'") {
                val text = "il applique que"
                it("a failing assertion contains '$text' instead of 'assert' in the error message") {
                    verbs.checkException {
                        assert(1).toBe(2)
                    }.toThrow<AssertionError> { message { contains("$text: 1") } }
                }
            }
        }

        context("properties file for ${DescriptionNumberAssertion::class.simpleName} is not provided for 'de_CH' nor one of its parents") {

            describe("translation for ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_LESS_THAN} is provided for 'it'") {
                it("throws an AssertionError which message contains the default of ${DescriptionNumberAssertion::class.simpleName}.${DescriptionNumberAssertion.IS_LESS_THAN}") {
                    verbs.checkException {
                        assert(1).isLessThan(1)
                    }.toThrow<AssertionError> { message { contains("${DescriptionNumberAssertion.IS_LESS_THAN.getDefault()}: 1") } }
                }
            }
        }

        context("properties file for ${DescriptionNumberAssertion::class.simpleName} is not provided for 'de_CH' nor one of its parents") {

            val firstOfFeb2017 = SimpleDateFormat("dd.MM.yyyy").parse("01.02.2017")
            describe("translation for $testTranslatable.${TestTranslatable.DATE_KNOWN} (with a date as parameter) is provided for 'fr'") {
                it("uses the translation form 'fr' but the primary Locale to format the date") {
                    verbs.checkException {
                        assert(1).createAndAddAssertion(TranslatableWithArgs(TestTranslatable.DATE_KNOWN, firstOfFeb2017), 1, { false })
                    }.toThrow<AssertionError> { message { contains("02/01/17 était Mittwoch!!") } }
                }
            }

            describe("translation for $testTranslatable.${TestTranslatable.DATE_UNKNOWN} (with a date as parameter) is provided for 'it'") {
                it("uses default translation but the primary Locale to format the date") {
                    verbs.checkException {
                        assert(1).createAndAddAssertion(TranslatableWithArgs(TestTranslatable.DATE_UNKNOWN, firstOfFeb2017), 1, { false })
                    }.toThrow<AssertionError> { message { contains("only Mittwoch") } }
                }
            }

            describe("translation for $testTranslatable.${TestTranslatable.PLACEHOLDER} "
                + "with $descriptionAnyAssertion.${DescriptionAnyAssertion.TO_BE} as Placeholder") {
                it("uses the translation from 'fr' for $testTranslatable.${TestTranslatable.PLACEHOLDER} "
                    + "and the translation from 'ch' for $descriptionAnyAssertion.${DescriptionAnyAssertion.TO_BE}") {
                    verbs.checkException {
                        assert(1).createAndAddAssertion(TranslatableWithArgs(TestTranslatable.PLACEHOLDER, DescriptionAnyAssertion.TO_BE), 1, { false })
                    }.toThrow<AssertionError> { message { contains("Caractère de remplacement ist") } }
                }
            }
        }
    }

}) {
    /**
     * Contains [ISimpleTranslatable]s which are used in [TranslationSupplierIntSpec].
     */
    enum class TestTranslatable(override val value: String) : ISimpleTranslatable {
        DATE_KNOWN("%tD is a %<tA"),
        DATE_UNKNOWN("only %tA"),
        PLACEHOLDER("placeholder %s")
    }
}
