package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable
import kotlin.reflect.KFunction2

class CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    getContainsDefaultTranslationOfPair(),
    getContainsNotDefaultTranslationOfPair(),
    "${IAssertionPlant<CharSequence>::toBe.name} ${Empty::class.simpleName}" to Companion::toBeEmpty,
    "${IAssertionPlant<CharSequence>::notToBe.name} ${Empty::class.simpleName}" to Companion::notToBeEmpty,
    IAssertionPlant<CharSequence>::startsWith.name to Companion::startsWith,
    IAssertionPlant<CharSequence>::startsNotWith.name to Companion::startsNotWith,
    IAssertionPlant<CharSequence>::endsWith.name to Companion::endsWith,
    IAssertionPlant<CharSequence>::endsNotWith.name to Companion::endsNotWith
) {
    companion object {
        fun getContainsDefaultTranslationOfPair()
            = getContainsName() to Companion::containsDefaultTranslationOf

        private fun getContainsName(): String {
            val f: KFunction2<IAssertionPlant<CharSequence>, DefaultTranslationsOf, IAssertionPlant<CharSequence>> = IAssertionPlant<CharSequence>::contains
            return f.name
        }

        private fun containsDefaultTranslationOf(plant: IAssertionPlant<CharSequence>, expected: ITranslatable, otherExpected: Array<out ITranslatable>)
            = plant contains DefaultTranslationsOf(expected, otherExpected)

        fun getContainsNotDefaultTranslationOfPair()
            = getContainsNotName() to Companion::containsNotDefaultTranslationOf

        private fun getContainsNotName(): String {
            val f: KFunction2<IAssertionPlant<CharSequence>, DefaultTranslationsOf, IAssertionPlant<CharSequence>> = IAssertionPlant<CharSequence>::containsNot
            return f.name
        }

        private fun containsNotDefaultTranslationOf(plant: IAssertionPlant<CharSequence>, expected: ITranslatable, otherExpected: Array<out ITranslatable>)
            = plant containsNot DefaultTranslationsOf(expected, otherExpected)

        fun toBeEmpty(plant: IAssertionPlant<CharSequence>)
            = plant toBe Empty

        fun notToBeEmpty(plant: IAssertionPlant<CharSequence>)
            = plant notToBe Empty

        fun startsWith(plant: IAssertionPlant<CharSequence>, expected: CharSequence)
            = plant startsWith expected

        fun startsNotWith(plant: IAssertionPlant<CharSequence>, expected: CharSequence)
            = plant startsNotWith expected

        fun endsWith(plant: IAssertionPlant<CharSequence>, expected: CharSequence)
            = plant endsWith expected

        fun endsNotWith(plant: IAssertionPlant<CharSequence>, expected: CharSequence)
            = plant endsNotWith expected
    }
}
