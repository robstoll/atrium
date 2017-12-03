package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

object CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.assertions.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<CharSequence>::containsDefaultTranslationOf.name to IAssertionPlant<CharSequence>::containsDefaultTranslationOf,
    IAssertionPlant<CharSequence>::containsNotDefaultTranslationOf.name to IAssertionPlant<CharSequence>::containsNotDefaultTranslationOf,
    IAssertionPlant<CharSequence>::isEmpty.name to IAssertionPlant<CharSequence>::isEmpty,
    IAssertionPlant<CharSequence>::isNotEmpty.name to IAssertionPlant<CharSequence>::isNotEmpty,
    IAssertionPlant<CharSequence>::startsWith.name to IAssertionPlant<CharSequence>::startsWith,
    IAssertionPlant<CharSequence>::startsNotWith.name to IAssertionPlant<CharSequence>::startsNotWith,
    IAssertionPlant<CharSequence>::endsWith.name to IAssertionPlant<CharSequence>::endsWith,
    IAssertionPlant<CharSequence>::endsNotWith.name to IAssertionPlant<CharSequence>::endsNotWith
)
