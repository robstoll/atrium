// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.creating.proofs.charsequence.contains.creators

import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.charsequence.contains.creators.impl.DefaultCharSequenceToContainProofs


    /** @since 1.3.0 */
fun <T : CharSequence> CharSequenceToContain.CheckerStepCore<T, NoOpSearchBehaviour>.values(expected: List<CharSequenceOrNumberOrChar>): ProofGroup =
    impl.values(this, expected)

    /** @since 1.3.0 */
fun <T : CharSequence> CharSequenceToContain.CheckerStepCore<T, IgnoringCaseSearchBehaviour>.valuesIgnoringCase(expected: List<CharSequenceOrNumberOrChar>): ProofGroup =
    impl.valuesIgnoringCase(this, expected)

    /** @since 1.3.0 */
fun <T : CharSequence> CharSequenceToContain.CheckerStepCore<T, NoOpSearchBehaviour>.regex(expected: List<Regex>): ProofGroup =
    impl.regex(this, expected)

    /** @since 1.3.0 */
fun <T : CharSequence> CharSequenceToContain.CheckerStepCore<T, IgnoringCaseSearchBehaviour>.regexIgnoringCase(expected: List<String>): ProofGroup =
    impl.regexIgnoringCase(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <SubjectT : CharSequence, SearchBehaviourT : CharSequenceToContain.SearchBehaviour> CharSequenceToContain.CheckerStepCore<SubjectT, SearchBehaviourT>.impl: CharSequenceToContainProofs
    get() = entryPointStepCore.container.getImpl(CharSequenceToContainProofs::class) { DefaultCharSequenceToContainProofs() }
