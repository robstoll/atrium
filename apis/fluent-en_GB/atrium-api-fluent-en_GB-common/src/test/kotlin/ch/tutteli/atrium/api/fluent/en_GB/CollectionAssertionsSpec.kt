package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

object CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    AssertionVerbFactory,
    Expect<Collection<Int>>::isEmpty.name to Expect<Collection<Int>>::isEmpty,
    Expect<Collection<Int>>::isNotEmpty.name to Expect<Collection<Int>>::isNotEmpty
)
