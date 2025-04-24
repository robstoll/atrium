// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.impl.DefaultCharSequenceProofs


    /**
     * Starts the building process of a sophisticated `toContain` expectation.
     *
     * @since 1.3.0
     */
fun <T : CharSequence> ProofContainer<T>.toContainBuilder(): CharSequenceToContain.EntryPointStep<T, NoOpSearchBehaviour> = impl.toContainBuilder(this)

    /**
     * Starts the building process of a sophisticated `toContain` expectation and already applies a [NotCheckerStep] with
     * a [NotSearchBehaviour].
     *
     * @since 1.3.0
     */
fun <T : CharSequence> ProofContainer<T>.notToContainBuilder(): NotCheckerStep<T, NotSearchBehaviour> = impl.notToContainBuilder(this)

    /** @since 1.3.0 */
fun <T : CharSequence> ProofContainer<T>.toStartWith(expected: CharSequence): Proof = impl.toStartWith(this, expected)

    /** @since 1.3.0 */
fun <T : CharSequence> ProofContainer<T>.notToStartWith(expected: CharSequence): Proof = impl.notToStartWith(this, expected)

    /** @since 1.3.0 */
fun <T : CharSequence> ProofContainer<T>.toEndWith(expected: CharSequence): Proof = impl.toEndWith(this, expected)

    /** @since 1.3.0 */
fun <T : CharSequence> ProofContainer<T>.notToEndWith(expected: CharSequence): Proof = impl.notToEndWith(this, expected)

    /** @since 1.3.0 */
fun <T : CharSequence> ProofContainer<T>.toBeEmpty(): Proof = impl.toBeEmpty(this)

    /** @since 1.3.0 */
fun <T : CharSequence> ProofContainer<T>.notToBeEmpty(): Proof = impl.notToBeEmpty(this)

    /** @since 1.3.0 */
fun <T : CharSequence> ProofContainer<T>.notToBeBlank(): Proof = impl.notToBeBlank(this)

    /** @since 1.3.0 */
fun <T : CharSequence> ProofContainer<T>.toMatch(expected: Regex): Proof = impl.toMatch(this, expected)

    /** @since 1.3.0 */
fun <T : CharSequence> ProofContainer<T>.notToMatch(expected: Regex): Proof = impl.notToMatch(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> ProofContainer<T>.impl: CharSequenceProofs
    get() = getImpl(CharSequenceProofs::class) { DefaultCharSequenceProofs() }
