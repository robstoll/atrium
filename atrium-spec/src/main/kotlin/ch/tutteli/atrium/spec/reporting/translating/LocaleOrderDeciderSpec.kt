package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.api.cc.en_UK.containsStrictly
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ILocaleOrderDecider
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*

abstract class LocaleOrderDeciderSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: () -> ILocaleOrderDecider
) : Spek({
    val assert: (Iterable<Locale>) -> IAssertionPlant<Iterable<Locale>> = verbs::checkImmediately
    val testee = testeeFactory()

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
                assert(result).containsStrictly(localeDe)
            }
        }

        describe(localeWithDeCh) {
            it("returns $localeWithDeCh, $localeWithDe and Locale.ROOT") {
                val result = testee.determineOrder(localeDeCh, emptyArray()).asIterable()
                assert(result).containsStrictly(localeDeCh, localeDe)
            }
        }

        describe(localeWithDeChVariantA) {
            it("returns "
                + "$localeWithDeChVariantA, "
                + "$localeWithDeCh, "
                + "$localeWithDe, "
                + "and Locale.ROOT") {
                val result = testee.determineOrder(localeDeChVariantA, emptyArray()).asIterable()
                assert(result).containsStrictly(localeDeChVariantA, localeDeCh, localeDe)
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
                    , localeDe)
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
                    , localeDe)
            }
        }
    }
})
