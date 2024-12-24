package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.infix.en_GB.MapToContainInOrderOnlyEntriesOfExpectationsSpec.Companion as C

class MapToContainInOrderOnlyEntriesOfExpectationsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInOrderOnlyKeyValuePairsExpectationsSpec(
        toContainKeyValuePair_s to C::toContainKeyValuePairs,
        (toContainKeyValuePair_s to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            "$toContain $filler $inOrder $entriesOf",
            mapOf("a" to 1),
            { input -> it toContain o inGiven order and only entriesOf input }
        )

    companion object : MapToContainSpecBase() {
        val toContainKeyValuePair_s = "$toContain $filler $inOrder $andOnly $entriesOf"

        private fun toContainKeyValuePairs(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Int>,
            aX: Array<out Pair<String, Int>>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Map<out String, Int>> {
            val mapLike: MapLike = if (aX.distinct().size != aX.size || aX.contains(a)) {
                sequenceOf(a, *aX).asIterable()
            } else {
                mapOf(a, *aX)
            }
            return if (report === MapLikeToContainFormatSpecBase.emptyInOrderOnlyReportOptions) {
                expect toContain o inGiven order and only entriesOf mapLike
            } else expect toContain o inGiven order and only the entriesOf(mapLike, reportOptionsInOrderOnly = {})

        }

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Map<out String?, Int?>> =
            if (report === MapLikeToContainFormatSpecBase.emptyInOrderOnlyReportOptions) {
                expect toContain o inGiven order and only entriesOf listOf(a, *aX)
            } else expect toContain o inGiven order and only the entriesOf(
                listOf(a, *aX),
                reportOptionsInOrderOnly = {}
            )
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

        map = map toContain o inGiven order and only entriesOf listOf(1 to "a")
        subMap = subMap toContain o inGiven order and only entriesOf listOf(1 to "a")
        nKeyMap = nKeyMap toContain o inGiven order and only entriesOf listOf(1 to "a")
        nValueMap = nValueMap toContain o inGiven order and only entriesOf listOf(1 to "a")
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only entriesOf listOf(1 to "a")
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only entriesOf listOf(1 to "a")
        starMap = starMap toContain o inGiven order and only entriesOf listOf(1 to "a")


        map = map toContain o inGiven order and only the entriesOf(
            listOf(1 to "a"),
            reportOptionsInOrderOnly = {}
        )
        subMap = subMap toContain o inGiven order and only the entriesOf(
            listOf(1 to "a"),
            reportOptionsInOrderOnly = {}
        )
        nKeyMap = nKeyMap toContain o inGiven order and only the entriesOf(
            listOf(1 to "a"),
            reportOptionsInOrderOnly = {}
        )
        nValueMap = nValueMap toContain o inGiven order and only the entriesOf(
            listOf(1 to "a"),
            reportOptionsInOrderOnly = {}
        )
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only the entriesOf(
            listOf(1 to "a"),
            reportOptionsInOrderOnly = {}
        )
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only the entriesOf(
            listOf(1 to "a"),
            reportOptionsInOrderOnly = {}
        )
        starMap = starMap toContain o inGiven order and only the entriesOf(
            listOf(1 to "a"),
            reportOptionsInOrderOnly = {}
        )
    }
}
