package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2

class MapEntryAssertionsSpec : ch.tutteli.atrium.specs.integration.MapEntryAssertionsSpec(
    AssertionVerbFactory,
    fun2(Expect<Map.Entry<String, Int>>::isKeyValue),
    fun2(Expect<Map.Entry<String?, Int?>>::isKeyValue)
)
