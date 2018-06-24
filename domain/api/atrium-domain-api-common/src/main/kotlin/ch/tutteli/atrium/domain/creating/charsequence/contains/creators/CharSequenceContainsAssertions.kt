package ch.tutteli.atrium.domain.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The access point to an implementation of [CharSequenceContainsAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val charSequenceContainsAssertions by lazy { loadSingleService(CharSequenceContainsAssertions::class) }


/**
 * Defines the minimum set of `contains` assertion functions for [CharSequence],
 * which an implementation of the domain of Atrium has to provide.
 */
interface CharSequenceContainsAssertions {

    fun <T : CharSequence> values(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<Any>
    ): AssertionGroup

    fun <T : CharSequence> valuesIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<Any>
    ): AssertionGroup

    fun <T : CharSequence> defaultTranslationOf(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<Translatable>
    ): AssertionGroup

    fun <T : CharSequence> defaultTranslationOfIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<Translatable>
    ): AssertionGroup

    fun <T : CharSequence> regex(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<String>
    ): AssertionGroup

    fun <T : CharSequence> regexIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<String>
    ): AssertionGroup
}
