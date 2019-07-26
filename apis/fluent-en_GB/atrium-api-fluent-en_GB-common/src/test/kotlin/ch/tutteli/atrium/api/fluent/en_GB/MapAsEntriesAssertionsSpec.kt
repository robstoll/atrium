package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun1

object MapAsEntriesAssertionsSpec : ch.tutteli.atrium.specs.integration.MapAsEntriesAssertionsSpec(
    AssertionVerbFactory,
    "asEntries",
    feature0<Map<String, Int>, Set<Map.Entry<String, Int>>> (Expect<Map<String, Int>>::asEntries),
    fun1<Map<String, Int>, Expect<Set<Map.Entry<String, Int>>>.() -> Unit>(Expect<Map<String, Int>>::asEntries)
)
