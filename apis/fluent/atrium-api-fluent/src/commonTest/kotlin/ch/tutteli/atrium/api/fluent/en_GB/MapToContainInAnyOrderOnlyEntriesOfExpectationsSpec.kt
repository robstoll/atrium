package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek

class MapToContainInAnyOrderOnlyEntriesOfExpectationsSpec : Spek({
    include(BuilderMapLikeToIterablePairSpec)
    include(ShortcutMapLikeToIterablePairSpec)
}) {

    object BuilderMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            functionDescription,
            mapOf("a" to 1),
            { input -> toContain.inAnyOrder.only.entriesOf(input) }
        )

    object ShortcutMapLikeToIterablePairSpec :
        ch.tutteli.atrium.specs.integration.MapLikeToIterablePairSpec<Map<String, Int>>(
            toContainOnlyEntriesOf,
            mapOf("a" to 1),
            { input -> toContainOnlyEntriesOf(input) }
        )

    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$only.$entriesOf"
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

        map = map.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        subMap = subMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))
        starMap = starMap.toContain.inAnyOrder.only.entriesOf(listOf(1 to "a"))

        map = map.toContainOnlyEntriesOf(listOf(1 to "a"))
        subMap = subMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        nKeyMap = nKeyMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        nValueMap = nValueMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        nKeyValueMap = nKeyValueMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        ronKeyValueMap = ronKeyValueMap.toContainOnlyEntriesOf(listOf(1 to "a"))
        starMap = starMap.toContainOnlyEntriesOf(listOf(1 to "a"))
    }
}
