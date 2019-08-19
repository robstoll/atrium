@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.reflect.KProperty
import kotlin.reflect.KFunction4

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<Iterable<*>>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<Iterable<*>>::containsNot
    protected val containsNot = containsNotProp.name
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
    private val withinInAnyOrderFun : KFunction4<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Group<Int>, Group<Int>, Array<out Group<Int>>, AssertionPlant<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAnyOrder
    protected val withinInAnyOrder = withinInAnyOrderFun.name

    @Suppress("unused")
    private fun ambiguityTest() {
        assert(listOf(1)).contains(1)
        assert(listOf(1)).contains(1, 2)
        assert(listOf(1)).contains {}
        assert(listOf(1)).contains({}, {})
        assert(listOf(1 as Int?)).contains(1)
        assert(listOf(1 as Int?)).contains(1, 1)
        assert(listOf(1 as Int?)).contains {}
        assert(listOf(1 as Int?)).contains(null)
        assert(listOf(1 as Int?)).contains({}, null)
        assert(listOf(1 as Int?)).contains({}, {})
        assert(listOf(1 as Int?)).contains(null, {})

        assert(listOf(1)).containsExactly(1)
        assert(listOf(1)).containsExactly(1, 2)
        assert(listOf(1)).containsExactly {}
        assert(listOf(1)).containsExactly({}, {})
        assert(listOf(1 as Int?)).containsExactly(1)
        assert(listOf(1 as Int?)).containsExactly(1, 1)
        assert(listOf(1 as Int?)).containsExactly {}
        assert(listOf(1 as Int?)).containsExactly(null)
        assert(listOf(1 as Int?)).containsExactly({}, null)
        assert(listOf(1 as Int?)).containsExactly({}, {})
        assert(listOf(1 as Int?)).containsExactly(null, {})

        assert(listOf(1)).contains.inAnyOrder.atLeast(1).value(1)
        assert(listOf(1)).contains.inAnyOrder.atLeast(1).value(null)
        assert(listOf(1)).contains.inAnyOrder.atLeast(1).entry {}
        assert(listOf(1)).contains.inAnyOrder.atLeast(1).entry(null)

        assert(listOf(1)).contains.inAnyOrder.only.value( 1)
        assert(listOf(1)).contains.inAnyOrder.only.value( null)
        assert(listOf(1)).contains.inAnyOrder.only.entry {}
        assert(listOf(1)).contains.inAnyOrder.only.entry(null)

        assert(listOf(1)).contains.inOrder.only.value(1)
        assert(listOf(1)).contains.inOrder.only.value(null)
        assert(listOf(1)).contains.inOrder.only.entry {}
        assert(listOf(1)).contains.inOrder.only.entry(null)


        assert(listOf(1)).contains.inOrder.only.grouped.within.inAnyOrder(
            Value(1),
            Value(null),
            Values(1),
            Values(null),
            Values(1, 1),
            Values(1, null),
            Values(null, null)
        )

        assert(listOf(1)).contains.inOrder.only.grouped.within.inAnyOrder(
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
