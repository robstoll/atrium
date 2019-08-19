package ch.tutteli.atrium.specs.reporting.translating

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.AssertionVerbFactory
import ch.tutteli.atrium.specs.prefixedDescribe
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
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
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = ist
 *
 * the primary Locale's natural first fallback: de
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = ist (de)
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE = ist nicht
 *
 * the fallback Locale: fr
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = est
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE = n'est pas
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-IS_NOT_SAME = n'est pas la même instance que
 * ch.tutteli.atrium.spec.AssertionVerb-ASSERT = il applique que
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-DATE_KNOWN = %tD était %<tA
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-PLACEHOLDER = Caractère de remplacement %s
 *
 * the Locale it:
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-DATE_KNOWN = solo %tA!!
 * ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec$TestTranslatable-DATE_UNKNOWN = solo %tA!!
 *
 * the Locale zh_Hant_TW:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = TO_BE zh_Hant_TW
 *
 * the Locale zh_TW:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE=TO_BE zh_TW
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE=NOT_TO_BE zh_TW
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-IS_NOT_SAME=IS_NOT_SAME zh_TW
 *
 * the Locale zh_Hant_HK:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = TO_BE zh_Hant_HK
 *
 * the Locale zh_HK:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE=TO_BE zh_HK
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE=NOT_TO_BE zh_HK
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-IS_NOT_SAME=IS_NOT_SAME zh_HK
 *
 * the Locale zh_Hant_MO:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = TO_BE zh_Hant_MO
 *
 * the Locale zh_MO:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE=TO_BE zh_MO
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE=NOT_TO_BE zh_MO
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-IS_NOT_SAME=IS_NOT_SAME zh_MO
 *
 * the natural first fallback of zh_TW, zh_TK, zh_MO: zh_Hant
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = zh_Hant
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE = NOT_TO_BE zh_Hant
 *
 * the Locale zh_Hans_CN:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = TO_BE zh_Hans_CN
 *
 * the Locale zh_CN:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE=TO_BE zh_CN
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE=NOT_TO_BE zh_CN
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-IS_NOT_SAME=IS_NOT_SAME zh_CN
 *
 * the Locale zh_Hans_SG:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = TO_BE zh_Hans_SG
 *
 * the Locale zh_SG:
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE=TO_BE zh_SG
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE=NOT_TO_BE zh_SG
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-IS_NOT_SAME=IS_NOT_SAME zh_SG
 *
 * the natural first fallback of zh_CN and zh_SG: zh_Hans
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE = TO_BE zh_Hans
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE = NOT_TO_BE zh_Hans
 *
 * the natural second fallback for zh_... => zh
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-TO_BE =TO_BE zh
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-NOT_TO_BE=NOT_TO_BE zh
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-IS_NOT_SAME=IS_NOT_SAME zh
 * ch.tutteli.atrium.translations.DescriptionAnyAssertion-IS_SAME=IS_SAME zh
 */
