package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature0
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented

object MapAsEntriesAssertionsSpec : ch.tutteli.atrium.specs.integration.MapAsEntriesAssertionsSpec(
    feature0<Map<String, Int>, Set<Map.Entry<String, Int>>>(Expect<Map<String, Int>>::asEntries),
    fun1<Map<String, Int>, Expect<Set<Map.Entry<String, Int>>>.() -> Unit>(Expect<Map<String, Int>>::asEntries)
) {

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nullableKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nullableValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nullableKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var readOnlyNullableKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()

        var starKeyMap: Expect<Map<*, CharSequence?>> = notImplemented()
        var starValueMap: Expect<Map<String, *>> = notImplemented()

        map.asEntries()
        subMap.asEntries()
        nullableKeyMap.asEntries()
        nullableValueMap.asEntries()
        nullableKeyValueMap.asEntries()
        readOnlyNullableKeyValueMap.asEntries()

        map = map.asEntries {}
        subMap = subMap.asEntries {}
        nullableKeyMap = nullableKeyMap.asEntries {}
        nullableValueMap = nullableValueMap.asEntries {}
        nullableKeyValueMap = nullableKeyValueMap.asEntries {}
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap.asEntries {}

        starKeyMap = starKeyMap.asEntries {}
        starValueMap = starValueMap.asEntries {}
    }
}
