package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.logic.creating.basic.contains.checkers.validateButAtMost
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.ButAtMostCheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerStepLogic

internal class ButAtMostCheckerStepImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    atLeastBuilder: WithTimesCheckerStepLogic<T, S>,
    nameContainsNotFun: String,
    atLeastButAtMostCall: (Int, Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    atMostCall: (Int) -> String,
    override val entryPointStepLogic: CharSequenceContains.EntryPointStepLogic<T, S>
) : ButAtMostCheckerStep<T, S>, CharSequenceContains.CheckerStepInternal<T, S> {

    init {
        validateButAtMost(
            atLeastBuilder.times,
            times,
            atLeastButAtMostCall,
            atLeastCall,
            butAtMostCall,
            exactlyCall
        )
    }

    override val checkers: List<CharSequenceContains.Checker> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        atMostChecker(entryPointStepLogic.container, times, nameContainsNotFun, atMostCall)
    )
}
