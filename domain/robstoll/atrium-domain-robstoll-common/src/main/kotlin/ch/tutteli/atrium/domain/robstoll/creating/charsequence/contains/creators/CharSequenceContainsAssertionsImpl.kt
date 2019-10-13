package ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.creators.CharSequenceContainsAssertions
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.creators.*
import ch.tutteli.atrium.reporting.translating.Translatable


class CharSequenceContainsAssertionsImpl : CharSequenceContainsAssertions {

    override fun <T : CharSequence> values(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<Any>
    ): AssertionGroup = _containsValues(checkerOption, expected)

    override fun <T : CharSequence> valuesIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<Any>
    ): AssertionGroup = _containsValuesIgnoringCase(checkerOption, expected)

    override fun <T : CharSequence> defaultTranslationOf(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<Translatable>
    ): AssertionGroup = _containsDefaultTranslationOf(checkerOption, expected)

    override fun <T : CharSequence> defaultTranslationOfIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<Translatable>
    ): AssertionGroup = _containsDefaultTranslationOfIgnoringCase(checkerOption, expected)

    override fun <T : CharSequence> regex(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<String>
    ): AssertionGroup = _containsRegex(checkerOption, expected)

    override fun <T : CharSequence> regexIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<String>
    ): AssertionGroup = _containsRegexIgnoringCase(checkerOption, expected)
}
