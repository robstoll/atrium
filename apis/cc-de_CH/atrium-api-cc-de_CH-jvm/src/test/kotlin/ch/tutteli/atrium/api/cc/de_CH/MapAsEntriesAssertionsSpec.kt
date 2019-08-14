@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object MapAsEntriesAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAsEntriesAssertionsSpec(
    AssertionVerbFactory,
    "asEntries",
    Assert<Map<String, Int>>::asEntries,
    Assert<Map<String, Int>>::asEntries
)
