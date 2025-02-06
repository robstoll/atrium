//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium._coreAppend
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar

/**
 * Starts a sophisticated `toContain` assertion building process based on this [Expect].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.toContainBuilder
 *
 * @since 0.17.0
 */
val <T : CharSequence> Expect<T>.toContain: CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour>
    get() = _logic.containsBuilder()

/**
 * Starts a sophisticated `noToContain` assertion building process based on this [Expect] and already chooses a
 * [NotCheckerStep].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.notToContainBuilder
 *
 * @since 0.17.0
 */
val <T : CharSequence> Expect<T>.notToContain: NotCheckerStep<T, NotSearchBehaviour>
    get() = _logic.containsNotBuilder()

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if given), using a non-disjoint search.
 *
 * It is a shortcut for `toContain.atLeast(1).values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and [expected]
 * is defined as `"a"` and one [otherExpected] is defined as `"a"` as well, then both match, even though they match the
 * same sequence in the input of the search. Use the property `toContain` to create a more sophisticated `toContain`
 * assertion where you can use options such as [atLeast], [atMost] and [exactly] to control the number of occurrences
 * you expect.
 *
 * Meaning you might want to use:
 *   `toContain.exactly(2).value("a")`
 * instead of:
 *   `toContain("a", "a")`
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.toContain
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.toContain(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = toContain.atLeast(1).values(expected, *otherExpected)

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if given).
 *
 * It is a shortcut for `notToContain.values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.notToContain
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.notToContain(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = notToContain.values(expected, *otherExpected)

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern] as well as the [otherPatterns] (if given), using a non-disjoint search.
 *
 * It is a shortcut for `toContain.atLeast(1).regex(pattern, *otherPatterns)`.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.exactly(2).regex("a(b)?")`
 * instead of:
 *   `toContain.atLeast(1).regex("a(b)?", "a(b)?")`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.toContainRegexString
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.toContainRegex(pattern: String, vararg otherPatterns: String): Expect<T> =
    toContain.atLeast(1).regex(pattern, *otherPatterns)

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern] as well as the [otherPatterns] (if given), using a non-disjoint search.
 *
 * It is a shortcut for `toContain.atLeast(1).regex(pattern, *otherPatterns)`.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.exactly(2).regex(Regex("a(b)?"))`
 * instead of:
 *   `toContain.atLeast(1).regex(Regex("a(b)?"), Regex("a(b)?"))`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.toContainRegex
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.toContainRegex(pattern: Regex, vararg otherPatterns: Regex): Expect<T> =
    toContain.atLeast(1).matchFor(pattern, *otherPatterns)

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) starts with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.toStartWith
 */
fun <T : CharSequence> Expect<T>.toStartWith(expected: CharSequence): Expect<T> =
    _coreAppend { startsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not start with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.notToStartWith
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.notToStartWith(expected: CharSequence): Expect<T> =
    _coreAppend { startsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) ends with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.toEndWith
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.toEndWith(expected: CharSequence): Expect<T> =
    _coreAppend { endsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not end with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.notToEndWith
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.notToEndWith(expected: CharSequence): Expect<T> =
    _coreAppend { endsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) [CharSequence].[kotlin.text.isEmpty].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.toBeEmpty
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.toBeEmpty(): Expect<T> =
    _coreAppend { isEmpty() }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.notToBeEmpty
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.notToBeEmpty(): Expect<T> =
    _coreAppend { isNotEmpty() }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) [CharSequence].[kotlin.text.isNotBlank].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.notToBeBlank
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.notToBeBlank(): Expect<T> =
    _coreAppend { isNotBlank() }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) matches the given [Regex] [pattern] .
 *
 * In contrast to [toContainRegex] it does not look for a partial match but for an entire match.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.toMatch
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.toMatch(pattern: Regex): Expect<T> =
    _coreAppend { matches(pattern) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) mismatches the given [Regex] [pattern].
 *
 * In contrast to `notToContain.regex(pattern)` it does not look for a partial match but for an entire match.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceExpectationSamples.notToMatch
 *
 * @since 0.17.0
 */
fun <T : CharSequence> Expect<T>.notToMatch(pattern: Regex): Expect<T> =
    _coreAppend { mismatches(pattern) }
