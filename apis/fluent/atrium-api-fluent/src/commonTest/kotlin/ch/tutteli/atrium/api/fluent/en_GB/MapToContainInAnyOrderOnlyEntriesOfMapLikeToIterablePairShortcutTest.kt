package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.specs.integration.AbstractMapLikeToIterablePairTest
import kotlin.test.Test

class MapToContainInAnyOrderOnlyEntriesOfMapLikeToIterablePairShortcutTest :
    AbstractMapLikeToIterablePairTest<Map<String, Int>>(
        functionDescription,
        mapOf("a" to 1),
        { input -> toContainOnlyEntriesOf(input) }
    ) {

    companion object : MapToContainSpecBase() {
        val functionDescription = toContainOnlyEntriesOf
    }

    @Test
    fun trigger_run_gutter() = Unit
}
