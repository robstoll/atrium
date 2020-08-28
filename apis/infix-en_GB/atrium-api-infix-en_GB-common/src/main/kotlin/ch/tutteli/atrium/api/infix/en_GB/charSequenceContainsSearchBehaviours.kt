package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerOption
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.ignoringCase
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.notCheckerOption

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoring(
    @Suppress("UNUSED_PARAMETER") case: case
): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour> = _logic.ignoringCase

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoring(
    @Suppress("UNUSED_PARAMETER") case: case
): NotCheckerOption<T, IgnoringCaseSearchBehaviour> =
    _logic.containsBuilder.ignoringCase._logic.notCheckerOption()
