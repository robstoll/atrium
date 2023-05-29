package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapToContainInAnyOrderOnlyKeyValueExpectationsSpec.Companion as C

class MapToContainInAnyOrderOnlyKeyValueExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValueExpectationsSpec(
        toContainKeyValue_s to C::toContainKeyValues,
        (toContainKeyValue_s to C::toContainKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapToContainInAnyOrderOnlyKeyValueExpectationsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::toContainOnly),
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::toContainOnly).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapToContainSpecBase() {
        val toContainKeyValue_s = "$toContain $filler $inAnyOrder $butOnly $keyValue/$keyValue"

        private fun toContainKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect toContain o inAny order but only entry first
            else expect toContain o inAny order but only the keyValues(first, *others)
        }

        private fun toContainKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect toContain o inAny order but only entry first
            else expect toContain o inAny order but only the keyValues(first, *others)
        }

        private fun toContainOnly(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect toContainOnly first
            else expect toContainOnly keyValues(first, *others)
        }

        @JvmName("toContainInAnyOrderOnlyNullable")
        private fun toContainOnly(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect toContainOnly first
            else expect toContainOnly keyValues(first, *others)
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

        map = map toContain o inAny order but only entry keyValue(1) { this toEqual "a" }
        subMap = subMap toContain o inAny order but only entry keyValue(1) { this toEqual "a" }
        nKeyMap = nKeyMap toContain o inAny order but only entry keyValue(1) { this toEqual "a" }
        nValueMap = nValueMap toContain o inAny order but only entry keyValue(1) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContain o inAny order but only entry keyValue(1) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only entry keyValue(1) { this toEqual "a" }
        starMap = starMap toContain o inAny order but only entry keyValue(1) { this toEqual "a" }

        map = map toContain o inAny order but only the keyValues(keyValue(1) { this toEqual "a" })
        subMap = subMap toContain o inAny order but only the keyValues(keyValue(1) { this toEqual "a" })
        nKeyMap = nKeyMap toContain o inAny order but only the keyValues(keyValue(1) { this toEqual "a" })
        nValueMap = nValueMap toContain o inAny order but only the keyValues(keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain o inAny order but only the keyValues(keyValue(1) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only the keyValues(keyValue(1) { this toEqual "a" })
        starMap = starMap toContain o inAny order but only the keyValues(keyValue(1) { this toEqual "a" })

        map = map toContain o inAny order but only the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        subMap = subMap toContain o inAny order but only the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyMap = nKeyMap toContain o inAny order but only the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nValueMap = nValueMap toContain o inAny order but only the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyValueMap = nKeyValueMap toContain o inAny order but only the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        starMap = starMap toContain o inAny order but only the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )

        nKeyMap = nKeyMap toContain o inAny order but only entry keyValue(null) { this toEqual "a" }
        nKeyMap = nKeyMap toContain o inAny order but only entry keyValue(null) { this toEqual "a" }
        nValueMap = nValueMap toContain o inAny order but only entry keyValue(1.2, null)
        nKeyValueMap = nKeyValueMap toContain o inAny order but only entry keyValue(null) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContain o inAny order but only entry keyValue(null, null)
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only entry keyValue(null) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only entry keyValue(null, null)
        starMap = starMap toContain o inAny order but only entry keyValue(null) { this toEqual "a" }
        starMap = starMap toContain o inAny order but only entry keyValue(null, null)

        nKeyMap = nKeyMap toContain o inAny order but only the keyValues(keyValue(null) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain o inAny order but only the keyValues(keyValue(null) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only the keyValues(keyValue(null) { this toEqual "a" })
        starMap = starMap toContain o inAny order but only the keyValues(keyValue(null) { this toEqual "a" })

        nKeyMap = nKeyMap toContain o inAny order but only the keyValues(keyValue(null) { this toEqual "a" })
        nValueMap = nValueMap toContain o inAny order but only the keyValues(keyValue(1, null), keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContain o inAny order but only the keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap toContain o inAny order but only the keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        starMap = starMap toContain o inAny order but only the keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )


        /// ------------- shortcuts -----------------------------------------------------------------

        map = map toContainOnly keyValue(1) { this toEqual "a" }
        subMap = subMap toContainOnly keyValue(1) { this toEqual "a" }
        nKeyMap = nKeyMap toContainOnly keyValue(1) { this toEqual "a" }
        nValueMap = nValueMap toContainOnly keyValue(1) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContainOnly keyValue(1) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContainOnly keyValue(1) { this toEqual "a" }
        starMap = starMap toContainOnly keyValue(1) { this toEqual "a" }

        map = map toContainOnly keyValues(keyValue(1) { this toEqual "a" })
        subMap = subMap toContainOnly keyValues(keyValue(1) { this toEqual "a" })
        nKeyMap = nKeyMap toContainOnly keyValues(keyValue(1) { this toEqual "a" })
        nValueMap = nValueMap toContainOnly keyValues(keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContainOnly keyValues(keyValue(1) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap toContainOnly keyValues(keyValue(1) { this toEqual "a" })
        starMap = starMap toContainOnly keyValues(keyValue(1) { this toEqual "a" })

        map = map toContainOnly keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        subMap = subMap toContainOnly keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyMap = nKeyMap toContainOnly keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nValueMap = nValueMap toContainOnly keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyValueMap = nKeyValueMap toContainOnly keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        ronKeyValueMap = ronKeyValueMap toContainOnly keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        starMap = starMap toContainOnly keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )

        nKeyMap = nKeyMap toContainOnly keyValue(null) { this toEqual "a" }
        nValueMap = nValueMap toContainOnly keyValue(1.2, null)
        nKeyValueMap = nKeyValueMap toContainOnly keyValue(null) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap toContainOnly keyValue(null, null)
        ronKeyValueMap = ronKeyValueMap toContainOnly keyValue(null) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap toContainOnly keyValue(null, null)
        starMap = starMap toContainOnly keyValue(null) { this toEqual "a" }
        starMap = starMap toContainOnly keyValue(null, null)

        nKeyMap = nKeyMap toContainOnly keyValues(keyValue(null) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContainOnly keyValues(keyValue(null) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap toContainOnly keyValues(keyValue(null) { this toEqual "a" })
        starMap = starMap toContainOnly keyValues(keyValue(null) { this toEqual "a" })

        nKeyMap = nKeyMap toContainOnly keyValues(keyValue(null) { this toEqual "a" })
        nValueMap = nValueMap toContainOnly keyValues(keyValue(1, null), keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap toContainOnly keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap toContainOnly keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        starMap = starMap toContainOnly keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
    }
}
