package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.impl

import ch.tutteli.atrium.creating.proofs.basic.contains.checkers.validateButAtMost
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.ButAtMostCheckerStep
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.WithTimesCheckerStepCore

class ButAtMostCheckerStepImpl<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>(
    times: Int,
    atLeastBuilder: WithTimesCheckerStepCore<T, S>,
    nameContainsNotFun: String,
    atLeastButAtMostCall: (Int, Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    atMostCall: (Int) -> String,
    override val entryPointStepCore: CharSequenceToContain.EntryPointStepCore<T, S>
) : ButAtMostCheckerStep<T, S>, CharSequenceToContain.CheckerStepInternal<T, S> {

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

    override val checkers: List<CharSequenceToContain.Checker> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        atMostChecker(entryPointStepCore.container, times, nameContainsNotFun, atMostCall)
    )
}
