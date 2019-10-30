package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedWithinSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction4
import kotlin.reflect.KProperty

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Expect<Iterable<*>>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Expect<Iterable<*>>::containsNot
    protected val containsNot = containsNotProp.name

    //@formatter:off
    protected val atLeast = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerOption<*, *, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = IterableContains.Builder<*, *, NoOpSearchBehaviour>::inAnyOrder.name
    protected val inAnyOrderValues = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::values.name
    protected val inAnyOrderEntries = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::entries.name
    protected val inAnyOrderElementsOf = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::elementsOf.name
    protected val inAnyOrderOnlyValues = IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::values.name
    protected val inAnyOrderOnlyEntries = IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::entries.name
    protected val inOrder = IterableContains.Builder<*, *, NoOpSearchBehaviour>::inOrder.name
    protected val only = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::only.name
    protected val inOrderOnlyValues = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::values.name
    protected val inOrderOnlyEntries = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::entries.name
    protected val inOrderElementsOf = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::elementsOf.name
    protected val grouped = IterableContains.Builder<*, *, InOrderOnlySearchBehaviour>::grouped.name
    protected val within = IterableContains.Builder<*, *, InOrderOnlyGroupedSearchBehaviour>::within.name
    private val withinInAnyOrderFun: KFunction4<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Group<Int>, Group<Int>, Array<out Group<Int>>, Expect<Iterable<Int>>> =
        IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAnyOrder
    protected val withinInAnyOrder = withinInAnyOrderFun.name
    //@formatter:on

    @Suppress("unused")
    private fun ambiguityTest() {
        val list: Expect<List<Number>> = notImplemented()
        val nullableList: Expect<List<Number?>> = notImplemented()
        val subList: Expect<ArrayList<out Number>> = notImplemented()

        list.contains(1)
        list.contains(1f)
        list.contains(1, 2)
        list.contains(1, 2f)
        list.contains {}
        list.contains({}, {})
        subList.contains(1)
        subList.contains(1f)
        subList.contains(1, 2)
        subList.contains(1, 2f)
        subList.contains {}
        subList.contains({}, {})
        nullableList.contains(1)
        nullableList.contains(1f)
        nullableList.contains(1, 2)
        nullableList.contains(1, 2f)
        nullableList.contains {}
        nullableList.contains(null)
        nullableList.contains({}, null)
        nullableList.contains({}, {})
        nullableList.contains(null, {})

        list.containsExactly(1)
        list.containsExactly(1, 2f)
        list.containsExactly {}
        list.containsExactly({}, {})
        subList.containsExactly(1)
        subList.containsExactly(1, 2f)
        subList.containsExactly {}
        subList.containsExactly({}, {})
        nullableList.containsExactly(1)
        nullableList.containsExactly(1, 1)
        nullableList.containsExactly {}
        nullableList.containsExactly(null)
        nullableList.containsExactly({}, null)
        nullableList.containsExactly({}, {})
        nullableList.containsExactly(null, {})

        list.contains.inAnyOrder.atLeast(1).value(1)
        list.contains.inAnyOrder.atLeast(1).value(null)
        list.contains.inAnyOrder.atLeast(1).entry {}
        list.contains.inAnyOrder.atLeast(1).entry(null)
        subList.contains.inAnyOrder.atLeast(1).value(1)
        subList.contains.inAnyOrder.atLeast(1).value(null)
        subList.contains.inAnyOrder.atLeast(1).entry {}
        subList.contains.inAnyOrder.atLeast(1).entry(null)

        list.contains.inAnyOrder.only.value(1)
        list.contains.inAnyOrder.only.value(null)
        list.contains.inAnyOrder.only.entry {}
        list.contains.inAnyOrder.only.entry(null)
        subList.contains.inAnyOrder.only.value(1)
        subList.contains.inAnyOrder.only.value(null)
        subList.contains.inAnyOrder.only.entry {}
        subList.contains.inAnyOrder.only.entry(null)

        list.contains.inOrder.only.value(1)
        list.contains.inOrder.only.value(null)
        list.contains.inOrder.only.entry {}
        list.contains.inOrder.only.entry(null)
        subList.contains.inOrder.only.value(1)
        subList.contains.inOrder.only.value(null)
        subList.contains.inOrder.only.entry {}
        subList.contains.inOrder.only.entry(null)


        list.contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Value(null),
            Values(1f),
            Values(null),
            Values(1f, 1),
            Values(1, null),
            Values(null, null)
        )
        subList.contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Value(null),
            Values(1f),
            Values(null),
            Values(1f, 1),
            Values(1, null),
            Values(null, null)
        )

        list.contains.inOrder.only.grouped.within.inAnyOrder(
            Entry {},
            Entry(null),
            Entries({}),
            Entries(null),
            Entries({}, {}),
            Entries({}, null),
            Entries(null, null)
        )
        subList.contains.inOrder.only.grouped.within.inAnyOrder(
            Entry {},
            Entry(null),
            Entries({}),
            Entries(null),
            Entries({}, {}),
            Entries({}, null),
            Entries(null, null)
        )
    }
}
