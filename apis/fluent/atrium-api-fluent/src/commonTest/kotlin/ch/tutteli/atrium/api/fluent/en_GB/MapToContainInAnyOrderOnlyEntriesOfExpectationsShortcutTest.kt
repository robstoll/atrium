package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInAnyOrderOnlyKeyValuePairsExpectationsTest
import ch.tutteli.atrium.specs.integration.mfun2
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.jvm.JvmName
import kotlin.test.Test

class MapToContainInAnyOrderOnlyEntriesOfExpectationsShortcutTest :
    AbstractMapToContainInAnyOrderOnlyKeyValuePairsExpectationsTest(
        mfun2<String, Int, Int>(Companion::toContainInAnyOrderOnlyEntriesOf),
        mfun2<String?, Int?, Int?>(Companion::toContainInAnyOrderOnlyEntriesOf).withNullableSuffix()
    ) {
    companion object {
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

    @Test
    fun trigger_run_gutter() = Unit
}