//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class TranslatorIntSpec(
    verbs: AssertionVerbFactory,
    reporterFactory: (Locale, Array<out Locale>) -> Reporter,
    //TODO Remove as soon as http://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8193496 is fixed in JDK8
    withSpecialCases: Boolean = true,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val reporterDeChFallbackFr = reporterFactory(Locale("de", "CH"), arrayOf(Locale("fr")))
    @Suppress("DEPRECATION")
    fun <T : Any> assertWithDeCh(subject: T) =
        ExpectImpl.assertionVerbBuilder(subject)
            .withVerb(AssertionVerb.ASSERT)
            .withCustomReporter(reporterDeChFallbackFr)
            .build()

    val descriptionAnyAssertion = DescriptionAnyAssertion::class.simpleName
    val testTranslatable = TestTranslatable::class.simpleName

    val descriptionNumberAssertion = DescriptionComparableAssertion::class.simpleName
    val toBe = DescriptionAnyAssertion.TO_BE
    val notToBe = DescriptionAnyAssertion.NOT_TO_BE
    val isNotSame = DescriptionAnyAssertion.IS_NOT_SAME
    val isSame = DescriptionAnyAssertion.IS_SAME

    prefixedDescribe("primary locale is 'de_CH' and fallback is 'fr'") {

        context("properties file for $descriptionAnyAssertion is provided for 'de_CH'") {

            describe("translation for $descriptionAnyAssertion.$toBe is provided for 'de_CH'") {
                test("a failing assertion contains 'ist' instead of 'to be' in the error message") {
                    verbs.checkException {
                        assertWithDeCh(1).toBe(2)
                    }.toThrow<AssertionError> { messageContains("ist: 2") }
                }
            }

            describe("translation for $descriptionAnyAssertion.$notToBe is provided for 'de'") {
                val text = "ist nicht"
                test("a failing assertion contains '$text' instead of 'not to be' in the error message") {
                    verbs.checkException {
                        assertWithDeCh(1).notToBe(1)
                    }.toThrow<AssertionError> { messageContains("$text: 1") }
                }
            }

            describe("translation for $descriptionAnyAssertion.$isNotSame is provided 'fr'") {
                val text = "n'est pas la même instance que"
                test("a failing assertion contains '$text' instead of 'assert' in the error message") {
                    verbs.checkException {
                        assertWithDeCh(1).isNotSameAs(1)
                    }.toThrow<AssertionError> { messageContains("$text: 1") }
                }
            }
        }

        context("properties file for ${AssertionVerb::class.simpleName} is not provided for 'de_CH' nor one of its parents") {
            describe("translation for ${AssertionVerb::class.simpleName}.${AssertionVerb.ASSERT} is provided for 'fr'") {
                val text = "il applique que"
                test("a failing assertion contains '$text' instead of 'assert' in the error message") {
                    verbs.checkException {
                        assertWithDeCh(1).toBe(2)
                    }.toThrow<AssertionError> { messageContains("$text: 1") }
                }
            }
        }

        context("properties file for $descriptionNumberAssertion is not provided for 'de_CH' nor one of its parents") {

            describe("translation for $descriptionNumberAssertion.${DescriptionComparableAssertion.IS_LESS_THAN} is provided for 'it'") {
                it("throws an AssertionError which message contains the default of $descriptionNumberAssertion.${DescriptionComparableAssertion.IS_LESS_THAN}") {
                    verbs.checkException {
                        assertWithDeCh(1).isLessThan(1)
                    }.toThrow<AssertionError> { messageContains("${DescriptionComparableAssertion.IS_LESS_THAN.getDefault()}: 1") }
                }
            }
        }

        context("properties file for $descriptionNumberAssertion is not provided for 'de_CH' nor one of its parents") {

            val firstOfFeb2017 = SimpleDateFormat("dd.MM.yyyy").parse("01.02.2017")
            describe("translation for $testTranslatable.${TestTranslatable.DATE_KNOWN} (with a date as parameter) is provided for 'fr'") {
                it("uses the translation form 'fr' but the primary Locale to format the date") {
                    verbs.checkException {
                        assertWithDeCh(1).createAndAddAssertion(TranslatableWithArgs(TestTranslatable.DATE_KNOWN, firstOfFeb2017), 1) { false }
                    }.toThrow<AssertionError> { messageContains("02/01/17 était Mittwoch!!") }
                }
            }

            describe("translation for $testTranslatable.${TestTranslatable.DATE_UNKNOWN} (with a date as parameter) is provided for 'it'") {
                it("uses default translation but the primary Locale to format the date") {
                    verbs.checkException {
                        assertWithDeCh(1).createAndAddAssertion(TranslatableWithArgs(TestTranslatable.DATE_UNKNOWN, firstOfFeb2017), 1) { false }
                    }.toThrow<AssertionError> { messageContains("only Mittwoch") }
                }
            }

            describe("translation for $testTranslatable.${TestTranslatable.PLACEHOLDER} "
                + "with $descriptionAnyAssertion.$toBe as Placeholder") {
                it("uses the translation from 'fr' for $testTranslatable.${TestTranslatable.PLACEHOLDER} "
                    + "and the translation from 'ch' for $descriptionAnyAssertion.$toBe") {
                    verbs.checkException {
                        assertWithDeCh(1).createAndAddAssertion(TranslatableWithArgs(TestTranslatable.PLACEHOLDER, toBe), 1) { false }
                    }.toThrow<AssertionError> { messageContains("Caractère de remplacement ist") }
                }
            }
        }
    }

    mapOf(
        "Hant" to listOf("TW", "HK", "MO"),
        "Hans" to listOf("CN", "SG")
    ).forEach { (script, countries) ->
        val zhWithScript = "zh_$script"
        countries.forEach { country ->
            val locale = Locale("zh", country)
            val reporter = reporterFactory(locale, arrayOf())

            val assert = ExpectImpl.assertionVerbBuilder(1)
                .withVerb(AssertionVerb.ASSERT)
                .withCustomReporter(reporter)
                .build()

            prefixedDescribe("primary locale is 'zh_$country' and no fallback defined") {
                if (withSpecialCases) {
                    describe("translation for $descriptionAnyAssertion.$toBe is provided for 'zh_$country' and for ${zhWithScript}_$country") {
                        test("a failing assertion contains '$toBe ${zhWithScript}_$country' instead of 'to be' in the error message") {
                            verbs.checkException {
                                assert.toBe(2)
                            }.toThrow<AssertionError> { messageContains("$toBe ${zhWithScript}_$country: 2") }
                        }
                    }
                    describe("translation for $descriptionAnyAssertion.$notToBe is provided for 'zh_$country' and for $zhWithScript") {
                        test("a failing assertion contains '$notToBe $zhWithScript' instead of 'to be' in the error message") {
                            verbs.checkException {
                                assert.notToBe(1)
                            }.toThrow<AssertionError> { messageContains("$notToBe $zhWithScript: 1") }
                        }
                    }
                }
                describe("translation for $descriptionAnyAssertion.$isNotSame is provided for 'zh_$country' and zh") {
                    test("a failing assertion contains '$isNotSame zh_$country' instead of 'to be' in the error message") {
                        verbs.checkException {
                            assert.isNotSameAs(1)
                        }.toThrow<AssertionError> { messageContains("$isNotSame zh_$country: 1") }
                    }
                }
                describe("translation for $descriptionAnyAssertion.$isSame is not provided for 'zh_$country' but for zh") {
                    test("a failing assertion contains '$isSame zh' instead of 'to be' in the error message") {
                        verbs.checkException {
                            assert.isSameAs(2)
                        }.toThrow<AssertionError> { messageContains("$isSame zh: 2") }
                    }
                }
            }
        }
    }
}) {
    /**
     * Contains [StringBasedTranslatable]s which are used in [TranslatorIntSpec].
     */
    enum class TestTranslatable(override val value: String) : StringBasedTranslatable {
        DATE_KNOWN("%tD is a %<tA"),
        DATE_UNKNOWN("only %tA"),
        PLACEHOLDER("placeholder %s")
    }
}
