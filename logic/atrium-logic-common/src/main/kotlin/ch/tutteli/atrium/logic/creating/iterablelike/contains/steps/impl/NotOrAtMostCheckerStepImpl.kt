package ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.impl

import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.NotOrAtMostCheckerStep
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

internal class NotOrAtMostCheckerStepImpl<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    times: Int,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String,
    override val entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>
) : NotOrAtMostCheckerStep<E, T, S>, IterableLikeContains.CheckerStepInternal<E, T, S> {

    override val checkers = listOf(
        atMostChecker(entryPointStepLogic.container, times, nameContainsNotFun, notOrAtMostCall)
    )
}
