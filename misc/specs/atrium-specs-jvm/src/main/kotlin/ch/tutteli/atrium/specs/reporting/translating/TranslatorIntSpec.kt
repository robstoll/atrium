package ch.tutteli.atrium.specs.reporting.translating

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.prefixedDescribeTemplate
import ch.tutteli.atrium.translations.DescriptionAnyExpectation
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.TO_BE_LESS_THAN
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
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
 * ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec.TestTranslatable-DATE_KNOWN = %tD était %tA
 * ch.tutteli.atrium.spec.reporting.translating.TranslatioIntSpec.TestTranslatable-PLACEHOLDER = Caractère de remplacement %s
 *
 * the Locale it:
 * ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec.TestTranslatable-DATE_KNOWN = solo %tA!!
 * ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec.TestTranslatable-DATE_UNKNOWN = solo %tA!!
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
//TODO 1.0.0 rename to TranslatorIntSpec
@ExperimentalComponentFactoryContainer
abstract class TranslatorIntSpec(
    translatorConfiguration: (RootExpectBuilder.OptionsChooser<*>, Locale, List<Locale>) -> Unit,
    //TODO Remove as soon as https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8193496 is fixed in JDK8
    withSpecialCases: Boolean = true,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) =
        prefixedDescribeTemplate(describePrefix, description, body)

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    fun <T : Any> assertWithDeCh_Fr(subject: T) =
        RootExpectBuilder.forSubject(subject)
            .withVerb(AssertionVerb.EXPECT)
            .withOptions {
                translatorConfiguration(this, Locale("de", "CH"), listOf(Locale("fr")))
            }
            .build()

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    fun <T : Any> assertWithDeCh_FrCh_ItCh(subject: T) =
        RootExpectBuilder.forSubject(subject)
            .withVerb(AssertionVerb.EXPECT)
            .withOptions {
                translatorConfiguration(this, Locale("de", "CH"), listOf(Locale("fr", "CH"), Locale("it", "CH")))
            }
            .build()

    val descriptionAnyExpectation= DescriptionAnyExpectation::class.simpleName
    val testTranslatable = TestTranslatable::class.simpleName

    val descriptionComparableAssertion = DescriptionComparableExpectation::class.simpleName
    val toEqual = DescriptionAnyExpectation.TO_EQUAL
    val notToEqual = DescriptionAnyExpectation.NOT_TO_EQUAL
    val notToBeTheInstance = DescriptionAnyExpectation.NOT_TO_BE_THE_INSTANCE
    val toBeTheInstance = DescriptionAnyExpectation.TO_BE_THE_INSTANCE
    val firstOfFeb2017 = SimpleDateFormat("dd.MM.yyyy").parse("01.02.2017")

    prefixedDescribe("primary locale is 'de_CH' and fallback is 'fr'") {

        context("properties file for $descriptionAnyExpectation is provided for 'de_CH'") {

            describe("translation for $descriptionAnyExpectation.$toEqual is provided for 'de_CH'") {
                it("a failing assertion contains 'ist' instead of 'to equal' in the error message") {
                    expect {
                        assertWithDeCh_Fr(1).toEqual(2)
                    }.toThrow<AssertionError> { messageToContain("ist: 2") }
                }
            }

            describe("translation for $descriptionAnyExpectation.$notToEqual is provided for 'de'") {
                val text = "ist nicht"
                it("a failing assertion contains '$text' instead of 'not to equal' in the error message") {
                    expect {
                        assertWithDeCh_Fr(1).notToEqual(1)
                    }.toThrow<AssertionError> { messageToContain("$text: 1") }
                }
            }

            describe("translation for $descriptionAnyExpectation.$notToBeTheInstance is provided 'fr'") {
                val text = "n'est pas la même instance que"
                it("a failing assertion contains '$text' instead of 'expect' in the error message") {
                    expect {
                        assertWithDeCh_Fr(1).notToBeTheInstance(1)
                    }.toThrow<AssertionError> { messageToContain("$text: 1") }
                }
            }
        }

        context("properties file for ${AssertionVerb::class.simpleName} is not provided for 'de_CH' nor one of its parents") {
            describe("translation for ${AssertionVerb::class.simpleName}.${AssertionVerb.EXPECT} is provided for 'fr'") {
                val text = "il applique que"
                it("a failing assertion contains '$text' instead of 'expect' in the error message") {
                    expect {
                        assertWithDeCh_Fr(1).toEqual(2)
                    }.toThrow<AssertionError> { messageToContain("$text: 1") }
                }
            }

            describe("translation for $descriptionComparableAssertion.$TO_BE_LESS_THAN is not provided for 'fr'") {
                it("throws an AssertionError which message contains the default of $descriptionComparableAssertion.$TO_BE_LESS_THAN") {
                    expect {
                        assertWithDeCh_Fr(1).toBeLessThan(1)
                    }.toThrow<AssertionError> { messageToContain("${TO_BE_LESS_THAN.getDefault()}: 1") }
                }
            }


            describe("translation for $testTranslatable.${TestTranslatable.DATE_KNOWN} (with a date as parameter) is provided for 'fr' and 'it'") {
                it("uses the translation form 'fr' but the primary Locale to format the date") {
                    expect {
                        val assertwithdechFr = assertWithDeCh_Fr(1)
                        assertwithdechFr._logic.append(assertwithdechFr._logic.createDescriptiveAssertion(
                            TranslatableWithArgs(
                                TestTranslatable.DATE_KNOWN,
                                firstOfFeb2017,
                                firstOfFeb2017
                            ), 1
                        ) { false })
                    }.toThrow<AssertionError> { messageToContain("02/01/17 était Mittwoch!!") }
                }
            }

            describe("translation for $testTranslatable.${TestTranslatable.DATE_UNKNOWN} (with a date as parameter) is provided for 'it' but not for 'fr'") {
                it("uses default translation but the primary Locale to format the date") {
                    expect {
                        val assertwithdechFr = assertWithDeCh_Fr(1)
                        assertwithdechFr._logic.append(assertwithdechFr._logic.createDescriptiveAssertion(
                            TranslatableWithArgs(
                                TestTranslatable.DATE_UNKNOWN,
                                firstOfFeb2017
                            ), 1
                        ) { false })
                    }.toThrow<AssertionError> { messageToContain("only Mittwoch") }
                }
            }

            describe(
                "translation for $testTranslatable.${TestTranslatable.PLACEHOLDER} "
                    + "with $descriptionAnyExpectation.$toEqual as Placeholder"
            ) {
                it(
                    "uses the translation from 'fr' for $testTranslatable.${TestTranslatable.PLACEHOLDER} "
                        + "and the translation from 'ch' for $descriptionAnyExpectation.$toEqual"
                ) {
                    expect {
                        val assertwithdechFr = assertWithDeCh_Fr(1)
                        assertwithdechFr._logic.append(assertwithdechFr._logic.createDescriptiveAssertion(
                            TranslatableWithArgs(
                                TestTranslatable.PLACEHOLDER,
                                toEqual
                            ), 1
                        ) { false })
                    }.toThrow<AssertionError> { messageToContain("Caractère de remplacement ist") }
                }
            }
        }
    }

    prefixedDescribe("primary locale is 'de_CH', fallback is 'fr' and then 'it'") {
        context("properties file for ${AssertionVerb::class.simpleName} is not provided for 'de_CH' nor one of its parents") {
            describe("translation for $descriptionComparableAssertion.$TO_BE_LESS_THAN is not provided for 'fr' nor for 'it'") {
                it("throws an AssertionError which message contains the default of $descriptionComparableAssertion.$TO_BE_LESS_THAN") {
                    expect {
                        assertWithDeCh_FrCh_ItCh(1).toBeLessThan(1)
                    }.toThrow<AssertionError> { messageToContain("${TO_BE_LESS_THAN.getDefault()}: 1") }
                }
            }
            describe("translation for $testTranslatable.${TestTranslatable.DATE_KNOWN} (with a date as parameter) is provided for 'fr' and 'it'") {
                it("uses the translation form 'fr' but the primary Locale to format the date") {
                    expect {
                        val assertwithdechFrchItch = assertWithDeCh_FrCh_ItCh(1)
                        assertwithdechFrchItch._logic.append(assertwithdechFrchItch._logic.createDescriptiveAssertion(
                            TranslatableWithArgs(
                                TestTranslatable.DATE_KNOWN,
                                firstOfFeb2017,
                                firstOfFeb2017
                            ), 1
                        ) { false })
                    }.toThrow<AssertionError> { messageToContain("02/01/17 était Mittwoch!!") }
                }
            }

            describe("translation for $testTranslatable.${TestTranslatable.DATE_UNKNOWN} (with a date as parameter) is provided for 'it' but not for 'fr'") {
                it("uses 'it' but the primary Locale to format the date") {
                    expect {
                        val assertwithdechFrchItch = assertWithDeCh_FrCh_ItCh(1)
                        assertwithdechFrchItch._logic.append(assertwithdechFrchItch._logic.createDescriptiveAssertion(
                            TranslatableWithArgs(
                                TestTranslatable.DATE_UNKNOWN,
                                firstOfFeb2017
                            ), 1
                        ) { false })
                    }.toThrow<AssertionError> { messageToContain("solo Mittwoch!!") }
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


            @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
            @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
            val assert = RootExpectBuilder.forSubject(1)
                .withVerb(AssertionVerb.EXPECT)
                .withOptions {
                    translatorConfiguration(this, locale, emptyList())
                }
                .build()

            prefixedDescribe("primary locale is 'zh_$country' and no fallback defined") {
                if (withSpecialCases) {
                    describe("translation for $descriptionAnyExpectation.$toEqual is provided for 'zh_$country' and for ${zhWithScript}_$country") {
                        it("a failing assertion contains '$toEqual ${zhWithScript}_$country' instead of 'to equal' in the error message") {
                            expect {
                                assert.toEqual(2)
                            }.toThrow<AssertionError> { messageToContain("$toEqual ${zhWithScript}_$country: 2") }
                        }
                    }
                    describe("translation for $descriptionAnyExpectation.$notToEqual is provided for 'zh_$country' and for $zhWithScript") {
                        it("a failing assertion contains '$notToEqual $zhWithScript' instead of 'to equal' in the error message") {
                            expect {
                                assert.notToEqual(1)
                            }.toThrow<AssertionError> { messageToContain("$notToEqual $zhWithScript: 1") }
                        }
                    }
                }
                describe("translation for $descriptionAnyExpectation.$notToBeTheInstance is provided for 'zh_$country' and zh") {
                    it("a failing assertion contains '$notToBeTheInstance zh_$country' instead of 'to equal' in the error message") {
                        expect {
                            assert.notToBeTheInstance(1)
                        }.toThrow<AssertionError> { messageToContain("$notToBeTheInstance zh_$country: 1") }
                    }
                }
                describe("translation for $descriptionAnyExpectation.$toBeTheInstance is not provided for 'zh_$country' but for zh") {
                    it("a failing assertion contains '$toBeTheInstance zh' instead of 'to equal' in the error message") {
                        expect {
                            assert.toBeTheInstance(2)
                        }.toThrow<AssertionError> { messageToContain("$toBeTheInstance zh: 2") }
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
        DATE_KNOWN("%tD is a %tA"),
        DATE_UNKNOWN("only %tA"),
        PLACEHOLDER("placeholder %s")
    }
}
