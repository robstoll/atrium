package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderOnlyEntriesOfAssertionsSpec.Companion as C

class MapContainsInAnyOrderOnlyEntriesOfAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderMapLikeToIterablePairSpec)
    include(ShortcutMapLikeToIterablePairSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec(
        containsKeyValuePair_s to C::containsKeyValuePairs,
        (containsKeyValuePair_s to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec(
        mfun2<String, Int, Int>(C::containsInAnyOrderOnlyEntriesOf),
        mfun2<String?, Int?, Int?>(C::containsInAnyOrderOnlyEntriesOf).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Shortcut] "
    )


    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            "$contains.$inAnyOrder.$only.$entriesOf",
            mapOf("a" to 1),
            { input -> contains.inAnyOrder.only.entriesOf(input) }
        )

    object ShortcutMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            "$containsOnlyEntriesOf",
            mapOf("a" to 1),
            { input -> containsOnlyEntriesOf(input) }
        )

    companion object : MapContainsSpecBase() {
        val containsKeyValuePair_s = "$contains.$inAnyOrder.$only.$entriesOf"

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
            return expect.contains.inAnyOrder.only.entriesOf(mapLike)
        }

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.contains.inAnyOrder.only.entriesOf(listOf(a, *aX))


        private fun containsInAnyOrderOnlyEntriesOf(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            expect.containsOnlyEntriesOf(sequenceOf(a, *aX))

        @JvmName("containsInAnyOrderOnlyEntriesOfNullable")
        private fun containsInAnyOrderOnlyEntriesOf(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.containsOnlyEntriesOf(arrayOf(a, *aX))
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

        map = map.contains.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        subMap = subMap.contains.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.contains.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.contains.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        starMap = starMap.contains.inAnyOrder.only.entriesOf(listOf(1 to "a"))

        map = map.containsOnlyEntriesOf(listOf(1 to "a"))
        subMap = subMap.containsOnlyEntriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.containsOnlyEntriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.containsOnlyEntriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.containsOnlyEntriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.containsOnlyEntriesOf(listOf(1 to "a"))
        starMap = starMap.containsOnlyEntriesOf(listOf(1 to "a"))
    }
}
