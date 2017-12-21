package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

class CharSequenceContainsDefaultTranslationOfAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceContainsDefaultTranslationAssertionSpec(
    AssertionVerbFactory,
    getNameContainsDefaultTranslationOf(),
    getAtLeastTriple(),
    getAtMostTriple(),
    getAtMostIgnoringCaseTriple()
) {

    companion object : CharSequenceContainsSpecBase() {

        private fun getNameContainsDefaultTranslationOf() = "$toContain with search mode $defaultTranslationOf"

        private fun getAtLeastTriple() = Triple(
            "$toContain $atLeast $defaultTranslationOf",
            { what: String, times: String -> "$toContain $what $atLeast $times" },
            Companion::containsAtLeast
        )

        private fun containsAtLeast(plant: AssertionPlant<CharSequence>, atLeast: Int, a: Translatable, aX: Array<out Translatable>): AssertionPlant<CharSequence> {
            return if (aX.isEmpty()) {
                plant to contain atLeast atLeast defaultTranslationOf a
            } else {
                plant to contain atLeast atLeast the DefaultTranslationsOf(a, *aX)
            }
        }

        private fun getAtMostTriple() = Triple(
            "$toContain $atMost $defaultTranslationOf",
            { what: String, times: String -> "$toContain $what $atMost $times" },
            Companion::containsAtMost
        )

        private fun containsAtMost(plant: AssertionPlant<CharSequence>, atMost: Int, a: Translatable, aX: Array<out Translatable>)
            = plant to contain atMost atMost the DefaultTranslationsOf(a, *aX)

        private fun getAtMostIgnoringCaseTriple() = Triple(
            "$toContain $ignoringCase $atMost $defaultTranslationOf",
            { what: String, times: String -> "$toContain $ignoringCase $what $atMost $times" },
            Companion::containsAtMostIgnoringCase
        )

        private fun containsAtMostIgnoringCase(plant: AssertionPlant<CharSequence>, atMost: Int, a: Translatable, aX: Array<out Translatable>): AssertionPlant<CharSequence> {
            return if (aX.isEmpty()) {
                plant to contain ignoring case atMost atMost defaultTranslationOf a
            } else {
                plant to contain ignoring case atMost atMost the DefaultTranslationsOf(a, *aX)
            }
        }
    }
}
