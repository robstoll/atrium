package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, *>>::enthaeltKey.name to Assert<Map<String, *>>::enthaeltKey,
    "${Assert<Map<String?, *>>::enthaeltKey.name} for nullable" to Assert<Map<String?, *>>::enthaeltKey,
    Assert<Map<*, *>>::hatDieGroesse.name to Assert<Map<*, *>>::hatDieGroesse,
    Assert<Map<*, *>>::istLeer.name to Assert<Map<*, *>>::istLeer,
    Assert<Map<*, *>>::istNichtLeer.name to Assert<Map<*, *>>::istNichtLeer
)
