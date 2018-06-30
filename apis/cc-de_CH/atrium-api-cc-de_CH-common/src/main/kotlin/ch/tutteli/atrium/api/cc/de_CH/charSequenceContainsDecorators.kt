package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @return The newly created builder.
 */
expect val <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoriereGrossKleinschreibung
    : CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>

/**
 * Defines that the search behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @return The newly created builder.
 */
expect val <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoriereGrossKleinschreibung
    : NotCheckerOption<T, IgnoringCaseSearchBehaviour>
