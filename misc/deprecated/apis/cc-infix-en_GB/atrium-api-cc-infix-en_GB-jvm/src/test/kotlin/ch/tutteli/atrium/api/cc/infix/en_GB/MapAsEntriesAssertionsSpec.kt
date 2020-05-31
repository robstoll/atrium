// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 1.0.0, no need to migrate to Spek 2
class MapAsEntriesAssertionsSpec : ch.tutteli.atrium.spec.integration.MapAsEntriesAssertionsSpec(
    AssertionVerbFactory,
    "asEntries",
    Companion::asEntries,
    Companion::asEntriesWithCreator
) {
    companion object {
        fun asEntries(plant: Assert<Map<String, Int>>) = plant.asEntries()
        fun asEntriesWithCreator(plant: Assert<Map<String, Int>>, assertionCreator: Assert<Set<Map.Entry<String, Int>>>.() -> Unit) = plant asEntries assertionCreator
    }
}
