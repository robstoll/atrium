package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderEntriesOfAssertionsSpec.Companion as C

class MapContainsInAnyOrderEntriesOfAssertionsSpec : Spek({
    include(BuilderSpec)
    //TODO 0.15.0: enable once implemented
    //include(ShortcutSpec)

    //TODO 0.15.0: enable once implemented
    //include(MapLikeSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValuePairsAssertionsSpec(
        containsKeyValuePair_s to C::containsKeyValuePairs,
        (containsKeyValuePair_s to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    //TODO 0.15.0: add MapLikeSpec which tests passing illegal values
//    object MapLikeSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValuePairsAssertionsSpec(
//        mfun2<String, Int, Int>(Expect<Map<out String, Int>>::contains),
//        mfun2<String?, Int?, Int?>(Expect<Map<out String?, Int?>>::contains).withNullableSuffix(),
//        "[Atrium][Shortcut] "
//    )

    companion object : MapContainsSpecBase() {
        val containsKeyValuePair_s = "${contains}.${inAnyOrder}.$entriesOf"

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
            expect.contains.inAnyOrder.entriesOf(sequenceOf(a, *aX))

        @JvmName("containsNullable")
        private fun containsEntriesOf(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect.contains.inAnyOrder.entriesOf(arrayOf(a, *aX))
    }
}
