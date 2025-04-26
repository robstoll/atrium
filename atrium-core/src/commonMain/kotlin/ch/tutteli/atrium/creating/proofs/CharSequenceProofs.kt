package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.NotCheckerStep

/**
 * Collection of proof functions and builders which are applicable to [CharSequence].
 *
 * @since 1.3.0
 */
interface CharSequenceProofs {

    /**
     * Starts the building process of a sophisticated `toContain` expectation.
     *
     * @since 1.3.0
     */
    fun <SubjectT : CharSequence> toContainBuilder(
        container: ProofContainer<SubjectT>
    ): CharSequenceToContain.EntryPointStep<SubjectT, NoOpSearchBehaviour>

    /**
     * Starts the building process of a sophisticated `toContain` expectation and already applies a
     * [NotCheckerStep] with a [NotSearchBehaviour].
     *
     * @since 1.3.0
     */
    fun <SubjectT : CharSequence> notToContainBuilder(
        container: ProofContainer<SubjectT>
    ): NotCheckerStep<SubjectT, NotSearchBehaviour>

    /** @since 1.3.0 */
    fun <SubjectT : CharSequence> toStartWith(container: ProofContainer<SubjectT>, expected: CharSequence): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CharSequence> notToStartWith(container: ProofContainer<SubjectT>, expected: CharSequence): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CharSequence> toEndWith(container: ProofContainer<SubjectT>, expected: CharSequence): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CharSequence> notToEndWith(container: ProofContainer<SubjectT>, expected: CharSequence): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CharSequence> toBeEmpty(container: ProofContainer<SubjectT>): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CharSequence> notToBeEmpty(container: ProofContainer<SubjectT>): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CharSequence> notToBeBlank(container: ProofContainer<SubjectT>): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CharSequence> toMatch(container: ProofContainer<SubjectT>, expected: Regex): Proof

    /** @since 1.3.0 */
    fun <SubjectT : CharSequence> notToMatch(container: ProofContainer<SubjectT>, expected: Regex): Proof
}
