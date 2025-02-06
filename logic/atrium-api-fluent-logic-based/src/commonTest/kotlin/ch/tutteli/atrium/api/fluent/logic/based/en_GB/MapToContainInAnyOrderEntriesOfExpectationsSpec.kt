package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.integration.mfun2
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.logic.based.en_GB.MapToContainInAnyOrderEntriesOfExpectationsSpec.Companion as C

class MapToContainInAnyOrderEntriesOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderMapLikeToIterablePairSpec)
    include(ShortcutMapLikeToIterablePairSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
        functionDescription to C::toContainKeyValuePairs,
        (functionDescription to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
        mfun2<String, Int, Int>(C::toContainEntriesOf),
        mfun2<String?, Int?, Int?>(C::toContainEntriesOf).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            functionDescription,
            mapOf("a" to 1),
            { input -> toContain.inAnyOrder.entriesOf(input) }
        )

    object ShortcutMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            toContainEntriesOf,
            mapOf("a" to 1),
            { input -> toContainEntriesOf(input)  }
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

        private fun toContainEntriesOf(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            expect.toContainEntriesOf(sequenceOf(a, *aX))

        @JvmName("toContainNullable")
        private fun toContainEntriesOf(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.toContainEntriesOf(arrayOf(a, *aX))
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

        map = map.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        subMap = subMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        starMap = starMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))

        map = map.toContainEntriesOf(listOf(1 to "a"))
        subMap = subMap.toContainEntriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContainEntriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContainEntriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContainEntriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContainEntriesOf(listOf(1 to "a"))
        starMap = starMap.toContainEntriesOf(listOf(1 to "a"))
    }
}
