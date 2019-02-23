package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
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
    private val withinInAnyOrderFun : KFunction4<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, GroupWithoutNullableEntries<Int>, GroupWithoutNullableEntries<Int>, Array<out GroupWithoutNullableEntries<Int>>, AssertionPlant<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAnyOrder
    protected val withinInAnyOrder = withinInAnyOrderFun.name
}
