package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import kotlin.reflect.KFunction4
import kotlin.reflect.KProperty

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<Iterable<*>>::enthaelt
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<Iterable<*>>::enthaeltNicht
    protected val containsNot = containsNotProp.name
    protected val atLeast = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::zumindest.name
    protected val butAtMost = AtLeastCheckerOption<*, *, InAnyOrderSearchBehaviour>::aberHoechstens.name
    protected val exactly = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::genau.name
    protected val atMost = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::hoechstens.name
    protected val notOrAtMost = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::nichtOderHoechstens.name
    protected val inAnyOrder = IterableContains.Builder<*, *, NoOpSearchBehaviour>::inBeliebigerReihenfolge.name
    protected val inAnyOrderValues = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::werte.name
    protected val inAnyOrderEntries = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::eintraege.name
    protected val inAnyOrderOnlyValues = IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::werte.name
    protected val inAnyOrderOnlyEntries = IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::eintraege.name
    protected val inOrder = IterableContains.Builder<*, *, NoOpSearchBehaviour>::inGegebenerReihenfolge.name
    protected val only = IterableContains.Builder<*, *, InAnyOrderSearchBehaviour>::nur.name
    protected val inOrderOnlyValues = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::werte.name
    protected val inOrderOnlyEntries = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::eintraege.name
    protected val grouped = IterableContains.Builder<*, *, InOrderOnlySearchBehaviour>::gruppiert.name
    protected val within = IterableContains.Builder<*, *, InOrderOnlyGroupedSearchBehaviour>::innerhalb.name
    private val withinInAnyOrderFun : KFunction4<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, GroupWithoutNullableEntries<Int>, GroupWithoutNullableEntries<Int>, Array<out GroupWithoutNullableEntries<Int>>, AssertionPlant<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inBeliebigerReihenfolge
    protected val withinInAnyOrder = withinInAnyOrderFun.name
}
