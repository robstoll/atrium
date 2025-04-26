package ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.basic.contains.steps.impl.EntryPointStepImpl

class EntryPointStepImpl<T : CharSequence, out S : CharSequenceToContain.SearchBehaviour>(
    container: ProofContainer<T>,
    searchBehaviour: S
) : EntryPointStepImpl<T, S>(container, searchBehaviour), CharSequenceToContain.EntryPointStepInternal<T, S>

