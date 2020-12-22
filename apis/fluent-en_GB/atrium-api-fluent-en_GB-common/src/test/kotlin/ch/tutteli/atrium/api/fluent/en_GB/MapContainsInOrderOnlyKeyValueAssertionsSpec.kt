package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInOrderOnlyKeyValueAssertionsSpec.Companion as C

class MapContainsInOrderOnlyKeyValueAssertionsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInOrderOnlyKeyValueAssertionsSpec(
        functionDescription to C::containsKeyValues,
        (functionDescription to C::containsKeyValuesNullable).withNullableSuffix(),
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    )

    companion object : MapContainsSpecBase() {
        val functionDescription = "$contains.$inOrder.$only.$keyValue/$keyValues"

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.contains.inOrder.only.entry(first)
            else expect.contains.inOrder.only.entries(first, *others)
        }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.contains.inOrder.only.entry(first)
            else expect.contains.inOrder.only.entries(first, *others)
        }
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

        map = map.contains.inOrder.only.entry(KeyValue(1) { toBe("a") })
        subMap = subMap.contains.inOrder.only.entry(KeyValue(1) { toBe("a") })
        nKeyMap = nKeyMap.contains.inOrder.only.entry(KeyValue(1) { toBe("a") })
        nValueMap = nValueMap.contains.inOrder.only.entry(KeyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entry(KeyValue(1) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entry(KeyValue(1) { toBe("a") })
        starMap = starMap.contains.inOrder.only.entry(KeyValue(1) { toBe("a") })

        map = map.contains.inOrder.only.entries(KeyValue(1) { toBe("a") })
        subMap = subMap.contains.inOrder.only.entries(KeyValue(1) { toBe("a") })
        nKeyMap = nKeyMap.contains.inOrder.only.entries(KeyValue(1) { toBe("a") })
        nValueMap = nValueMap.contains.inOrder.only.entries(KeyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entries(KeyValue(1) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entries(KeyValue(1) { toBe("a") })
        starMap = starMap.contains.inOrder.only.entries(KeyValue(1) { toBe("a") })

        map = map.contains.inOrder.only.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        subMap = subMap.contains.inOrder.only.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        nKeyMap = nKeyMap.contains.inOrder.only.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        nValueMap = nValueMap.contains.inOrder.only.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        starMap = starMap.contains.inOrder.only.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )

        nKeyMap = nKeyMap.contains.inOrder.only.entry(KeyValue(null) { toBe("a") })
        nKeyMap = nKeyMap.contains.inOrder.only.entry(KeyValue(null) { toBe("a") })
        nValueMap = nValueMap.contains.inOrder.only.entry(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entry(KeyValue(null) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entry(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entry(KeyValue(null) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entry(KeyValue(null, null))
        starMap = starMap.contains.inOrder.only.entry(KeyValue(null) { toBe("a") })
        starMap = starMap.contains.inOrder.only.entry(KeyValue(null, null))

        nKeyMap = nKeyMap.contains.inOrder.only.entries(KeyValue(null) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entries(KeyValue(null) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entries(KeyValue(null) { toBe("a") })
        starMap = starMap.contains.inOrder.only.entries(KeyValue(null) { toBe("a") })

        nKeyMap = nKeyMap.contains.inOrder.only.entries(KeyValue(null) { toBe("a") })
        nValueMap = nValueMap.contains.inOrder.only.entries(KeyValue(1, null), KeyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inOrder.only.entries(
            KeyValue(null) { toBe("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.contains.inOrder.only.entries(
            KeyValue(null) { toBe("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.contains.inOrder.only.entries(
            KeyValue(null) { toBe("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
    }
}
