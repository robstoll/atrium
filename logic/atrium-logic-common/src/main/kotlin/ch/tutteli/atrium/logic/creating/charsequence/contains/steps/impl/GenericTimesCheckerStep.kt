package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.*

internal class GenericTimesCheckerStep<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    override val times: Int,
    override val entryPointStepLogic: CharSequenceContains.EntryPointStepLogic<T, S>,
    override val checkers: List<CharSequenceContains.Checker>
) : WithTimesCheckerStepInternal<T, S>,
    AtLeastCheckerStep<T, S>,
    ButAtMostCheckerStep<T, S>,
    ExactlyCheckerStep<T, S>
