package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KProperty
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

abstract class MapContainsSpecBase {
    private val containsProp: KProperty<*> = Expect<Map<*, *>>::contains
    protected val contains = containsProp.name

    //@formatter:off
    protected val inAnyOrder = MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, NoOpSearchBehaviour>::inAnyOrder.name

    private val keyValuePairF: KFunction2<MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>, Pair<String,Int>, Expect<Map<String, Int>>> =
        MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::entry
    protected val keyValuePair = keyValuePairF.name

    private val keyValuePairsF: KFunction3<MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>, Pair<String,Int>, Array<out Pair<String,Int>>, Expect<Map<String, Int>>> =
        MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::entries
    protected val keyValuePairs = keyValuePairsF.name

    private val keyValueF: KFunction2<MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>, KeyValue<String,Int>, Expect<Map<String, Int>>> =
        MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::entry
    protected val keyValue = keyValueF.name

    private val keyValuesF: KFunction3<MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>, KeyValue<String,Int>, Array<out KeyValue<String,Int>>, Expect<Map<String, Int>>> =
        MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::entries
    protected val keyValues = keyValuesF.name

    protected val entriesOf = MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::entriesOf.name

    protected val inOrder = MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, NoOpSearchBehaviour>::inOrder.name
    protected val only = MapLikeContains.EntryPointStep<String, Int, Map<String, Int>, InAnyOrderSearchBehaviour>::only.name
    //@formatter:on

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var map: Expect<Map<Number, CharSequence>> = notImplemented()
        var subMap: Expect<LinkedHashMap<out Number, String>> = notImplemented()
        var nullableKeyMap: Expect<Map<Number?, CharSequence>> = notImplemented()
        var nullableValueMap: Expect<Map<Number, CharSequence?>> = notImplemented()
        var nullableKeyValueMap: Expect<Map<Number?, CharSequence?>> = notImplemented()
        var readOnlyNullableKeyValueMap: Expect<Map<out Number?, CharSequence?>> = notImplemented()
        var starMap: Expect<Map<*, *>> = notImplemented()

        map.contains(1 to "a")
        map.contains(1 to "a", 2 to "b")
        map.contains(KeyValue(1) {})
        map.contains(KeyValue(1) {}, KeyValue(2) {})
        map.contains(1.0 to StringBuilder("a"))
        map.contains(12f to "a", 2L to StringBuilder("b"))
        map.contains(KeyValue(1) {})
        map.contains(KeyValue(1) {}, KeyValue(2) {})

        subMap.contains(1 to "a")
        subMap.contains(1 to "a", 2 to "b")
        subMap.contains(KeyValue(1) {})
        subMap.contains(KeyValue(1) {}, KeyValue(2) {})
        subMap.contains(1.0 to StringBuilder("a"))
        subMap.contains(12f to "a", 2L to StringBuilder("b"))
        subMap.contains(KeyValue(1) {})
        subMap.contains(KeyValue(1) {}, KeyValue(2) {})

        nullableKeyMap.contains(1 to "a")
        nullableKeyMap.contains(1 to "a", 2 to "b")
        nullableKeyMap.contains(KeyValue(1) {})
        nullableKeyMap.contains(KeyValue(1) {}, KeyValue(2) {})
        nullableKeyMap.contains(null to "a")
        nullableKeyMap.contains(null to "a", null to "b")
        nullableKeyMap.contains(null to "a", 2 to "b")
        nullableKeyMap.contains(KeyValue(null) {})
        nullableKeyMap.contains(KeyValue(null) {}, KeyValue(null) {})
        nullableKeyMap.contains(KeyValue(null) {}, KeyValue(2) {})

        nullableValueMap.contains(1 to "a")
        nullableValueMap.contains(1 to "a", 2 to "b")
        nullableValueMap.contains(KeyValue(1) {})
        nullableValueMap.contains(KeyValue(1) {}, KeyValue(2) {})
        nullableValueMap.contains(1 to null)
        nullableValueMap.contains(1 to null, 2 to null)
        nullableValueMap.contains(1 to null, 2 to "a")
        nullableValueMap.contains(KeyValue(1, null))
        nullableValueMap.contains(KeyValue(1, null), KeyValue(2, null))
        nullableValueMap.contains(KeyValue(1, null), KeyValue(2) {})

        nullableKeyValueMap.contains(1 to "a")
        nullableKeyValueMap.contains(1 to "a", 2 to "b")
        nullableKeyValueMap.contains(KeyValue(1) {})
        nullableKeyValueMap.contains(KeyValue(1) {}, KeyValue(2) {})

