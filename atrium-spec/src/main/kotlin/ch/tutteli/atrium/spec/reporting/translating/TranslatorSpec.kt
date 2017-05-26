package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.reporting.translating.*
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.toBe
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import java.util.*

open class TranslatorSpec(
    val verbs: IAssertionVerbFactory,
    val testeeFactory: (translationProvider: ITranslationProvider, locale: Locale, fallbackLocals: Array<out Locale>) -> ITranslator
) : Spek({

    fun testeeFactory(translationProvider: ITranslationProvider, locale: Locale, vararg fallbackLocals: Locale)
        = testeeFactory(translationProvider, locale, fallbackLocals)

    fun mockTranslationProvider(locale: Locale, translations: Pair<ITranslatable, String>): ITranslationProvider {
        val provider = mock<ITranslationProvider> {
            on { get(locale) }.doReturn(mapOf(translations))
        }
        return provider
    }

    val localeUK = Locale.UK
    val translatableTest = object : IEnTranslatable {
        override val value = "test"
    }
    val translatableHello = object : IEnTranslatable {
        override val value = "hello"
    }

    fun SpecBody.checkUsesDefaultOfTranslatable(testee: ITranslator) {
        it("uses ${ITranslatable::class.simpleName}'s ${ITranslatable::getDefault.name}") {
            val result = testee.translate(translatableHello)
            verbs.checkImmediately(result).toBe(translatableHello.value)
        }
    }

    fun SpecBody.checkTranslationSuccessfulForDesiredTranslatable(testee: ITranslator, translation: String, localeName: String) {
        context("but for the wrong ${ITranslatable::class.simpleName}") {
            it("uses ${ITranslatable::class.simpleName}'s ${ITranslatable::getDefault.name}") {
                val result = testee.translate(translatableTest)
                verbs.checkImmediately(result).toBe(translatableTest.value)
            }
        }

        context("for the desired ${ITranslatable::class.simpleName}") {
            it("uses the translation of Locale $localeName") {
                val result = testee.translate(translatableHello)
                verbs.checkImmediately(result).toBe(translation)
            }
        }
    }
    val translationEn = "hi"
    val providerWithEn = mockTranslationProvider(Locale.ENGLISH, translatableHello to translationEn)
    val translationEnUk = "heya"
    val providerWithEnUk = mockTranslationProvider(Locale.UK, translatableHello to translationEnUk)
    val translationEnUs = "howdy"
    val providerWithEnUs = mockTranslationProvider(Locale.US, translatableHello to translationEnUs)

    val translationFr = "bonjour"
    val providerWithFr = mockTranslationProvider(Locale.FRENCH, translatableHello to translationFr)
    val translationFrFr = "salut"
    val providerWithFrFr = mockTranslationProvider(Locale.FRANCE, translatableHello to translationFrFr)

    describe("translating a ${ITranslatable::class.simpleName} to $localeUK without fallbacks") {

        context("no translations provided at all") {
            val testee = testeeFactory(mock<ITranslationProvider>(), localeUK)
            checkUsesDefaultOfTranslatable(testee)
        }

        context("translation provided for fr_FR") {
            val testee = testeeFactory(providerWithFrFr, localeUK)
            checkUsesDefaultOfTranslatable(testee)
        }

        context("translation provided for en_US") {
            val testee = testeeFactory(providerWithEnUs, localeUK)
            checkUsesDefaultOfTranslatable(testee)
        }

        context("translation provided for en") {
            val testee = testeeFactory(providerWithEn, localeUK)
            checkTranslationSuccessfulForDesiredTranslatable(testee, translationEn, "en")
        }

        context("translation provided for en_UK") {
            val testee = testeeFactory(providerWithEnUk, localeUK)
            checkTranslationSuccessfulForDesiredTranslatable(testee, translationEnUk, "en_GB")
        }

    }

    describe("translating a ${ITranslatable::class.simpleName} to $localeUK with fallbacks"){

        context("translation provided but for Locale fr_FR without a fallback to it") {
            val testee = testeeFactory(providerWithFrFr, localeUK)
            checkUsesDefaultOfTranslatable(testee)
        }

        context("translation provided in Locale fr_FR but fallback to fr_CA defined") {
            val testee = testeeFactory(providerWithFrFr, localeUK, Locale.CANADA_FRENCH)
            checkUsesDefaultOfTranslatable(testee)
        }

        context("translation provided in Locale fr_FR but fallback to fr defined") {
            val testee = testeeFactory(providerWithFrFr, localeUK, Locale.FRENCH)
            checkUsesDefaultOfTranslatable(testee)
        }

        context("translation provided in Locale fr and fallback to fr_FR defined") {

            context("as first fallback") {
                val testee = testeeFactory(providerWithFr, localeUK, Locale.FRANCE)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr")
            }
            context("as second fallback") {
                val testee = testeeFactory(providerWithFr, localeUK, Locale.CANADA, Locale.FRANCE)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr")
            }

            context("as third fallback") {
                val testee = testeeFactory(providerWithFr, localeUK, Locale.CANADA, Locale.GERMAN, Locale.FRANCE)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr")
            }
        }

        context("translation provided in Locale fr_FR and fallback to fr_FR defined") {

            context("as first fallback") {
                val testee = testeeFactory(providerWithFr, localeUK, Locale.FRENCH)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr_FR")
            }
            context("as second fallback") {
                val testee = testeeFactory(providerWithFr, localeUK, Locale.CANADA, Locale.FRENCH)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr_FR")
            }

            context("as third fallback") {
                val testee = testeeFactory(providerWithFr, localeUK, Locale.CANADA, Locale.GERMAN, Locale.FRENCH)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr_FR")
            }
        }
    }

})
