package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Pairs
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyValues
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithValueCreator
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import kotlin.reflect.KFunction2

abstract class MapContainsSpecBase : WithAsciiReporter() {
    private val containsProp: KFunction2<Expect<Map<*, *>>, o, *> = Expect<Map<*, *>>::contains
    protected val contains = containsProp.name
    protected val filler = o::class.simpleName
    protected val containsEntriesOf =  Expect<Map<*, *>>::containsEntriesOf
    protected val containsOnlyEntriesOf =  Expect<Map<*, *>>::containsOnlyEntriesOf

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
}
