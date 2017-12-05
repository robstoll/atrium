package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsAtLeastCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant
import kotlin.reflect.KFunction2

abstract class CharSequenceContainsSpecBase {
    private val containsNotFun: KFunction2<IAssertionPlant<String>, Any, IAssertionPlant<String>> = IAssertionPlant<String>::containsNot
    protected val toContain = "${IAssertionPlant<String>::to.name} ${contain::class.simpleName}"
    protected val containsNotValues = "${containsNotFun.name} ${Values::class.simpleName}"
    protected val atLeast = CharSequenceContainsBuilder<*, *>::atLeast.name
    protected val butAtMost = CharSequenceContainsAtLeastCheckerBuilder<*, *>::butAtMost.name
    protected val exactly = CharSequenceContainsBuilder<*, *>::exactly.name
    protected val atMost = CharSequenceContainsBuilder<*, *>::atMost.name
    protected val notOrAtMost = CharSequenceContainsBuilder<*, *>::notOrAtMost.name
    protected val regex = CharSequenceContainsCheckerBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::regex.name
    protected val ignoringCase = "${CharSequenceContainsBuilder<*, CharSequenceContainsNoOpSearchBehaviour>::ignoring.name} ${case::class.simpleName}"
}
