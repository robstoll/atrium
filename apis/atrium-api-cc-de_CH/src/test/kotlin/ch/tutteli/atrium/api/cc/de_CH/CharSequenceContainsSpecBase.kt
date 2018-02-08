package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<String>::enthaelt
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<String>::enthaeltNicht
    protected val containsNot = containsNotProp.name
    protected val containsRegex = Assert<String>::enthaeltRegex.name
    protected val atLeast = CharSequenceContainsBuilder<*, *>::zumindest.name
    protected val butAtMost = CharSequenceContainsAtLeastCheckerBuilder<*, *>::aberHoechstens.name
    protected val exactly = CharSequenceContainsBuilder<*, *>::genau.name
    protected val atMost = CharSequenceContainsBuilder<*, *>::hoechstens.name
    protected val notOrAtMost = CharSequenceContainsBuilder<*, *>::nichtOderHoechstens.name
    protected val regex = CharSequenceContainsCheckerBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::regex.name
    protected val defaultTranslationOf = CharSequenceContainsCheckerBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::standardUebersetzungVon.name
    protected val ignoringCase = CharSequenceContainsBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::ignoriereGrossKleinschreibung.name
}
