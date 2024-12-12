package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.infix.en_GB.MapToContainInOrderOnlyKeyValueExpectationsSpec.Companion as C

class MapToContainInOrderOnlyKeyValueExpectationsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInOrderOnlyKeyValueExpectationsSpec(
        toContainKeyValue_s to C::toContainKeyValues,
        (toContainKeyValue_s to C::toContainKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    companion object : MapToContainSpecBase() {
        val toContainKeyValue_s = "$toContain $filler $inOrder $andOnly $keyValue/$theEntries"

        private fun toContainKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (report === MapLikeToContainFormatSpecBase.emptyInOrderOnlyReportOptions) {
                if (others.isEmpty()) expect toContain o inGiven order and only entry first
                else expect toContain o inGiven order and only the entries(first, *others)
            } else expect toContain o inGiven order and only the entries(
                first,
                *others,
                reportOptionsInOrderOnly = report
            )
        }

        private fun toContainKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>,
            report: InOrderOnlyReportingOptions.() -> Unit
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (report === MapLikeToContainFormatSpecBase.emptyInOrderOnlyReportOptions) {
                if (others.isEmpty()) expect toContain o inGiven order and only entry first
                else expect toContain o inGiven order and only the entries(first, *others)
            } else expect toContain o inGiven order and only the entries(
                first,
                *others,
                reportOptionsInOrderOnly = report
            )
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

        map = map toContain o inGiven order and only entry keyValue(1) { this toEqual "a" }
        subMap = subMap toContain o inGiven order and only entry keyValue(1) { this toEqual "a" }
        nKeyMap = nKeyMap toContain o inGiven order and only entry keyValue(1) { this toEqual "a" }
        nValueMap = nValueMap toContain o inGiven order and only entry keyValue(1) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only entry keyValue(1) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only entry keyValue(1) { this toEqual "a" }
        starMap = starMap toContain o inGiven order and only entry keyValue(1) { this toEqual "a" }

        map = map toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" })
        subMap = subMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" })
        nKeyMap = nKeyMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" })
        nValueMap = nValueMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" })
        ronKeyValueMap =
            ronKeyValueMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" })
        starMap = starMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" })

        map = map toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" },
            reportOptionsInOrderOnly = {})
        subMap = subMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" },
            reportOptionsInOrderOnly = {})
        nKeyMap = nKeyMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" },
            reportOptionsInOrderOnly = {})
        nValueMap = nValueMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" },
            reportOptionsInOrderOnly = {})
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" },
            reportOptionsInOrderOnly = {})
        ronKeyValueMap =
            ronKeyValueMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" },
                reportOptionsInOrderOnly = {})
        starMap = starMap toContain o inGiven order and only the entries(keyValue(1) { this toEqual "a" },
            reportOptionsInOrderOnly = {})

        map = map toContain o inGiven order and only the entries(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        subMap = subMap toContain o inGiven order and only the entries(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyMap = nKeyMap toContain o inGiven order and only the entries(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nValueMap = nValueMap toContain o inGiven order and only the entries(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only the entries(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only the entries(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        starMap = starMap toContain o inGiven order and only the entries(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )

        nKeyMap = nKeyMap toContain o inGiven order and only entry keyValue(null) { this toEqual "a" }
        nKeyMap = nKeyMap toContain o inGiven order and only entry keyValue(null) { this toEqual "a" }
        nValueMap = nValueMap toContain o inGiven order and only entry keyValue(1.2, null)
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only entry keyValue(null) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only entry keyValue(null, null)
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only entry keyValue(null) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only entry keyValue(null, null)
        starMap = starMap toContain o inGiven order and only entry keyValue(null) { this toEqual "a" }
        starMap = starMap toContain o inGiven order and only entry keyValue(null, null)

        nKeyMap = nKeyMap toContain o inGiven order and only the entries(keyValue(null) { this toEqual "a" })
        nKeyValueMap =
            nKeyValueMap toContain o inGiven order and only the entries(keyValue(null) { this toEqual "a" })
        ronKeyValueMap =
            ronKeyValueMap toContain o inGiven order and only the entries(keyValue(null) { this toEqual "a" })
        starMap = starMap toContain o inGiven order and only the entries(keyValue(null) { this toEqual "a" })

        nKeyMap = nKeyMap toContain o inGiven order and only the entries(keyValue(null) { this toEqual "a" },
            reportOptionsInOrderOnly = {})
        nKeyValueMap =
            nKeyValueMap toContain o inGiven order and only the entries(keyValue(null) { this toEqual "a" },
                reportOptionsInOrderOnly = {})
        ronKeyValueMap =
            ronKeyValueMap toContain o inGiven order and only the entries(keyValue(null) { this toEqual "a" },
                reportOptionsInOrderOnly = {})
        starMap = starMap toContain o inGiven order and only the entries(keyValue(null) { this toEqual "a" },
            reportOptionsInOrderOnly = {})

        nKeyMap = nKeyMap toContain o inGiven order and only the entries(keyValue(null) { this toEqual "a" })
        nValueMap = nValueMap toContain o inGiven order and only the entries(
            keyValue(1, null),
            keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain o inGiven order and only the entries(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap toContain o inGiven order and only the entries(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        starMap = starMap toContain o inGiven order and only the entries(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )

        starMap =
            starMap toContain o inGiven order and only the entriesOf(mapOf("a" to 1), reportOptionsInOrderOnly = {})
    }
}
