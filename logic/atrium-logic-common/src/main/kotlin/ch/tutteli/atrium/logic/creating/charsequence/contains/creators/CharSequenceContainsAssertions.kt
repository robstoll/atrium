package ch.tutteli.atrium.logic.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Collection of assertion functions which are intended to be used as part of the final step of a sophisticated
 * `contains`-building process for [CharSequence].
 */
interface CharSequenceContainsAssertions {

    fun <T : CharSequence> values(
        checkerStepLogic: CharSequenceContains.CheckerStepLogic<T, NoOpSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): AssertionGroup

    fun <T : CharSequence> valuesIgnoringCase(
        checkerStepLogic: CharSequenceContains.CheckerStepLogic<T, IgnoringCaseSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): AssertionGroup

    fun <T : CharSequence> regex(
        checkerStepLogic: CharSequenceContains.CheckerStepLogic<T, NoOpSearchBehaviour>,
        expected: List<Regex>
    ): AssertionGroup

    fun <T : CharSequence> regexIgnoringCase(
        checkerStepLogic: CharSequenceContains.CheckerStepLogic<T, IgnoringCaseSearchBehaviour>,
        expected: List<String>
    ): AssertionGroup
}
