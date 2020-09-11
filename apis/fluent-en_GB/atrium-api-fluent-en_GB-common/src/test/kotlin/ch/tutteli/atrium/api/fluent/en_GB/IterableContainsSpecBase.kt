package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction4
import kotlin.reflect.KProperty

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Expect<Iterable<*>>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Expect<Iterable<*>>::containsNot
    protected val containsNot = containsNotProp.name

    //@formatter:off
    protected val atLeast = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerStep<*, *, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = IterableLikeContains.EntryPointStep<Int, List<Int>, NoOpSearchBehaviour>::inAnyOrder.name
    protected val inAnyOrderValues = IterableLikeContains.CheckerStep<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::values.name
    protected val inAnyOrderEntries = IterableLikeContains.CheckerStep<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::entries.name
    protected val inAnyOrderElementsOf = IterableLikeContains.CheckerStep<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::elementsOf.name
    protected val inAnyOrderOnlyValues = IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::values.name
    protected val inAnyOrderOnlyEntries = IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::entries.name
    protected val inAnyOrderOnlyElementsOf = IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::elementsOf.name
    protected val inOrder = IterableLikeContains.EntryPointStep<Int, List<Int>, NoOpSearchBehaviour>::inOrder.name
    protected val only = IterableLikeContains.EntryPointStep<Int, List<Int>, InAnyOrderSearchBehaviour>::only.name
    protected val inOrderOnlyValues = IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::values.name
    protected val inOrderOnlyEntries = IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::entries.name
    protected val inOrderElementsOf = IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::elementsOf.name
    protected val grouped = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlySearchBehaviour>::grouped.name
    protected val within = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlyGroupedSearchBehaviour>::within.name
    private val withinInAnyOrderFun: KFunction4<IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Group<Int>, Group<Int>, Array<out Group<Int>>, Expect<Iterable<Int>>> =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAnyOrder
    protected val withinInAnyOrder = withinInAnyOrderFun.name
    //@formatter:on

    @Suppress("unused")
    private fun ambiguityTest() {
        val list: Expect<List<Number>> = notImplemented()
        val nullableList: Expect<Set<Number?>> = notImplemented()
        val subList: Expect<ArrayList<out Number>> = notImplemented()
        val star: Expect<Collection<*>> = notImplemented()
        val any: Expect<Collection<Any>> = notImplemented()

        list.contains(1)
        list.contains(1f)
        list.contains(1, 2f)
        list.contains {}
        list.contains({}, {})
        list.containsNot(1)
        list.containsNot(1f)
        list.containsNot(1, 2f)
        list.containsNot.entry {}
        list.containsNot.entries({}, {})

        subList.contains(1)
        subList.contains(1f)
        subList.contains(1, 2f)
        subList.contains {}
        subList.contains({}, {})
        subList.containsNot(1)
        subList.containsNot(1f)
        subList.containsNot(1, 2f)
        subList.containsNot.entry {}
        subList.containsNot.entries({}, {})

        nullableList.contains(1)
        nullableList.contains(1f)
        nullableList.contains(1, 2f)
        nullableList.contains {}
        nullableList.contains({}, {})
        nullableList.containsNot(1)
        nullableList.containsNot(1f)
        nullableList.containsNot(1, 2f)
        nullableList.containsNot.entry {}
        nullableList.containsNot.entries({}, {})
        nullableList.contains(null)
        nullableList.contains({}, null)
        nullableList.contains(null, {})
        nullableList.contains(null, null)
        nullableList.containsNot(null)
        nullableList.containsNot.entries({}, null)
        nullableList.containsNot.entries(null, {})
        nullableList.containsNot.entries(null, null)

        star.contains(1)
        star.contains(1f)
        star.contains(1, 2f)
        star.contains {}
        star.contains({}, {})
        star.containsNot(1)
        star.containsNot(1f)
        star.containsNot(1, 2f)
        star.containsNot.entry {}
        star.containsNot.entries({}, {})
        star.contains(null)
        star.contains({}, null)
        star.contains(null, {})
        star.contains(null, null)
        star.containsNot(null)
        star.containsNot.entries({}, null)
        star.containsNot.entries(null, {})
        star.containsNot.entries(null, null)

        list.containsExactly(1)
        list.containsExactly(1, 2f)
        list.containsExactly {}
        list.containsExactly({}, {})
        subList.containsExactly(1)
        subList.containsExactly(1, 2f)
        subList.containsExactly {}
        subList.containsExactly({}, {})
        nullableList.containsExactly(1)
        nullableList.containsExactly(1, 1f)
        nullableList.containsExactly {}
        nullableList.containsExactly(null)
        nullableList.containsExactly({}, null)
        nullableList.containsExactly({}, {})
        nullableList.containsExactly(null, {})

        list.contains.inAnyOrder.atLeast(1).value(1)
        list.contains.inAnyOrder.atLeast(1).values(2, 1f)
        list.contains.inAnyOrder.atLeast(1).entry {}
        list.contains.inAnyOrder.atLeast(1).entries({}, {})
        list.contains.inAnyOrder.atLeast(1).elementsOf(listOf(1, 2f))
        subList.contains.inAnyOrder.atLeast(1).value(1)
        subList.contains.inAnyOrder.atLeast(1).values(2, 1)
        subList.contains.inAnyOrder.atLeast(1).entry {}
        subList.contains.inAnyOrder.atLeast(1).entries({}, {})
        subList.contains.inAnyOrder.atLeast(1).elementsOf(listOf(1, 2f))
        nullableList.contains.inAnyOrder.atLeast(1).value(1)
        nullableList.contains.inAnyOrder.atLeast(1).values(2, 1f)
        nullableList.contains.inAnyOrder.atLeast(1).entry {}
        nullableList.contains.inAnyOrder.atLeast(1).entries({}, {})
        nullableList.contains.inAnyOrder.atLeast(1).elementsOf(listOf(1, 2f))
        nullableList.contains.inAnyOrder.atLeast(1).value(null)
        nullableList.contains.inAnyOrder.atLeast(1).values(null, 1)
        nullableList.contains.inAnyOrder.atLeast(1).values(2f, null)
        nullableList.contains.inAnyOrder.atLeast(1).values(null, null)
        nullableList.contains.inAnyOrder.atLeast(1).entry(null)
        nullableList.contains.inAnyOrder.atLeast(1).entries(null, {})
        nullableList.contains.inAnyOrder.atLeast(1).entries({}, null)
        nullableList.contains.inAnyOrder.atLeast(1).entries(null, null)
        star.contains.inAnyOrder.atLeast(1).value(1)
        star.contains.inAnyOrder.atLeast(1).values(2, 1f)
        star.contains.inAnyOrder.atLeast(1).entry {}
        star.contains.inAnyOrder.atLeast(1).entries({}, {})
        star.contains.inAnyOrder.atLeast(1).elementsOf(listOf(1, 2f))
        star.contains.inAnyOrder.atLeast(1).value(null)
        star.contains.inAnyOrder.atLeast(1).values(null, 1)
        star.contains.inAnyOrder.atLeast(1).values(2, null)
        star.contains.inAnyOrder.atLeast(1).values(null, null)
        star.contains.inAnyOrder.atLeast(1).entry(null)
        star.contains.inAnyOrder.atLeast(1).entries(null, {})
        star.contains.inAnyOrder.atLeast(1).entries({}, null)
        star.contains.inAnyOrder.atLeast(1).entries(null, null)

        list.contains.inAnyOrder.only.value(1)
        list.contains.inAnyOrder.only.values(2, 1)
        list.contains.inAnyOrder.only.entry {}
        list.contains.inAnyOrder.only.entries({}, {})
        list.contains.inAnyOrder.only.elementsOf(listOf(1, 2f))
        subList.contains.inAnyOrder.only.value(1)
        subList.contains.inAnyOrder.only.values(2, 1)
        subList.contains.inAnyOrder.only.entry {}
        subList.contains.inAnyOrder.only.entries({}, {})
        subList.contains.inAnyOrder.only.elementsOf(listOf(1, 2f))
        nullableList.contains.inAnyOrder.only.value(1)
        nullableList.contains.inAnyOrder.only.values(2, 1f)
        nullableList.contains.inAnyOrder.only.entry {}
        nullableList.contains.inAnyOrder.only.entries({}, {})
        nullableList.contains.inAnyOrder.only.elementsOf(listOf(1, 2f))
        nullableList.contains.inAnyOrder.only.value(null)
        nullableList.contains.inAnyOrder.only.values(null, 1)
        nullableList.contains.inAnyOrder.only.values(2f, null)
        nullableList.contains.inAnyOrder.only.values(null, null)
        nullableList.contains.inAnyOrder.only.entry(null)
        nullableList.contains.inAnyOrder.only.entries(null, {})
        nullableList.contains.inAnyOrder.only.entries({}, null)
        nullableList.contains.inAnyOrder.only.entries(null, null)
        star.contains.inAnyOrder.only.value(1)
        star.contains.inAnyOrder.only.values(2, 1f)
        star.contains.inAnyOrder.only.entry {}
        star.contains.inAnyOrder.only.entries({}, {})
        star.contains.inAnyOrder.only.elementsOf(listOf(1, 2f))
        star.contains.inAnyOrder.only.value(null)
        star.contains.inAnyOrder.only.values(null, 1)
        star.contains.inAnyOrder.only.values(2f, null)
        star.contains.inAnyOrder.only.values(null, null)
        star.contains.inAnyOrder.only.entry(null)
        star.contains.inAnyOrder.only.entries(null, {})
        star.contains.inAnyOrder.only.entries({}, null)
        star.contains.inAnyOrder.only.entries(null, null)

        list.contains.inOrder.only.value(1)
        list.contains.inOrder.only.values(2, 1)
        list.contains.inOrder.only.entry {}
        list.contains.inOrder.only.entries({}, {})
        list.contains.inOrder.only.elementsOf(listOf(1, 2f))
        subList.contains.inOrder.only.value(1)
        subList.contains.inOrder.only.values(2, 1f)
        subList.contains.inOrder.only.entry {}
        subList.contains.inOrder.only.entries({}, {})
        subList.contains.inOrder.only.elementsOf(listOf(1, 2f))
        nullableList.contains.inOrder.only.value(1)
        nullableList.contains.inOrder.only.values(2, 1)
        nullableList.contains.inOrder.only.entry {}
        nullableList.contains.inOrder.only.entries({}, {})
        nullableList.contains.inOrder.only.elementsOf(listOf(1, 2f))
        nullableList.contains.inOrder.only.value(null)
        nullableList.contains.inOrder.only.values(null, 1)
        nullableList.contains.inOrder.only.values(2f, null)
        nullableList.contains.inOrder.only.values(null, null)
        nullableList.contains.inOrder.only.entry(null)
        nullableList.contains.inOrder.only.entries(null, {})
        nullableList.contains.inOrder.only.entries({}, null)
        nullableList.contains.inOrder.only.entries(null, null)
        star.contains.inOrder.only.value(1)
        star.contains.inOrder.only.values(2, 1f)
        star.contains.inOrder.only.entry {}
        star.contains.inOrder.only.entries({}, {})
        star.contains.inOrder.only.elementsOf(listOf(1, 2f))
        star.contains.inOrder.only.value(null)
        star.contains.inOrder.only.values(null, 1)
        star.contains.inOrder.only.values(2f, null)
        star.contains.inOrder.only.values(null, null)
        star.contains.inOrder.only.entry(null)
        star.contains.inOrder.only.entries(null, {})
        star.contains.inOrder.only.entries({}, null)
        star.contains.inOrder.only.entries(null, null)
        star.contains.inOrder.only.entries(null, null)

        list.contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Values(1f),
            //TODO check if this is resolved correctly with kotlin 1.4 otherwise report an issue
            Values<Number>(1f, 1)
        )
        subList.contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Values(1f),
            Values<Number>(1f, 1)
        )
        nullableList.contains.inOrder.only.grouped.within.inAnyOrder(
            Value(null),
            Values(null),
            Values(null, 2),
            Values(1, null),
            Values(null, null)
        )
        star.contains.inOrder.only.grouped.within.inAnyOrder(
            Value(null),
            Values(null),
            Values(null, 2),
            Values(1, null),
            Values(null, null)
        )
        any.contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Values(2),
            Values(1f, 2),
            Values(1, 1),
            Values("je", 'a')
        )

        list.contains.inOrder.only.grouped.within.inAnyOrder(
            Entry {},
            Entries({}),
            Entries({}, {})
        )
        subList.contains.inOrder.only.grouped.within.inAnyOrder(
            Entry {},
            Entries({}),
            Entries({}, {})
        )
        nullableList.contains.inOrder.only.grouped.within.inAnyOrder(
            Entry(null),
            Entries(null),
            Entries(null, {}),
            Entries({}, null),
            Entries(null, null)
        )
        //TODO check if this is resolved correctly with kotlin 1.4 otherwise report an issue
        star.contains.inOrder.only.grouped.within.inAnyOrder<Any, Collection<Any?>>(
            Entry(null),
            Entries(null),
            Entries(null, {}),
            Entries({}, null),
            Entries(null, null)
        )
        any.contains.inOrder.only.grouped.within.inAnyOrder(
            Entry(null),
            Entries(null),
            Entries(null, {}),
            Entries({}, null),
            Entries(null, null)
        )
    }
}
