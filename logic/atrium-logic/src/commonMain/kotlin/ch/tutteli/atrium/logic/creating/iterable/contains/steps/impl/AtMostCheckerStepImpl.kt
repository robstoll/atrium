package ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.basic.contains.checkers.validateAtMost
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtMostCheckerStep
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

internal class AtMostCheckerStepImpl<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    times: Int,
    nameContainsNotFun: String,
    atMostCall: (Int) -> String,
    atLeastCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    override val entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>
) : AtMostCheckerStep<E, T, S>, IterableLikeContains.CheckerStepInternal<E, T, S> {

    init {
        validateAtMost(
            times,
            atMostCall,
            atLeastCall,
            exactlyCall
        )
    }

    override val checkers: List<IterableLikeContains.Checker> = listOf(
        atLeastChecker(entryPointStepLogic.container, 1, nameContainsNotFun, atMostCall),
        atMostChecker(entryPointStepLogic.container, times, nameContainsNotFun, atMostCall)
    )
}
