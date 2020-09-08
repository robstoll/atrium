package ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl

import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.basic.contains.checkers.validateButAtMost
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.ButAtMostCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.WithTimesCheckerStepLogic

internal class ButAtMostCheckerStepImpl<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    times: Int,
    atLeastBuilder: WithTimesCheckerStepLogic<E, T, S>,
    nameContainsNotFun: String,
    atLeastButAtMostCall: (Int, Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    atMostCall: (Int) -> String,
    override val entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>
) : ButAtMostCheckerStep<E, T, S>, IterableLikeContains.CheckerStepInternal<E, T, S> {

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

    override val checkers: List<IterableLikeContains.Checker> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        atMostChecker(entryPointStepLogic.container, times, nameContainsNotFun, atMostCall)
    )
}
