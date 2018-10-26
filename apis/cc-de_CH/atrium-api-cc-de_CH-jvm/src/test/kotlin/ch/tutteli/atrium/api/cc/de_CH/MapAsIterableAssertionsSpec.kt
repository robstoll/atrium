package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object MapAsIterableAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAsIterableAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, Int>>::asKeys.name,
    Assert<Map<String, Int>>::asKeys,
    Assert<Map<String, Int>>::asValues.name,
    Assert<Map<String, Int>>::asValues,
    Assert<Map<String, Int>>::asEntries.name,
    Assert<Map<String, Int>>::asEntries
)
