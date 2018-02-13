package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.creating.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder
import ch.tutteli.atrium.creating.AssertImpl
import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour

/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> CharSequenceContains.Builder<T, CharSequenceContainsNoOpSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case)
    = AssertImpl.charSequence.contains.searchBehaviours.ignoringCase(this)

/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> CharSequenceContainsNotCheckerBuilder<T, CharSequenceContainsNotSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case)
    = CharSequenceContainsNotCheckerBuilder(containsBuilder ignoring case)
