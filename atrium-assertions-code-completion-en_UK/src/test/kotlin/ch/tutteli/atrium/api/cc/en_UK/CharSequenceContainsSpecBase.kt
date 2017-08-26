package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>> = IAssertionPlant<String>::contains
    protected val contains = containsProp.name
    protected val containsNot = IAssertionPlant<String>::containsNot.name
    protected val atLeast = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::atLeast.name
    protected val butAtMost = CharSequenceContainsAtLeastCheckerBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::butAtMost.name
    protected val exactly = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::exactly.name
    protected val atMost = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::atMost.name
    protected val notOrAtMost = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::notOrAtMost.name
    protected val regex = CharSequenceContainsCheckerBuilder<String, CharSequenceContainsNoOpDecorator>::regex.name
    protected val ignoringCase = CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>::ignoringCase.name
}
