package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotOrAtMostCheckerStep

internal class NotOrAtMostCheckerStepImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String,
    override val containsBuilder: CharSequenceContains.EntryPointStepLogic<T, S>
) : NotOrAtMostCheckerStep<T, S>, CharSequenceContains.CheckerStepInternal<T, S> {

    override val checkers = listOf(
        atMostChecker(containsBuilder.container, times, nameContainsNotFun, notOrAtMostCall)
    )
}
