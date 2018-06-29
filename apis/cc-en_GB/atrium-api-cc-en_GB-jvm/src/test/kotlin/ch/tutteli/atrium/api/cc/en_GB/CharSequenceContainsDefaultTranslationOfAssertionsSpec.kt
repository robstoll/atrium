package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
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

        private fun getNameContainsDefaultTranslationOf() = "$contains with search mode defaultTranslationOf was removed from this API"

        private fun getAtLeastTriple() = Triple(
            "$contains.$atLeast.defaultTranslationOf",
            { what: String, times: String -> "$contains $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: Assert<CharSequence>, atLeast: Int, a: Translatable, aX: Array<out Translatable>)
            = plant.contains.atLeast(atLeast).values(a.getDefault(), *aX.map { it.getDefault() }.toTypedArray())

        private fun getAtMostTriple() = Triple(
            "$contains.$atMost.defaultTranslationOf",
            { what: String, times: String -> "$contains $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: Assert<CharSequence>, atMost: Int, a: Translatable, aX: Array<out Translatable>)
            = plant.contains.atMost(atMost).values(a.getDefault(), *aX.map { it.getDefault() }.toTypedArray())

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$contains.$ignoringCase.$atMost.defaultTranslationOf",
            { what: String, times: String -> "$contains $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: Assert<CharSequence>, atMost: Int, a: Translatable, aX: Array<out Translatable>)
            = plant.contains.ignoringCase.atMost(atMost).values(a.getDefault(), *aX.map { it.getDefault() }.toTypedArray())
    }
}
