package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.translating.*
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import java.util.*

open class TranslatorSpec(
    val verbs: IAssertionVerbFactory,
    val testeeFactory: (translationProvider: ITranslationProvider, locale: Locale, fallbackLocals: Array<out Locale>) -> ITranslator
) : Spek({

    fun testeeFactory(translationProvider: ITranslationProvider, locale: Locale, vararg fallbackLocals: Locale)
        = testeeFactory(translationProvider, locale, fallbackLocals)

    fun createTranslationProviderReviser(locale: Locale, vararg translations: Pair<ITranslatable, String>): ITranslationProviderReviser
        = AtriumFactory.newTranslationProviderReviser(ReporterBuilder.EMPTY_TRANSLATION_PROVIDER)
        .add(locale, *translations)


    val localeUK = Locale.UK
    val translatableTest = object : IEnTranslatable {
        override val value = "test"
    }
    val translatableHello = object : IEnTranslatable {
        override val value = "hello"
    }

    fun SpecBody.checkUsesTranslatablesDefault(testee: ITranslator) {
        it("uses ${ITranslatable::class.simpleName}'s ${ITranslatable::getDefault.name}") {
            val result = testee.translate(translatableHello)
            verbs.checkImmediately(result).toBe(translatableHello.value)
        }
    }

    fun SpecBody.checkTranslationSuccessfulForDesiredTranslatable(testee: ITranslator, translation: String, localeName: String){
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



    describe("translating ${ITranslatable::class.simpleName} in $localeUK") {

        context("no translations provided at all") {
            val testee = testeeFactory(ReporterBuilder.EMPTY_TRANSLATION_PROVIDER, localeUK)
            checkUsesTranslatablesDefault(testee)
        }

        val translationFr = "bonjour"
        val translationProvider = createTranslationProviderReviser(Locale.FRENCH, translatableHello to translationFr)
        context("translation provided but for Locale fr without a fallback to it") {
            val testee = testeeFactory(translationProvider, localeUK)
            checkUsesTranslatablesDefault(testee)
        }

        context("translation provided in Locale fr_CA and fallback to fr_FR defined") {
            val testee = testeeFactory(translationProvider, localeUK, Locale.CANADA_FRENCH)
            checkUsesTranslatablesDefault(testee)
        }

        //TODO implement feature
        xcontext("translation provided in Locale fr and fallback to fr_FR defined") {
            val testee = testeeFactory(translationProvider, localeUK, Locale.FRANCE)
            checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr")
        }

        context("translation provided in Locale fr and fallback to fr defined") {

            context("as first fallback") {
                val testee = testeeFactory(translationProvider, localeUK, Locale.FRENCH)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr")
            }
            context("as second fallback") {
                val testee = testeeFactory(translationProvider, localeUK, Locale.CANADA, Locale.FRENCH)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr")
            }

            context("as third fallback") {
                val testee = testeeFactory(translationProvider, localeUK, Locale.CANADA, Locale.GERMAN, Locale.FRENCH)
                checkTranslationSuccessfulForDesiredTranslatable(testee, translationFr, "fr")
            }
        }


        val translationEn = "howdy"
        context("translation provided in Locale en_CA") {
            val translationProviderEn = createTranslationProviderReviser(Locale.CANADA, translatableHello to translationEn)
            val testee = testeeFactory(translationProviderEn, localeUK)
            checkUsesTranslatablesDefault(testee)
        }

        //TODO implement feature
        xcontext("translation provided in Locale en") {
            val translationProviderEn = createTranslationProviderReviser(Locale.ENGLISH, translatableHello to translationEn)
            val testee = testeeFactory(translationProviderEn, localeUK)
            checkTranslationSuccessfulForDesiredTranslatable(testee, translationEn, "en")
        }

        context("translation provided in Locale en_GB") {
            val translationProviderEn = createTranslationProviderReviser(Locale.UK, translatableHello to translationEn)
            val testee = testeeFactory(translationProviderEn, localeUK)
            checkTranslationSuccessfulForDesiredTranslatable(testee, translationEn, "en_GB")
        }
    }

})
