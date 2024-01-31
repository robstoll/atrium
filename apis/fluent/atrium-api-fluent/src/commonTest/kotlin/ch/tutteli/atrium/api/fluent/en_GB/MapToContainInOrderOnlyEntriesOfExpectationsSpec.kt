package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.MapToContainInOrderOnlyEntriesOfExpectationsSpec.Companion as C

class MapToContainInOrderOnlyEntriesOfExpectationsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInOrderOnlyKeyValuePairsExpectationsSpec(
        functionDescription to C::toContainKeyValuePairs,
        (functionDescription to C::toContainKeyValuePairsNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            functionDescription,
            mapOf("a" to 1),
            { input -> toContain.inOrder.only.entriesOf(input) }
        )

    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$entriesOf"

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
            return if (report == emptyInOrderOnlyReportOptions) {
                expect.toContain.inOrder.only.entriesOf(mapLike)
            } else expect.toContain.inOrder.only.entriesOf(mapLike, report = report)
        }

        private fun toContainKeyValuePairsNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, Int?>,
            aX: Array<out Pair<String?, Int?>>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ): Expect<Map<out String?, Int?>> =
            if (report == emptyInOrderOnlyReportOptions) {
                expect.toContain.inOrder.only.entriesOf(arrayOf(a, *aX))
            } else expect.toContain.inOrder.only.entriesOf(arrayOf(a, *aX), report = report)
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

        map = map.toContain.inOrder.only.entriesOf(listOf(1 to "a"))
        subMap = subMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"))
        starMap = starMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"))

        map = map.toContain.inOrder.only.entriesOf(listOf(1 to "a"), report = {})
        subMap = subMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"), report = {})
        nKeyMap = nKeyMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"), report = {})
        nValueMap = nValueMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"), report = {})
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"), report = {})
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"), report = {})
        starMap = starMap.toContain.inOrder.only.entriesOf(listOf(1 to "a"), report = {})
    }
}
