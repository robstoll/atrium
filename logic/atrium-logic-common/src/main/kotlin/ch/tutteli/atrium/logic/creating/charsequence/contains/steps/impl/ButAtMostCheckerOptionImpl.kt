package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.logic.creating.basic.contains.checkers.validateButAtMost
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.ButAtMostCheckerOption
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.WithTimesCheckerOptionLogic

internal class ButAtMostCheckerOptionImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    atLeastBuilder: WithTimesCheckerOptionLogic<T, S>,
    nameContainsNotFun: String,
    atLeastButAtMostCall: (Int, Int) -> String,
    atLeastCall: (Int) -> String,
    butAtMostCall: (Int) -> String,
    exactlyCall: (Int) -> String,
    atMostCall: (Int) -> String,
    override val containsBuilder: CharSequenceContains.BuilderLogic<T, S>
) : ButAtMostCheckerOption<T, S>, CharSequenceContains.CheckerOptionInternal<T, S> {

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
        atMostChecker(containsBuilder.container, times, nameContainsNotFun, atMostCall)
    )
}
