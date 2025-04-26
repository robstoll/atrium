package ch.tutteli.atrium.creating.proofs.charsequence.contains.creators

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.proofs.ProofGroup

/** @since 1.3.0 */
fun <T : CharSequence> CharSequenceToContain.CheckerStepCore<T, NoOpSearchBehaviour>.regex(expected: List<String>): ProofGroup =
    regex(expected.map { it.toRegex() })
