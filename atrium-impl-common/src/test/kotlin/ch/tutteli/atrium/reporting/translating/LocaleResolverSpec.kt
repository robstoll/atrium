package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*

class LocaleResolverSpec : Spek({
    val verbs: IAssertionVerbFactory = AssertionVerbFactory
    val testee = LocaleResolver()


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
                val result = testee.resolve(localeDe, emptyArray())
                //TODO implement contains for collections
                //verbs.checkImmediately(result).containsStrict(Locale("de"), Locale.ROOT)
                val iterator = result.iterator()
                verbs.checkImmediately(iterator.next()).toBe(localeDe)
                verbs.checkImmediately(iterator.next()).toBe(Locale.ROOT)
                verbs.checkImmediately(iterator.hasNext()).toBe(false)
            }
        }


        describe(localeWithDeCh) {
            it("returns $localeWithDeCh, $localeWithDe and Locale.ROOT") {
                val result = testee.resolve(localeDeCh, emptyArray())
                //TODO replace once assertion function contains for collections exists
                val iterator = result.iterator()
                verbs.checkImmediately(iterator.next()).toBe(localeDeCh)
                verbs.checkImmediately(iterator.next()).toBe(localeDe)
                verbs.checkImmediately(iterator.next()).toBe(Locale.ROOT)
                verbs.checkImmediately(iterator.hasNext()).toBe(false)
            }
        }

        describe(localeWithDeChVariantA) {
            it("returns "
                + "$localeWithDeChVariantA, "
                + "$localeWithDeCh, "
                + "$localeWithDe, "
                + "and Locale.ROOT") {
                val result = testee.resolve(localeDeChVariantA, emptyArray())
                //TODO replace once assertion function contains for collections exists
                val iterator = result.iterator()
                verbs.checkImmediately(iterator.next()).toBe(localeDeChVariantA)
                verbs.checkImmediately(iterator.next()).toBe(localeDeCh)
                verbs.checkImmediately(iterator.next()).toBe(localeDe)
                verbs.checkImmediately(iterator.next()).toBe(Locale.ROOT)
                verbs.checkImmediately(iterator.hasNext()).toBe(false)
            }
        }


        describe(localeWithDeChVariantAVariantB) {
            it("returns: "
                + "$localeWithDeChVariantAVariantB, "
                + "$localeWithDeChVariantA, "
                + "$localeWithDeCh, "
                + "$localeWithDe, "
                + "and Locale.ROOT") {
                val result = testee.resolve(localeDeChVariantAVariantB, emptyArray())
                //TODO replace once assertion function contains for collections exists
                val iterator = result.iterator()
                verbs.checkImmediately(iterator.next()).toBe(localeDeChVariantAVariantB)
                verbs.checkImmediately(iterator.next()).toBe(localeDeChVariantA)
                verbs.checkImmediately(iterator.next()).toBe(localeDeCh)
                verbs.checkImmediately(iterator.next()).toBe(localeDe)
                verbs.checkImmediately(iterator.next()).toBe(Locale.ROOT)
                verbs.checkImmediately(iterator.hasNext()).toBe(false)
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
                val result = testee.resolve(localeDeChVariantAScriptLatnBuilder.build(), emptyArray())
                //TODO replace once assertion function contains for collections exists
                val iterator = result.iterator()
                verbs.checkImmediately(iterator.next()).toBe(localeDeChVariantAScriptLatnBuilder.build())
                verbs.checkImmediately(iterator.next()).toBe(localeDeChVariantAScriptLatnBuilder.setVariant("").build())
                verbs.checkImmediately(iterator.next()).toBe(localeDeChVariantAScriptLatnBuilder.setRegion("").build())
                verbs.checkImmediately(iterator.next()).toBe(localeDeChVariantA)
                verbs.checkImmediately(iterator.next()).toBe(localeDeCh)
                verbs.checkImmediately(iterator.next()).toBe(localeDe)
                verbs.checkImmediately(iterator.next()).toBe(Locale.ROOT)
                verbs.checkImmediately(iterator.hasNext()).toBe(false)
            }
        }
    }
})
