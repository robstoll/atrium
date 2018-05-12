package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import kotlin.reflect.KProperty
import kotlin.reflect.KFunction4

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<Iterable<Double>>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<Iterable<Double>>::containsNot
    protected val containsNot = containsNotProp.name
    protected val atLeast = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerOption<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inAnyOrder.name
    protected val inAnyOrderEntries = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::entries.name
    protected val inAnyOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>::values.name
    protected val inAnyOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>::entries.name
    protected val inOrder = IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inOrder.name
    protected val only = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::only.name
    protected val inOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::values.name
    protected val inOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::entries.name
    protected val grouped = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::grouped.name
    protected val within = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlyGroupedSearchBehaviour>::within.name
    private val withinInAnyOrderFun : KFunction4<IterableContains.Builder<Double, Iterable<Double>, InOrderOnlyGroupedWithinSearchBehaviour>, GroupWithoutNullableEntries<Double>, GroupWithoutNullableEntries<Double>, Array<out GroupWithoutNullableEntries<Double>>, AssertionPlant<Iterable<Double>>>
        = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlyGroupedWithinSearchBehaviour>::inAnyOrder
    protected val withinInAnyOrder = withinInAnyOrderFun.name
}
