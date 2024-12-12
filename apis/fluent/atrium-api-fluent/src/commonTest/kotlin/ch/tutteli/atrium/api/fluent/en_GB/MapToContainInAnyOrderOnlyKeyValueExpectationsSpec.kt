package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapToContainInAnyOrderOnlyKeyValueExpectationsSpec.Companion as C

class MapToContainInAnyOrderOnlyKeyValueExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValueExpectationsSpec(
        functionDescription to C::toContainKeyValues,
        (functionDescription to C::toContainKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )


    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValueExpectationsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::toContainOnly),
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::toContainOnly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$only.$keyValue/$keyValues"

        private fun toContainKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.toContain.inAnyOrder.only.entry(first)
            else expect.toContain.inAnyOrder.only.entries(first, *others)
        }

        private fun toContainKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.toContain.inAnyOrder.only.entry(first)
            else expect.toContain.inAnyOrder.only.entries(first, *others)
        }

        private fun toContainOnly(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                expect.toContainOnly(first, *others)
            }

        @JvmName("toContainInAnyOrderOnlyNullable")
        private fun toContainOnly(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                expect.toContainOnly(first, *others)
            }
    }


    @Suppress("unused")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var ronKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

        map = map.toContain.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain.inAnyOrder.only.entry(KeyValue(1) { toEqual("a") })

        map = map.toContain.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain.inAnyOrder.only.entries(KeyValue(1) { toEqual("a") })

        map = map.toContain.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMap = subMap.toContain.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMap = nValueMap.toContain.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMap = starMap.toContain.inAnyOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.toContain.inAnyOrder.only.entry(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entry(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entry(KeyValue(null, null))
        starMap = starMap.toContain.inAnyOrder.only.entry(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContain.inAnyOrder.only.entry(KeyValue(null, null))

        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContain.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })

        nKeyMap = nKeyMap.toContain.inAnyOrder.only.entries(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.toContain.inAnyOrder.only.entries(KeyValue(1, null), KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.toContain.inAnyOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )

        /// ------------- shortcuts -----------------------------------------------------------------

        map = map.toContainOnly(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContainOnly(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContainOnly(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContainOnly(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContainOnly(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContainOnly(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContainOnly(KeyValue(1) { toEqual("a") })

        map = map.toContainOnly(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContainOnly(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContainOnly(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContainOnly(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContainOnly(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContainOnly(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContainOnly(KeyValue(1) { toEqual("a") })

        map = map.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMap = subMap.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMap = nKeyMap.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMap = nValueMap.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMap = nKeyValueMap.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMap = ronKeyValueMap.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMap = starMap.toContainOnly(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        nKeyMap = nKeyMap.toContainOnly(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.toContainOnly(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.toContainOnly(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContainOnly(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.toContainOnly(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContainOnly(KeyValue(null, null))
        starMap = starMap.toContainOnly(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContainOnly(KeyValue(null, null))

        nKeyMap = nKeyMap.toContainOnly(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContainOnly(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContainOnly(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContainOnly(KeyValue(null) { toEqual("a") })

        nKeyMap = nKeyMap.toContainOnly(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.toContainOnly(KeyValue(1, null), KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContainOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.toContainOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.toContainOnly(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
    }
}
