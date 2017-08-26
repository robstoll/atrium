package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>> = IAssertionPlant<String>::enthaelt
    protected val contains = containsProp.name
    protected val containsNot = IAssertionPlant<String>::enthaeltNicht.name
    protected val atLeast = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::zumindest.name
    protected val butAtMost = CharSequenceContainsAtLeastCheckerBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::aberHoechstens.name
    protected val exactly = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::genau.name
    protected val atMost = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::hoechstens.name
    protected val notOrAtMost = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::nichtOderHoechstens.name
    protected val regex = CharSequenceContainsCheckerBuilder<String, CharSequenceContainsNoOpDecorator>::regex.name
    protected val ignoringCase = CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>::ignoriereGrossKleinschreibung.name
}
