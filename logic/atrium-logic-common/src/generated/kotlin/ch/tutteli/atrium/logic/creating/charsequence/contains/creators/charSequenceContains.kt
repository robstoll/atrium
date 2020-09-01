//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour


fun <T : CharSequence> CharSequenceContains.CheckerStepLogic<T, NoOpSearchBehaviour>.values(expected: List<CharSequenceOrNumberOrChar>): AssertionGroup = _charSequenceContainsImpl.values(this, expected)

fun <T : CharSequence> CharSequenceContains.CheckerStepLogic<T, IgnoringCaseSearchBehaviour>.valuesIgnoringCase(expected: List<CharSequenceOrNumberOrChar>): AssertionGroup = _charSequenceContainsImpl.valuesIgnoringCase(this, expected)

fun <T : CharSequence> CharSequenceContains.CheckerStepLogic<T, NoOpSearchBehaviour>.regex(expected: List<Regex>): AssertionGroup = _charSequenceContainsImpl.regex(this, expected)

fun <T : CharSequence> CharSequenceContains.CheckerStepLogic<T, IgnoringCaseSearchBehaviour>.regexIgnoringCase(expected: List<String>): AssertionGroup = _charSequenceContainsImpl.regexIgnoringCase(this, expected)
