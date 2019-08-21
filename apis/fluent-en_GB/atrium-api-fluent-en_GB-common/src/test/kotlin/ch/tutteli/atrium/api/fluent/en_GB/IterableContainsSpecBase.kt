package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.verbs.internal.assert
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
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
    protected val inAnyOrderOnlyValues = IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::values.name
    protected val inAnyOrderOnlyEntries = IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::entries.name
    protected val inOrder = IterableContains.Builder<*, *, NoOpSearchBehaviour>::inOrder.name
    protected val only = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::only.name
    protected val inOrderOnlyValues = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::values.name
    protected val inOrderOnlyEntries = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::entries.name
    protected val grouped = IterableContains.Builder<*, *, InOrderOnlySearchBehaviour>::grouped.name
    protected val within = IterableContains.Builder<*, *, InOrderOnlyGroupedSearchBehaviour>::within.name
    private val withinInAnyOrderFun: KFunction4<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Group<Int>, Group<Int>, Array<out Group<Int>>, Expect<Iterable<Int>>> =
        IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAnyOrder
    protected val withinInAnyOrder = withinInAnyOrderFun.name
    //@formatter:on

    @Suppress("unused")
    private fun ambiguityTest() {
        val list: List<Number> = notImplemented()
        val nullableList: List<Number?> = notImplemented()
        val subList: ArrayList<out Number> = notImplemented()

        assert(list).contains(1)
        assert(list).contains(1f)
        assert(list).contains(1, 2)
        assert(list).contains(1, 2f)
        assert(list).contains {}
        assert(list).contains({}, {})
        assert(subList).contains(1)
        assert(subList).contains(1f)
        assert(subList).contains(1, 2)
        assert(subList).contains(1, 2f)
        assert(subList).contains {}
        assert(subList).contains({}, {})
        assert(nullableList).contains(1)
        assert(nullableList).contains(1f)
        assert(nullableList).contains(1, 2)
        assert(nullableList).contains(1, 2f)
        assert(nullableList).contains {}
        assert(nullableList).contains(null)
        assert(nullableList).contains({}, null)
        assert(nullableList).contains({}, {})
        assert(nullableList).contains(null, {})

        assert(list).containsExactly(1)
        assert(list).containsExactly(1, 2f)
        assert(list).containsExactly {}
        assert(list).containsExactly({}, {})
        assert(subList).containsExactly(1)
        assert(subList).containsExactly(1, 2f)
        assert(subList).containsExactly {}
        assert(subList).containsExactly({}, {})
        assert(nullableList).containsExactly(1)
        assert(nullableList).containsExactly(1, 1)
        assert(nullableList).containsExactly {}
        assert(nullableList).containsExactly(null)
        assert(nullableList).containsExactly({}, null)
        assert(nullableList).containsExactly({}, {})
        assert(nullableList).containsExactly(null, {})

        assert(list).contains.inAnyOrder.atLeast(1).value(1)
        assert(list).contains.inAnyOrder.atLeast(1).value(null)
        assert(list).contains.inAnyOrder.atLeast(1).entry {}
        assert(list).contains.inAnyOrder.atLeast(1).entry(null)
        assert(subList).contains.inAnyOrder.atLeast(1).value(1)
        assert(subList).contains.inAnyOrder.atLeast(1).value(null)
        assert(subList).contains.inAnyOrder.atLeast(1).entry {}
        assert(subList).contains.inAnyOrder.atLeast(1).entry(null)

        assert(list).contains.inAnyOrder.only.value(1)
        assert(list).contains.inAnyOrder.only.value(null)
        assert(list).contains.inAnyOrder.only.entry {}
        assert(list).contains.inAnyOrder.only.entry(null)
        assert(subList).contains.inAnyOrder.only.value(1)
        assert(subList).contains.inAnyOrder.only.value(null)
        assert(subList).contains.inAnyOrder.only.entry {}
        assert(subList).contains.inAnyOrder.only.entry(null)

        assert(list).contains.inOrder.only.value(1)
        assert(list).contains.inOrder.only.value(null)
        assert(list).contains.inOrder.only.entry {}
        assert(list).contains.inOrder.only.entry(null)
        assert(subList).contains.inOrder.only.value(1)
        assert(subList).contains.inOrder.only.value(null)
        assert(subList).contains.inOrder.only.entry {}
        assert(subList).contains.inOrder.only.entry(null)


        assert(list).contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Value(null),
            Values(1f),
            Values(null),
            Values(1f, 1),
            Values(1, null),
            Values(null, null)
        )
        assert(subList).contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Value(null),
            Values(1f),
            Values(null),
            Values(1f, 1),
            Values(1, null),
            Values(null, null)
        )

        assert(list).contains.inOrder.only.grouped.within.inAnyOrder(
            Entry {},
            Entry(null),
            Entries({}),
            Entries(null),
            Entries({}, {}),
            Entries({}, null),
            Entries(null, null)
        )
        assert(subList).contains.inOrder.only.grouped.within.inAnyOrder(
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
