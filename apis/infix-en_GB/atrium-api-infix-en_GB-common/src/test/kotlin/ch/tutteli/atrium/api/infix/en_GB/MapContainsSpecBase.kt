package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Pairs
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyValues
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithValueCreator
import ch.tutteli.atrium.api.infix.en_GB.entriesOf
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import kotlin.reflect.KFunction2

abstract class MapContainsSpecBase : WithAsciiReporter() {
    private val containsProp: KFunction2<Expect<Map<*, *>>, o, *> = Expect<Map<*, *>>::contains
    protected val contains = containsProp.name
    protected val filler = o::class.simpleName

    //@formatter:off
    protected val inAnyOrder = "${MapLikeContains.EntryPointStep<String, Int, List<Int>, NoOpSearchBehaviour>::inAny.name} ${order::class.simpleName}"
    protected val inOrder = "${MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, NoOpSearchBehaviour>::inGiven.name} ${order::class.simpleName}"
    protected val butOnly = "${MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::but.name} ${only::class.simpleName}"
    protected val andOnly = "${MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InOrderSearchBehaviour>::and.name} ${only::class.simpleName}"

    private val keyValuePairF: KFunction2<MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>, Pair<String,Int>, Expect<Map<String, Int>>> =
        MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::entry
    protected val keyValuePair = keyValuePairF.name

    private val keyValuePairsF: KFunction2<MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>, Pairs<String,Int>, Expect<Map<String, Int>>> =
        MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::the
    protected val keyValuePairs = "${keyValuePairsF.name} pairs"

    private val keyValueF: KFunction2<MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>, KeyWithValueCreator<String,Int>, Expect<Map<String, Int>>> =
        MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::entry
    protected val keyValue = keyValueF.name

    private val keyValuesF: KFunction2<MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>, KeyValues<String,Int>, Expect<Map<String, Int>>> =
        MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::the
    protected val keyValues = "${keyValuesF.name} keyValues"

    protected val entriesOf = MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::entriesOf.name
    //@formatter:on


    //TODO 0.15.0: check if list is complete
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nullableKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nullableValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nullableKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var readOnlyNullableKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

        map contains (1 to "a")
        map contains pairs(1 to "a", 2 to "b")
        map contains keyValue(1) {}
        map contains all(
            keyValue(1) {},
            keyValue(2) {})
        map contains pairs(1.0 to StringBuilder("a"))
        map contains pairs(12f to "a", 2L to StringBuilder("b"))
        map contains keyValue(1) {}
        map contains all(
            keyValue(1) {},
            keyValue(2) {})
        subMap contains (1 to "a")
        subMap contains pairs(1 to "a", 2 to "b")
        subMap contains keyValue(1) {}
        subMap contains all(
            keyValue(1) {},
            keyValue(2) {})
        subMap contains (1.0 to StringBuilder("a"))
        subMap contains pairs(12f to "a", 2L to StringBuilder("b"))
        subMap contains keyValue(1) {}
        subMap contains all(
            keyValue(1) {},
            keyValue(2) {})

        nullableKeyMap contains (1 to "a")
        nullableKeyMap contains pairs(1 to "a", 2 to "b")
        nullableKeyMap contains keyValue(1) {}
        nullableKeyMap contains all(
            keyValue(1) {},
            keyValue(2) {})
        nullableKeyMap contains (null to "a")
        nullableKeyMap contains pairs(null to "a", null to "b")
        nullableKeyMap contains pairs(null to "a", 2 to "b")
        nullableKeyMap contains (keyValue(null) {})
        nullableKeyMap contains all(
            keyValue(null) {},
            keyValue(null) {})
        nullableKeyMap contains all(
            keyValue(null) {},
            keyValue(2) {})

        nullableValueMap contains (1 to "a")
        nullableValueMap contains pairs(1 to "a", 2 to "b")
        nullableValueMap contains keyValue(1) {}
        nullableValueMap contains all(
            keyValue(1) {},
            keyValue(2) {})
        nullableValueMap contains (1 to null)
        nullableValueMap contains pairs(1 to null, 2 to null)
        nullableValueMap contains pairs(1 to null, 2 to "a")
        nullableValueMap contains (keyValue(1, null))
        nullableValueMap contains all(
            keyValue(1, null),
            keyValue(2, null)
        )
        nullableValueMap contains all(
            keyValue(1, null),
            keyValue(2) {})
        nullableKeyValueMap contains (1 to "a")
        nullableKeyValueMap contains pairs(1 to "a", 2 to "b")
        nullableKeyValueMap contains keyValue(1) {}
        nullableKeyValueMap contains all(
            keyValue(1) {},
            keyValue(2) {})

