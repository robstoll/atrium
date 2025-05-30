//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.All
import ch.tutteli.atrium.api.infix.en_GB.creating.RegexPatterns
import ch.tutteli.atrium.api.infix.en_GB.creating.Values

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep

/**
 * Starts a sophisticated `toContain` assertion building process based on this [Expect].
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContainBuilder
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toContain(
    @Suppress("UNUSED_PARAMETER") o: o
): CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour> = _logic.containsBuilder()

/**
 * Starts a sophisticated `toContain` assertion building process based on this [Expect] and already chooses a
 * [NotCheckerStep].
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.notToContainBuilder
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.notToContain(
    @Suppress("UNUSED_PARAMETER") o: o
): NotCheckerStep<T, NotSearchBehaviour> = _logic.containsNotBuilder()

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains the [expected]'s [toString] representation.
 *
 * It is a shortcut for `toContain o atLeast 1 value expected`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContain
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toContain(expected: CharSequenceOrNumberOrChar): Expect<T> =
    this toContain o atLeast 1 value expected

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains the [toString] representation of the
 * given [values] using a non-disjoint search.
 *
 * It is a shortcut for `toContain o atLeast 1 the values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and
 * [Values] is defined as `values("a", "a")`, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain o exactly 2 value "a"`
 * instead of:
 *   `toContain values("a", "a")`
 *
 * @param values The values which are expected to be contained within the input of the search
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case one of the [values] is not a
 *   [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContain
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toContain(values: Values<CharSequenceOrNumberOrChar>): Expect<T> =
    this toContain o atLeast 1 the values

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not contain [expected]'s [toString] representation.
 *
 * It is a shortcut for `noToContain o value expected`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.notToContain
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.notToContain(expected: CharSequenceOrNumberOrChar): Expect<T> =
    this notToContain o value expected

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not contain the [toString] representation
 * of the given [values].
 *
 * It is a shortcut for `notToContain o the values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @param values The values which should not be found -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.notToContain
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.notToContain(values: Values<CharSequenceOrNumberOrChar>): Expect<T> =
    this notToContain o the values

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern].
 *
 * It is a shortcut for `toContain o atLeast 1 regex pattern`.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContainRegexStringSingle
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toContainRegex(pattern: String): Expect<T> =
    this toContain o atLeast 1 regex pattern

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern].
 *
 * It is a shortcut for `toContain o atLeast 1 matchFor pattern`.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContainRegexSingle
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toContain(pattern: Regex): Expect<T> =
    this toContain o atLeast 1 matchFor pattern

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [regexPatterns], using a non-disjoint search.
 *
 * It is a shortcut for `toContain o atLeast 1 the regexPatterns(pattern, *otherPatterns)`.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and
 * [RegexPatterns] is defined as `regexPatterns("a(b)?", "a(b)?")` as well, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain o exactly 2 regex "a(b)?"`
 * instead of:
 *   `toContain o atLeast 1 the regexPatterns("a(b)?", "a(b)?")`
 *
 * @param regexPatterns The patterns which are expected to have a match against the input of the search --
 *   use the function `regexPatterns(t, ...)` to create a [RegexPatterns].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContainRegexStringMultiple
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toContain(regexPatterns: RegexPatterns): Expect<T> =
    this toContain o atLeast 1 the regexPatterns


/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [patterns], using a non-disjoint search.
 *
 * It is a shortcut for `toContain o atLeast 1 regex All(pattern, *otherPatterns)`.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and
 * [All] is defined as `all(Regex("a(b)?"), Regex("a(b)?"))` as well, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain o exactly 2 regex "a(b)?"`
 * instead of:
 *   `toContain o atLeast 1 the all(Regex("a(b)?"), Regex("a(b)?"))`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search --
 *   use the function `all(Regex(...), ...)` to create a [All].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContainRegexMultiple
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toContain(patterns: All<Regex>): Expect<T> =
    this toContain o atLeast 1 matchFor patterns

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) starts with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toStartWith
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toStartWith(expected: CharSequence): Expect<T> =
    _logicAppend { startsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not start with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.notToStartWith
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.notToStartWith(expected: CharSequence): Expect<T> =
    _logicAppend { startsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) ends with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toEndWith
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toEndWith(expected: CharSequence): Expect<T> =
    _logicAppend { endsWith(expected) }


/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not end with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.notToEndWith
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.notToEndWith(expected: CharSequence): Expect<T> =
    _logicAppend { endsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) matches the given [Regex] [pattern].
 *
 * In contrast to [toContainRegex] it does not look for a partial match but for an entire match.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toMatch
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.toMatch(pattern: Regex): Expect<T> =
    _logicAppend { matches(pattern) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) mismatches the given [Regex] [pattern].
 *
 * In contrast to `notToContain o regex expected` it does not look for a partial match but for an entire match.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.notToMatch
 *
 * @since 0.17.0
 */
infix fun <T : CharSequence> Expect<T>.notToMatch(pattern: Regex): Expect<T> =
    _logicAppend { mismatches(pattern) }


/**
 * Expects that the subject of `this` expectation (a [CharSequence]) [CharSequence].[kotlin.text.isEmpty].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toBeEmpty
 */
infix fun <T : CharSequence> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _logicAppend { isEmpty() }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.notToBeEmpty
 */
infix fun <T : CharSequence> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") empty: empty): Expect<T> =
    _logicAppend { isNotEmpty() }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) [CharSequence].[kotlin.text.isNotBlank].
 *
 * @param blank Use the pseudo-keyword `blank`.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.notToBeBlank
 */
infix fun <T : CharSequence> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") blank: blank): Expect<T> =
    _logicAppend { isNotBlank() }
