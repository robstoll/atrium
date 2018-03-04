package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.creating.charsequence.contains.builders.AtLeastCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<String>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<String>::containsNot
    protected val containsNot = containsNotProp.name
    protected val containsRegex = Assert<String>::containsRegex.name
    protected val atLeast = CharSequenceContains.Builder<*, *>::atLeast.name
    protected val butAtMost = AtLeastCheckerBuilder<*, *>::butAtMost.name
    protected val exactly = CharSequenceContains.Builder<*, *>::exactly.name
    protected val atMost = CharSequenceContains.Builder<*, *>::atMost.name
    protected val notOrAtMost = CharSequenceContains.Builder<*, *>::notOrAtMost.name
    protected val regex = CharSequenceContains.CheckerBuilder<*, NoOpSearchBehaviour>::regex.name
    protected val defaultTranslationOf = CharSequenceContains.CheckerBuilder<*, NoOpSearchBehaviour>::defaultTranslationOf.name
    protected val ignoringCase = CharSequenceContains.Builder<*, NoOpSearchBehaviour>::ignoringCase.name
}
