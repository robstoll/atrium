@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

class CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    Assert<CharSequence>::containsDefaultTranslationOf.name to Assert<CharSequence>::containsDefaultTranslationOf,
    Assert<CharSequence>::containsNotDefaultTranslationOf.name to Assert<CharSequence>::containsNotDefaultTranslationOf,
    Assert<CharSequence>::isEmpty.name to Assert<CharSequence>::isEmpty,
    Assert<CharSequence>::isNotEmpty.name to Assert<CharSequence>::isNotEmpty,
    "isNotBlank not implemented in en_UK" to Companion::isNotBlank,
    Assert<CharSequence>::startsWith.name to Assert<CharSequence>::startsWith,
    Assert<CharSequence>::startsNotWith.name to Assert<CharSequence>::startsNotWith,
    Assert<CharSequence>::endsWith.name to Assert<CharSequence>::endsWith,
    Assert<CharSequence>::endsNotWith.name to Assert<CharSequence>::endsNotWith,
    "◆ ", "⚬ "
) {
    companion object {
        private fun isNotBlank(plant: Assert<CharSequence>) : Assert<CharSequence>
            = plant.addAssertion(AssertImpl.charSequence.isNotBlank(plant))
    }
}
