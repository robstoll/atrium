package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour

/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case)
    = CharSequenceContainsBuilder(plant, CharSequenceContainsIgnoringCaseSearchBehaviour)
