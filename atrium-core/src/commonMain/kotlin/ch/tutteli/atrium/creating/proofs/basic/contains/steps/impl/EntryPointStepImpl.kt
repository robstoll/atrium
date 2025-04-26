package ch.tutteli.atrium.creating.proofs.basic.contains.steps.impl

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.basic.contains.ToContain

abstract class EntryPointStepImpl<T : Any, out S : ToContain.SearchBehaviour>(
    override val container: ProofContainer<T>,
    override val searchBehaviour: S
) : ToContain.EntryPointStepCore<T, S>
