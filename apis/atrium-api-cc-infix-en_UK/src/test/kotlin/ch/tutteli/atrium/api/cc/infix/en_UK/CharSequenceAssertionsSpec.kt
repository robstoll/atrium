package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KFunction2

class CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    getContainsDefaultTranslationOfPair(),
    getContainsNotDefaultTranslationOfPair(),
    "${AssertionPlant<CharSequence>::toBe.name} ${Empty::class.simpleName}" to Companion::toBeEmpty,
    "${AssertionPlant<CharSequence>::notToBe.name} ${Empty::class.simpleName}" to Companion::notToBeEmpty,
    AssertionPlant<CharSequence>::startsWith.name to Companion::startsWith,
    AssertionPlant<CharSequence>::startsNotWith.name to Companion::startsNotWith,
    AssertionPlant<CharSequence>::endsWith.name to Companion::endsWith,
    AssertionPlant<CharSequence>::endsNotWith.name to Companion::endsNotWith
) {
    companion object {
        fun getContainsDefaultTranslationOfPair()
            = getContainsName() to Companion::containsDefaultTranslationOf

        private fun getContainsName(): String {
            val f: KFunction2<AssertionPlant<CharSequence>, DefaultTranslationsOf, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::contains
            return "${f.name} ${DefaultTranslationsOf::class.simpleName}"
        }

        private fun containsDefaultTranslationOf(plant: AssertionPlant<CharSequence>, expected: Translatable, otherExpected: Array<out Translatable>): AssertionPlant<CharSequence> {
            return if (otherExpected.isEmpty()) {
                plant containsDefaultTranslationOf expected
            } else {
                plant contains DefaultTranslationsOf(expected, *otherExpected)
            }
        }


        fun getContainsNotDefaultTranslationOfPair()
            = getContainsNotName() to Companion::containsNotDefaultTranslationOf

        private fun getContainsNotName(): String {
            val f: KFunction2<AssertionPlant<CharSequence>, DefaultTranslationsOf, AssertionPlant<CharSequence>> = AssertionPlant<CharSequence>::containsNot
            return "${f.name} ${DefaultTranslationsOf::class.simpleName}"
        }

        private fun containsNotDefaultTranslationOf(plant: AssertionPlant<CharSequence>, expected: Translatable, otherExpected: Array<out Translatable>)
            = plant containsNot DefaultTranslationsOf(expected, *otherExpected)

        fun toBeEmpty(plant: AssertionPlant<CharSequence>)
            = plant toBe Empty

        fun notToBeEmpty(plant: AssertionPlant<CharSequence>)
            = plant notToBe Empty

        fun startsWith(plant: AssertionPlant<CharSequence>, expected: CharSequence)
            = plant startsWith expected

        fun startsNotWith(plant: AssertionPlant<CharSequence>, expected: CharSequence)
            = plant startsNotWith expected

        fun endsWith(plant: AssertionPlant<CharSequence>, expected: CharSequence)
            = plant endsWith expected

        fun endsNotWith(plant: AssertionPlant<CharSequence>, expected: CharSequence)
            = plant endsNotWith expected
    }
}
