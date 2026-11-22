package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInAnyOrderOnlyKeyValuePairsExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class MapToContainInAnyOrderOnlyEntriesOfExpectationsBuilderTest :
    AbstractMapToContainInAnyOrderOnlyKeyValuePairsExpectationsTest(
        functionDescription to Companion::toContainKeyValuePairs,
        (functionDescription to Companion::toContainKeyValuePairsNullable).withNullableSuffix()
    ) {
    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$only.$entriesOf"

        private fun toContainKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> {
            val mapLike: MapLike = if (aX.distinct().size != aX.size || aX.contains(a)) {
                sequenceOf(a, *aX).asIterable()
            } else {
                mapOf(a, *aX)
            }
            return expect.toContain.inAnyOrder.only.entriesOf(mapLike)
        }

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.toContain.inAnyOrder.only.entriesOf(listOf(a, *aX))
    }

    @Test
    fun trigger_run_gutter() = Unit
}
