package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapToContainInAnyOrderEntriesOfExpectationsSpec.Companion as C

class MapToContainInAnyOrderEntriesOfExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderMapLikeToIterablePairSpec)
    include(ShortcutMapLikeToIterablePairSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
        toContainKeyValuePair_s to C::toContainKeyValuePairs,
        (toContainKeyValuePair_s to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
        mfun2<String, Int, Int>(C::toContainEntriesOf),
        mfun2<String?, Int?, Int?>(C::toContainEntriesOf).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            "$toContain $filler $inAnyOrder $entriesOf",
            mapOf("a" to 1),
            { input -> it toContain o inAny order entriesOf input }
        )

    object ShortcutMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            toContainEntriesOf,
            mapOf("a" to 1),
            { input -> it toContainOnlyEntriesOf  input }
        )

    companion object : MapToContainSpecBase() {
        val toContainKeyValuePair_s = "$toContain $filler $inAnyOrder $entriesOf"

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
            return expect toContain o inAny order entriesOf mapLike
        }

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect toContain o inAny order entriesOf listOf(a, *aX)

        private fun toContainEntriesOf(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            expect toContainEntriesOf sequenceOf(a, *aX)

        @JvmName("toContainEntriesOfNullable")
        private fun toContainEntriesOf(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect toContainEntriesOf arrayOf(a, *aX)
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

        map = map toContain o inAny order entriesOf listOf(1 to "a")
        subMap = subMap toContain o inAny order entriesOf listOf(1 to "a")
        nKeyMap = nKeyMap toContain o inAny order entriesOf listOf(1 to "a")
        nValueMap = nValueMap toContain o inAny order entriesOf listOf(1 to "a")
        nKeyValueMap = nKeyValueMap toContain o inAny order entriesOf listOf(1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain o inAny order entriesOf listOf(1 to "a")
        starMap = starMap toContain o inAny order entriesOf listOf(1 to "a")

        map = map toContainEntriesOf listOf(1 to "a")
        subMap = subMap toContainEntriesOf listOf(1 to "a")
        nKeyMap = nKeyMap toContainEntriesOf listOf(1 to "a")
        nValueMap = nValueMap toContainEntriesOf listOf(1 to "a")
        nKeyValueMap = nKeyValueMap toContainEntriesOf listOf(1 to "a")
        ronKeyValueMap = ronKeyValueMap toContainEntriesOf listOf(1 to "a")
        starMap = starMap toContainEntriesOf listOf(1 to "a")
    }
}
