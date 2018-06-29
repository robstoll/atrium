package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, Int>>::hasSize.name to Assert<Map<String, Int>>::hasSize,
    Assert<Map<String, Int>>::isEmpty.name to Assert<Map<String, Int>>::isEmpty,
    Assert<Map<String, Int>>::isNotEmpty.name to Assert<Map<String, Int>>::isNotEmpty
)
