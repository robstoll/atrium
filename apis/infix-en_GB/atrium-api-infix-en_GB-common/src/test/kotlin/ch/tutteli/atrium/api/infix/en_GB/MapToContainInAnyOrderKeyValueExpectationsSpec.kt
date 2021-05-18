package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapToContainInAnyOrderKeyValueExpectationsSpec.Companion as C

class MapToContainInAnyOrderKeyValueExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValueExpectationsSpec(
        toContainKeyValue_s to C::toContainKeyValues,
        (toContainKeyValue_s to C::toContainKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderKeyValueExpectationsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::toContain),
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::toContain).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapToContainSpecBase() {
        val toContainKeyValue_s = "$toContain $filler $inAnyOrder $keyValue/$keyValues"

        private fun toContainKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
                if (others.isEmpty()) expect toContain o inAny order entry first
                else expect toContain o inAny order the keyValues(first, *others)
            }

        private fun toContainKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
                if (others.isEmpty()) expect toContain o inAny order entry first
                else expect toContain o inAny order the keyValues(first, *others)
            }

        private fun toContain(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect toContain keyValue(a.first, a.second)
            else mapArguments(a, aX)
                .to { keyValue(it.first, it.second) }
                .let { (first, others) -> expect toContain keyValues(first, *others) }

        @JvmName("toContainNullable")
        private fun toContain(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect toContain keyValue(a.first, a.second)
            else mapArguments(a, aX)
                .to { keyValue(it.first, it.second) }
                .let { (first, others) -> expect toContain keyValues(first, *others) }
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

        map = map toContain o inAny order entry keyValue(1) { this toEqual "a" }
        subMap = subMap toContain o inAny order entry keyValue(1) { this toEqual "a" }
        nKeyMap = nKeyMap toContain o inAny order entry keyValue(1) { this toEqual "a" }
        nValueMap = nValueMap toContain o inAny order entry keyValue(1) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContain o inAny order entry keyValue(1) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContain o inAny order entry keyValue(1) { this toEqual "a" }
        starMap = starMap toContain o inAny order entry keyValue(1) { this toEqual "a" }

        map = map toContain o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        subMap = subMap toContain o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        nKeyMap = nKeyMap toContain o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        nValueMap = nValueMap toContain o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap toContain o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        starMap = starMap toContain o inAny order the keyValues(keyValue(1) { this toEqual "a" })

        map = map toContain o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        subMap = subMap toContain o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyMap = nKeyMap toContain o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nValueMap = nValueMap toContain o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyValueMap = nKeyValueMap toContain o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        ronKeyValueMap = ronKeyValueMap toContain o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        starMap = starMap toContain o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )

        nKeyMap = nKeyMap toContain o inAny order entry keyValue(null) { this toEqual "a" }
        nKeyMap = nKeyMap toContain o inAny order entry keyValue(null) { this toEqual "a" }
        nValueMap = nValueMap toContain o inAny order entry keyValue(1.2, null)
        nKeyValueMap = nKeyValueMap toContain o inAny order entry keyValue(null) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContain o inAny order entry keyValue(null, null)
        ronKeyValueMap = ronKeyValueMap toContain o inAny order entry keyValue(null) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContain o inAny order entry keyValue(null, null)
        starMap = starMap toContain o inAny order entry keyValue(null) { this toEqual "a" }
        starMap = starMap toContain o inAny order entry keyValue(null, null)

        nKeyMap = nKeyMap toContain o inAny order the keyValues(keyValue(null) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain o inAny order the keyValues(keyValue(null) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap toContain o inAny order the keyValues(keyValue(null) { this toEqual "a" })
        starMap = starMap toContain o inAny order the keyValues(keyValue(null) { this toEqual "a" })

        nKeyMap = nKeyMap toContain o inAny order the keyValues(keyValue(null) { this toEqual "a" })
        nValueMap = nValueMap toContain o inAny order the keyValues(keyValue(1, null), keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain o inAny order the keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap toContain o inAny order the keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        starMap = starMap toContain o inAny order the keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )

        /// ------------- shortcuts -----------------------------------------------------------------

        map = map toContain keyValue(1) { this toEqual "a" }
        subMap = subMap toContain keyValue(1) { this toEqual "a" }
        nKeyMap = nKeyMap toContain keyValue(1) { this toEqual "a" }
        nValueMap = nValueMap toContain keyValue(1) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContain keyValue(1) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContain keyValue(1) { this toEqual "a" }
        starMap = starMap toContain keyValue(1) { this toEqual "a" }

        map = map toContain keyValues(keyValue(1) { this toEqual "a" })
        subMap = subMap toContain keyValues(keyValue(1) { this toEqual "a" })
        nKeyMap = nKeyMap toContain keyValues(keyValue(1) { this toEqual "a" })
        nValueMap = nValueMap toContain keyValues(keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain keyValues(keyValue(1) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap toContain keyValues(keyValue(1) { this toEqual "a" })
        starMap = starMap toContain keyValues(keyValue(1) { this toEqual "a" })

        map = map toContain keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        subMap = subMap toContain keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyMap = nKeyMap toContain keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nValueMap = nValueMap toContain keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyValueMap = nKeyValueMap toContain keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        ronKeyValueMap = ronKeyValueMap toContain keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        starMap = starMap toContain keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )

        nKeyMap = nKeyMap toContain keyValue(null) { this toEqual "a" }
        nValueMap = nValueMap toContain keyValue(1.2, null)
        nKeyValueMap = nKeyValueMap toContain keyValue(null) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContain keyValue(null, null)
        ronKeyValueMap = ronKeyValueMap toContain keyValue(null) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContain keyValue(null, null)
        starMap = starMap toContain keyValue(null) { this toEqual "a" }
        starMap = starMap toContain keyValue(null, null)

        nKeyMap = nKeyMap toContain keyValues(keyValue(null) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain keyValues(keyValue(null) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap toContain keyValues(keyValue(null) { this toEqual "a" })
        starMap = starMap toContain keyValues(keyValue(null) { this toEqual "a" })

        nKeyMap = nKeyMap toContain keyValues(keyValue(null) { this toEqual "a" })
        nValueMap = nValueMap toContain keyValues(keyValue(1, null), keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap toContain keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        starMap = starMap toContain keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
    }
}
