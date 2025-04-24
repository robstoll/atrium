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
    fun <T : CharSequence> toContainBuilder(
        container: ProofContainer<T>
    ): CharSequenceToContain.EntryPointStep<T, NoOpSearchBehaviour>

    /**
     * Starts the building process of a sophisticated `toContain` expectation and already applies a [NotCheckerStep] with
     * a [NotSearchBehaviour].
     *
     * @since 1.3.0
     */
    fun <T : CharSequence> notToContainBuilder(
        container: ProofContainer<T>
    ): NotCheckerStep<T, NotSearchBehaviour>

    /** @since 1.3.0 */
    fun <T : CharSequence> toStartWith(container: ProofContainer<T>, expected: CharSequence): Proof

    /** @since 1.3.0 */
    fun <T : CharSequence> notToStartWith(container: ProofContainer<T>, expected: CharSequence): Proof

    /** @since 1.3.0 */
    fun <T : CharSequence> toEndWith(container: ProofContainer<T>, expected: CharSequence): Proof

    /** @since 1.3.0 */
    fun <T : CharSequence> notToEndWith(container: ProofContainer<T>, expected: CharSequence): Proof

    /** @since 1.3.0 */
    fun <T : CharSequence> toBeEmpty(container: ProofContainer<T>): Proof

    /** @since 1.3.0 */
    fun <T : CharSequence> notToBeEmpty(container: ProofContainer<T>): Proof

    /** @since 1.3.0 */
    fun <T : CharSequence> notToBeBlank(container: ProofContainer<T>): Proof

    /** @since 1.3.0 */
    fun <T : CharSequence> toMatch(container: ProofContainer<T>, expected: Regex): Proof

    /** @since 1.3.0 */
    fun <T : CharSequence> notToMatch(container: ProofContainer<T>, expected: Regex): Proof
}
