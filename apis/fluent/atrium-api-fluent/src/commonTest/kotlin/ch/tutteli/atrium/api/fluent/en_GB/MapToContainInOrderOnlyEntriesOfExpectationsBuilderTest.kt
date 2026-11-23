package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.MapLike
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInOrderOnlyKeyValuePairsExpectationsTest
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class MapToContainInOrderOnlyEntriesOfExpectationsBuilderTest :
    AbstractMapToContainInOrderOnlyKeyValuePairsExpectationsTest(
        functionDescription to Companion::toContainKeyValuePairs,
        (functionDescription to Companion::toContainKeyValuePairsNullable).withNullableSuffix()
    ) {
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

    @Test
    fun trigger_run_gutter() = Unit
}
