package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction4
import kotlin.reflect.KProperty

abstract class IterableContainsSpecBase {
    private val containsProp: KProperty<*> = Expect<Iterable<*>>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Expect<Iterable<*>>::containsNot
    protected val containsNot = containsNotProp.name
    protected val value = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlySearchBehaviour>::value.name
    protected val values = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlySearchBehaviour>::values.name
    protected val entry = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlySearchBehaviour>::entry.name
    protected val entries = IterableLikeContains.EntryPointStep<*, *, InOrderOnlySearchBehaviour>::entries.name
    protected val elementsOf =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::elementsOf.name

    //@formatter:off
    protected val atLeast = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerStep<*, *, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableLikeContains.EntryPointStep<*, *, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = IterableLikeContains.EntryPointStep<Int, List<Int>, NoOpSearchBehaviour>::inAnyOrder.name
    protected val inOrder = IterableLikeContains.EntryPointStep<Int, List<Int>, NoOpSearchBehaviour>::inOrder.name
    protected val only = IterableLikeContains.EntryPointStep<Int, List<Int>, InAnyOrderSearchBehaviour>::only.name
    protected val grouped = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlySearchBehaviour>::grouped.name
    protected val within = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlyGroupedSearchBehaviour>::within.name
    private val withinInAnyOrderFun: KFunction4<IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Group<Int>, Group<Int>, Array<out Group<Int>>, Expect<Iterable<Int>>> =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAnyOrder
    protected val withinInAnyOrder = withinInAnyOrderFun.name
    //@formatter:on
}
