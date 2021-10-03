package ch.tutteli.atrium.specs.reporting.translating

import ch.tutteli.atrium.api.fluent.en_GB.containsExactly
import ch.tutteli.atrium.api.fluent.en_GB.toContainExactly
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.specs.prefixedDescribeTemplate
import ch.tutteli.kbox.joinToString
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class LocaleOrderDeciderSpec(
    testeeFactory: () -> LocaleOrderDecider,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) =
        prefixedDescribeTemplate(describePrefix, description, body)

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
        Triple(
            "with fallback fr_CH and fr_FR",
            listOf(Locale("fr", "CH"), france),
            arrayOf(Locale("fr", "CH"), french, france, french)
        )
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
                    expect(result).toContainExactly(
                        localeDe,
                        *additionalLocaleCandidates
                    )
                }
            }

            context("primary locale is $localeDeCh") {
                it("returns $localeDeCh, $localeDe$andAdditional") {
                    val result = testee.determineOrder(localeDeCh, fallbackLocales).asIterable()
                    expect(result).toContainExactly(
                        localeDeCh,
                        localeDe,
                        *additionalLocaleCandidates
                    )
                }
            }

            context("primary locale is $localeDeChVariantA") {
                it(
                    "returns "
                        + "$localeDeChVariantA, "
                        + "$localeDeCh, "
                        + "$localeDe"
                        + andAdditional
                ) {
                    val result = testee.determineOrder(localeDeChVariantA, fallbackLocales).asIterable()
                    expect(result).toContainExactly(
                        localeDeChVariantA,
                        localeDeCh,
                        localeDe,
                        *additionalLocaleCandidates
                    )
                }
            }

            describe("$localeDeChVariantAVariantB") {
                it(
                    "returns: "
                        + "$localeDeChVariantAVariantB, "
                        + "$localeDeChVariantA, "
                        + "$localeDeCh, "
                        + "$localeDe"
                        + andAdditional
                ) {
                    val result = testee.determineOrder(localeDeChVariantAVariantB, fallbackLocales).asIterable()
                    expect(result).toContainExactly(
                        localeDeChVariantAVariantB,
                        localeDeChVariantA,
                        localeDeCh,
                        localeDe,
                        *additionalLocaleCandidates
                    )
                }
            }

            describe("$localeDeScriptLatnChVariantA") {
                it(
                    "returns: "
                        + "$localeDeScriptLatnChVariantA, "
                        + "$localeDeScriptLatnCh, "
                        + "$localeDeScriptLatn, "
                        + "$localeDeChVariantA, "
                        + "$localeDeCh, "
                        + "$localeDe"
                        + andAdditional
                ) {
                    val result = testee.determineOrder(localeDeScriptLatnChVariantA, fallbackLocales).asIterable()
                    expect(result).toContainExactly(
                        localeDeScriptLatnChVariantA,
                        localeDeScriptLatnCh,
                        localeDeScriptLatn,
                        localeDeChVariantA,
                        localeDeCh,
                        localeDe,
                        *additionalLocaleCandidates
                    )
                }
            }
        }
    }
})
