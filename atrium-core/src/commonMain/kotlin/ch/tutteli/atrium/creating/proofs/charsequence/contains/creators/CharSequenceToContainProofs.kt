package ch.tutteli.atrium.creating.proofs.charsequence.contains.creators

import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.typeutils.CharSequenceOrNumberOrChar

/**
 * Collection of assertion functions which are intended to be used as part of the final step of a sophisticated
 * `contains`-building process for [CharSequence].
 */
interface CharSequenceToContainProofs {

    fun <T : CharSequence> values(
        checkerStepCore: CharSequenceToContain.CheckerStepCore<T, NoOpSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): ProofGroup

    fun <T : CharSequence> valuesIgnoringCase(
        checkerStepCore: CharSequenceToContain.CheckerStepCore<T, IgnoringCaseSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): ProofGroup

    fun <T : CharSequence> regex(
        checkerStepCore: CharSequenceToContain.CheckerStepCore<T, NoOpSearchBehaviour>,
        expected: List<Regex>
    ): ProofGroup

    fun <T : CharSequence> regexIgnoringCase(
        checkerStepCore: CharSequenceToContain.CheckerStepCore<T, IgnoringCaseSearchBehaviour>,
        expected: List<String>
    ): ProofGroup
}
