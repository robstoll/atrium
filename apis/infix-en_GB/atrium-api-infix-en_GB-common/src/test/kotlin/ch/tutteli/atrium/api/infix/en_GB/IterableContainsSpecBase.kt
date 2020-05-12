package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.Order
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
    private val Values = "values"
    private val Entries = "entries"

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
    protected val containsNot = "${containsNotProp.name} $Values"

    @Suppress("unused")
    private fun ambiguityTest() {
        val list: Expect<List<Number>> = notImplemented()
        val nullableList: Expect<Set<Number?>> = notImplemented()
        val subList: Expect<ArrayList<out Number>> = notImplemented()
        val star: Expect<Collection<*>> = notImplemented()

        list contains 1
        list contains 1f
        list contains values(1, 2f)
        list contains {}
        list contains entries({}, {})
        list containsNot 1
        list containsNot 1f
        list containsNot values(1, 2f)
        list containsNot o entry {}
        list containsNot o the entries({}, {})

        subList contains 1
        subList contains 1f; subList contains values(1, 2f)
        subList contains {}
        subList contains entries({}, {})
        subList containsNot 1
        subList containsNot 1f
        subList containsNot values(1, 2f)
        subList containsNot o entry {}
        subList containsNot o the entries({}, {})

        nullableList contains 1
        nullableList contains 1f
        nullableList contains values(1, 2f)
        nullableList contains {}
        nullableList contains entries({}, {})
        nullableList containsNot 1
        nullableList containsNot 1f
        nullableList containsNot values(1, 2f)
        nullableList containsNot o entry {}
        nullableList containsNot o the entries({}, {})
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        nullableList contains null as Number?
        nullableList contains entries(null, {})
        nullableList contains entries({}, null)
        nullableList contains entries(null, null)
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        nullableList containsNot null as Number?
        nullableList containsNot o the entries(null, {})
        nullableList containsNot o the entries({}, null)
        nullableList containsNot o the entries(null, null)

        star contains 1
        star contains 1f
        star contains values(1, 2f)
        star contains {}
        star contains entries({}, {})
        star containsNot 1
        star containsNot 1f
        star containsNot values(1, 2f)
        star containsNot o entry {}
        star containsNot o the entries({}, {})
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        star contains (null as Number?)
        star contains entries(null, {})
        star contains entries({}, null)
        star contains entries(null, null)
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        star containsNot (null as Number?)
        star containsNot o the entries(null, {})
        star containsNot o the entries({}, null)
        star containsNot o the entries(null, null)

        list containsExactly 1
        list containsExactly values(1, 2f)
        list containsExactly {}
        list containsExactly entries({}, {})

        subList containsExactly 1
        subList containsExactly values(1, 2f)
        subList containsExactly {}
        subList containsExactly entries({}, {})

        nullableList containsExactly 1
        nullableList containsExactly values(1, 2f)
        nullableList containsExactly {}
        nullableList containsExactly entries({}, {})
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        nullableList containsExactly (null as (Expect<Number>.() -> Unit)?)
        nullableList containsExactly entries({}, null)
        nullableList containsExactly entries(null, {})
        nullableList containsExactly entries(null, null)

        star containsExactly 1
        star containsExactly values(1, 2f)
        star containsExactly {}
        star containsExactly entries({}, {})
        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        star containsExactly (null as (Expect<Number>.() -> Unit)?)
        star containsExactly entries({}, null)
        star containsExactly entries(null, {})
        star containsExactly entries(null, null)

        list contains o inAny order atLeast 1 value 1
        list contains o inAny order atLeast 1 the values(2, 1)
        list contains o inAny order atLeast 1 entry {}
        list contains o inAny order atLeast 1 the entries({}, {})
        list contains o inAny order atLeast 1 elementsOf listOf(1, 2)
        subList contains o inAny order atLeast 1 value 1
        subList contains o inAny order atLeast 1 the values(2, 1)
        subList contains o inAny order atLeast 1 entry {}
        subList contains o inAny order atLeast 1 the entries({}, {})
        subList contains o inAny order atLeast 1 elementsOf listOf(1, 2)
        nullableList contains o inAny order atLeast 1 value 1
        nullableList contains o inAny order atLeast 1 the values(2, 1)
        nullableList contains o inAny order atLeast 1 entry {}
        nullableList contains o inAny order atLeast 1 the entries({}, {})
        nullableList contains o inAny order atLeast 1 elementsOf listOf(1, 2)
        nullableList contains o inAny order atLeast 1 value null
        nullableList contains o inAny order atLeast 1 the values(null, 1)
        nullableList contains o inAny order atLeast 1 the values(2, null)
        nullableList contains o inAny order atLeast 1 the values(null, null)
        nullableList contains o inAny order atLeast 1 entry null
        nullableList contains o inAny order atLeast 1 the entries(null, {})
        nullableList contains o inAny order atLeast 1 the entries({}, null)
        nullableList contains o inAny order atLeast 1 the entries(null, null)
        star contains o inAny order atLeast 1 value 1
        star contains o inAny order atLeast 1 the values(2, 1)
        star contains o inAny order atLeast 1 entry {}
        star contains o inAny order atLeast 1 the entries({}, {})
        star contains o inAny order atLeast 1 elementsOf listOf(1, 2)
        star contains o inAny order atLeast 1 value null
        star contains o inAny order atLeast 1 the values(null, 1)
        star contains o inAny order atLeast 1 the values(2, null)
        star contains o inAny order atLeast 1 the values(null, null)
        star contains o inAny order atLeast 1 entry null
        star contains o inAny order atLeast 1 the entries(null, {})
        star contains o inAny order atLeast 1 the entries({}, null)
        star contains o inAny order atLeast 1 the entries(null, null)

        list contains o inAny order but only value 1
        list contains o inAny order but only the values(2, 1)
        list contains o inAny order but only entry {}
        list contains o inAny order but only the entries({}, {})
        list contains o inAny order but only elementsOf listOf(1, 2)
        subList contains o inAny order but only value 1
        subList contains o inAny order but only the values(2, 1)
        subList contains o inAny order but only entry {}
        subList contains o inAny order but only the entries({}, {})
        subList contains o inAny order but only elementsOf listOf(1, 2)
        nullableList contains o inAny order but only value 1
        nullableList contains o inAny order but only the values(2, 1)
        nullableList contains o inAny order but only entry {}
        nullableList contains o inAny order but only the entries({}, {})
        nullableList contains o inAny order but only elementsOf listOf(1, 2)
        nullableList contains o inAny order but only value null
        nullableList contains o inAny order but only the values(null, 1)
        nullableList contains o inAny order but only the values(2, null)
        nullableList contains o inAny order but only the values(null, null)
        nullableList contains o inAny order but only entry null
        nullableList contains o inAny order but only the entries(null, {})
        nullableList contains o inAny order but only the entries({}, null)
        nullableList contains o inAny order but only the entries(null, null)
        star contains o inAny order but only value 1
        star contains o inAny order but only the values(2, 1)
        star contains o inAny order but only entry {}
        star contains o inAny order but only the entries({}, {})
        star contains o inAny order but only elementsOf listOf(1, 2)
        star contains o inAny order but only value null
        star contains o inAny order but only the values(null, 1)
        star contains o inAny order but only the values(2, null)
        star contains o inAny order but only the values(null, null)
        star contains o inAny order but only entry null
        star contains o inAny order but only the entries(null, {})
        star contains o inAny order but only the entries({}, null)
        star contains o inAny order but only the entries(null, null)

        list contains o inGiven order and only value 1
        list contains o inGiven order and only the values(2, 1)
        list contains o inGiven order and only entry {}
        list contains o inGiven order and only the entries({}, {})
        list contains o inGiven order and only elementsOf listOf(1, 2)
        subList contains o inGiven order and only value 1
        subList contains o inGiven order and only the values(2, 1)
        subList contains o inGiven order and only entry {}
        subList contains o inGiven order and only the entries({}, {})
        subList contains o inGiven order and only elementsOf listOf(1, 2)
        nullableList contains o inGiven order and only value 1
        nullableList contains o inGiven order and only the values(2, 1)
        nullableList contains o inGiven order and only entry {}
        nullableList contains o inGiven order and only the entries({}, {})
        nullableList contains o inGiven order and only elementsOf listOf(1, 2)
        nullableList contains o inGiven order and only value null
        nullableList contains o inGiven order and only the values(null, 1)
        nullableList contains o inGiven order and only the values(2, null)
        nullableList contains o inGiven order and only the values(null, null)
        nullableList contains o inGiven order and only entry null
        nullableList contains o inGiven order and only the entries(null, {})
        nullableList contains o inGiven order and only the entries({}, null)
        nullableList contains o inGiven order and only the entries(null, null)
        star contains o inGiven order and only value 1
        star contains o inGiven order and only the values(2, 1)
        star contains o inGiven order and only entry {}
        star contains o inGiven order and only the entries({}, {})
        star contains o inGiven order and only elementsOf listOf(1, 2)
        star contains o inGiven order and only value null
        star contains o inGiven order and only the values(null, 1)
        star contains o inGiven order and only the values(2, null)
        star contains o inGiven order and only the values(null, null)
        star contains o inGiven order and only entry null
        star contains o inGiven order and only the entries(null, {})
        star contains o inGiven order and only the entries({}, null)
        star contains o inGiven order and only the entries(null, null)

        list contains o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1f),
            values(1f, 1)
        )
        subList contains o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1f),
            values(1f, 1)
        )
        nullableList contains o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null),
            values(null, 2),
            values(1, null),
            values(null, null)
        )
        star contains o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null),
            values(null, 2),
            values(1, null),
            values(null, null)
        )

        list contains o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}),
            entries({}, {})
        )
        subList contains o inGiven order and only grouped entries within group inAny order(
            entry {},
            entries({}),
            entries({}, {})
        )
        nullableList contains o inGiven order and only grouped entries within group inAny order(
            entry(null),
            entries(null),
            entries(null, {}),
            entries({}, null),
            entries(null, null)
        )
        star contains o inGiven order and only grouped entries within group inAny order(
            entry(null),
            entries(null),
            entries(null, {}),
            entries({}, null),
            entries(null, null)
        )
    }
}
