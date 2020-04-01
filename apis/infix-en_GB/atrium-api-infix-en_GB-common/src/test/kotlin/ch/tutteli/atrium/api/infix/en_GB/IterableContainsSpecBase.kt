package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import kotlin.reflect.KFunction2

abstract class IterableContainsSpecBase : WithAsciiReporter() {
    protected val Values = Values::class.simpleName
    private val Entries = Entries::class.simpleName

    //@formatter:off
    protected val atLeast = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerOption<*, *, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = "${IterableContains.Builder<*, Iterable<*>, NoOpSearchBehaviour>::inAny.name} ${order::class.simpleName}"
    protected val butOnly = "${IterableContains.Builder<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::but.name} ${only::class.simpleName}"
    private val theInAnyOrderFun: KFunction2<IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>, Values<Int>, Expect<Iterable<Int>>>
        = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::the
    private val theInAnyOrder = theInAnyOrderFun.name
    protected val inAnyOrderEntries = "$theInAnyOrder $Entries"
    protected val inAnyOrderValues = "$theInAnyOrder $Values"
    protected val inAnyOrderElementsOf = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::elementsOf.name

    private val theInAnyOrderOnlyFun: KFunction2<IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>, Values<Int>, Expect<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::the
    private val theInAnyOrderOnly = theInAnyOrderOnlyFun.name
    protected val inAnyOrderOnlyValues = "$theInAnyOrderOnly $Values"
    protected val inAnyOrderOnlyEntries = "$theInAnyOrderOnly $Entries"
    protected val inAnyOrderOnlyElementsOf = IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::elementsOf.name

    protected val inOrder = "${IterableContains.Builder<*, Iterable<*>, NoOpSearchBehaviour>::inGiven.name} ${order::class.simpleName}"
    protected val andOnly = "${IterableContains.Builder<Int, Iterable<Int>, InOrderSearchBehaviour>::and.name} ${only::class.simpleName}"
    private val theInOrderOnlyFun: KFunction2<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>, Values<Int>, Expect<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::the
    private val theInOrderOnly = theInOrderOnlyFun.name
    protected val inOrderOnlyValues = "$theInOrderOnly $Values"
    protected val inOrderOnlyEntries = "$theInOrderOnly $Entries"
    protected val inOrderElementsOf = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::elementsOf.name
    protected val grouped = "${IterableContains.Builder<*, *, InOrderOnlySearchBehaviour>::grouped.name} ${entries::class.simpleName}"
    protected val within = IterableContains.Builder<*, *, InOrderOnlyGroupedSearchBehaviour>::within.name
    private val withinInAnyOrderFun : KFunction2<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Order<Int, Group<Int>>, Expect<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAny
    protected val withinInAnyOrder = withinInAnyOrderFun.name
    //@formatter:on

    protected val filler = o::class.simpleName
    private val containsProp: KFunction2<Expect<Iterable<Int>>, o, IterableContains.Builder<Int, Iterable<Int>, NoOpSearchBehaviour>> =
        Expect<Iterable<Int>>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KFunction2<Expect<Iterable<Int>>, o, NotCheckerOption<Int, Iterable<Int>, NotSearchBehaviour>> =
        Expect<Iterable<Int>>::containsNot
    protected val containsNot = "${containsNotProp.name} $filler $inAnyOrderValues"

