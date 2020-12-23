package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.integration.mfun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderEntriesOfAssertionsSpec.Companion as C

class MapContainsInAnyOrderEntriesOfAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderMapLikeToIterablePairSpec)
    include(ShortcutMapLikeToIterablePairSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValuePairsAssertionsSpec(
        functionDescription to C::containsKeyValuePairs,
        (functionDescription to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValuePairsAssertionsSpec(
        mfun2<String, Int, Int>(C::containsEntriesOf),
        mfun2<String?, Int?, Int?>(C::containsEntriesOf).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            functionDescription,
            mapOf("a" to 1),
            { input -> contains.inAnyOrder.entriesOf(input) }
        )

    object ShortcutMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            containsEntriesOf,
            mapOf("a" to 1),
            { input -> containsEntriesOf(input)  }
        )

    companion object : MapContainsSpecBase() {
        val functionDescription = "$contains.$inAnyOrder.$entriesOf"

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
            return expect.contains.inAnyOrder.entriesOf(mapLike)
        }

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.contains.inAnyOrder.entriesOf(listOf(a, *aX))

        private fun containsEntriesOf(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            expect.containsEntriesOf(sequenceOf(a, *aX))

        @JvmName("containsNullable")
        private fun containsEntriesOf(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.containsEntriesOf(arrayOf(a, *aX))
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

        map = map.contains.inAnyOrder.entriesOf(listOf(1 to "a"))
        subMap = subMap.contains.inAnyOrder.entriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.contains.inAnyOrder.entriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.contains.inAnyOrder.entriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entriesOf(listOf(1 to "a"))
        starMap = starMap.contains.inAnyOrder.entriesOf(listOf(1 to "a"))

        map = map.containsEntriesOf(listOf(1 to "a"))
        subMap = subMap.containsEntriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.containsEntriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.containsEntriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.containsEntriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.containsEntriesOf(listOf(1 to "a"))
        starMap = starMap.containsEntriesOf(listOf(1 to "a"))
    }
}
