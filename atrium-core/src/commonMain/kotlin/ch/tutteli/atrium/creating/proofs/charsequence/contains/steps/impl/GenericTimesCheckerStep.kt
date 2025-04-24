package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.impl

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.*

class GenericTimesCheckerStep<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>(
    override val times: Int,
    override val entryPointStepCore: CharSequenceToContain.EntryPointStepCore<T, S>,
    override val checkers: List<CharSequenceToContain.Checker>
) : WithTimesCheckerStepInternal<T, S>,
    AtLeastCheckerStep<T, S>,
    ButAtMostCheckerStep<T, S>,
    ExactlyCheckerStep<T, S>
