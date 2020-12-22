package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInOrderOnlyEntriesOfAssertionsSpec.Companion as C

class MapContainsInOrderOnlyEntriesOfAssertionsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInOrderOnlyKeyValuePairsAssertionsSpec(
        functionDescription to C::containsKeyValuePairs,
        (functionDescription to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            functionDescription,
            mapOf("a" to 1),
            { input -> contains.inOrder.only.entriesOf(input) }
        )

    companion object : MapContainsSpecBase() {
        val functionDescription = "$contains.$inOrder.$only.$entriesOf"

        private fun containsKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> {
            val mapLike: MapLike = if (aX.distinct().size != aX.size || aX.contains(a)) {
                sequenceOf(a, *aX).asIterable()
            } else {
                mapOf(a, *aX)
            }
            return expect.contains.inOrder.only.entriesOf(mapLike)
        }

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.contains.inOrder.only.entriesOf(arrayOf(a, *aX))
    }



    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

        map = map.contains.inOrder.only.entriesOf(listOf(1 to "a"))
        subMap = subMap.contains.inOrder.only.entriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.contains.inOrder.only.entriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.contains.inOrder.only.entriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entriesOf(listOf(1 to "a"))
        starMap = starMap.contains.inOrder.only.entriesOf(listOf(1 to "a"))
    }
}
