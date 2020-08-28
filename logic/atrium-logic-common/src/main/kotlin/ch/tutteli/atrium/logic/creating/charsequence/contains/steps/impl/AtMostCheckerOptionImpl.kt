package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.basic.contains.checkers.validateAtMost
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtMostCheckerOption

internal class AtMostCheckerOptionImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    override val containsBuilder: CharSequenceContains.BuilderLogic<T, S>
) : AtMostCheckerOption<T, S>, CharSequenceContains.CheckerOptionInternal<T, S> {

    init {
        validateAtMost(
            times,
            atMostCall,
            atLeastCall,
            exactlyCall
        )
    }

    @Suppress( /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2*/ "DEPRECATION")
    @UseExperimental(ExperimentalNewExpectTypes::class)
    override val checkers: List<CharSequenceContains.Checker> = listOf(
        atLeastChecker(containsBuilder.container, 1, nameContainsNotFun, atMostCall),
        atMostChecker(containsBuilder.container, times, nameContainsNotFun, atMostCall)
    )
}
