package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.Order
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.specs.notImplemented
import kotlin.reflect.KFunction2

abstract class IterableToContainSpecBase {
    private val Values = "values"
    private val Entries = "entries"

    //@formatter:off
    protected val atLeast = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::atLeast.name
    protected val butAtMost = AtLeastCheckerStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::butAtMost.name
    protected val exactly = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::exactly.name
    protected val atMost = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::atMost.name
    protected val notOrAtMost = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InAnyOrderSearchBehaviour>::notOrAtMost.name
    protected val inAnyOrder =
        "${IterableLikeContains.EntryPointStep<Any, Iterable<*>, NoOpSearchBehaviour>::inAny.name} ${order::class.simpleName}"
    protected val butOnly =
        "${IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::but.name} ${only::class.simpleName}"
    private val theInAnyOrderFun: KFunction2<IterableLikeContains.CheckerStep<Int, Iterable<Int>, InAnyOrderSearchBehaviour>, Values<Int>, Expect<Iterable<Int>>> =
        IterableLikeContains.CheckerStep<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::the
    private val theInAnyOrder = theInAnyOrderFun.name
    protected val inAnyOrderEntries = "$theInAnyOrder $Entries"
    protected val inAnyOrderValues = "$theInAnyOrder $Values"
    protected val inAnyOrderElementsOf =
        IterableLikeContains.CheckerStep<Int, Iterable<Int>, InAnyOrderSearchBehaviour>::elementsOf.name

    private val theInAnyOrderOnlyFun: KFunction2<IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>, Values<Int>, Expect<Iterable<Int>>> =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::the
    private val theInAnyOrderOnly = theInAnyOrderOnlyFun.name
    protected val inAnyOrderOnlyValues = "$theInAnyOrderOnly $Values"
    protected val inAnyOrderOnlyEntries = "$theInAnyOrderOnly $Entries"
    protected val inAnyOrderOnlyElementsOf =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InAnyOrderOnlySearchBehaviour>::elementsOf.name

    protected val inOrder =
        "${IterableLikeContains.EntryPointStep<Any, Iterable<*>, NoOpSearchBehaviour>::inGiven.name} ${order::class.simpleName}"
    protected val andOnly =
        "${IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderSearchBehaviour>::and.name} ${only::class.simpleName}"
    private val theInOrderOnlyFun: KFunction2<IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlySearchBehaviour>, Values<Int>, Expect<Iterable<Int>>> =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::the
    private val theInOrderOnly = theInOrderOnlyFun.name
    protected val inOrderOnlyValues = "$theInOrderOnly $Values"
    protected val inOrderOnlyEntries = "$theInOrderOnly $Entries"
    protected val inOrderElementsOf =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlySearchBehaviour>::elementsOf.name
    protected val grouped =
        "${IterableLikeContains.EntryPointStep<Any, Iterable<*>, InOrderOnlySearchBehaviour>::grouped.name} ${entries::class.simpleName}"
    protected val within = IterableLikeContains.EntryPointStep<Any, Iterable<*>, InOrderOnlyGroupedSearchBehaviour>::within.name
    private val withinInAnyOrderFun: KFunction2<IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Order<Int, Group<Int>>, Expect<Iterable<Int>>> =
        IterableLikeContains.EntryPointStep<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAny
    protected val withinInAnyOrder = withinInAnyOrderFun.name
    //@formatter:on

    protected val filler = o::class.simpleName
    private val toContainProp: KFunction2<Expect<Iterable<Int>>, o, IterableLikeContains.EntryPointStep<Int, Iterable<Int>, NoOpSearchBehaviour>> =
        Expect<Iterable<Int>>::toContain
    protected val toContain = toContainProp.name
    private val notToContainProp: KFunction2<Expect<Iterable<Int>>, o, NotCheckerStep<Int, Iterable<Int>, NotSearchBehaviour>> =
        Expect<Iterable<Int>>::notToContain
    protected val notToContain = "${notToContainProp.name} $Values"

