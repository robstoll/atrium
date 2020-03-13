@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Blank
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 1.0.0, no need to migrate to Spek 2
class CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    getContainsDefaultTranslationOfPair(),
    getContainsNotDefaultTranslationOfPair(),
    "toBe ${Empty::class.simpleName}" to Companion::toBeEmpty,
    "notToBe ${Empty::class.simpleName}" to Companion::notToBeEmpty,
    "notToBe ${Blank::class.simpleName}" to Companion::notToBeBlank,
    Assert<CharSequence>::startsWith.name to Companion::startsWith,
    Assert<CharSequence>::startsNotWith.name to Companion::startsNotWith,
    Assert<CharSequence>::endsWith.name to Companion::endsWith,
    Assert<CharSequence>::endsNotWith.name to Companion::endsNotWith,
    "◆ ", "⚬ "
) {
    companion object {
        fun getContainsDefaultTranslationOfPair()
            = "containsDefaultTranslationOf no longer in this API"  to Companion::containsDefaultTranslationOf


        private fun containsDefaultTranslationOf(plant: Assert<CharSequence>, expected: Translatable, otherExpected: Array<out Translatable>): Assert<CharSequence> {
            return if (otherExpected.isEmpty()) {
                plant contains expected.getDefault()
            } else {
                plant contains Values(expected.getDefault(), *otherExpected.map { it.getDefault() }.toTypedArray())
            }
        }

        fun getContainsNotDefaultTranslationOfPair()
            = "containsNotDefaultTranslationOf no longer in this API" to Companion::containsNotDefaultTranslationOf


        private fun containsNotDefaultTranslationOf(plant: Assert<CharSequence>, expected: Translatable, otherExpected: Array<out Translatable>)
            = plant containsNot Values(expected.getDefault(), *otherExpected.map { it.getDefault() }.toTypedArray())

        fun toBeEmpty(plant: Assert<CharSequence>)
            = plant toBe Empty

        fun notToBeEmpty(plant: Assert<CharSequence>)
            = plant notToBe Empty

        fun notToBeBlank(plant: Assert<CharSequence>)
            = plant notToBe Blank

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
