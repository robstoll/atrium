@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.entries
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.group
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.reflect.KFunction2

//TODO remove with 1.0.0, no need to migrate to Spek 2
abstract class IterableContainsSpecBase {
    protected val Values = Values::class.simpleName
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
    private val withinInAnyOrderFun : KFunction2<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Order<Int, Group<Int>>, AssertionPlant<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inAny
    protected val withinInAnyOrder = "${withinInAnyOrderFun.name} ${order::class.simpleName}"


    @Suppress("unused")
    private fun ambiguityTest() {
        assert(listOf(1)) contains 1
        assert(listOf(1)) contains Values(1, 2)
        assert(listOf(1)) contains {}
        assert(listOf(1)) contains Entries({}, {})
        assert(listOf(1 as Int?)) contains 1
        assert(listOf(1 as Int?)) contains Values(1, 1)
        assert(listOf(1 as Int?)) contains {}
        //TODO should work, uncomment as soon as KT-6591 is fixed
        //assert(listOf(1 as Int?)) contains null
        assert(listOf(1 as Int?)) contains Entries({}, null)
        assert(listOf(1 as Int?)) contains Entries({}, {})
        assert(listOf(1 as Int?)) contains Entries(null, {})
        assert(listOf(1)) contains 1
        assert(listOf(1)) contains {}
        assert(listOf(1 as Int?)) contains 1
        assert(listOf(1 as Int?)) contains {}

        assert(listOf(1)) containsExactly 1
        assert(listOf(1)) containsExactly Values(1, 2)
        assert(listOf(1)) containsExactly {}
        assert(listOf(1)) containsExactly Entries({}, {})
        assert(listOf(1 as Int?)) containsExactly 1
        assert(listOf(1 as Int?)) containsExactly Values(1, 1)
        assert(listOf(1 as Int?)) containsExactly {}
        //TODO should work, uncomment as soon as KT-6591 is fixed
        //assert(listOf(1 as Int?)) containsExactly null
        assert(listOf(1 as Int?)) containsExactly Entries({}, null)
        assert(listOf(1 as Int?)) containsExactly Entries({}, {})
        assert(listOf(1 as Int?)) containsExactly Entries(null, {})
        assert(listOf(1)) containsExactly 1
        assert(listOf(1)) containsExactly {}
        assert(listOf(1 as Int?)) containsExactly 1
        assert(listOf(1 as Int?)) containsExactly {}

        assert(listOf(1)) to contain inAny order atLeast 1 value 1
        assert(listOf(1)) to contain inAny order atLeast 1 value null
        assert(listOf(1)) to contain inAny order atLeast 1 entry {}
        assert(listOf(1)) to contain inAny order atLeast 1 entry null

        assert(listOf(1)) to contain inAny order but only value 1
        assert(listOf(1)) to contain inAny order but only value null
        assert(listOf(1)) to contain inAny order but only entry {}
        assert(listOf(1)) to contain inAny order but only entry null

        assert(listOf(1)) to contain inGiven order and only value 1
        assert(listOf(1)) to contain inGiven order and only value null
        assert(listOf(1)) to contain inGiven order and only entry {}
        assert(listOf(1)) to contain inGiven order and only entry null


        assert(listOf(1)) to contain inGiven order and only grouped entries within group inAny Order(
            Value(1),
            Value(null),
            Values(1),
            Values(null),
            Values(1, 1),
            Values(1, null),
            Values(null, null)
        )

        assert(listOf(1)) to contain inGiven order and only grouped entries within group inAny Order(
            Entry {},
            Entry(null),
            Entries({}),
            Entries(null),
            Entries({}, {}),
            Entries({}, null),
            Entries(null, null)
        )
    }
}
