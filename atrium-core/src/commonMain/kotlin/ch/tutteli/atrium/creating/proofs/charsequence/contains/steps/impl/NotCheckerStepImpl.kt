package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.NotChecker
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.creating.proofs.charsequence.contains.checkers.impl.DefaultNotChecker

class NotCheckerStepImpl<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>(
    override val entryPointStepCore: CharSequenceToContain.EntryPointStepCore<T, S>
) : NotCheckerStep<T, S>, CharSequenceToContain.CheckerStepInternal<T, S> {

    @OptIn(ExperimentalNewExpectTypes::class)
    override val checkers = listOf(
        entryPointStepCore.container.getImpl(NotChecker::class) { DefaultNotChecker() }
    )
}
