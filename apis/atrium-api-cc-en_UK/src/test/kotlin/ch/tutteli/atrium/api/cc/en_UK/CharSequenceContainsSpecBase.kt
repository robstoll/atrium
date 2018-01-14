package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.Assert
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<String>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<String>::containsNot
    protected val containsNot = containsNotProp.name
    protected val containsRegex = Assert<String>::containsRegex.name
    protected val atLeast = CharSequenceContainsBuilder<*, *>::atLeast.name
    protected val butAtMost = CharSequenceContainsAtLeastCheckerBuilder<*, *>::butAtMost.name
    protected val exactly = CharSequenceContainsBuilder<*, *>::exactly.name
    protected val atMost = CharSequenceContainsBuilder<*, *>::atMost.name
    protected val notOrAtMost = CharSequenceContainsBuilder<*, *>::notOrAtMost.name
    protected val regex = CharSequenceContainsCheckerBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::regex.name
    protected val defaultTranslationOf = CharSequenceContainsCheckerBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::defaultTranslationOf.name
    protected val ignoringCase = CharSequenceContainsBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::ignoringCase.name
}
