package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import kotlin.reflect.KFunction6
import kotlin.reflect.KProperty

abstract class IterableToContainSpecBase {
    private val toContainProp: KProperty<*> = Expect<Iterable<*>>::toContain
    protected val toContain = toContainProp.name
    private val notToContainProp: KProperty<*> = Expect<Iterable<*>>::notToContain
    protected val notToContain = notToContainProp.name
    protected val value = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlySearchBehaviour>::value.name
    protected val values = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlySearchBehaviour>::values.name
    protected val entry = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlySearchBehaviour>::entry.name
    protected val entries = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InOrderOnlySearchBehaviour>::entries.name
    protected val elementsOf =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::elementsOf.name

    //@formatter:off
    protected val atLeast = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder = IterableLikeContains.EntryPointStep<Int, List<Int>, NoOpSearchBehaviour>::inAnyOrder.name
    protected val inOrder = IterableLikeContains.EntryPointStep<Int, List<Int>, NoOpSearchBehaviour>::inOrder.name
    protected val only = IterableLikeContains.EntryPointStep<Int, List<Int>, InAnyOrderSearchBehaviour>::only.name
    protected val grouped = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlySearchBehaviour>::grouped.name
    protected val within = IterableLikeContains.EntryPointStep<Int, List<Int>, InOrderOnlyGroupedSearchBehaviour>::within.name
    private val withinInAnyOrderFun: KFunction6<IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Group<Int>, Group<Int>, Array<out Group<Int>>, InOrderOnlyReportingOptions.() -> Unit, InAnyOrderOnlyReportingOptions.() -> Unit, Expect<Iterable<Int>>> =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAnyOrder
    protected val withinInAnyOrder = withinInAnyOrderFun.name
    //@formatter:on
}
