package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import kotlin.reflect.KProperty

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<Iterable<Double>>::enthaelt
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<Iterable<Double>>::enthaeltNicht
    protected val containsNot = containsNotProp.name
    protected val atLeast = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::zumindest.name
    protected val butAtMost = AtLeastCheckerOption<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::aberHoechstens.name
    protected val exactly = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::genau.name
    protected val atMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::hoechstens.name
    protected val notOrAtMost = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::nichtOderHoechstens.name
    protected val inAnyOrder = IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inBeliebigerReihenfolge.name
    protected val inAnyOrderEntries = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::eintraege.name
    protected val inAnyOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>::werte.name
    protected val inAnyOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderOnlySearchBehaviour>::eintraege.name
    protected val inOrder = IterableContains.Builder<Double, Iterable<Double>, NoOpSearchBehaviour>::inGegebenerReihenfolge.name
    protected val only = IterableContains.Builder<Double, Iterable<Double>, InAnyOrderSearchBehaviour>::nur.name
    protected val inOrderOnlyValues = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::werte.name
    protected val inOrderOnlyEntries = IterableContains.Builder<Double, Iterable<Double>, InOrderOnlySearchBehaviour>::eintraege.name
}
