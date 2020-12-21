package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInAnyOrderOnlyEntriesOfAssertionsSpec.Companion as C

class MapContainsInAnyOrderOnlyEntriesOfAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
    include(BuilderMapLikeToIterablePairSpec)
    include(ShortcutMapLikeToIterablePairSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec(
        containsKeyValuePair_s to C::containsKeyValuePairs,
        (containsKeyValuePair_s to C::containsKeyValuePairsNullable).withNullableSuffix(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValuePairsAssertionsSpec(
        mfun2<String, Int, Int>(C::containsInAnyOrderOnlyEntriesOf),
        mfun2<String?, Int?, Int?>(C::containsInAnyOrderOnlyEntriesOf).withNullableSuffix(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
        "[Atrium][Shortcut] "
    )


    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            "$contains $filler $inAnyOrder $butOnly $entriesOf",
            mapOf("a" to 1),
            { input -> it contains o inAny order but only entriesOf input }
        )

    object ShortcutMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            //TODO 0.15.0 change to shortcut
            "$contains $filler $inAnyOrder $butOnly $entriesOf",
            mapOf("a" to 1),
            //TODO 0.15.0 change to shortcut
            { input -> it contains o inAny order but only entriesOf input }
        )

    companion object : MapContainsSpecBase() {
        val containsKeyValuePair_s = "$contains $filler $inAnyOrder $butOnly $entriesOf"

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
            return expect contains o inAny order but only entriesOf mapLike
        }

        private fun containsKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            expect contains o inAny order but only entriesOf listOf(a, *aX)

        private fun containsInAnyOrderOnlyEntriesOf(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>
        ): Expect<Map<out String, Int>> =
            //TODO 0.15.0: change to shortcut
            expect contains o inAny order but only entriesOf sequenceOf(a, *aX)

        @JvmName("containsInAnyOrderOnlyEntriesOfNullable")
        private fun containsInAnyOrderOnlyEntriesOf(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>
        ): Expect<Map<out String?, Int?>> =
            //TODO 0.15.0: change to shortcut
            expect contains o inAny order but only entriesOf arrayOf(a, *aX)
    }
}
