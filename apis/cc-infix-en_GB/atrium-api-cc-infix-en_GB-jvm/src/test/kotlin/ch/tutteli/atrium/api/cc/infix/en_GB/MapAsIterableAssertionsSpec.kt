package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

object MapAsIterableAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAsIterableAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, Int>>::asEntries.name,
    Assert<Map<String, Int>>::asEntries
)
