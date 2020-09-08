package ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl

import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.AtLeastCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.ButAtMostCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.ExactlyCheckerStep
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.WithTimesCheckerStepInternal

internal class GenericTimesCheckerStep<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    override val times: Int,
    override val entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>,
    override val checkers: List<IterableLikeContains.Checker>
) : WithTimesCheckerStepInternal<E, T, S>,
    AtLeastCheckerStep<E, T, S>,
    ButAtMostCheckerStep<E, T, S>,
    ExactlyCheckerStep<E, T, S>
