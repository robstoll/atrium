package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun1
import kotlin.test.Test

class MapAsEntriesExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractMapAsEntriesExpectationsTest(
    feature0(Expect<Map<String, Int>>::asEntries),
    fun1(Expect<Map<String, Int>>::asEntries)
) {

    @Suppress("UNUSED_VALUE", "UNUSED_VARIABLE", "unused", "AssignedValueIsNeverRead")
    @Test
    fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = expect(mapOf<Number, CharSequence>(1 to "a"))
        var subMap: Expect<LinkedHashMap<out Number, String>> = expect(linkedMapOf<Number, String>(1 to "a"))
        var nullableKeyMap: Expect<Map<Number?, CharSequence>> = expect(mapOf<Number?, CharSequence>(1 to "a"))
        var nullableValueMap: Expect<Map<Number, CharSequence?>> = expect(mapOf<Number, CharSequence?>(1 to "a"))
        var nullableKeyValueMap: Expect<Map<Number?, CharSequence?>> = expect(mapOf<Number?, CharSequence?>(1 to "a"))
        var readOnlyNullableKeyValueMap: Expect<Map<out Number?, CharSequence?>> =
            expect(mapOf<Number?, CharSequence?>(1 to "a"))

        var starKeyMap: Expect<Map<*, CharSequence?>> = expect(mapOf<Number, CharSequence?>(1 to "a"))
        var starValueMap: Expect<Map<String, *>> = expect(mapOf<String, CharSequence?>("a" to null))

        map.asEntries()
        subMap.asEntries()
        nullableKeyMap.asEntries()
        nullableValueMap.asEntries()
        nullableKeyValueMap.asEntries()
        readOnlyNullableKeyValueMap.asEntries()

        map = map.asEntries { toHaveSize(1) }
        subMap = subMap.asEntries { toHaveSize(1) }
        nullableKeyMap = nullableKeyMap.asEntries { toHaveSize(1) }
        nullableValueMap = nullableValueMap.asEntries { toHaveSize(1) }
        nullableKeyValueMap = nullableKeyValueMap.asEntries { toHaveSize(1) }
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.asEntries { toHaveSize(1) }

        starKeyMap = starKeyMap.asEntries { toHaveSize(1) }
        starValueMap = starValueMap.asEntries { toHaveSize(1) }
    }
}
