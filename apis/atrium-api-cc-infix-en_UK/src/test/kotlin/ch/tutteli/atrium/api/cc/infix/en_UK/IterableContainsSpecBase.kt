package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.IterableContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import kotlin.reflect.KFunction2

abstract class IterableContainsSpecBase {
    private val values = Values::class.simpleName
    private val entries = Entries::class.simpleName

    private val containsNotFun: KFunction2<Assert<Iterable<Double>>, Double, Assert<Iterable<Double>>> = Assert<Iterable<Double>>::containsNot
    protected val toContain = "${Assert<Iterable<Double>>::to.name} ${contain::class.simpleName}"
    protected val containsNot = "${Assert<Iterable<Double>>::notTo.name} ${contain::class.simpleName}"
    protected val containsNotValues = "${containsNotFun.name} ${Values::class.simpleName}"
    protected val atLeast = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = IterableContainsAtLeastCheckerBuilder<Double, Iterable<Double>>::butAtMost.name
    protected val exactly = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = "${IterableContainsBuilder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inAny.name} ${order::class.simpleName}"
    private val theInAnyOrderFun: KFunction2<IterableContainsCheckerBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>, Values<Double>, Assert<Iterable<Double>>>
        = IterableContainsCheckerBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::the
    private val theInAnyOrder = theInAnyOrderFun.name
    protected val inAnyOrderEntries = "$theInAnyOrder $entries"

    private val theInAnyOrderOnlyFun: KFunction2<IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>, Values<Double>, Assert<Iterable<Double>>>
        = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>::the
    private val theInAnyOrderOnly = theInAnyOrderOnlyFun.name
    protected val inAnyOrderOnlyValues = "$theInAnyOrderOnly $values"
    protected val inAnyOrderOnlyEntries = "$theInAnyOrderOnly $entries"
    protected val inOrder = "${IterableContainsBuilder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inGiven.name} ${order::class.simpleName}"
    protected val butOnly = "${IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::but.name} ${only::class.simpleName}"
    private val theInOrderOnlyFun: KFunction2<IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInOrderOnlySearchBehaviour>, Values<Double>, Assert<Iterable<Double>>>
        = IterableContainsBuilder<Double, Iterable<Double>, IterableContainsInOrderOnlySearchBehaviour>::the
    private val theInOrderOnly = theInOrderOnlyFun.name
    protected val inOrderOnlyValues = "$theInOrderOnly $values"
    protected val inOrderOnlyEntries = "$theInOrderOnly $entries"
}
