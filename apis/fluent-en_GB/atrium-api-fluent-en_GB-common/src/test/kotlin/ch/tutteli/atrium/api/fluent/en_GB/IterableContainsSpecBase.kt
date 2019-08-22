package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.verbs.internal.expect
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

        expect(list).contains(1)
        expect(list).contains(1f)
        expect(list).contains(1, 2)
        expect(list).contains(1, 2f)
        expect(list).contains {}
        expect(list).contains({}, {})
        expect(subList).contains(1)
        expect(subList).contains(1f)
        expect(subList).contains(1, 2)
        expect(subList).contains(1, 2f)
        expect(subList).contains {}
        expect(subList).contains({}, {})
        expect(nullableList).contains(1)
        expect(nullableList).contains(1f)
        expect(nullableList).contains(1, 2)
        expect(nullableList).contains(1, 2f)
        expect(nullableList).contains {}
        expect(nullableList).contains(null)
        expect(nullableList).contains({}, null)
        expect(nullableList).contains({}, {})
        expect(nullableList).contains(null, {})

        expect(list).containsExactly(1)
        expect(list).containsExactly(1, 2f)
        expect(list).containsExactly {}
        expect(list).containsExactly({}, {})
        expect(subList).containsExactly(1)
        expect(subList).containsExactly(1, 2f)
        expect(subList).containsExactly {}
        expect(subList).containsExactly({}, {})
        expect(nullableList).containsExactly(1)
        expect(nullableList).containsExactly(1, 1)
        expect(nullableList).containsExactly {}
        expect(nullableList).containsExactly(null)
        expect(nullableList).containsExactly({}, null)
        expect(nullableList).containsExactly({}, {})
        expect(nullableList).containsExactly(null, {})

        expect(list).contains.inAnyOrder.atLeast(1).value(1)
        expect(list).contains.inAnyOrder.atLeast(1).value(null)
        expect(list).contains.inAnyOrder.atLeast(1).entry {}
        expect(list).contains.inAnyOrder.atLeast(1).entry(null)
        expect(subList).contains.inAnyOrder.atLeast(1).value(1)
        expect(subList).contains.inAnyOrder.atLeast(1).value(null)
        expect(subList).contains.inAnyOrder.atLeast(1).entry {}
        expect(subList).contains.inAnyOrder.atLeast(1).entry(null)

        expect(list).contains.inAnyOrder.only.value(1)
        expect(list).contains.inAnyOrder.only.value(null)
        expect(list).contains.inAnyOrder.only.entry {}
        expect(list).contains.inAnyOrder.only.entry(null)
        expect(subList).contains.inAnyOrder.only.value(1)
        expect(subList).contains.inAnyOrder.only.value(null)
        expect(subList).contains.inAnyOrder.only.entry {}
        expect(subList).contains.inAnyOrder.only.entry(null)

        expect(list).contains.inOrder.only.value(1)
        expect(list).contains.inOrder.only.value(null)
        expect(list).contains.inOrder.only.entry {}
        expect(list).contains.inOrder.only.entry(null)
        expect(subList).contains.inOrder.only.value(1)
        expect(subList).contains.inOrder.only.value(null)
        expect(subList).contains.inOrder.only.entry {}
        expect(subList).contains.inOrder.only.entry(null)


        expect(list).contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Value(null),
            Values(1f),
            Values(null),
            Values(1f, 1),
            Values(1, null),
            Values(null, null)
        )
        expect(subList).contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Value(null),
            Values(1f),
            Values(null),
            Values(1f, 1),
            Values(1, null),
            Values(null, null)
        )

        expect(list).contains.inOrder.only.grouped.within.inAnyOrder(
            Entry {},
            Entry(null),
            Entries({}),
            Entries(null),
            Entries({}, {}),
            Entries({}, null),
            Entries(null, null)
        )
        expect(subList).contains.inOrder.only.grouped.within.inAnyOrder(
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
