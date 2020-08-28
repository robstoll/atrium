package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.*

internal class GenericTimesCheckerOption<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    override val times: Int,
    override val containsBuilder: CharSequenceContains.BuilderLogic<T, S>,
    override val checkers: List<CharSequenceContains.Checker>
) : WithTimesCheckerOptionInternal<T, S>,
    AtLeastCheckerOption<T, S>,
    ButAtMostCheckerOption<T, S>,
    ExactlyCheckerOption<T, S>
