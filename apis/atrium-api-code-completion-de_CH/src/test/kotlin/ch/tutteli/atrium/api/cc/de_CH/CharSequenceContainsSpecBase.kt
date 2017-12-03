package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<*> = IAssertionPlant<String>::enthaelt
    protected val contains = containsProp.name
    protected val containsNot = IAssertionPlant<String>::enthaeltNicht.name
    protected val atLeast = CharSequenceContainsBuilder<*, *>::zumindest.name
    protected val butAtMost = CharSequenceContainsAtLeastCheckerBuilder<*, *>::aberHoechstens.name
    protected val exactly = CharSequenceContainsBuilder<*, *>::genau.name
    protected val atMost = CharSequenceContainsBuilder<*, *>::hoechstens.name
    protected val notOrAtMost = CharSequenceContainsBuilder<*, *>::nichtOderHoechstens.name
    protected val regex = CharSequenceContainsCheckerBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::regex.name
    protected val ignoringCase = CharSequenceContainsBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::ignoriereGrossKleinschreibung.name
}
