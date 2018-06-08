package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.reporting.translating.Translatable

class CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    "containsDefaultTranslationOf no longer in this API" to Companion::containsDefaultTranslationOf,
    "containsNotDefaultTranslationOf no longer in this API" to Companion::containsNotDefaultTranslationOf,
    Assert<CharSequence>::isEmpty.name to Assert<CharSequence>::isEmpty,
    Assert<CharSequence>::isNotEmpty.name to Assert<CharSequence>::isNotEmpty,
    Assert<CharSequence>::startsWith.name to Assert<CharSequence>::startsWith,
    Assert<CharSequence>::startsNotWith.name to Assert<CharSequence>::startsNotWith,
    Assert<CharSequence>::endsWith.name to Assert<CharSequence>::endsWith,
    Assert<CharSequence>::endsNotWith.name to Assert<CharSequence>::endsNotWith,
    "◆ ", "⚬ "
){
    companion object{

        fun containsDefaultTranslationOf(plant: Assert<CharSequence>, translatable: Translatable, arrayOfTranslatables: Array<out Translatable>): Assert<CharSequence> {
            return plant.contains(translatable.getDefault(), *arrayOfTranslatables.map { it.getDefault() }.toTypedArray())
        }

        fun containsNotDefaultTranslationOf(plant: Assert<CharSequence>, translatable: Translatable, arrayOfTranslatables: Array<out Translatable>): Assert<CharSequence> {
            return plant.containsNot(translatable.getDefault(), *arrayOfTranslatables.map { it.getDefault() }.toTypedArray())
        }
    }
}
