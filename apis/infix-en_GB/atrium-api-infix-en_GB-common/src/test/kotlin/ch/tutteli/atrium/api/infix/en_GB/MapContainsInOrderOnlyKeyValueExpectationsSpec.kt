package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.mapArguments
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import ch.tutteli.atrium.api.infix.en_GB.MapContainsInOrderOnlyKeyValueExpectationsSpec.Companion as C

class MapContainsInOrderOnlyKeyValueExpectationsSpec : Spek({
    include(BuilderSpec)
}) {

    object BuilderSpec : ch.tutteli.atrium.specs.integration.MapContainsInOrderOnlyKeyValueExpectationsSpec(
        containsKeyValue_s to C::containsKeyValues,
        (containsKeyValue_s to C::containsKeyValuesNullable).withNullableSuffix(),
        "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
        "[Atrium][Builder] "
    )

    companion object : MapContainsSpecBase() {
        val containsKeyValue_s = "$contains $filler $inOrder $andOnly $keyValue/$keyValues"

        private fun containsKeyValues(
            expect: Expect<Map<out String, Int>>,
            a: Pair<String, Expect<Int>.() -> Unit>,
            aX: Array<out Pair<String, Expect<Int>.() -> Unit>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect contains o inGiven order and only entry first
            else expect contains o inGiven order and only the keyValues(first, *others)
        }

        private fun containsKeyValuesNullable(
            expect: Expect<Map<out String?, Int?>>,
            a: Pair<String?, (Expect<Int>.() -> Unit)?>,
            aX: Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>
        ) = mapArguments(a, aX).to { keyValue(it.first, it.second) }.let { (first, others) ->
            if (others.isEmpty()) expect contains o inGiven order and only entry first
            else expect contains o inGiven order and only the keyValues(first, *others)
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

        map = map contains o inGiven order and only entry keyValue(1) { toBe("a") }
        subMap = subMap contains o inGiven order and only entry keyValue(1) { toBe("a") }
        nKeyMap = nKeyMap contains o inGiven order and only entry keyValue(1) { toBe("a") }
        nValueMap = nValueMap contains o inGiven order and only entry keyValue(1) { toBe("a") }
        nKeyValueMap = nKeyValueMap contains o inGiven order and only entry keyValue(1) { toBe("a") }
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only entry keyValue(1) { toBe("a") }
        starMap = starMap contains o inGiven order and only entry keyValue(1) { toBe("a") }

        map = map contains o inGiven order and only the keyValues(keyValue(1) { toBe("a") })
        subMap = subMap contains o inGiven order and only the keyValues(keyValue(1) { toBe("a") })
        nKeyMap = nKeyMap contains o inGiven order and only the keyValues(keyValue(1) { toBe("a") })
        nValueMap = nValueMap contains o inGiven order and only the keyValues(keyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap contains o inGiven order and only the keyValues(keyValue(1) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only the keyValues(keyValue(1) { toBe("a") })
        starMap = starMap contains o inGiven order and only the keyValues(keyValue(1) { toBe("a") })

        map = map contains o inGiven order and only the keyValues(
            keyValue(1 as Number) { toBe("a") },
            keyValue(1.2) { toBe("b") }
        )
        subMap = subMap contains o inGiven order and only the keyValues(
            keyValue(1 as Number) { toBe("a") },
            keyValue(1.2) { toBe("b") }
        )
        nKeyMap = nKeyMap contains o inGiven order and only the keyValues(
            keyValue(1 as Number) { toBe("a") },
            keyValue(1.2) { toBe("b") }
        )
        nValueMap = nValueMap contains o inGiven order and only the keyValues(
            keyValue(1 as Number) { toBe("a") },
            keyValue(1.2) { toBe("b") }
        )
        nKeyValueMap = nKeyValueMap contains o inGiven order and only the keyValues(
            keyValue(1 as Number) { toBe("a") },
            keyValue(1.2) { toBe("b") }
        )
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only the keyValues(
            keyValue(1 as Number) { toBe("a") },
            keyValue(1.2) { toBe("b") }
        )
        starMap = starMap contains o inGiven order and only the keyValues(
            keyValue(1 as Number) { toBe("a") },
            keyValue(1.2) { toBe("b") }
        )

        nKeyMap = nKeyMap contains o inGiven order and only entry keyValue(null) { toBe("a") }
        nKeyMap = nKeyMap contains o inGiven order and only entry keyValue(null) { toBe("a") }
        nValueMap = nValueMap contains o inGiven order and only entry keyValue(1.2, null)
        nKeyValueMap = nKeyValueMap contains o inGiven order and only entry keyValue(null) { toBe("a") }
        nKeyValueMap = nKeyValueMap contains o inGiven order and only entry keyValue(null, null)
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only entry keyValue(null) { toBe("a") }
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only entry keyValue(null, null)
        starMap = starMap contains o inGiven order and only entry keyValue(null) { toBe("a") }
        starMap = starMap contains o inGiven order and only entry keyValue(null, null)

        nKeyMap = nKeyMap contains o inGiven order and only the keyValues(keyValue(null) { toBe("a") })
        nKeyValueMap = nKeyValueMap contains o inGiven order and only the keyValues(keyValue(null) { toBe("a") })
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only the keyValues(keyValue(null) { toBe("a") })
        starMap = starMap contains o inGiven order and only the keyValues(keyValue(null) { toBe("a") })

        nKeyMap = nKeyMap contains o inGiven order and only the keyValues(keyValue(null) { toBe("a") })
        nValueMap = nValueMap contains o inGiven order and only the keyValues(keyValue(1, null), keyValue(1) { toBe("a") })
        nKeyValueMap = nKeyValueMap contains o inGiven order and only the keyValues(
            keyValue(null) { toBe("a") },
            keyValue(null, null),
            keyValue(1, null)
        )
        ronKeyValueMap = ronKeyValueMap contains o inGiven order and only the keyValues(
            keyValue(null) { toBe("a") },
            keyValue(null, null),
            keyValue(1, null)
        )
        starMap = starMap contains o inGiven order and only the keyValues(
            keyValue(null) { toBe("a") },
            keyValue(null, null),
            keyValue(1, null)
        )
    }
}
