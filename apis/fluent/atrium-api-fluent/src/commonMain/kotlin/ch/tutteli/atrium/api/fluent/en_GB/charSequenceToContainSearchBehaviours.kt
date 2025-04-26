package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium._core
import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain.EntryPointStep
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.ignoringCase
import ch.tutteli.atrium.creating.proofs.charsequence.contains.steps.notCheckerStep

/**
 * Defines that the v behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainSearchBehaviourSamples.ignoringCase
 */
val <T : CharSequence> EntryPointStep<T, NoOpSearchBehaviour>.ignoringCase: EntryPointStep<T, IgnoringCaseSearchBehaviour>
    get() = _core.ignoringCase

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainSearchBehaviourSamples.ignoringCaseWithNotChecker
 */
val <T : CharSequence> NotCheckerStep<T, NotSearchBehaviour>.ignoringCase: NotCheckerStep<T, IgnoringCaseSearchBehaviour>
    get() = _core.entryPointStepCore.ignoringCase._core.notCheckerStep()
