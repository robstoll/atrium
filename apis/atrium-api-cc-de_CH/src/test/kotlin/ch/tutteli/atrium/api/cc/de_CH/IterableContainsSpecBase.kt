package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.AtLeastCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import kotlin.reflect.KProperty

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<Iterable<Double>>::enthaelt
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<Iterable<Double>>::enthaeltNicht
    protected val containsNot = containsNotProp.name
    protected val atLeast = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::zumindest.name
    protected val butAtMost = AtLeastCheckerBuilder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::aberHoechstens.name
    protected val exactly = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::genau.name
    protected val atMost = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::hoechstens.name
    protected val notOrAtMost = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::nichtOderHoechstens.name
    protected val inAnyOrder = IterableContains.Builder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inBeliebigerReihenfolge.name
    protected val inAnyOrderEntries = IterableContains.CheckerBuilder<Int, Iterable<Int>, IterableContainsInAnyOrderSearchBehaviour>::eintraege.name
    protected val inAnyOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>::werte.name
    protected val inAnyOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderOnlySearchBehaviour>::eintraege.name
    protected val inOrder = IterableContains.Builder<Double, Iterable<Double>, IterableContainsNoOpSearchBehaviour>::inGegebenerReihenfolge.name
    protected val only = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInAnyOrderSearchBehaviour>::nur.name
    protected val inOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInOrderOnlySearchBehaviour>::werte.name
    protected val inOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, IterableContainsInOrderOnlySearchBehaviour>::eintraege.name
}
