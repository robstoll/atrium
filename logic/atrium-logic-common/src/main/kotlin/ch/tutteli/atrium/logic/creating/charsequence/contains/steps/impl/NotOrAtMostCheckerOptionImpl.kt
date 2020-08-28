package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotOrAtMostCheckerOption

internal class NotOrAtMostCheckerOptionImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    nameContainsNotFun: String,
    notOrAtMostCall: (Int) -> String,
    override val containsBuilder: CharSequenceContains.BuilderLogic<T, S>
) : NotOrAtMostCheckerOption<T, S>, CharSequenceContains.CheckerOptionInternal<T, S> {

    override val checkers = listOf(
        atMostChecker(containsBuilder.container, times, nameContainsNotFun, notOrAtMostCall)
    )
}
