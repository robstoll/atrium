package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractMapToContainInAnyOrderKeyValuePairsExpectationsTest
import ch.tutteli.atrium.specs.integration.mfun2
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class MapToContainInAnyOrderKeyValuePairsExpectationsShortcutTest :
    AbstractMapToContainInAnyOrderKeyValuePairsExpectationsTest(
        mfun2<String, Int, Int>(Expect<Map<out String, Int>>::toContain),
        mfun2<String?, Int?, Int?>(Expect<Map<out String?, Int?>>::toContain).withNullableSuffix()
    ) {

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

        map = map.toContain(1 to "a")
        subMap = subMap.toContain(1 to "a")
        nKeyMap = nKeyMap.toContain(1 to "a")
        nValueMap = nValueMap.toContain(1 to "a")
        nKeyValueMap = nKeyValueMap.toContain(1 to "a")
        ronKeyValueMap = ronKeyValueMap.toContain(1 to "a")
        starMap = starMap.toContain(1 to "a")

        map = expect(mapOf<Number, CharSequence>(1 to "a", 1.2 to "b"))
        subMap = expect(linkedMapOf<Number, String>(1 to "a", 1.2 to "b"))
        nKeyMap = expect(mapOf<Number?, CharSequence>(1 to "a", 1.2 to "b"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1 to "a", 1.2 to "b"))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))
        starMap = expect(mapOf<Number?, CharSequence?>(1 to "a", 1.2 to "b"))

        map = map.toContain(1 as Number to "a", 1.2 to "b")
        subMap = subMap.toContain(1 as Number to "a", 1.2 to "b")
        nKeyMap = nKeyMap.toContain(1 as Number to "a", 1.2 to "b")
        nValueMap = nValueMap.toContain(1 as Number to "a", 1.2 to "b")
        nKeyValueMap = nKeyValueMap.toContain(1 as Number to "a", 1.2 to "b")
        ronKeyValueMap = ronKeyValueMap.toContain(1 as Number to "a", 1.2 to "b")
        starMap = starMap.toContain(1 as Number to "a", 1.2 to "b")

        nKeyMap = expect(mapOf<Number?, CharSequence>(null to "a"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1.2 to null))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to null))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to "a"))
        starMap = expect(mapOf<Number?, CharSequence?>(null to "a"))

        nKeyMap = nKeyMap.toContain(null to "a")
        nValueMap = nValueMap.toContain(1.2 to null)
        nKeyValueMap = nKeyValueMap.toContain(null to null)
        ronKeyValueMap = ronKeyValueMap.toContain(null to "a")
        starMap = starMap.toContain(null to "a")

        nKeyMap = expect(mapOf<Number?, CharSequence>(null to "a"))
        nValueMap = expect(mapOf<Number, CharSequence?>(1.2 to null, 1 to null))
        nKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))
        ronKeyValueMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))
        starMap = expect(mapOf<Number?, CharSequence?>(null to "a", 2 to null, 1 to null))

        nKeyMap = nKeyMap.toContain(null to "a")
        nValueMap = nValueMap.toContain(1.2 to null, 1 to null)
        nKeyValueMap = nKeyValueMap.toContain(null to "a", 2 to null, 1 to null)
        ronKeyValueMap = ronKeyValueMap.toContain(null to "a", 2 to null, 1 to null)
        starMap = starMap.toContain(null to "a", 2 to null, 1 to null)
    }
}
