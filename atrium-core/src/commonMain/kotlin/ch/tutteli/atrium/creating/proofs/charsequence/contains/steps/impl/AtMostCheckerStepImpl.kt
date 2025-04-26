package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.impl

import ch.tutteli.atrium.creating.proofs.basic.contains.checkers.validateAtMost
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.AtMostCheckerStep

class AtMostCheckerStepImpl<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>(
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    override val entryPointStepCore: CharSequenceToContain.EntryPointStepCore<T, S>
) : AtMostCheckerStep<T, S>, CharSequenceToContain.CheckerStepInternal<T, S> {

    init {
        validateAtMost(
            times,
            atMostCall,
            atLeastCall,
            exactlyCall
        )
    }

    override val checkers: List<CharSequenceToContain.Checker> = listOf(
        atLeastChecker(entryPointStepCore.container, 1, nameContainsNotFun, atMostCall),
        atMostChecker(entryPointStepCore.container, times, nameContainsNotFun, atMostCall)
    )
}
