package ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.NotChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.impl.DefaultNotChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

internal class NotCheckerStepImpl<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    override val entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>
) : NotCheckerStep<E, T, S>, IterableLikeContains.CheckerStepInternal<E, T, S> {

    @OptIn(ExperimentalNewExpectTypes::class)
    override val checkers = listOf(
        entryPointStepLogic.container.getImpl(NotChecker::class) { DefaultNotChecker() }
    )
}
