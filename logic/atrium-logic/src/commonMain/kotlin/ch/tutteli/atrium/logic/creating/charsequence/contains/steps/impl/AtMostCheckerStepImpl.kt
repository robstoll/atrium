package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.logic.creating.basic.contains.checkers.validateAtMost
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.AtMostCheckerStep

class AtMostCheckerStepImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    override val entryPointStepLogic: CharSequenceContains.EntryPointStepLogic<T, S>
) : AtMostCheckerStep<T, S>, CharSequenceContains.CheckerStepInternal<T, S> {

    init {
        validateAtMost(
            times,
            atMostCall,
            atLeastCall,
            exactlyCall
        )
    }

    override val checkers: List<CharSequenceContains.Checker> = listOf(
        atLeastChecker(entryPointStepLogic.container, 1, nameContainsNotFun, atMostCall),
        atMostChecker(entryPointStepLogic.container, times, nameContainsNotFun, atMostCall)
    )
}