    //TODO move to specific specs
    @Suppress("unused")
    private fun ambiguityTest() {
        val list: Expect<List<Number>> = notImplemented()
        val nullableList: Expect<Set<Number?>> = notImplemented()
        val subList: Expect<ArrayList<out Number>> = notImplemented()
        val star: Expect<Collection<*>> = notImplemented()

        list toContain 1
        list toContain 1f
        list toContain values(1, 2f)
        list toContain {}
        list toContain entries({}, {})
        list notToContain 1
        list notToContain 1f
        list notToContain values(1, 2f)
        list notToContain o entry {}
        list notToContain o the entries({}, {})

        subList toContain 1
        subList toContain 1f; subList toContain values(1, 2f)
        subList toContain {}
        subList toContain entries({}, {})
        subList notToContain 1
        subList notToContain 1f
        subList notToContain values(1, 2f)
        subList notToContain o entry {}
        subList notToContain o the entries({}, {})

        nullableList toContain 1
        nullableList toContain 1f
        nullableList toContain values(1, 2f)
        nullableList toContain {}
        nullableList toContain entries({}, {})
        nullableList notToContain 1
        nullableList notToContain 1f
        nullableList notToContain values(1, 2f)
        nullableList notToContain o entry {}
        nullableList notToContain o the entries({}, {})
        nullableList toContain null
        nullableList toContain entries(null, {})
        nullableList toContain entries({}, null)
        nullableList toContain entries(null, null)
        nullableList notToContain null
        nullableList notToContain o the entries(null, {})
        nullableList notToContain o the entries({}, null)
        nullableList notToContain o the entries(null, null)

        star toContain 1
        star toContain 1f
        star toContain values(1, 2f)
        star toContain {}
        star toContain entries({}, {})
        star notToContain 1
        star notToContain 1f
        star notToContain values(1, 2f)
        star notToContain o entry {}
        star notToContain o the entries({}, {})
        star toContain null
        star toContain entries(null, {})
        star toContain entries({}, null)
        star toContain entries(null, null)
        star notToContain null
        star notToContain o the entries(null, {})
        star notToContain o the entries({}, null)
        star notToContain o the entries(null, null)

        list toContainExactly 1
        list toContainExactly values(1, 2f)

        subList toContainExactly 1
        subList toContainExactly values(1, 2f)

        nullableList toContainExactly 1
        nullableList toContainExactly values(1, 2f)
        nullableList toContainExactly null

        star toContainExactly 1
        star toContainExactly values(1, 2f)
        star toContainExactly null


        list toContain o inAny order atLeast 1 value 1
        list toContain o inAny order atLeast 1 the values(2, 1)
        list toContain o inAny order atLeast 1 entry {}
        list toContain o inAny order atLeast 1 the entries({}, {})
        list toContain o inAny order atLeast 1 elementsOf listOf(1, 2)
        subList toContain o inAny order atLeast 1 value 1
        subList toContain o inAny order atLeast 1 the values(2, 1)
        subList toContain o inAny order atLeast 1 entry {}
        subList toContain o inAny order atLeast 1 the entries({}, {})
        subList toContain o inAny order atLeast 1 elementsOf listOf(1, 2)
        nullableList toContain o inAny order atLeast 1 value 1
        nullableList toContain o inAny order atLeast 1 the values(2, 1)
        nullableList toContain o inAny order atLeast 1 entry {}
        nullableList toContain o inAny order atLeast 1 the entries({}, {})
        nullableList toContain o inAny order atLeast 1 elementsOf listOf(1, 2)
        nullableList toContain o inAny order atLeast 1 value null
        nullableList toContain o inAny order atLeast 1 the values(null, 1)
        nullableList toContain o inAny order atLeast 1 the values(2, null)
        nullableList toContain o inAny order atLeast 1 the values(null, null)
        nullableList toContain o inAny order atLeast 1 entry null
        nullableList toContain o inAny order atLeast 1 the entries(null, {})
        nullableList toContain o inAny order atLeast 1 the entries({}, null)
        nullableList toContain o inAny order atLeast 1 the entries(null, null)
        star toContain o inAny order atLeast 1 value 1
        star toContain o inAny order atLeast 1 the values(2, 1)
        star toContain o inAny order atLeast 1 entry {}
        star toContain o inAny order atLeast 1 the entries({}, {})
        star toContain o inAny order atLeast 1 elementsOf listOf(1, 2)
        star toContain o inAny order atLeast 1 value null
        star toContain o inAny order atLeast 1 the values(null, 1)
        star toContain o inAny order atLeast 1 the values(2, null)
        star toContain o inAny order atLeast 1 the values(null, null)
        star toContain o inAny order atLeast 1 entry null
        star toContain o inAny order atLeast 1 the entries(null, {})
        star toContain o inAny order atLeast 1 the entries({}, null)
        star toContain o inAny order atLeast 1 the entries(null, null)

        list toContain o inAny order but only value 1
        list toContain o inAny order but only the values(2, 1)
        list toContain o inAny order but only entry {}
        list toContain o inAny order but only the entries({}, {})
        list toContain o inAny order but only elementsOf listOf(1, 2)
        subList toContain o inAny order but only value 1
        subList toContain o inAny order but only the values(2, 1)
        subList toContain o inAny order but only entry {}
        subList toContain o inAny order but only the entries({}, {})
        subList toContain o inAny order but only elementsOf listOf(1, 2)
        nullableList toContain o inAny order but only value 1
        nullableList toContain o inAny order but only the values(2, 1)
        nullableList toContain o inAny order but only entry {}
        nullableList toContain o inAny order but only the entries({}, {})
        nullableList toContain o inAny order but only elementsOf listOf(1, 2)
        nullableList toContain o inAny order but only value null
        nullableList toContain o inAny order but only the values(null, 1)
        nullableList toContain o inAny order but only the values(2, null)
        nullableList toContain o inAny order but only the values(null, null)
        nullableList toContain o inAny order but only entry null
        nullableList toContain o inAny order but only the entries(null, {})
        nullableList toContain o inAny order but only the entries({}, null)
        nullableList toContain o inAny order but only the entries(null, null)
        star toContain o inAny order but only value 1
        star toContain o inAny order but only the values(2, 1)
        star toContain o inAny order but only entry {}
        star toContain o inAny order but only the entries({}, {})
        star toContain o inAny order but only elementsOf listOf(1, 2)
        star toContain o inAny order but only value null
        star toContain o inAny order but only the values(null, 1)
        star toContain o inAny order but only the values(2, null)
        star toContain o inAny order but only the values(null, null)
        star toContain o inAny order but only entry null
        star toContain o inAny order but only the entries(null, {})
        star toContain o inAny order but only the entries({}, null)
        star toContain o inAny order but only the entries(null, null)

        list toContain o inGiven order and only value 1
        list toContain o inGiven order and only the values(2, 1)

        list toContain o inGiven order and only elementsOf listOf(1, 2)
        subList toContain o inGiven order and only value 1
        subList toContain o inGiven order and only the values(2, 1)

        subList toContain o inGiven order and only elementsOf listOf(1, 2)
        nullableList toContain o inGiven order and only value 1
        nullableList toContain o inGiven order and only the values(2, 1)

        nullableList toContain o inGiven order and only elementsOf listOf(1, 2)
        nullableList toContain o inGiven order and only value null
        nullableList toContain o inGiven order and only the values(null, 1)
        nullableList toContain o inGiven order and only the values(2, null)
        nullableList toContain o inGiven order and only the values(null, null)

        star toContain o inGiven order and only value 1
        star toContain o inGiven order and only the values(2, 1)


        star toContain o inGiven order and only elementsOf listOf(1, 2)
        star toContain o inGiven order and only value null
        star toContain o inGiven order and only the values(null, 1)
        star toContain o inGiven order and only the values(2, null)
        star toContain o inGiven order and only the values(null, null)

    }
}