        nullableKeyValueMap contains (null to "a")
        nullableKeyValueMap contains pairs(null to "a", null to "b")
        nullableKeyValueMap contains pairs(null to "a", 2 to "b")
        nullableKeyValueMap contains (keyValue(null) {})
        nullableKeyValueMap contains all(
            keyValue(null) {},
            keyValue(null) {})
        nullableKeyValueMap contains all(
            keyValue(null) {},
            keyValue(2) {})

        nullableKeyValueMap contains (1 to null)
        nullableKeyValueMap contains pairs(1 to null, 2 to null)
        nullableKeyValueMap contains pairs(1 to null, 2 to "a")
        nullableKeyValueMap contains (keyValue(1, null))
        nullableKeyValueMap contains all(
            keyValue(1, null),
            keyValue(2, null)
        )
        nullableKeyValueMap contains all(
            keyValue(1, null),
            keyValue(2) {})

        nullableKeyValueMap contains (null to null)
        nullableKeyValueMap contains pairs(null to null, null to null)
        nullableKeyValueMap contains pairs(1 to null, null to "a")
        nullableKeyValueMap contains (keyValue(null, null))
        nullableKeyValueMap contains all(
            keyValue(null, null),
            keyValue(null, null)
        )
        nullableKeyValueMap contains all(
            keyValue(1, null),
            keyValue(null) {})

        readOnlyNullableKeyValueMap contains (1 to "a")
        readOnlyNullableKeyValueMap contains pairs(1 to "a", 2 to "b")
        readOnlyNullableKeyValueMap contains keyValue(1) {}
        readOnlyNullableKeyValueMap contains all(
            keyValue(1) {},
            keyValue(2) {})

        readOnlyNullableKeyValueMap contains (null to "a")
        readOnlyNullableKeyValueMap contains pairs(null to "a", null to "b")
        readOnlyNullableKeyValueMap contains pairs(null to "a", 2 to "b")
        readOnlyNullableKeyValueMap contains (keyValue(null) {})
        readOnlyNullableKeyValueMap contains all(
            keyValue(null) {},
            keyValue(null) {})
        readOnlyNullableKeyValueMap contains all(
            keyValue(null) {},
            keyValue(2) {})

        readOnlyNullableKeyValueMap contains (1 to null)
        readOnlyNullableKeyValueMap contains pairs(1 to null, 2 to null)
        readOnlyNullableKeyValueMap contains pairs(1 to null, 2 to "a")
        readOnlyNullableKeyValueMap contains (keyValue(1, null))
        readOnlyNullableKeyValueMap contains all(
            keyValue(1, null),
            keyValue(2, null)
        )
        readOnlyNullableKeyValueMap contains all(
            keyValue(1, null),
            keyValue(2) {})

        readOnlyNullableKeyValueMap contains (null to null)
        readOnlyNullableKeyValueMap contains pairs(null to null, null to null)
        readOnlyNullableKeyValueMap contains pairs(1 to null, null to "a")
        readOnlyNullableKeyValueMap contains (keyValue(null, null))
        readOnlyNullableKeyValueMap contains all(
            keyValue(null, null),
            keyValue(null, null)
        )
        readOnlyNullableKeyValueMap contains all(
            keyValue(1, null),
            keyValue(null) {})

        readOnlyNullableKeyValueMap contains (1 to "a")
        readOnlyNullableKeyValueMap contains pairs(1 to "a", 2 to "b")
        readOnlyNullableKeyValueMap contains keyValue(1) {}
        readOnlyNullableKeyValueMap contains all(
            keyValue(1) {},
            keyValue(2) {})

        starMap contains (null to "a")
        starMap contains pairs(null to "a", null to "b")
        starMap contains pairs(null to "a", 2 to "b")
        starMap contains (keyValue(null) {})
        starMap contains all(
            keyValue(null) {},
            keyValue(null) {})
        starMap contains all(
            keyValue(null) {},
            keyValue(2) {})

        starMap contains (1 to null)
        starMap contains pairs(1 to null, 2 to null)
        starMap contains pairs(1 to null, 2 to "a")
        starMap contains (keyValue(1, null))
        starMap contains all(
            keyValue(1, null),
            keyValue(2, null)
        )
        starMap contains all(
            keyValue(1, null),
            keyValue(2) {})

        starMap contains (null to null)
        starMap contains pairs(null to null, null to null)
        starMap contains pairs(1 to null, null to "a")
        starMap contains (keyValue(null, null))
        starMap contains all(
            keyValue(null, null),
            keyValue(null, null)
        )
        starMap contains all(
            keyValue(1, null),
            keyValue(null) {})
    }
}
