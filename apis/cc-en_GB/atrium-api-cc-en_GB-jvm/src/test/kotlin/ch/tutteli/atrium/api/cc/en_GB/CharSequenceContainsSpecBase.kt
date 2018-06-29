package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.cc.en_GB.creating.charsequence.contains.builders.AtLeastCheckerOption
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import kotlin.reflect.KProperty

abstract class CharSequenceContainsSpecBase {
    private val containsProp: KProperty<*> = Assert<String>::contains
    protected val contains = containsProp.name
    private val containsNotProp: KProperty<*> = Assert<String>::containsNot
    protected val containsNot = containsNotProp.name
    protected val containsRegex = Assert<String>::containsRegex.name
    protected val atLeast = CharSequenceContains.Builder<*, *>::atLeast.name
    protected val butAtMost = AtLeastCheckerOption<*, *>::butAtMost.name
    protected val exactly = CharSequenceContains.Builder<*, *>::exactly.name
    protected val atMost = CharSequenceContains.Builder<*, *>::atMost.name
    protected val notOrAtMost = CharSequenceContains.Builder<*, *>::notOrAtMost.name
    protected val regex = CharSequenceContains.CheckerOption<*, NoOpSearchBehaviour>::regex.name
    protected val ignoringCase = CharSequenceContains.Builder<*, NoOpSearchBehaviour>::ignoringCase.name
}
