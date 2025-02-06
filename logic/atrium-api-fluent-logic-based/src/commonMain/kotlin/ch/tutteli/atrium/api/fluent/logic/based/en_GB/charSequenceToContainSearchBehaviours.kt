//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.ignoringCase
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.notCheckerStep

/**
 * Defines that the v behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainSearchBehaviourSamples.ignoringCase
 */
val <T : CharSequence> EntryPointStep<T, NoOpSearchBehaviour>.ignoringCase: EntryPointStep<T, IgnoringCaseSearchBehaviour>
    get() = _logic.ignoringCase

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainSearchBehaviourSamples.ignoringCaseWithNotChecker
 */
val <T : CharSequence> NotCheckerStep<T, NotSearchBehaviour>.ignoringCase: NotCheckerStep<T, IgnoringCaseSearchBehaviour>
    get() = _logic.entryPointStepLogic.ignoringCase._logic.notCheckerStep()
