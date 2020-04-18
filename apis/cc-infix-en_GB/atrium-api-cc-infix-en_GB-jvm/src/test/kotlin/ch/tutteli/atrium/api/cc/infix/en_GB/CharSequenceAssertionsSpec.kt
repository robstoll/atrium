// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Blank
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
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
                plant.asExpect().contains(expected.getDefault()).asAssert()
            } else {
                plant.asExpect().contains(Values(expected.getDefault(), *otherExpected.map { it.getDefault() }.toTypedArray())).asAssert()
            }
        }

        fun getContainsNotDefaultTranslationOfPair()
            = "containsNotDefaultTranslationOf no longer in this API" to Companion::containsNotDefaultTranslationOf


        private fun containsNotDefaultTranslationOf(plant: Assert<CharSequence>, expected: Translatable, otherExpected: Array<out Translatable>)
            = plant.asExpect().containsNot(Values(expected.getDefault(), *otherExpected.map { it.getDefault() }.toTypedArray())).asAssert()

        fun toBeEmpty(plant: Assert<CharSequence>)
            = plant.asExpect().toBe(empty).asAssert()

        fun notToBeEmpty(plant: Assert<CharSequence>)
            = plant.asExpect().notToBe(empty).asAssert()

        fun notToBeBlank(plant: Assert<CharSequence>)
            = plant.asExpect().notToBe(blank).asAssert()

        fun startsWith(plant: Assert<CharSequence>, expected: CharSequence)
            = plant.asExpect().startsWith(expected).asAssert()

        fun startsNotWith(plant: Assert<CharSequence>, expected: CharSequence)
            = plant.asExpect().startsNotWith(expected).asAssert()

        fun endsWith(plant: Assert<CharSequence>, expected: CharSequence)
            = plant.asExpect().endsWith(expected).asAssert()

        fun endsNotWith(plant: Assert<CharSequence>, expected: CharSequence)
            = plant.asExpect().endsNotWith(expected).asAssert()
    }
}
