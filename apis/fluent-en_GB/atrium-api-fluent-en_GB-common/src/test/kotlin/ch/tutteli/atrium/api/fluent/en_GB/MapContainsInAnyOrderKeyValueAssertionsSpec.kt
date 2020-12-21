package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderKeyValueAssertionsSpec.Companion as C

class MapContainsInAnyOrderKeyValueAssertionsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValueAssertionsSpec(
        containsKeyValue_s to C::containsKeyValues,
        (containsKeyValue_s to C::containsKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValueAssertionsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::contains).adjustName { "$it ${KeyValue::class.simpleName}" },
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::contains).adjustName { "$it ${KeyValue::class.simpleName}" }
            .withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValue_s = "$contains.$inAnyOrder.$keyValue/$keyValues"

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                if (others.isEmpty()) expect.contains.inAnyOrder.entry(first)
                else expect.contains.inAnyOrder.entries(first, *others)
            }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.contains.inAnyOrder.entry(first)
            else expect.contains.inAnyOrder.entries(first, *others)
        }

        private fun contains(
            expect: Expect<Map<out String, Int>>,
            KeyValue: Pair<String, Expect<Int>.() -> Unit>,
            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(KeyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.contains(first, *others)
        }

        @JvmName("containsNullable")
        private fun contains(
            expect: Expect<Map<out String?, Int?>>,
            KeyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(KeyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.contains(first, *others)
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

        map = map.contains.inAnyOrder.entry(KeyValue(1) { toBe("a") })
        subMap = subMap.contains.inAnyOrder.entry(KeyValue(1) { toBe("a") })
        nKeyMap = nKeyMap.contains.inAnyOrder.entry(KeyValue(1) { toBe("a") })
        nValueMap = nValueMap.contains.inAnyOrder.entry(KeyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entry(KeyValue(1) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entry(KeyValue(1) { toBe("a") })
        starMap = starMap.contains.inAnyOrder.entry(KeyValue(1) { toBe("a") })

        map = map.contains.inAnyOrder.entries(KeyValue(1) { toBe("a") })
        subMap = subMap.contains.inAnyOrder.entries(KeyValue(1) { toBe("a") })
        nKeyMap = nKeyMap.contains.inAnyOrder.entries(KeyValue(1) { toBe("a") })
        nValueMap = nValueMap.contains.inAnyOrder.entries(KeyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entries(KeyValue(1) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entries(KeyValue(1) { toBe("a") })
        starMap = starMap.contains.inAnyOrder.entries(KeyValue(1) { toBe("a") })

        map = map.contains.inAnyOrder.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        subMap = subMap.contains.inAnyOrder.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        nKeyMap = nKeyMap.contains.inAnyOrder.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        nValueMap = nValueMap.contains.inAnyOrder.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        starMap = starMap.contains.inAnyOrder.entries(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )

        nKeyMap = nKeyMap.contains.inAnyOrder.entry(KeyValue(null) { toBe("a") })
        nKeyMap = nKeyMap.contains.inAnyOrder.entry(KeyValue(null) { toBe("a") })
        nValueMap = nValueMap.contains.inAnyOrder.entry(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entry(KeyValue(null) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entry(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entry(KeyValue(null) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entry(KeyValue(null, null))
        starMap = starMap.contains.inAnyOrder.entry(KeyValue(null) { toBe("a") })
        starMap = starMap.contains.inAnyOrder.entry(KeyValue(null, null))

        nKeyMap = nKeyMap.contains.inAnyOrder.entries(KeyValue(null) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entries(KeyValue(null) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entries(KeyValue(null) { toBe("a") })
        starMap = starMap.contains.inAnyOrder.entries(KeyValue(null) { toBe("a") })

        nKeyMap = nKeyMap.contains.inAnyOrder.entries(KeyValue(null) { toBe("a") })
        nValueMap = nValueMap.contains.inAnyOrder.entries(KeyValue(1, null), KeyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.entries(
            KeyValue(null) { toBe("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.entries(
            KeyValue(null) { toBe("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.contains.inAnyOrder.entries(
            KeyValue(null) { toBe("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )

        /// ------------- shortcuts -----------------------------------------------------------------

        map = map.contains(KeyValue(1) { toBe("a") })
        subMap = subMap.contains(KeyValue(1) { toBe("a") })
        nKeyMap = nKeyMap.contains(KeyValue(1) { toBe("a") })
        nValueMap = nValueMap.contains(KeyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains(KeyValue(1) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains(KeyValue(1) { toBe("a") })
        starMap = starMap.contains(KeyValue(1) { toBe("a") })

        map = map.contains(KeyValue(1) { toBe("a") })
        subMap = subMap.contains(KeyValue(1) { toBe("a") })
        nKeyMap = nKeyMap.contains(KeyValue(1) { toBe("a") })
        nValueMap = nValueMap.contains(KeyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains(KeyValue(1) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains(KeyValue(1) { toBe("a") })
        starMap = starMap.contains(KeyValue(1) { toBe("a") })

        map = map.contains(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        subMap = subMap.contains(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        nKeyMap = nKeyMap.contains(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        nValueMap = nValueMap.contains(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        nKeyValueMap = nKeyValueMap.contains(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        ronKeyValueMap = ronKeyValueMap.contains(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )
        starMap = starMap.contains(
            KeyValue(1 as Number) { toBe("a") },
            KeyValue(1.2) { toBe("b") }
        )

        nKeyMap = nKeyMap.contains(KeyValue(null) { toBe("a") })
        nValueMap = nValueMap.contains(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.contains(KeyValue(null) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.contains(KeyValue(null) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains(KeyValue(null, null))
        starMap = starMap.contains(KeyValue(null) { toBe("a") })
        starMap = starMap.contains(KeyValue(null, null))

        nKeyMap = nKeyMap.contains(KeyValue(null) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains(KeyValue(null) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap.contains(KeyValue(null) { toBe("a") })
        starMap = starMap.contains(KeyValue(null) { toBe("a") })

        nKeyMap = nKeyMap.contains(KeyValue(null) { toBe("a") })
        nValueMap = nValueMap.contains(KeyValue(1, null), KeyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap.contains(
            KeyValue(null) { toBe("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.contains(
            KeyValue(null) { toBe("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.contains(
            KeyValue(null) { toBe("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
    }
}
