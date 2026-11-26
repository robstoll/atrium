package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInAnyOrderKeyValuePairsExpectationsTest
import ch.tutteli.atrium.specs.integration.mfun2
import ch.tutteli.atrium.specs.integration.utils.mapLikeToIterablePairTestFactory
import ch.tutteli.atrium.specs.withNullableSuffix
import ch.tutteli.atrium.testfactories.TestFactory
import kotlin.jvm.JvmName
import kotlin.test.Test

class MapToContainInAnyOrderEntriesOfExpectationsShortcutTest :
    AbstractMapToContainInAnyOrderKeyValuePairsExpectationsTest(
        mfun2<String, Int, Int>(Companion::toContainEntriesOf),
        mfun2<String?, Int?, Int?>(Companion::toContainEntriesOf).withNullableSuffix()
    ) {

    @TestFactory
    fun mapLikeToIterablePairTest() = mapLikeToIterablePairTestFactory<Map<String, Int>>(
        mapOf("a" to 1),
        shortcutDescription to { input -> toContainEntriesOf(input) }
    )

    companion object {
        private val shortcutDescription = Expect<Map<*, *>>::toContainEntriesOf.name

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

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VARIABLE", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = expect(mapOf<Number, CharSequence>(1 to "a"))
        var subMap: Expect<LinkedHashMap<out Number, String>> = expect(linkedMapOf<Number, String>(1 to "a"))
        var nKeyMap: Expect<Map<Number?, CharSequence>> = expect(mapOf<Number?, CharSequence>(1 to "a"))
        var nValueMap: Expect<Map<Number, CharSequence?>> = expect(mapOf<Number, CharSequence?>(1 to "a"))
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = expect(mapOf<Number?, CharSequence?>(1 to "a"))
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = expect(mapOf<Number?, CharSequence?>(1 to "a"))
        var starMap: Expect<Map<*, *>> = expect(mapOf<Number, CharSequence?>(1 to "a"))

        map = map.toContainEntriesOf(listOf(1 to "a"))
        subMap = subMap.toContainEntriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContainEntriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContainEntriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContainEntriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContainEntriesOf(listOf(1 to "a"))
        starMap = starMap.toContainEntriesOf(listOf(1 to "a"))
    }

    @Test
    fun trigger_run_gutter() = Unit
}
