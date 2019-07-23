package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun0

object CollectionAssertionsSpec : ch.tutteli.atrium.specs.integration.CollectionAssertionsSpec(
    AssertionVerbFactory,
    fun0(Expect<Collection<Int>>::isEmpty),
    fun0(Expect<Collection<Int>>::isNotEmpty)
)
