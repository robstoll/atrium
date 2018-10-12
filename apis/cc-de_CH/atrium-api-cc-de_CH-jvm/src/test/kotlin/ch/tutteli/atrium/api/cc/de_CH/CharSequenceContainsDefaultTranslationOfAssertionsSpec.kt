@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.reporting.translating.Translatable

class CharSequenceContainsDefaultTranslationOfAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceContainsDefaultTranslationAssertionsSpec(
    AssertionVerbFactory,
    getNameContainsDefaultTranslationOf(),
    getAtLeastTriple(),
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple()
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNameContainsDefaultTranslationOf() = "$contains with search mode $defaultTranslationOf"

        private fun getAtLeastTriple() = Triple(
            "$contains.$atLeast.$defaultTranslationOf",
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: Assert<CharSequence>, atLeast: Int, a: Translatable, aX: Array<out Translatable>)
            = plant.enthaelt.zumindest(atLeast).standardUebersetzungVon(a, *aX)

        private fun getAtMostTriple() = Triple(
            "$contains.$atMost.$defaultTranslationOf",
            { what: String, times: String -> "$contains $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Assert<CharSequence>, atMost: Int, a: Translatable, aX: Array<out Translatable>)
            = plant.enthaelt.hoechstens(atMost).standardUebersetzungVon(a, *aX)

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$atMost.$defaultTranslationOf",
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: Assert<CharSequence>, atMost: Int, a: Translatable, aX: Array<out Translatable>)
            = plant.enthaelt.ignoriereGrossKleinschreibung.hoechstens(atMost).standardUebersetzungVon(a, *aX)
    }
}
