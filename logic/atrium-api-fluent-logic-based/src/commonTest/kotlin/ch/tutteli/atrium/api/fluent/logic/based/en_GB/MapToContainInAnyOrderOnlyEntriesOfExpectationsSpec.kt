package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.logic.based.en_GB.MapToContainInAnyOrderOnlyEntriesOfExpectationsSpec.Companion as C

class MapToContainInAnyOrderOnlyEntriesOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderMapLikeToIterablePairSpec)
    include(ShortcutMapLikeToIterablePairSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec(
        functionDescription to C::toContainKeyValuePairs,
        (functionDescription to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec(
        mfun2<String, Int, Int>(C::toContainInAnyOrderOnlyEntriesOf),
        mfun2<String?, Int?, Int?>(C::toContainInAnyOrderOnlyEntriesOf).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )


    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            functionDescription,
            mapOf("a" to 1),
            { input -> toContain.inAnyOrder.only.entriesOf(input) }
        )

    object ShortcutMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            toContainOnlyEntriesOf,
            mapOf("a" to 1),
            { input -> toContainOnlyEntriesOf(input) }
        )

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


        private fun toContainInAnyOrderOnlyEntriesOf(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            expect.toContainOnlyEntriesOf(sequenceOf(a, *aX))

        @JvmName("toContainInAnyOrderOnlyEntriesOfNullable")
        private fun toContainInAnyOrderOnlyEntriesOf(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.toContainOnlyEntriesOf(arrayOf(a, *aX))
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

        map = map.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        subMap = subMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        starMap = starMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))

        map = map.toContainOnlyEntriesOf(listOf(1 to "a"))
        subMap = subMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        starMap = starMap.toContainOnlyEntriesOf(listOf(1 to "a"))
    }
}
