package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

object CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<CharSequence>::containsDefaultTranslationOf.name to AssertionPlant<CharSequence>::containsDefaultTranslationOf,
    AssertionPlant<CharSequence>::containsNotDefaultTranslationOf.name to AssertionPlant<CharSequence>::containsNotDefaultTranslationOf,
    AssertionPlant<CharSequence>::isEmpty.name to AssertionPlant<CharSequence>::isEmpty,
    AssertionPlant<CharSequence>::isNotEmpty.name to AssertionPlant<CharSequence>::isNotEmpty,
    AssertionPlant<CharSequence>::startsWith.name to AssertionPlant<CharSequence>::startsWith,
    AssertionPlant<CharSequence>::startsNotWith.name to AssertionPlant<CharSequence>::startsNotWith,
    AssertionPlant<CharSequence>::endsWith.name to AssertionPlant<CharSequence>::endsWith,
    AssertionPlant<CharSequence>::endsNotWith.name to AssertionPlant<CharSequence>::endsNotWith
)
