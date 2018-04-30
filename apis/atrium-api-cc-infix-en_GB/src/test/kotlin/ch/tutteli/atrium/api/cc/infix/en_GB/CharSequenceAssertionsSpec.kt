package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KFunction2

class CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    getContainsDefaultTranslationOfPair(),
    getContainsNotDefaultTranslationOfPair(),
    "${Assert<CharSequence>::toBe.name} ${Empty::class.simpleName}" to Companion::toBeEmpty,
    "${Assert<CharSequence>::notToBe.name} ${Empty::class.simpleName}" to Companion::notToBeEmpty,
    Assert<CharSequence>::startsWith.name to Companion::startsWith,
    Assert<CharSequence>::startsNotWith.name to Companion::startsNotWith,
    Assert<CharSequence>::endsWith.name to Companion::endsWith,
    Assert<CharSequence>::endsNotWith.name to Companion::endsNotWith
) {
    companion object {
        fun getContainsDefaultTranslationOfPair()
            = getContainsName() to Companion::containsDefaultTranslationOf

        private fun getContainsName(): String {
            val f: KFunction2<Assert<CharSequence>, DefaultTranslationsOf, Assert<CharSequence>> = Assert<CharSequence>::contains
            return "${f.name} ${DefaultTranslationsOf::class.simpleName}"
        }

        private fun containsDefaultTranslationOf(plant: Assert<CharSequence>, expected: Translatable, otherExpected: Array<out Translatable>): Assert<CharSequence> {
            return if (otherExpected.isEmpty()) {
                plant containsDefaultTranslationOf expected
            } else {
                plant contains DefaultTranslationsOf(expected, *otherExpected)
            }
        }


        fun getContainsNotDefaultTranslationOfPair()
            = getContainsNotName() to Companion::containsNotDefaultTranslationOf

        private fun getContainsNotName(): String {
            val f: KFunction2<Assert<CharSequence>, DefaultTranslationsOf, Assert<CharSequence>> = Assert<CharSequence>::containsNot
            return "${f.name} ${DefaultTranslationsOf::class.simpleName}"
        }

        private fun containsNotDefaultTranslationOf(plant: Assert<CharSequence>, expected: Translatable, otherExpected: Array<out Translatable>)
            = plant containsNot DefaultTranslationsOf(expected, *otherExpected)

        fun toBeEmpty(plant: Assert<CharSequence>)
            = plant toBe Empty

        fun notToBeEmpty(plant: Assert<CharSequence>)
            = plant notToBe Empty

        fun startsWith(plant: Assert<CharSequence>, expected: CharSequence)
            = plant startsWith expected

        fun startsNotWith(plant: Assert<CharSequence>, expected: CharSequence)
            = plant startsNotWith expected

        fun endsWith(plant: Assert<CharSequence>, expected: CharSequence)
            = plant endsWith expected

        fun endsNotWith(plant: Assert<CharSequence>, expected: CharSequence)
            = plant endsNotWith expected
    }
}
