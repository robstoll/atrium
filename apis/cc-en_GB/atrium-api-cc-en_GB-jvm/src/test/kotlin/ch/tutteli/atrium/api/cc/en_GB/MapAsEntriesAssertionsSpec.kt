package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

object MapAsEntriesAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAsEntriesAssertionsSpec(
    AssertionVerbFactory,
    "asEntries",
    Assert<Map<String, Int>>::asEntries,
    Assert<Map<String, Int>>::asEntries
)
