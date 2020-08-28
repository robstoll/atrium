package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerOption
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.ignoringCase
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.notCheckerOption

/**
 * Defines that the v behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoringCase: CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
    get() : CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour> = _logic.ignoringCase

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoringCase: NotCheckerOption<T, IgnoringCaseSearchBehaviour>
    get() : NotCheckerOption<T, IgnoringCaseSearchBehaviour> =
        _logic.containsBuilder.ignoringCase._logic.notCheckerOption()
