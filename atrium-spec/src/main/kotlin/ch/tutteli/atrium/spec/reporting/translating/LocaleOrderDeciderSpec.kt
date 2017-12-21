package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.api.cc.en_UK.containsStrictly
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.kbox.joinToString
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*

abstract class LocaleOrderDeciderSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: () -> LocaleOrderDecider,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit)
        = prefixedDescribe(describePrefix, description, body)

    val assert: (Iterable<Locale>) -> AssertionPlant<Iterable<Locale>> = verbs::checkImmediately
    val testee = testeeFactory()

    val variantA = "VariantA"
    val variantAB = "${variantA}_VariantB"
    val localeDe = Locale("de")
    val localeDeCh = Locale("de", "ch")
    val localeDeChVariantA = Locale("de", "ch", variantA)
    val localeDeChVariantAVariantB = Locale("de", "ch", variantAB)
    val localeDeChVariantAScriptLatnBuilder = Locale.Builder()
        .setLanguage("de")
        .setScript("Latn")
        .setRegion("ch")
        .setVariant(variantA)
    val localeDeScriptLatnChVariantA = localeDeChVariantAScriptLatnBuilder.build()
    val localeDeScriptLatnCh = localeDeChVariantAScriptLatnBuilder.setVariant("").build()
    val localeDeScriptLatn = localeDeChVariantAScriptLatnBuilder.setRegion("").build()
    listOf(
        Triple("without fallbackLocales", emptyArray(), emptyArray()),
        Triple("with fallback fr", arrayOf(Locale.FRENCH), arrayOf(Locale.FRENCH)),
        Triple("with fallback fr_FR", arrayOf(Locale.FRANCE), arrayOf(Locale.FRANCE, Locale.FRENCH)),
        Triple("with fallback fr_CH and fr_FR", arrayOf(Locale("fr", "CH"), Locale.FRANCE), arrayOf(Locale("fr", "CH"), Locale.FRENCH, Locale.FRANCE, Locale.FRENCH))

    ).forEach { (description, fallbackLocales, additionalLocaleCandidates) ->
        val andAdditional = if (additionalLocaleCandidates.isNotEmpty()) {
            ", " + additionalLocaleCandidates.joinToString(", ", " and ", { it, sb -> sb.append(it) })
        } else {
            ""
        }
        prefixedDescribe(description) {

            context("primary locale is $localeDe") {
                it("returns $localeDe$andAdditional") {
                    val result = testee.determineOrder(localeDe, fallbackLocales).asIterable()
                    assert(result).containsStrictly(
                        localeDe
                        , *additionalLocaleCandidates)
                }
            }

            context("primary locale is $localeDeCh") {
                it("returns $localeDeCh, $localeDe$andAdditional") {
                    val result = testee.determineOrder(localeDeCh, fallbackLocales).asIterable()
                    assert(result).containsStrictly(
                        localeDeCh
                        , localeDe
                        , *additionalLocaleCandidates)
                }
            }

            context("primary locale is $localeDeChVariantA") {
                it("returns "
                    + "$localeDeChVariantA, "
                    + "$localeDeCh, "
                    + "$localeDe"
                    + andAdditional) {
                    val result = testee.determineOrder(localeDeChVariantA, fallbackLocales).asIterable()
                    assert(result).containsStrictly(
                        localeDeChVariantA
                        , localeDeCh
                        , localeDe
                        , *additionalLocaleCandidates)
                }
            }

            describe("$localeDeChVariantAVariantB") {
                it("returns: "
                    + "$localeDeChVariantAVariantB, "
                    + "$localeDeChVariantA, "
                    + "$localeDeCh, "
                    + "$localeDe"
                    + andAdditional) {
                    val result = testee.determineOrder(localeDeChVariantAVariantB, fallbackLocales).asIterable()
                    assert(result).containsStrictly(
                        localeDeChVariantAVariantB
                        , localeDeChVariantA
                        , localeDeCh
                        , localeDe
                        , *additionalLocaleCandidates)
                }
            }

            describe("$localeDeScriptLatnChVariantA") {
                it("returns: "
                    + "$localeDeScriptLatnChVariantA, "
                    + "$localeDeScriptLatnCh, "
                    + "$localeDeScriptLatn, "
                    + "$localeDeChVariantA, "
                    + "$localeDeCh, "
                    + "$localeDe"
                    + andAdditional) {
                    val result = testee.determineOrder(localeDeScriptLatnChVariantA, fallbackLocales).asIterable()
                    assert(result).containsStrictly(
                        localeDeScriptLatnChVariantA
                        , localeDeScriptLatnCh
                        , localeDeScriptLatn
                        , localeDeChVariantA
                        , localeDeCh
                        , localeDe
                        , *additionalLocaleCandidates)
                }
            }
        }
    }
})
