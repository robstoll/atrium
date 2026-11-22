package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.specs.integration.AbstractMapLikeToIterablePairTest
import kotlin.test.Test

class MapToContainInAnyOrderEntriesOfMapLikeToIterablePairBuilderTest :
    AbstractMapLikeToIterablePairTest<Map<String, Int>>(
        functionDescription,
        mapOf("a" to 1),
        { input -> toContain.inAnyOrder.entriesOf(input) }
    ) {

    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$entriesOf"
    }

    @Test
    fun trigger_run_gutter() = Unit
}
