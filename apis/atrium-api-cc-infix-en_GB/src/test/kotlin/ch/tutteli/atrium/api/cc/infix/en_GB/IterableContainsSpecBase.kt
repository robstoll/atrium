package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.entries
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.group
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import kotlin.reflect.KFunction2

abstract class IterableContainsSpecBase {
    private val Values = Values::class.simpleName
    private val Entries = Entries::class.simpleName

    private val containsNotFun: KFunction2<Assert<Iterable<Int>>, Int, Assert<Iterable<Int>>> = Assert<Iterable<Int>>::containsNot
    protected val toContain = "${Assert<Iterable<*>>::to.name} ${contain::class.simpleName}"
    protected val containsNot = "${Assert<Iterable<*>>::notTo.name} ${contain::class.simpleName}"
    protected val containsNotValues = "${containsNotFun.name} $Values"
    protected val atLeast = IterableContains.Builder<*, Iterable<*>, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerOption<*, Iterable<*>, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableContains.Builder<*, Iterable<*>, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableContains.Builder<*, Iterable<*>, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableContains.Builder<*, Iterable<*>, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = "${IterableContains.Builder<*, Iterable<*>, NoOpSearchBehaviour>::inAny.name} ${order::class.simpleName}"
    private val theInAnyOrderFun: KFunction2<IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>, Values<Int>, Assert<Iterable<Int>>>
        = IterableContains.CheckerOption<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::the
    private val theInAnyOrder = theInAnyOrderFun.name
    protected val inAnyOrderEntries = "$theInAnyOrder $Entries"
    protected val inAnyOrderValues = "$theInAnyOrder $Values"

    private val theInAnyOrderOnlyFun: KFunction2<IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>, Values<Int>, Assert<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::the
    private val theInAnyOrderOnly = theInAnyOrderOnlyFun.name
    protected val inAnyOrderOnlyValues = "$theInAnyOrderOnly $Values"
    protected val inAnyOrderOnlyEntries = "$theInAnyOrderOnly $Entries"
    protected val inOrder = "${IterableContains.Builder<*, Iterable<*>, NoOpSearchBehaviour>::inGiven.name} ${order::class.simpleName}"
    protected val butOnly = "${IterableContains.Builder<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::but.name} ${only::class.simpleName}"
    private val theInOrderOnlyFun: KFunction2<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>, Values<Int>, Assert<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::the
    private val theInOrderOnly = theInOrderOnlyFun.name
    protected val inOrderOnlyValues = "$theInOrderOnly $Values"
    protected val inOrderOnlyEntries = "$theInOrderOnly $Entries"
    protected val groupedEntries = "${IterableContains.Builder<*, Iterable<*>, InOrderOnlySearchBehaviour>::grouped.name} ${entries::class.simpleName}"
    protected val withinGroup = "${IterableContains.Builder<*, Iterable<*>, InOrderOnlyGroupedSearchBehaviour>::within.name} ${group::class.simpleName}"
    private val withinInAnyOrderFun : KFunction2<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Order<Int, GroupWithoutNullableEntries<Int>>, AssertionPlant<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAny
    protected val withinInAnyOrder = "${withinInAnyOrderFun.name} ${order::class.simpleName}"
}
