package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object CharSequenceAssertionsSpec : ch.tutteli.atrium.spec.integration.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    Assert<CharSequence>::containsDefaultTranslationOf.name to Assert<CharSequence>::containsDefaultTranslationOf,
    Assert<CharSequence>::containsNotDefaultTranslationOf.name to Assert<CharSequence>::containsNotDefaultTranslationOf,
    Assert<CharSequence>::isEmpty.name to Assert<CharSequence>::isEmpty,
    Assert<CharSequence>::isNotEmpty.name to Assert<CharSequence>::isNotEmpty,
    Assert<CharSequence>::startsWith.name to Assert<CharSequence>::startsWith,
    Assert<CharSequence>::startsNotWith.name to Assert<CharSequence>::startsNotWith,
    Assert<CharSequence>::endsWith.name to Assert<CharSequence>::endsWith,
    Assert<CharSequence>::endsNotWith.name to Assert<CharSequence>::endsNotWith
)
