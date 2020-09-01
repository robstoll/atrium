//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The access point to an implementation of [CharSequenceContainsAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Deprecated("Use the CharSequenceContainsAssertions from atrium-logic; will be removed with 1.0.0")
val charSequenceContainsAssertions by lazy { loadSingleService(CharSequenceContainsAssertions::class) }


/**
 * Defines the minimum set of `contains` assertion functions for [CharSequence],
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated("Use the CharSequenceContainsAssertions from atrium-logic; will be removed with 1.0.0")
interface CharSequenceContainsAssertions {

    fun <T : CharSequence> values(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): AssertionGroup

    fun <T : CharSequence> valuesIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<CharSequenceOrNumberOrChar>
    ): AssertionGroup

    //TODO remove with 1.0.0
    @Deprecated("Will be removed with 1.0.0")
    fun <T : CharSequence> defaultTranslationOf(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<Translatable>
    ): AssertionGroup

    //TODO remove with 1.0.0
    @Deprecated("Will be removed with 1.0.0")
    fun <T : CharSequence> defaultTranslationOfIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<Translatable>
    ): AssertionGroup

    fun <T : CharSequence> regex(
        checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
        expected: List<Regex>
    ): AssertionGroup

    fun <T : CharSequence> regexIgnoringCase(
        checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
        expected: List<String>
    ): AssertionGroup
}
