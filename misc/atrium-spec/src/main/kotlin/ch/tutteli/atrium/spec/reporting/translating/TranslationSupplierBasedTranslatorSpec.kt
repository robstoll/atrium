package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.translating.*
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

abstract class TranslationSupplierBasedTranslatorSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, locale: java.util.Locale, fallbackLocals: List<java.util.Locale>) -> Translator,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    fun testeeFactory(translationSupplier: TranslationSupplier, locale: Locale, vararg fallbackLocals: Locale)
        = testeeFactory(translationSupplier, coreFactory.newLocaleOrderDecider(), locale.toJavaLocale(), fallbackLocals.map { it.toJavaLocale() })

    fun mockTranslationProvider(locale: Locale, translatable: Translatable, translation: String): TranslationSupplier {
        return mock {
            on { get(translatable, locale) }.doReturn(translation)
        }
    }

    val greatBritain = Locale("en", "GB")
    val french = Locale("fr")
    val france = Locale("fr", "FR")
    val canada = Locale("fr", "CA")
    val german = Locale("de")
    val translatableTest = object : StringBasedTranslatable {
        override val value = "test"
        override val name = "test"
    }
    val translatableHello = object : StringBasedTranslatable {
        override val value = "hello"
        override val name = "hello"
    }

    fun SpecBody.checkUsesDefaultOfTranslatable(testee: Translator) {
        it("uses ${Translatable::class.simpleName}'s ${Translatable::getDefault.name}") {
            val result = testee.translate(translatableHello)
            verbs.checkImmediately(result).toBe(translatableHello.value)
        }
    }

    fun SpecBody.checkTranslationSuccessfulForDesiredTranslatable(testee: Translator, translation: String, locale: Locale) {
        context("but for the wrong ${Translatable::class.simpleName}") {
            it("uses ${Translatable::class.simpleName}'s ${Translatable::getDefault.name}") {
                val result = testee.translate(translatableTest)
                verbs.checkImmediately(result).toBe(translatableTest.value)
            }
        }

        context("for the desired ${Translatable::class.simpleName}") {
            it("uses the translation of Locale $locale") {
                val result = testee.translate(translatableHello)
                verbs.checkImmediately(result).toBe(translation)
            }
        }
    }

    val translationEn = "hi"
    val providerWithEn = mockTranslationProvider(Locale("en"), translatableHello, translationEn)
    val translationEnUk = "heya"
    val providerWithEnUk = mockTranslationProvider(greatBritain, translatableHello, translationEnUk)
    val translationEnUs = "howdy"
    val providerWithEnUs = mockTranslationProvider(Locale("en", "Us"), translatableHello, translationEnUs)

    val translationFr = "bonjour"
    val providerWithFr = mockTranslationProvider(french, translatableHello, translationFr)
    val translationFrFr = "salut"
    val providerWithFrFr = mockTranslationProvider(france, translatableHello, translationFrFr)

    describeFun(Translator::translate.name) {

        describe("translating a ${Translatable::class.simpleName} to $greatBritain without fallbacks") {

            context("no translations provided at all") {
                val testee = testeeFactory(mock(), greatBritain)
                checkUsesDefaultOfTranslatable(testee)
            }

            context("translation provided for fr_FR") {
                val testee = testeeFactory(providerWithFrFr, greatBritain)
                checkUsesDefaultOfTranslatable(testee)
            }

            context("translation provided for en_US") {
                val testee = testeeFactory(providerWithEnUs, greatBritain)
                checkUsesDefaultOfTranslatable(testee)
            }

            context("translation provided for en") {
                val testee = testeeFactory(providerWithEn, greatBritain)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationEn, greatBritain)
            }

            context("translation provided for en_GB") {
                val testee = testeeFactory(providerWithEnUk, greatBritain)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationEnUk, greatBritain)
            }

        }


        describe("translating a ${Translatable::class.simpleName} to $greatBritain with fallbacks") {

            context("translation provided but for Locale fr_FR without a fallback to it") {
                val testee = testeeFactory(providerWithFrFr, greatBritain)
                checkUsesDefaultOfTranslatable(testee)
            }

            context("translation provided in Locale fr_FR but fallback to fr_CA defined") {
                val testee = testeeFactory(providerWithFrFr, greatBritain, canada)
                checkUsesDefaultOfTranslatable(testee)
            }

            context("translation provided in Locale fr_FR but fallback to fr defined") {
                val testee = testeeFactory(providerWithFrFr, greatBritain, french)
                checkUsesDefaultOfTranslatable(testee)
            }

            context("translation provided in Locale fr and fallback to fr_FR defined") {

                context("as first fallback") {
                    val testee = testeeFactory(providerWithFr, greatBritain, france)
                    checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, french)
                }
                context("as second fallback") {
                    val testee = testeeFactory(providerWithFr, greatBritain, canada, france)
                    checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, french)
                }

                context("as third fallback") {
                    val testee = testeeFactory(providerWithFr, greatBritain, canada, german, france)
                    checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, french)
                }
            }

            context("translation provided in Locale fr_FR and fallback to fr_FR defined") {

                context("as first fallback") {
                    val testee = testeeFactory(providerWithFr, greatBritain, french)
                    checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, france)
                }
                context("as second fallback") {
                    val testee = testeeFactory(providerWithFr, greatBritain, canada, french)
                    checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, france)
                }

                context("as third fallback") {
                    val testee = testeeFactory(providerWithFr, greatBritain, canada, german, french)
                    checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, france)
                }
            }
        }
    }

})
