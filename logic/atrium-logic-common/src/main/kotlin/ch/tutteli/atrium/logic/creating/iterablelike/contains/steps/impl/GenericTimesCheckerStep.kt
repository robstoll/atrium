package ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.impl

import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.ButAtMostCheckerStep
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.ExactlyCheckerStep
import ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.WithTimesCheckerStepInternal
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

internal class GenericTimesCheckerStep<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    override val times: Int,
    override val entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>,
    override val checkers: List<IterableLikeContains.Checker>
) : WithTimesCheckerStepInternal<E, T, S>,
    AtLeastCheckerStep<E, T, S>,
    ButAtMostCheckerStep<E, T, S>,
    ExactlyCheckerStep<E, T, S>
