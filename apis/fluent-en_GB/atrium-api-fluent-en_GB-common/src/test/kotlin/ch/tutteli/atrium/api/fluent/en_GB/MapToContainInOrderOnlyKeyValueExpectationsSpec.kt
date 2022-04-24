package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.emptyInOrderOnlyReportOptions
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.fluent.en_GB.MapToContainInOrderOnlyKeyValueExpectationsSpec.Companion as C

class MapToContainInOrderOnlyKeyValueExpectationsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInOrderOnlyKeyValueExpectationsSpec(
        functionDescription to C::toContainKeyValues,
        (functionDescription to C::toContainKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    companion object : MapToContainSpecBase() {
        val functionDescription = "$toContain.$inOrder.$only.$keyValue/$keyValues"

        private fun toContainKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>,
            report : InOrderOnlyReportingOptions.() -> Unit
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (report === emptyInOrderOnlyReportOptions) {
                if (others.isEmpty()) expect.toContain.inOrder.only.entry(first)
                else expect.toContain.inOrder.only.entries(first, *others)
            } else expect.toContain.inOrder.only.entries(first, *others, report = report)
        }

        private fun toContainKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>,
            report : InOrderOnlyReportingOptions.() -> Unit
        ) = mapArguments(a, aX).to { KeyValue(it.first, it.second) }.let { (first, others) ->
            if (report === emptyInOrderOnlyReportOptions) {
                if (others.isEmpty()) expect.toContain.inOrder.only.entry(first)
                else expect.toContain.inOrder.only.entries(first, *others)
            } else expect.toContain.inOrder.only.entries(first, *others, report = report)
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

        map = map.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain.inOrder.only.entry(KeyValue(1) { toEqual("a") })

        map = map.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        subMap = subMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        nValueMap = nValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })
        starMap = starMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") })

        map = map.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        subMap = subMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        nKeyMap = nKeyMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        nValueMap = nValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})
        starMap = starMap.toContain.inOrder.only.entries(KeyValue(1) { toEqual("a") }, report = {})

        map = map.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        subMap = subMap.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyMap = nKeyMap.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nValueMap = nValueMap.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )
        starMap = starMap.toContain.inOrder.only.entries(
            KeyValue(1 as Number) { toEqual("a") },
            KeyValue(1.2) { toEqual("b") }
        )

        nKeyMap = nKeyMap.toContain.inOrder.only.entry(KeyValue(null) { toEqual("a") })
        nKeyMap = nKeyMap.toContain.inOrder.only.entry(KeyValue(null, null))
        nValueMap = nValueMap.toContain.inOrder.only.entry(KeyValue(1.2, null))
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entry(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entry(KeyValue(null, null))
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entry(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entry(KeyValue(null, null))
        starMap = starMap.toContain.inOrder.only.entry(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContain.inOrder.only.entry(KeyValue(null, null))

        nKeyMap = nKeyMap.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") })
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") })
        starMap = starMap.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") })

        nKeyMap = nKeyMap.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") }, report = {})
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") }, report = {})
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") }, report = {})
        starMap = starMap.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") }, report = {})

        nKeyMap = nKeyMap.toContain.inOrder.only.entries(KeyValue(null) { toEqual("a") }, KeyValue(1, null))
        nValueMap = nValueMap.toContain.inOrder.only.entries(KeyValue(1, null), KeyValue(1) { toEqual("a") })
        nKeyValueMap = nKeyValueMap.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
        starMap = starMap.toContain.inOrder.only.entries(
            KeyValue(null) { toEqual("a") },
            KeyValue(null, null),
            KeyValue(1, null)
        )
    }
}
