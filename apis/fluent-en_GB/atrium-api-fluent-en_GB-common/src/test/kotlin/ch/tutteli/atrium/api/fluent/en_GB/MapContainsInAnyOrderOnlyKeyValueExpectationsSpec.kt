package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapContainsInAnyOrderOnlyKeyValueExpectationsSpec.Companion as C

class MapContainsInAnyOrderOnlyKeyValueExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValueExpectationsSpec(
        functionDescription to C::containsKeyValues,
        (functionDescription to C::containsKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )


    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderOnlyKeyValueExpectationsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::containsOnly),
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::containsOnly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val functionDescription = "$contains.$inAnyOrder.$only.$keyValue/$keyValues"

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.contains.inAnyOrder.only.entry(first)
            else expect.contains.inAnyOrder.only.entries(first, *others)
        }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.contains.inAnyOrder.only.entry(first)
            else expect.contains.inAnyOrder.only.entries(first, *others)
        }

        private fun containsOnly(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                expect.containsOnly(first, *others)
            }

        @JvmName("containsInAnyOrderOnlyNullable")
        private fun containsOnly(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                expect.containsOnly(first, *others)
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

        map = map.contains.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        subMap = subMap.contains.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.contains.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.contains.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        starMap = starMap.contains.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })

        map = map.contains.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        subMap = subMap.contains.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.contains.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.contains.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        starMap = starMap.contains.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })

        map = map.contains.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMap = subMap.contains.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMap = nKeyMap.contains.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMap = nValueMap.contains.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMap = starMap.contains.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        nKeyMap = nKeyMap.contains.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        nKeyMap = nKeyMap.contains.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.contains.inAnyOrder.only.entry(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entry(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entry(KeyValue(null, null))
        starMap = starMap.contains.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        starMap = starMap.contains.inAnyOrder.only.entry(KeyValue(null, null))

        nKeyMap = nKeyMap.contains.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })
        starMap = starMap.contains.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })

        nKeyMap = nKeyMap.contains.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.contains.inAnyOrder.only.entries(KeyValue(1, null), KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.contains.inAnyOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.contains.inAnyOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.contains.inAnyOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )

        /// ------------- shortcuts -----------------------------------------------------------------

        map = map.containsOnly(KeyValue(1) { toEqual("a") })
        subMap = subMap.containsOnly(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.containsOnly(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.containsOnly(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.containsOnly(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.containsOnly(KeyValue(1) { toEqual("a") })
        starMap = starMap.containsOnly(KeyValue(1) { toEqual("a") })

        map = map.containsOnly(KeyValue(1) { toEqual("a") })
        subMap = subMap.containsOnly(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.containsOnly(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.containsOnly(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.containsOnly(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.containsOnly(KeyValue(1) { toEqual("a") })
        starMap = starMap.containsOnly(KeyValue(1) { toEqual("a") })

        map = map.containsOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMap = subMap.containsOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMap = nKeyMap.containsOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMap = nValueMap.containsOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMap = nKeyValueMap.containsOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMap = ronKeyValueMap.containsOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMap = starMap.containsOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        nKeyMap = nKeyMap.containsOnly(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.containsOnly(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.containsOnly(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.containsOnly(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.containsOnly(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.containsOnly(KeyValue(null, null))
        starMap = starMap.containsOnly(KeyValue(null) { toEqual("a") })
        starMap = starMap.containsOnly(KeyValue(null, null))

        nKeyMap = nKeyMap.containsOnly(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.containsOnly(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.containsOnly(KeyValue(null) { toEqual("a") })
        starMap = starMap.containsOnly(KeyValue(null) { toEqual("a") })

        nKeyMap = nKeyMap.containsOnly(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.containsOnly(KeyValue(1, null), KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.containsOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.containsOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.containsOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
    }
}
