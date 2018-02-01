package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders.IterableContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsCheckerBuilder
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
    protected val atLeast = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = IterableContainsAtLeastCheckerBuilder<Double, Iterable<Double>>::butAtMost.name
    protected val exactly = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inAnyOrder.name
    protected val inAnyOrderEntries = IterableContainsCheckerBuilder<Int, Iterable<Int>, IterableContainsInAnyOrderSearchBehaviour>::entries.name
    protected val inAnyOrderOnlyValues = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>::values.name
    protected val inAnyOrderOnlyEntries = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>::entries.name
    protected val inOrder = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inOrder.name
    protected val only = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::only.name
    protected val inOrderOnlyValues = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInOrderOnlySearchBehaviour>::values.name
    protected val inOrderOnlyEntries = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInOrderOnlySearchBehaviour>::entries.name
}
