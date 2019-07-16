package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

class CharSequenceAssertionsSpec : ch.tutteli.atrium.specs.integration.CharSequenceAssertionsSpec(
    AssertionVerbFactory,
    Expect<CharSequence>::isEmpty.name to Expect<CharSequence>::isEmpty,
    Expect<CharSequence>::isNotEmpty.name to Expect<CharSequence>::isNotEmpty,
    Expect<CharSequence>::isNotBlank.name to Expect<CharSequence>::isNotBlank,
    Expect<CharSequence>::startsWith.name to Expect<CharSequence>::startsWith,
    Expect<CharSequence>::startsNotWith.name to Expect<CharSequence>::startsNotWith,
    Expect<CharSequence>::endsWith.name to Expect<CharSequence>::endsWith,
    Expect<CharSequence>::endsNotWith.name to Expect<CharSequence>::endsNotWith
)
