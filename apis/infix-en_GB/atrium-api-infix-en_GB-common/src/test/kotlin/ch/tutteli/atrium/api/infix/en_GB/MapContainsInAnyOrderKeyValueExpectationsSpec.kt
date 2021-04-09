package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.mfun2
import org.spekframework.spek2.Spek
import kotlin.jvm.JvmName
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInAnyOrderKeyValueExpectationsSpec.Companion as C

class MapContainsInAnyOrderKeyValueExpectationsSpec : Spek({
    include(BuilderSpec)
    include(ShortcutSpec)
}) {
    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValueExpectationsSpec(
        containsKeyValue_s to C::containsKeyValues,
        (containsKeyValue_s to C::containsKeyValuesNullable).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    object ShortcutSpec : ch.tutteli.atrium.specs.integration.MapContainsInAnyOrderKeyValueExpectationsSpec(
        mfun2<String, Int, Expect<Int>.() -> Unit>(C::contains),
        mfun2<String?, Int?, (Expect<Int>.() -> Unit)?>(C::contains).withNullableSuffix(),
        "[Atrium][Shortcut] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValue_s = "$contains $filler $inAnyOrder $keyValue/$keyValues"

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
                if (others.isEmpty()) expect contains o inAny order entry first
                else expect contains o inAny order the keyValues(first, *others)
            }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
                if (others.isEmpty()) expect contains o inAny order entry first
                else expect contains o inAny order the keyValues(first, *others)
            }

        private fun contains(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ): Expect<Map<out String, Int>> =
            if (aX.isEmpty()) expect contains keyValue(a.first, a.second)
            else mapArguments(a, aX)
                .to { keyValue(it.first, it.second) }
                .let { (first, others) -> expect contains keyValues(first, *others) }

        @JvmName("containsNullable")
        private fun contains(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ): Expect<Map<out String?, Int?>> =
            if (aX.isEmpty()) expect contains keyValue(a.first, a.second)
            else mapArguments(a, aX)
                .to { keyValue(it.first, it.second) }
                .let { (first, others) -> expect contains keyValues(first, *others) }
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

        map = map contains o inAny order entry keyValue(1) { this toEqual "a" }
        subMap = subMap contains o inAny order entry keyValue(1) { this toEqual "a" }
        nKeyMap = nKeyMap contains o inAny order entry keyValue(1) { this toEqual "a" }
        nValueMap = nValueMap contains o inAny order entry keyValue(1) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap contains o inAny order entry keyValue(1) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap contains o inAny order entry keyValue(1) { this toEqual "a" }
        starMap = starMap contains o inAny order entry keyValue(1) { this toEqual "a" }

        map = map contains o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        subMap = subMap contains o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        nKeyMap = nKeyMap contains o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        nValueMap = nValueMap contains o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap contains o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap contains o inAny order the keyValues(keyValue(1) { this toEqual "a" })
        starMap = starMap contains o inAny order the keyValues(keyValue(1) { this toEqual "a" })

        map = map contains o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        subMap = subMap contains o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyMap = nKeyMap contains o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nValueMap = nValueMap contains o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyValueMap = nKeyValueMap contains o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        ronKeyValueMap = ronKeyValueMap contains o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        starMap = starMap contains o inAny order the keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )

        nKeyMap = nKeyMap contains o inAny order entry keyValue(null) { this toEqual "a" }
        nKeyMap = nKeyMap contains o inAny order entry keyValue(null) { this toEqual "a" }
        nValueMap = nValueMap contains o inAny order entry keyValue(1.2, null)
        nKeyValueMap = nKeyValueMap contains o inAny order entry keyValue(null) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap contains o inAny order entry keyValue(null, null)
        ronKeyValueMap = ronKeyValueMap contains o inAny order entry keyValue(null) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap contains o inAny order entry keyValue(null, null)
        starMap = starMap contains o inAny order entry keyValue(null) { this toEqual "a" }
        starMap = starMap contains o inAny order entry keyValue(null, null)

        nKeyMap = nKeyMap contains o inAny order the keyValues(keyValue(null) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap contains o inAny order the keyValues(keyValue(null) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap contains o inAny order the keyValues(keyValue(null) { this toEqual "a" })
        starMap = starMap contains o inAny order the keyValues(keyValue(null) { this toEqual "a" })

        nKeyMap = nKeyMap contains o inAny order the keyValues(keyValue(null) { this toEqual "a" })
        nValueMap = nValueMap contains o inAny order the keyValues(keyValue(1, null), keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap contains o inAny order the keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap contains o inAny order the keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        starMap = starMap contains o inAny order the keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )

        /// ------------- shortcuts -----------------------------------------------------------------

        map = map contains keyValue(1) { this toEqual "a" }
        subMap = subMap contains keyValue(1) { this toEqual "a" }
        nKeyMap = nKeyMap contains keyValue(1) { this toEqual "a" }
        nValueMap = nValueMap contains keyValue(1) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap contains keyValue(1) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap contains keyValue(1) { this toEqual "a" }
        starMap = starMap contains keyValue(1) { this toEqual "a" }

        map = map contains keyValues(keyValue(1) { this toEqual "a" })
        subMap = subMap contains keyValues(keyValue(1) { this toEqual "a" })
        nKeyMap = nKeyMap contains keyValues(keyValue(1) { this toEqual "a" })
        nValueMap = nValueMap contains keyValues(keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap contains keyValues(keyValue(1) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap contains keyValues(keyValue(1) { this toEqual "a" })
        starMap = starMap contains keyValues(keyValue(1) { this toEqual "a" })

        map = map contains keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        subMap = subMap contains keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyMap = nKeyMap contains keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nValueMap = nValueMap contains keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        nKeyValueMap = nKeyValueMap contains keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        ronKeyValueMap = ronKeyValueMap contains keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )
        starMap = starMap contains keyValues(
            keyValue(1 as Number) { this toEqual "a" },
            keyValue(1.2) { this toEqual "b" }
        )

        nKeyMap = nKeyMap contains keyValue(null) { this toEqual "a" }
        nValueMap = nValueMap contains keyValue(1.2, null)
        nKeyValueMap = nKeyValueMap contains keyValue(null) { this toEqual "a" }
        nKeyValueMap = nKeyValueMap contains keyValue(null, null)
        ronKeyValueMap = ronKeyValueMap contains keyValue(null) { this toEqual "a" }
        ronKeyValueMap = ronKeyValueMap contains keyValue(null, null)
        starMap = starMap contains keyValue(null) { this toEqual "a" }
        starMap = starMap contains keyValue(null, null)

        nKeyMap = nKeyMap contains keyValues(keyValue(null) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap contains keyValues(keyValue(null) { this toEqual "a" })
        ronKeyValueMap = ronKeyValueMap contains keyValues(keyValue(null) { this toEqual "a" })
        starMap = starMap contains keyValues(keyValue(null) { this toEqual "a" })

        nKeyMap = nKeyMap contains keyValues(keyValue(null) { this toEqual "a" })
        nValueMap = nValueMap contains keyValues(keyValue(1, null), keyValue(1) { this toEqual "a" })
        nKeyValueMap = nKeyValueMap contains keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap contains keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
        starMap = starMap contains keyValues(
            keyValue(null) { this toEqual "a" },
            keyValue(null, null),
            keyValue(1, null)
        )
    }
}
