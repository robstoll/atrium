package ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.NotChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.checkers.impl.DefaultNotChecker
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.NotCheckerStep

internal class NotCheckerStepImpl<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    override val entryPointStepLogic: IterableLikeContains.EntryPointStepLogic<E, T, S>
) : NotCheckerStep<E, T, S>, IterableLikeContains.CheckerStepInternal<E, T, S> {

    @Suppress( /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2*/ "DEPRECATION")
    @UseExperimental(ExperimentalNewExpectTypes::class)
    override val checkers = listOf(
        entryPointStepLogic.container.getImpl<NotChecker>(NotChecker::class) { DefaultNotChecker() }
    )
}
