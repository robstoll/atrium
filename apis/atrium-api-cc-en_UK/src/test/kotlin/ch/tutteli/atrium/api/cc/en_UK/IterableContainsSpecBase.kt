package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders.AtLeastCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import kotlin.reflect.KProperty

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<Iterable<Double>>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<Iterable<Double>>::containsNot
    protected val containsNot = containsNotProp.name
    protected val atLeast = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = IterableContains.Builder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inAnyOrder.name
    protected val inAnyOrderEntries = IterableContains.CheckerBuilder<Int, Iterable<Int>, IterableContainsInAnyOrderSearchBehaviour>::entries.name
    protected val inAnyOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>::values.name
    protected val inAnyOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>::entries.name
    protected val inOrder = IterableContains.Builder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inOrder.name
    protected val only = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::only.name
    protected val inOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInOrderOnlySearchBehaviour>::values.name
    protected val inOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInOrderOnlySearchBehaviour>::entries.name
}
