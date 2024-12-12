package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.fluent.en_GB.MapToContainInAnyOrderKeyValueExpectationsSpec.Companion as C

class MapToContainInAnyOrderKeyValueExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValueExpectationsSpec(
        functionDescription to C::toContainKeyValues,
        (functionDescription to C::toContainKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValueExpectationsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::toContain).adjustName { "$it ${KeyValue::class.simpleName}" },
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::toContain).adjustName { "$it ${KeyValue::class.simpleName}" }
            .withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$keyValue/$keyValues"

        private fun toContainKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
                if (others.isEmpty()) expect.toContain.inAnyOrder.entry(first)
                else expect.toContain.inAnyOrder.entries(first, *others)
            }

        private fun toContainKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect.toContain.inAnyOrder.entry(first)
            else expect.toContain.inAnyOrder.entries(first, *others)
        }

        private fun toContain(
            expect: Expect<Map<out String, Int>>,
            KeyValue: Pair<String, Expect<Int>.() -> Unit>,
            otherKeyValues: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(KeyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.toContain(first, *others)
        }

        @JvmName("toContainNullable")
        private fun toContain(
            expect: Expect<Map<out String?, Int?>>,
            KeyValue: Pair<String?, (Expect<Int>.() -> Unit)?>,
            otherKeyValues: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(KeyValue, otherKeyValues).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            expect.toContain(first, *others)
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

        map = map.toContain.inAnyOrder.entry(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain.inAnyOrder.entry(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inAnyOrder.entry(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain.inAnyOrder.entry(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entry(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entry(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain.inAnyOrder.entry(KeyValue(1) { toEqual("a") })

        map = map.toContain.inAnyOrder.entries(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain.inAnyOrder.entries(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inAnyOrder.entries(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain.inAnyOrder.entries(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entries(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entries(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain.inAnyOrder.entries(KeyValue(1) { toEqual("a") })

        map = map.toContain.inAnyOrder.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMap = subMap.toContain.inAnyOrder.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMap = nKeyMap.toContain.inAnyOrder.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMap = nValueMap.toContain.inAnyOrder.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMap = starMap.toContain.inAnyOrder.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        nKeyMap = nKeyMap.toContain.inAnyOrder.entry(KeyValue(null) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inAnyOrder.entry(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.toContain.inAnyOrder.entry(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entry(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entry(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entry(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entry(KeyValue(null, null))
        starMap = starMap.toContain.inAnyOrder.entry(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContain.inAnyOrder.entry(KeyValue(null, null))

        nKeyMap = nKeyMap.toContain.inAnyOrder.entries(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entries(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entries(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContain.inAnyOrder.entries(KeyValue(null) { toEqual("a") })

        nKeyMap = nKeyMap.toContain.inAnyOrder.entries(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.toContain.inAnyOrder.entries(KeyValue(1, null), KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inAnyOrder.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.toContain.inAnyOrder.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.toContain.inAnyOrder.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )

        /// ------------- shortcuts -----------------------------------------------------------------

        map = map.toContain(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain(KeyValue(1) { toEqual("a") })

        map = map.toContain(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain(KeyValue(1) { toEqual("a") })

        map = map.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMap = subMap.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMap = nKeyMap.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMap = nValueMap.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMap = nKeyValueMap.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMap = ronKeyValueMap.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMap = starMap.toContain(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        nKeyMap = nKeyMap.toContain(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.toContain(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.toContain(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.toContain(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain(KeyValue(null, null))
        starMap = starMap.toContain(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContain(KeyValue(null, null))

        nKeyMap = nKeyMap.toContain(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContain(KeyValue(null) { toEqual("a") })

        nKeyMap = nKeyMap.toContain(KeyValue(null) { toEqual("a") })
        nValueMap = nValueMap.toContain(KeyValue(1, null), KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.toContain(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.toContain(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
    }
}
