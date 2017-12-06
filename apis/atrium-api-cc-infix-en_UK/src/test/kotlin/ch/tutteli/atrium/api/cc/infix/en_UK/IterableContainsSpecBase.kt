package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders.IterableContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KFunction2

abstract class IterableContainsSpecBase {
    private val containsNotFun: KFunction2<IAssertionPlant<Iterable<Double>>, Double, IAssertionPlant<Iterable<Double>>> = IAssertionPlant<Iterable<Double>>::containsNot
    protected val toContain = "${IAssertionPlant<Iterable<Double>>::to.name} ${contain::class.simpleName}"
    protected val containsNotValues = "${containsNotFun.name} ${Values::class.simpleName}"
    protected val atLeast = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = IterableContainsAtLeastCheckerBuilder<Double, Iterable<Double>>::butAtMost.name
    protected val exactly = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = "${IterableContainsBuilder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inAny.name} ${order::class.simpleName}"
    protected val inOrder = "${IterableContainsBuilder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inGiven.name} ${order::class.simpleName}"
    protected val butOnly = "${IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::but.name} ${only::class.simpleName}"
    private val theFun: KFunction2<IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>, Values<Double>, IAssertionPlant<Iterable<Double>>>
        = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>::the
    protected val the = theFun.name
    private val values = Values::class.simpleName
    protected val inAnyOrderOnlyValues = "$the $values"
    protected val inOrderOnlyValues = "$the $values"
}
