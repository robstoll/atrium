package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders.AtLeastCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import kotlin.reflect.KProperty

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<Iterable<Double>>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<Iterable<Double>>::containsNot
    protected val containsNot = containsNotProp.name
    protected val atLeast = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerBuilder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inAnyOrder.name
    protected val inAnyOrderEntries = IterableContains.CheckerBuilder<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::entries.name
    protected val inAnyOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>::values.name
    protected val inAnyOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>::entries.name
    protected val inOrder = IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inOrder.name
    protected val only = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::only.name
    protected val inOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::values.name
    protected val inOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::entries.name
}
