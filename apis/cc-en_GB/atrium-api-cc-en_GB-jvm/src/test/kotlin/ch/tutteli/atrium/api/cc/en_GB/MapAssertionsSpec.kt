package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, *>>::containsKey.name to Assert<Map<String, *>>::containsKey,
    "${Assert<Map<String?, *>>::containsKey.name} for nullable" to Assert<Map<String?, *>>::containsKey,
    Assert<Map<*, *>>::hasSize.name to Assert<Map<*, *>>::hasSize,
    Assert<Map<*, *>>::isEmpty.name to Assert<Map<*, *>>::isEmpty,
    Assert<Map<*, *>>::isNotEmpty.name to Assert<Map<*, *>>::isNotEmpty
)
