package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

class CharSequenceContainsDefaultTranslationOfAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsDefaultTranslationAssertionSpec(
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

        private fun containsAtLeast(plant: IAssertionPlant<CharSequence>, atLeast: Int, a: ITranslatable, aX: Array<out ITranslatable>)
            = plant.enthaelt.zumindest(atLeast).standardUebersetzungVon(a, *aX)

        private fun getAtMostTriple() = Triple(
            "$contains.$atMost.$defaultTranslationOf",
            { what: String, times: String -> "$contains $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: IAssertionPlant<CharSequence>, atMost: Int, a: ITranslatable, aX: Array<out ITranslatable>)
            = plant.enthaelt.hoechstens(atMost).standardUebersetzungVon(a, *aX)

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$atMost.$defaultTranslationOf",
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: IAssertionPlant<CharSequence>, atMost: Int, a: ITranslatable, aX: Array<out ITranslatable>)
            = plant.enthaelt.ignoriereGrossKleinschreibung.hoechstens(atMost).standardUebersetzungVon(a, *aX)
    }
}
