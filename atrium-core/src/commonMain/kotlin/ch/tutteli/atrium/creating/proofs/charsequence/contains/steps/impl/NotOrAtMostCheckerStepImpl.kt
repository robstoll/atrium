package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.impl

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.NotOrAtMostCheckerStep

class NotOrAtMostCheckerStepImpl<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>(
    times: Int,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String,
    override val entryPointStepLogic: CharSequenceToContain.EntryPointStepCore<T, S>
) : NotOrAtMostCheckerStep<T, S>, CharSequenceToContain.CheckerStepInternal<T, S> {

    override val checkers = listOf(
        atMostChecker(entryPointStepLogic.container, times, nameContainsNotFun, notOrAtMostCall)
    )
}
