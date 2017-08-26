package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<*> = IAssertionPlant<String>::contains
    protected val contains = containsProp.name
    protected val containsNot = IAssertionPlant<String>::containsNot.name
    protected val atLeast = CharSequenceContainsBuilder<*, *>::atLeast.name
    protected val butAtMost = CharSequenceContainsAtLeastCheckerBuilder<*, *>::butAtMost.name
    protected val exactly = CharSequenceContainsBuilder<*, *>::exactly.name
    protected val atMost = CharSequenceContainsBuilder<*, *>::atMost.name
    protected val notOrAtMost = CharSequenceContainsBuilder<*, *>::notOrAtMost.name
    protected val regex = CharSequenceContainsCheckerBuilder<*, CharSequenceContainsNoOpDecorator>::regex.name
    protected val ignoringCase = CharSequenceContainsBuilder<*, CharSequenceContainsNoOpDecorator>::ignoringCase.name
}
