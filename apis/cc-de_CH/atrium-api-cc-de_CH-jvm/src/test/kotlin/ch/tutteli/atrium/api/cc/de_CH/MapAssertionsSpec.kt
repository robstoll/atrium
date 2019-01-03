package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object MapAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAssertionsSpec(
    AssertionVerbFactory,
    Assert<Map<String, Int>>::enthaeltKey.name to Assert<Map<String, Int>>::enthaeltKey,
    Assert<Map<String, Int>>::hatDieGroesse.name to Assert<Map<String, Int>>::hatDieGroesse,
    Assert<Map<String, Int>>::istLeer.name to Assert<Map<String, Int>>::istLeer,
    Assert<Map<String, Int>>::istNichtLeer.name to Assert<Map<String, Int>>::istNichtLeer
)
