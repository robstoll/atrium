package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.feature1
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter

class MapAsEntriesAssertionsSpec : ch.tutteli.atrium.specs.integration.MapAsEntriesAssertionsSpec(
    asEntriesPair(),
    fun1<Map<String, Int>, Expect<Set<Map.Entry<String, Int>>>.() -> Unit>(Expect<Map<String, Int>>::asEntries)
) {

    companion object : WithAsciiReporter() {
        fun asEntriesPair() =
            feature1<Map<String, Int>, o, Set<Map.Entry<String, Int>>>(Expect<Map<String, Int>>::asEntries).name to ::asEntriesFeature

        fun asEntriesFeature(expect: Expect<Map<String, Int>>) = expect asEntries o
    }

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

        map asEntries o
        subMap asEntries o
        nullableKeyMap asEntries o
        nullableValueMap asEntries o
        nullableKeyValueMap asEntries o
        readOnlyNullableKeyValueMap asEntries o

        map = map asEntries {}
        subMap = subMap asEntries {}
        nullableKeyMap = nullableKeyMap asEntries {}
        nullableValueMap = nullableValueMap asEntries {}
        nullableKeyValueMap = nullableKeyValueMap asEntries {}
        readOnlyNullableKeyValueMap = readOnlyNullableKeyValueMap asEntries {}

        starKeyMap = starKeyMap asEntries {}
        starValueMap = starValueMap asEntries {}
    }
}
