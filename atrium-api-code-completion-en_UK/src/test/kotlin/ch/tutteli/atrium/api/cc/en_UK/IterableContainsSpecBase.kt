package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders.IterableContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInOrderOnlyDecorator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsNoOpDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KProperty

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = IAssertionPlant<Iterable<Double>>::contains
    protected val contains = containsProp.name
    protected val containsNot = IAssertionPlant<Iterable<Double>>::containsNot.name
    protected val atLeast = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderDecorator>::atLeast.name
    protected val butAtMost = IterableContainsAtLeastCheckerBuilder<Double, Iterable<Double>>::butAtMost.name
    protected val exactly = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderDecorator>::exactly.name
    protected val atMost = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderDecorator>::atMost.name
    protected val notOrAtMost = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderDecorator>::notOrAtMost.name
    protected val inAnyOrder = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsNoOpDecorator>::inAnyOrder.name
    protected val inOrder = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsNoOpDecorator>::inOrder.name
    protected val only = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderDecorator>::only.name
    protected val inAnyOrderOnlyValues = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlyDecorator>::values.name
    protected val inOrderOnlyValues = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInOrderOnlyDecorator>::values.name
}
