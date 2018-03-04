package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.AtLeastCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import kotlin.reflect.KFunction2

abstract class IterableContainsSpecBase {
    private val values = Values::class.simpleName
    private val entries = Entries::class.simpleName

    private val containsNotFun: KFunction2<Assert<Iterable<Double>>, Double, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsNot
    protected val toContain = "${Assert<Iterable<Double>>::to.name} ${contain::class.simpleName}"
    protected val containsNot = "${Assert<Iterable<Double>>::notTo.name} ${contain::class.simpleName}"
    protected val containsNotValues = "${containsNotFun.name} ${Values::class.simpleName}"
    protected val atLeast = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerBuilder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = "${IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inAny.name} ${order::class.simpleName}"
    private val theInAnyOrderFun: KFunction2<IterableContains.CheckerBuilder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>, Values<Double>, Assert<Iterable<Double>>>
        = IterableContains.CheckerBuilder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::the
    private val theInAnyOrder = theInAnyOrderFun.name
    protected val inAnyOrderEntries = "$theInAnyOrder $entries"

    private val theInAnyOrderOnlyFun: KFunction2<IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>, Values<Double>, Assert<Iterable<Double>>>
        = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>::the
    private val theInAnyOrderOnly = theInAnyOrderOnlyFun.name
    protected val inAnyOrderOnlyValues = "$theInAnyOrderOnly $values"
    protected val inAnyOrderOnlyEntries = "$theInAnyOrderOnly $entries"
    protected val inOrder = "${IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inGiven.name} ${order::class.simpleName}"
    protected val butOnly = "${IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::but.name} ${only::class.simpleName}"
    private val theInOrderOnlyFun: KFunction2<IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>, Values<Double>, Assert<Iterable<Double>>>
        = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::the
    private val theInOrderOnly = theInOrderOnlyFun.name
    protected val inOrderOnlyValues = "$theInOrderOnly $values"
    protected val inOrderOnlyEntries = "$theInOrderOnly $entries"
}
