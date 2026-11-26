package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInAnyOrderKeyValuePairsExpectationsTest
import ch.tutteli.atrium.specs.integration.utils.mapLikeToIterablePairTestFactory
import ch.tutteli.atrium.specs.withNullableSuffix
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.test.Test

class MapToContainInAnyOrderEntriesOfExpectationsBuilderTest :
    AbstractMapToContainInAnyOrderKeyValuePairsExpectationsTest(
        functionDescription to Companion::toContainKeyValuePairs,
        (functionDescription to Companion::toContainKeyValuePairsNullable).withNullableSuffix()
    ) {

    @TestFactory
    fun mapLikeToIterablePairTest() = mapLikeToIterablePairTestFactory<Map<String, Int>>(
        mapOf("a" to 1),
        functionDescription to { input -> toContain.inAnyOrder.entriesOf(input) }
    )

    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$entriesOf"

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
            return expect.toContain.inAnyOrder.entriesOf(mapLike)
        }

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.toContain.inAnyOrder.entriesOf(listOf(a, *aX))
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = expect(mapOf<Number, CharSequence>(1 to "a"))
        var subMap: Expect<LinkedHashMap<out Number, String>> = expect(linkedMapOf<Number, String>(1 to "a"))
        var nKeyMap: Expect<Map<Number?, CharSequence>> = expect(mapOf<Number?, CharSequence>(1 to "a"))
        var nValueMap: Expect<Map<Number, CharSequence?>> = expect(mapOf<Number, CharSequence?>(1 to "a"))
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = expect(mapOf<Number?, CharSequence?>(1 to "a"))
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = expect(mapOf<Number?, CharSequence?>(1 to "a"))
        var starMap: Expect<Map<*, *>> = expect(mapOf<Number, CharSequence?>(1 to "a"))

        map = map.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        subMap = subMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        starMap = starMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
    }

    @Test
    fun trigger_run_gutter() = Unit
}
