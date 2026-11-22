package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek

class MapToContainInAnyOrderEntriesOfExpectationsSpec : Spek({
    include(BuilderMapLikeToIterablePairSpec)
    include(ShortcutMapLikeToIterablePairSpec)
}) {

    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            functionDescription,
            mapOf("a" to 1),
            { input -> toContain.inAnyOrder.entriesOf(input) }
        )

    object ShortcutMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            toContainEntriesOf,
            mapOf("a" to 1),
            { input -> toContainEntriesOf(input) }
        )

    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$entriesOf"
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

        map = map.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        subMap = subMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))
        starMap = starMap.toContain.inAnyOrder.entriesOf(listOf(1 to "a"))

        map = map.toContainEntriesOf(listOf(1 to "a"))
        subMap = subMap.toContainEntriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContainEntriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContainEntriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContainEntriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContainEntriesOf(listOf(1 to "a"))
        starMap = starMap.toContainEntriesOf(listOf(1 to "a"))
    }
}
