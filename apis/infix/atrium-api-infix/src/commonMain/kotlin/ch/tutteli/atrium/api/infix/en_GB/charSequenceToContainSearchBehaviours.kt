//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.ignoringCase
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.notCheckerStep
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceToContainSearchBehaviourSamples.ignoringCase
 */
infix fun <T : CharSequence> CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour>.ignoring(
    @Suppress("UNUSED_PARAMETER") case: case
): CharSequenceContains.EntryPointStep<T, IgnoringCaseSearchBehaviour> = _logic.ignoringCase

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceToContainSearchBehaviourSamples.ignoringCaseWithNotChecker
 */
infix fun <T : CharSequence> NotCheckerStep<T, NotSearchBehaviour>.ignoring(
    @Suppress("UNUSED_PARAMETER") case: case
): NotCheckerStep<T, IgnoringCaseSearchBehaviour> =
    _logic.entryPointStepLogic.ignoringCase._logic.notCheckerStep()
