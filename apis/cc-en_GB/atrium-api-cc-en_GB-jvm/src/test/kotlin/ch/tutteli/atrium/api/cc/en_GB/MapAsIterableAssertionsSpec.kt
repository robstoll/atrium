package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

object MapAsIterableAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAsIterableAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, Int>>::asKeys.name,
    Assert<Map<String, Int>>::asKeys,
    Assert<Map<String, Int>>::asValues.name,
    Assert<Map<String, Int>>::asValues,
    Assert<Map<String, Int>>::asEntries.name,
    Assert<Map<String, Int>>::asEntries
)
