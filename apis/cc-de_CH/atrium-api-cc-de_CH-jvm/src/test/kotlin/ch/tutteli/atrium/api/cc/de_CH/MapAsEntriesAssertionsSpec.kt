package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object MapAsEntriesAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAsEntriesAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, Int>>::asEntries.name,
    Assert<Map<String, Int>>::asEntries
)