    @Suppress("unused")
    private fun ambiguityTest() {
        val list: Expect<List<Number>> = notImplemented()
        val nullableList: Expect<Set<Number?>> = notImplemented()
        val subList: Expect<ArrayList<out Number>> = notImplemented()
        val star: Expect<Collection<*>> = notImplemented()

        list contains 1
        list contains 1f
        list contains Values(1, 2f)
        list contains {}
        list contains Entries({}, {})
        list containsNot 1
        list containsNot 1f
        list containsNot Values(1, 2f)
        list containsNot o entry {}
        list containsNot o the Entries({}, {})

        subList contains 1
        subList contains 1f
        subList contains Values(1, 2f)
        subList contains {}
        subList contains Entries({}, {})
        subList containsNot 1
        subList containsNot 1f
        subList containsNot Values(1, 2f)
        subList containsNot o entry {}
        subList containsNot o the Entries({}, {})

        nullableList contains 1
        nullableList contains 1f
        nullableList contains Values(1, 2f)
        nullableList contains {}
        nullableList contains Entries({}, {})
        nullableList containsNot 1
        nullableList containsNot 1f
        nullableList containsNot Values(1, 2f)
        nullableList containsNot o entry {}
        nullableList containsNot o the Entries({}, {})
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        nullableList contains null as Number?
        nullableList contains Entries(null, {})
        nullableList contains Entries({}, null)
        nullableList contains Entries(null, null)
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        nullableList containsNot null as Number?
        nullableList containsNot o the Entries(null, {})
        nullableList containsNot o the Entries({}, null)
        nullableList containsNot o the Entries(null, null)

        star contains 1
        star contains 1f
        star contains Values(1, 2f)
        star contains {}
        star contains Entries({}, {})
        star containsNot 1
        star containsNot 1f
        star containsNot Values(1, 2f)
        star containsNot o entry {}
        star containsNot o the Entries({}, {})
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        star contains (null as Number?)
        star contains Entries(null, {})
        star contains Entries({}, null)
        star contains Entries(null, null)
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        star containsNot (null as Number?)
        star containsNot o the Entries(null, {})
        star containsNot o the Entries({}, null)
        star containsNot o the Entries(null, null)

        list containsExactly 1
        list containsExactly Values(1, 2f)
        list containsExactly {}
        list containsExactly Entries({}, {})

        subList containsExactly 1
        subList containsExactly Values(1, 2f)
        subList containsExactly {}
        subList containsExactly Entries({}, {})

        nullableList containsExactly 1
        nullableList containsExactly Values(1, 2f)
        nullableList containsExactly {}
        nullableList containsExactly Entries({}, {})
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        nullableList containsExactly (null as (Expect<Number>.() -> Unit)?)
        nullableList containsExactly Entries({}, null)
        nullableList containsExactly Entries(null, {})
        nullableList containsExactly Entries(null, null)

        star containsExactly 1
        star containsExactly Values(1, 2f)
        star containsExactly {}
        star containsExactly Entries({}, {})
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        star containsExactly (null as (Expect<Number>.() -> Unit)?)
        star containsExactly Entries({}, null)
        star containsExactly Entries(null, {})
        star containsExactly Entries(null, null)

        list contains o inAny order atLeast 1 value 1
        list contains o inAny order atLeast 1 the Values(2, 1)
        list contains o inAny order atLeast 1 entry {}
        list contains o inAny order atLeast 1 the Entries({}, {})
        list contains o inAny order atLeast 1 elementsOf listOf(1, 2)
        subList contains o inAny order atLeast 1 value 1
        subList contains o inAny order atLeast 1 the Values(2, 1)
        subList contains o inAny order atLeast 1 entry {}
        subList contains o inAny order atLeast 1 the Entries({}, {})
        subList contains o inAny order atLeast 1 elementsOf listOf(1, 2)
        nullableList contains o inAny order atLeast 1 value 1
        nullableList contains o inAny order atLeast 1 the Values(2, 1)
        nullableList contains o inAny order atLeast 1 entry {}
        nullableList contains o inAny order atLeast 1 the Entries({}, {})
        nullableList contains o inAny order atLeast 1 elementsOf listOf(1, 2)
        nullableList contains o inAny order atLeast 1 value null
        nullableList contains o inAny order atLeast 1 the Values(null, 1)
        nullableList contains o inAny order atLeast 1 the Values(2, null)
        nullableList contains o inAny order atLeast 1 the Values(null, null)
        nullableList contains o inAny order atLeast 1 entry null
        nullableList contains o inAny order atLeast 1 the Entries(null, {})
        nullableList contains o inAny order atLeast 1 the Entries({}, null)
        nullableList contains o inAny order atLeast 1 the Entries(null, null)
        star contains o inAny order atLeast 1 value 1
        star contains o inAny order atLeast 1 the Values(2, 1)
        star contains o inAny order atLeast 1 entry {}
        star contains o inAny order atLeast 1 the Entries({}, {})
        star contains o inAny order atLeast 1 elementsOf listOf(1, 2)
        star contains o inAny order atLeast 1 value null
        star contains o inAny order atLeast 1 the Values(null, 1)
        star contains o inAny order atLeast 1 the Values(2, null)
        star contains o inAny order atLeast 1 the Values(null, null)
        star contains o inAny order atLeast 1 entry null
        star contains o inAny order atLeast 1 the Entries(null, {})
        star contains o inAny order atLeast 1 the Entries({}, null)
        star contains o inAny order atLeast 1 the Entries(null, null)

        list contains o inAny order but only value 1
        list contains o inAny order but only the Values(2, 1)
        list contains o inAny order but only entry {}
        list contains o inAny order but only the Entries({}, {})
        list contains o inAny order but only elementsOf listOf(1, 2)
        subList contains o inAny order but only value 1
        subList contains o inAny order but only the Values(2, 1)
        subList contains o inAny order but only entry {}
        subList contains o inAny order but only the Entries({}, {})
        subList contains o inAny order but only elementsOf listOf(1, 2)
        nullableList contains o inAny order but only value 1
        nullableList contains o inAny order but only the Values(2, 1)
        nullableList contains o inAny order but only entry {}
        nullableList contains o inAny order but only the Entries({}, {})
        nullableList contains o inAny order but only elementsOf listOf(1, 2)
        nullableList contains o inAny order but only value null
        nullableList contains o inAny order but only the Values(null, 1)
        nullableList contains o inAny order but only the Values(2, null)
        nullableList contains o inAny order but only the Values(null, null)
        nullableList contains o inAny order but only entry null
        nullableList contains o inAny order but only the Entries(null, {})
        nullableList contains o inAny order but only the Entries({}, null)
        nullableList contains o inAny order but only the Entries(null, null)
        star contains o inAny order but only value 1
        star contains o inAny order but only the Values(2, 1)
        star contains o inAny order but only entry {}
        star contains o inAny order but only the Entries({}, {})
        star contains o inAny order but only elementsOf listOf(1, 2)
        star contains o inAny order but only value null
        star contains o inAny order but only the Values(null, 1)
        star contains o inAny order but only the Values(2, null)
        star contains o inAny order but only the Values(null, null)
        star contains o inAny order but only entry null
        star contains o inAny order but only the Entries(null, {})
        star contains o inAny order but only the Entries({}, null)
        star contains o inAny order but only the Entries(null, null)

        list contains o inGiven order and only value 1
        list contains o inGiven order and only the Values(2, 1)
        list contains o inGiven order and only entry {}
        list contains o inGiven order and only the Entries({}, {})
        list contains o inGiven order and only elementsOf listOf(1, 2)
        subList contains o inGiven order and only value 1
        subList contains o inGiven order and only the Values(2, 1)
        subList contains o inGiven order and only entry {}
        subList contains o inGiven order and only the Entries({}, {})
        subList contains o inGiven order and only elementsOf listOf(1, 2)
        nullableList contains o inGiven order and only value 1
        nullableList contains o inGiven order and only the Values(2, 1)
        nullableList contains o inGiven order and only entry {}
        nullableList contains o inGiven order and only the Entries({}, {})
        nullableList contains o inGiven order and only elementsOf listOf(1, 2)
        nullableList contains o inGiven order and only value null
        nullableList contains o inGiven order and only the Values(null, 1)
        nullableList contains o inGiven order and only the Values(2, null)
        nullableList contains o inGiven order and only the Values(null, null)
        nullableList contains o inGiven order and only entry null
        nullableList contains o inGiven order and only the Entries(null, {})
        nullableList contains o inGiven order and only the Entries({}, null)
        nullableList contains o inGiven order and only the Entries(null, null)
        star contains o inGiven order and only value 1
        star contains o inGiven order and only the Values(2, 1)
        star contains o inGiven order and only entry {}
        star contains o inGiven order and only the Entries({}, {})
        star contains o inGiven order and only elementsOf listOf(1, 2)
        star contains o inGiven order and only value null
        star contains o inGiven order and only the Values(null, 1)
        star contains o inGiven order and only the Values(2, null)
        star contains o inGiven order and only the Values(null, null)
        star contains o inGiven order and only entry null
        star contains o inGiven order and only the Entries(null, {})
        star contains o inGiven order and only the Entries({}, null)
        star contains o inGiven order and only the Entries(null, null)

        list contains o inGiven order and only grouped entries within group inAny Order(
            Value(1),
            Values(1f),
            Values(1f, 1)
        )
        subList contains o inGiven order and only grouped entries within group inAny Order(
            Value(1),
            Values(1f),
            Values(1f, 1)
        )
        nullableList contains o inGiven order and only grouped entries within group inAny Order(
            Value(null),
            Values(null),
            Values(null, 2),
            Values(1, null),
            Values(null, null)
        )
        star contains o inGiven order and only grouped entries within group inAny Order(
            Value(null),
            Values(null),
            Values(null, 2),
            Values(1, null),
            Values(null, null)
        )

        list contains o inGiven order and only grouped entries within group inAny Order(
            Entry {},
            Entries({}),
            Entries({}, {})
        )
        subList contains o inGiven order and only grouped entries within group inAny Order(
            Entry {},
            Entries({}),
            Entries({}, {})
        )
        nullableList contains o inGiven order and only grouped entries within group inAny Order(
            Entry(null),
            Entries(null),
            Entries(null, {}),
            Entries({}, null),
            Entries(null, null)
        )
        star contains o inGiven order and only grouped entries within group inAny Order(
            Entry(null),
            Entries(null),
            Entries(null, {}),
            Entries({}, null),
            Entries(null, null)
        )
    }
}