        nullableKeyValueMap.contains(null to "a")
        nullableKeyValueMap.contains(null to "a", null to "b")
        nullableKeyValueMap.contains(null to "a", 2 to "b")
        nullableKeyValueMap.contains(KeyValue(null) {})
        nullableKeyValueMap.contains(KeyValue(null) {}, KeyValue(null) {})
        nullableKeyValueMap.contains(KeyValue(null) {}, KeyValue(2) {})

        nullableKeyValueMap.contains(1 to null)
        nullableKeyValueMap.contains(1 to null, 2 to null)
        nullableKeyValueMap.contains(1 to null, 2 to "a")
        nullableKeyValueMap.contains(KeyValue(1, null))
        nullableKeyValueMap.contains(KeyValue(1, null), KeyValue(2, null))
        nullableKeyValueMap.contains(KeyValue(1, null), KeyValue(2) {})

        nullableKeyValueMap.contains(null to null)
        nullableKeyValueMap.contains(null to null, null to null)
        nullableKeyValueMap.contains(1 to null, null to "a")
        nullableKeyValueMap.contains(KeyValue(null, null))
        nullableKeyValueMap.contains(KeyValue(null, null), KeyValue(null, null))
        nullableKeyValueMap.contains(KeyValue(1, null), KeyValue(null) {})

        readOnlyNullableKeyValueMap.contains(1 to "a")
        readOnlyNullableKeyValueMap.contains(1 to "a", 2 to "b")
        readOnlyNullableKeyValueMap.contains(KeyValue(1) {})
        readOnlyNullableKeyValueMap.contains(KeyValue(1) {}, KeyValue(2) {})

        readOnlyNullableKeyValueMap.contains(null to "a")
        readOnlyNullableKeyValueMap.contains(null to "a", null to "b")
        readOnlyNullableKeyValueMap.contains(null to "a", 2 to "b")
        readOnlyNullableKeyValueMap.contains(KeyValue(null) {})
        readOnlyNullableKeyValueMap.contains(KeyValue(null) {}, KeyValue(null) {})
        readOnlyNullableKeyValueMap.contains(KeyValue(null) {}, KeyValue(2) {})

        readOnlyNullableKeyValueMap.contains(1 to null)
        readOnlyNullableKeyValueMap.contains(1 to null, 2 to null)
        readOnlyNullableKeyValueMap.contains(1 to null, 2 to "a")
        readOnlyNullableKeyValueMap.contains(KeyValue(1, null))
        readOnlyNullableKeyValueMap.contains(KeyValue(1, null), KeyValue(2, null))
        readOnlyNullableKeyValueMap.contains(KeyValue(1, null), KeyValue(2) {})

        readOnlyNullableKeyValueMap.contains(null to null)
        readOnlyNullableKeyValueMap.contains(null to null, null to null)
        readOnlyNullableKeyValueMap.contains(1 to null, null to "a")
        readOnlyNullableKeyValueMap.contains(KeyValue(null, null))
        readOnlyNullableKeyValueMap.contains(KeyValue(null, null), KeyValue(null, null))
        readOnlyNullableKeyValueMap.contains(KeyValue(1, null), KeyValue(null) {})

        readOnlyNullableKeyValueMap.contains(1 to "a")
        readOnlyNullableKeyValueMap.contains(1 to "a", 2 to "b")
        readOnlyNullableKeyValueMap.contains(KeyValue(1) {})
        readOnlyNullableKeyValueMap.contains(KeyValue(1) {}, KeyValue(2) {})

        starMap.contains(null to "a")
        starMap.contains(null to "a", null to "b")
        starMap.contains(null to "a", 2 to "b")
        starMap.contains(KeyValue(null) {})
        starMap.contains(KeyValue(null) {}, KeyValue(null) {})
        starMap.contains(KeyValue(null) {}, KeyValue(2) {})

        starMap.contains(1 to null)
        starMap.contains(1 to null, 2 to null)
        starMap.contains(1 to null, 2 to "a")
        starMap.contains(KeyValue(1, null))
        starMap.contains(KeyValue(1, null), KeyValue(2, null))
        starMap.contains(KeyValue(1, null), KeyValue(2) {})

        starMap.contains(null to null)
        starMap.contains(null to null, null to null)
        starMap.contains(1 to null, null to "a")
        starMap.contains(KeyValue(null, null))
        starMap.contains(KeyValue(null, null), KeyValue(null, null))
        starMap.contains(KeyValue(1, null), KeyValue(null) {})
    }
}
