package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInAnyOrderKeyValuePairsExpectationsTest
import ch.tutteli.atrium.specs.integration.mfun2
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.jvm.JvmName
import kotlin.test.Test

class MapToContainInAnyOrderEntriesOfExpectationsShortcutTest :
    AbstractMapToContainInAnyOrderKeyValuePairsExpectationsTest(
        mfun2<String, Int, Int>(Companion::toContainEntriesOf),
        mfun2<String?, Int?, Int?>(Companion::toContainEntriesOf).withNullableSuffix()
    ) {
    companion object {
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

    @Test
    fun trigger_run_gutter() = Unit
}
