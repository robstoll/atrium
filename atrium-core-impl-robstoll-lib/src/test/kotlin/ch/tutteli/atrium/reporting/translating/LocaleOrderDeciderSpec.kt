package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.en_UK.containsStrictly
import ch.tutteli.atrium.creating.IAssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*

class LocaleOrderDeciderSpec : Spek({
    val assert: (Iterable<Locale>) -> IAssertionPlant<Iterable<Locale>> = AssertionVerbFactory::checkImmediately
    val testee = LocaleOrderDecider()

    val variantA = "VariantA"
    val variantAB = "${variantA}_VariantB"
    val localeDe = Locale("de")
    val localeWithDe = "Locale(de)"
    val localeDeCh = Locale("de", "ch")
    val localeWithDeCh = "Locale(de, CH)"
    val localeDeChVariantA = Locale("de", "ch", variantA)
    val localeWithDeChVariantA = "Locale(de, CH, VariantA)"
    val localeDeChVariantAVariantB = Locale("de", "ch", variantAB)
    val localeWithDeChVariantAVariantB = "Locale(de, CH, VariantA_VariantB)"
    val localeDeChVariantAScriptLatnBuilder = Locale.Builder()
        .setLanguage("de")
        .setScript("latn")
        .setRegion("ch")
        .setVariant(variantA)
    val localeWithDeChVariantAScriptLatn = "Locale(de, CH, VariantA, script=latn)"
    val localeWithDeChScriptLatn = "Locale(de, CH, script=latn)"
    val localeWithDeScriptLatn = "Locale(de, script=latn)"

    describe("without fallbackLocales") {

        describe(localeWithDe) {
            it("returns $localeWithDe and Locale.ROOT") {
                val result = testee.determineOrder(localeDe, emptyArray()).asIterable()
                assert(result).containsStrictly(localeDe, Locale.ROOT)
            }
        }

        describe(localeWithDeCh) {
            it("returns $localeWithDeCh, $localeWithDe and Locale.ROOT") {
                val result = testee.determineOrder(localeDeCh, emptyArray()).asIterable()
                assert(result).containsStrictly(localeDeCh, localeDe, Locale.ROOT)
            }
        }

        describe(localeWithDeChVariantA) {
            it("returns "
                + "$localeWithDeChVariantA, "
                + "$localeWithDeCh, "
                + "$localeWithDe, "
                + "and Locale.ROOT") {
                val result = testee.determineOrder(localeDeChVariantA, emptyArray()).asIterable()
                assert(result).containsStrictly(localeDeChVariantA, localeDeCh, localeDe, Locale.ROOT)
            }
        }

        describe(localeWithDeChVariantAVariantB) {
            it("returns: "
                + "$localeWithDeChVariantAVariantB, "
                + "$localeWithDeChVariantA, "
                + "$localeWithDeCh, "
                + "$localeWithDe, "
                + "and Locale.ROOT") {
                val result = testee.determineOrder(localeDeChVariantAVariantB, emptyArray()).asIterable()
                assert(result).containsStrictly(
                      localeDeChVariantAVariantB
                    , localeDeChVariantA
                    , localeDeCh
                    , localeDe
                    , Locale.ROOT)
            }
        }

        describe(localeWithDeChVariantAScriptLatn) {
            it("returns: "
                + "$localeWithDeChVariantAScriptLatn, "
                + "$localeWithDeChScriptLatn, "
                + "$localeWithDeScriptLatn, "
                + "$localeWithDeChVariantA, "
                + "$localeWithDeCh, "
                + "$localeWithDe, "
                + "and Locale.ROOT") {
                val result = testee.determineOrder(localeDeChVariantAScriptLatnBuilder.build(), emptyArray()).asIterable()
                assert(result).containsStrictly(
                      localeDeChVariantAScriptLatnBuilder.build()
                    , localeDeChVariantAScriptLatnBuilder.setVariant("").build()
                    , localeDeChVariantAScriptLatnBuilder.setRegion("").build()
                    , localeDeChVariantA
                    , localeDeCh
                    , localeDe
                    , Locale.ROOT)
            }
        }
    }
})
