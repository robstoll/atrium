package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import kotlin.reflect.KProperty
import kotlin.reflect.KFunction2
import kotlin.reflect.KFunction3

abstract class MapContainsSpecBase {
    private val containsProp: KProperty<*> = Expect<Map<*, *>>::contains
    protected val contains = containsProp.name
    protected val containsEntriesOf = Expect<Map<*, *>>::containsEntriesOf
    protected val containsOnlyEntriesOf = Expect<Map<*, *>>::containsOnlyEntriesOf

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
}
