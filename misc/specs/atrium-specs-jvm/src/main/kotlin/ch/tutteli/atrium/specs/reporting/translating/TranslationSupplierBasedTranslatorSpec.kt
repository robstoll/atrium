package ch.tutteli.atrium.specs.reporting.translating

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.translating.*
import ch.tutteli.atrium.specs.describeFunTemplate
import io.mockk.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class TranslationSupplierBasedTranslatorSpec(
    testeeFactory: (translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, locale: Locale, fallbackLocals: List<Locale>) -> Translator,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    fun testeeFactory(translationSupplier: TranslationSupplier, locale: Locale, vararg fallbackLocals: Locale) =
        testeeFactory(translationSupplier, coreFactory.newLocaleOrderDecider(), locale, fallbackLocals.toList())

    fun mockTranslationProvider(locale: Locale, translatable: Translatable, translation: String?): TranslationSupplier {
        return mockk {
            every { get(any(), any()) } returns null
            every { get(translatable, locale) } returns translation
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

    @Suppress("unused")
    fun Suite.checkUsesDefaultOfTranslatable(testee: Translator) {
        it("uses ${Translatable::class.simpleName}'s ${Translatable::getDefault.name}") {
            val result = testee.translate(translatableHello)
            expect(result).toBe(translatableHello.value)
        }
    }

    @Suppress("unused")
    fun Suite.checkTranslationSuccessfulForDesiredTranslatable(
        testee: Translator,
        translation: String,
        locale: Locale
    ) {
        describe("but for the wrong ${Translatable::class.simpleName}") {
            it("uses ${Translatable::class.simpleName}'s ${Translatable::getDefault.name}") {
                val result = testee.translate(translatableTest)
                expect(result).toBe(translatableTest.value)
            }
        }

        describe("for the desired ${Translatable::class.simpleName}") {
            it("uses the translation of Locale $locale") {
                val result = testee.translate(translatableHello)
                expect(result).toBe(translation)
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
                val testee = testeeFactory(mockk {
                    every { get(any(), any()) } returns null
                }, greatBritain)
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
