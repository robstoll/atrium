@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.esGilt
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
    private val withinInAnyOrderFun : KFunction4<IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>, Group<Int>, Group<Int>, Array<out Group<Int>>, AssertionPlant<Iterable<Int>>>
        = IterableContains.Builder<Int, Iterable<Int>, InOrderOnlyGroupedWithinSearchBehaviour>::inBeliebigerReihenfolge
    protected val withinInAnyOrder = withinInAnyOrderFun.name


    @Suppress("unused")
    private fun ambiguityTest() {
        esGilt(listOf(1)).enthaelt(1)
        esGilt(listOf(1)).enthaelt(1, 2)
        esGilt(listOf(1)).enthaelt {}
        esGilt(listOf(1)).enthaelt({}, {})
        esGilt(listOf(1 as Int?)).enthaelt(1)
        esGilt(listOf(1 as Int?)).enthaelt(1, 1)
        esGilt(listOf(1 as Int?)).enthaelt {}
        esGilt(listOf(1 as Int?)).enthaelt(null)
        esGilt(listOf(1 as Int?)).enthaelt({}, null)
        esGilt(listOf(1 as Int?)).enthaelt({}, {})
        esGilt(listOf(1 as Int?)).enthaelt(null, {})

        esGilt(listOf(1)).enthaeltExakt(1)
        esGilt(listOf(1)).enthaeltExakt(1, 2)
        esGilt(listOf(1)).enthaeltExakt {}
        esGilt(listOf(1)).enthaeltExakt({}, {})
        esGilt(listOf(1 as Int?)).enthaeltExakt(1)
        esGilt(listOf(1 as Int?)).enthaeltExakt(1, 1)
        esGilt(listOf(1 as Int?)).enthaeltExakt {}
        esGilt(listOf(1 as Int?)).enthaeltExakt(null)
        esGilt(listOf(1 as Int?)).enthaeltExakt({}, null)
        esGilt(listOf(1 as Int?)).enthaeltExakt({}, {})
        esGilt(listOf(1 as Int?)).enthaeltExakt(null, {})

        esGilt(listOf(1)).enthaelt.inBeliebigerReihenfolge.zumindest(1).wert(1)
        esGilt(listOf(1)).enthaelt.inBeliebigerReihenfolge.zumindest(1).wert(null)
        esGilt(listOf(1)).enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag {}
        esGilt(listOf(1)).enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(null)

        esGilt(listOf(1)).enthaelt.inBeliebigerReihenfolge.nur.wert( 1)
        esGilt(listOf(1)).enthaelt.inBeliebigerReihenfolge.nur.wert( null)
        esGilt(listOf(1)).enthaelt.inBeliebigerReihenfolge.nur.eintrag {}
        esGilt(listOf(1)).enthaelt.inBeliebigerReihenfolge.nur.eintrag(null)

        esGilt(listOf(1)).enthaelt.inGegebenerReihenfolge.nur.wert(1)
        esGilt(listOf(1)).enthaelt.inGegebenerReihenfolge.nur.wert(null)
        esGilt(listOf(1)).enthaelt.inGegebenerReihenfolge.nur.eintrag {}
        esGilt(listOf(1)).enthaelt.inGegebenerReihenfolge.nur.eintrag(null)


        esGilt(listOf(1)).enthaelt.inGegebenerReihenfolge.nur.gruppiert.innerhalb.inBeliebigerReihenfolge(
            Wert(1),
            Wert(null),
            Werte(1),
            Werte(null),
            Werte(1, 1),
            Werte(1, null),
            Werte(null, null)
        )

        esGilt(listOf(1)).enthaelt.inGegebenerReihenfolge.nur.gruppiert.innerhalb.inBeliebigerReihenfolge(
            Eintrag {},
            Eintrag(null),
            Eintraege({}),
            Eintraege(null),
            Eintraege({}, {}),
            Eintraege({}, null),
            Eintraege(null, null)
        )
    }
}
