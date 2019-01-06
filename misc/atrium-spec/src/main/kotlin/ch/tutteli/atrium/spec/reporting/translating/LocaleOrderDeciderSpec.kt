package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.api.cc.en_GB.containsExactly
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.kbox.joinToString
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

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
    val localeDeChVariantA = Locale("de", null, "ch", variantA)
    val localeDeChVariantAVariantB = Locale("de", null, "ch", variantAB)
    val localeDeScriptLatnChVariantA = Locale("de", "Latn", "ch", variantA)
    val localeDeScriptLatnCh = Locale("de", "Latn", "ch", null)
    val localeDeScriptLatn = Locale("de", "Latn", null, null)
    val france = Locale("fr", "FR")
    val french = Locale("fr")
    listOf(
        Triple("without fallbackLocales", emptyList(), emptyArray()),
        Triple("with fallback fr", listOf(french), arrayOf(french)),
        Triple("with fallback fr_FR", listOf(france), arrayOf(france, french)),
        Triple("with fallback fr_CH and fr_FR", listOf(Locale("fr", "CH"), france), arrayOf(Locale("fr", "CH"), french, france, french))
    ).forEach { (description, fallbackLocales, additionalLocaleCandidates) ->
        val andAdditional = if (additionalLocaleCandidates.isNotEmpty()) {
            ", " + additionalLocaleCandidates.joinToString(", ", " and ") { it, sb -> sb.append(it) }
        } else {
            ""
        }
        prefixedDescribe(description) {

            context("primary locale is $localeDe") {
                it("returns $localeDe$andAdditional") {
                    val result = testee.determineOrder(localeDe, fallbackLocales).asIterable()
                    assert(result).containsExactly(
                        localeDe
                        , *additionalLocaleCandidates
                    )
                }
            }

            context("primary locale is $localeDeCh") {
                it("returns $localeDeCh, $localeDe$andAdditional") {
                    val result = testee.determineOrder(localeDeCh, fallbackLocales).asIterable()
                    assert(result).containsExactly(
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
                    assert(result).containsExactly(
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
                    assert(result).containsExactly(
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
                    assert(result).containsExactly(
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
