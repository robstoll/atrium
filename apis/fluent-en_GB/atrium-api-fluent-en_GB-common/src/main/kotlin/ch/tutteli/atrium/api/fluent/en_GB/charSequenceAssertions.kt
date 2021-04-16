//TODO remove both annotations with 1.0.0
@file:JvmMultifileClass
@file:JvmName("CharSequenceAssertionsKt")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.steps.NotCheckerStep
import ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Starts a sophisticated `contains` assertion building process based on this [Expect].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsBuilder
 */
@Deprecated("Use toContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.toContain"))
val <T : CharSequence> Expect<T>.contains: CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour>
    get() = _logic.containsBuilder()

/**
 * Starts a sophisticated `contains` assertion building process based on this [Expect] and already chooses a
 * [NotCheckerStep].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsNotBuilder
 */
@Deprecated("Use notToContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToContain"))
val <T : CharSequence> Expect<T>.containsNot: NotCheckerStep<T, NotSearchBehaviour>
    get() = _logic.containsNotBuilder()

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if given), using a non disjoint search.
 *
 * It is a shortcut for `contains.atLeast(1).values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and [expected]
 * is defined as `"a"` and one [otherExpected] is defined as `"a"` as well, then both match, even though they match the
 * same sequence in the input of the search. Use the property `contains` to create a more sophisticated `contains`
 * assertion where you can use options such as [atLeast], [atMost] and [exactly] to control the number of occurrences
 * you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).value("a")`
 * instead of:
 *   `contains("a", "a")`
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.contains
 */
@Deprecated(
    "Use toContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContain<T>(expected, *otherExpected)")
)
fun <T : CharSequence> Expect<T>.contains(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = toContain.atLeast(1).values(expected, *otherExpected)

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if given).
 *
 * It is a shortcut for `containsNot.values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsNot
 */
@Deprecated(
    "Use notToContain; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToContain<T>(expected, *otherExpected)")
)
fun <T : CharSequence> Expect<T>.containsNot(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = notToContain.values(expected, *otherExpected)

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern] as well as the [otherPatterns] (if given), using a non disjoint search.
 *
 * It is a shortcut for `contains.atLeast(1).regex(pattern, *otherPatterns)`.
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).regex("a(b)?")`
 * instead of:
 *   `contains.atLeast(1).regex("a(b)?", "a(b)?")`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsRegexString
 */
@Deprecated(
    "Use toContainRegex; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainRegex<T>(pattern, *otherPatterns)")
)
fun <T : CharSequence> Expect<T>.containsRegex(pattern: String, vararg otherPatterns: String): Expect<T> =
    toContain.atLeast(1).regex(pattern, *otherPatterns)

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern] as well as the [otherPatterns] (if given), using a non disjoint search.
 *
 * It is a shortcut for `contains.atLeast(1).regex(pattern, *otherPatterns)`.
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).regex(Regex("a(b)?"))`
 * instead of:
 *   `contains.atLeast(1).regex(Regex("a(b)?"), Regex("a(b)?"))`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsRegex
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use toContainRegex; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainRegex<T>(pattern, *otherPatterns)")
)
fun <T : CharSequence> Expect<T>.containsRegex(pattern: Regex, vararg otherPatterns: Regex): Expect<T> =
    toContain.atLeast(1).regex(pattern, *otherPatterns)

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) starts with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.startsWith
 */
@Deprecated("Use toStartWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.toStartWith<T>(expected)"))
fun <T : CharSequence> Expect<T>.startsWith(expected: CharSequence): Expect<T> =
    _logicAppend { startsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) starts with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.startsWithChar
 *
 * @since 0.9.0
 */
@Deprecated("Use toStartWith which expects a String; will be removed with 1.0.0 at the latest", ReplaceWith("this.toStartWith<T>(expected.toString())"))
fun <T : CharSequence> Expect<T>.startsWith(expected: Char): Expect<T> =
    toStartWith(expected.toString())

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not start with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.startsNotWith
 */
@Deprecated("Use notToStartWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToStartWith<T>(expected)"))
fun <T : CharSequence> Expect<T>.startsNotWith(expected: CharSequence): Expect<T> =
    _logicAppend { startsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not start with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.startsNotWithChar
 *
 * @since 0.9.0
 */
@Deprecated("Use notToStartWith which expects a String; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToStartWith<T>(expected.toString())"))
fun <T : CharSequence> Expect<T>.startsNotWith(expected: Char): Expect<T> =
    notToStartWith(expected.toString())


/**
 * Expects that the subject of `this` expectation (a [CharSequence]) ends with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.endsWith
 */
@Deprecated("Use toEndWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.toEndWith<T>(expected)"))
fun <T : CharSequence> Expect<T>.endsWith(expected: CharSequence): Expect<T> =
    _logicAppend { endsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) ends with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.endsWithChar
 *
 * @since 0.9.0
 */
@Deprecated("Use toEndWith with expects a String; will be removed with 1.0.0 at the latest", ReplaceWith("this.toEndWith<T>(expected.toString())"))
fun <T : CharSequence> Expect<T>.endsWith(expected: Char): Expect<T> =
    toEndWith(expected.toString())

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not end with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.endsNotWith
 */
@Deprecated("Use notToEndWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToEndWith<T>(expected)"))
fun <T : CharSequence> Expect<T>.endsNotWith(expected: CharSequence): Expect<T> =
    _logicAppend { endsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not end with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.endsNotWithChar
 *
 * @since 0.9.0
 */
@Deprecated("Use notToEndWith which expects a String; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToEndWith<T>(expected.toString())"))
fun <T : CharSequence> Expect<T>.endsNotWith(expected: Char): Expect<T> =
    notToEndWith(expected.toString())


/**
 * Expects that the subject of `this` expectation (a [CharSequence]) [CharSequence].[kotlin.text.isEmpty].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.isEmpty
 */
@Deprecated("Use toBeEmpty; will be removed with 1.0.0 at the latest", ReplaceWith("this.toBeEmpty<T>()"))
fun <T : CharSequence> Expect<T>.isEmpty(): Expect<T> =
    _logicAppend { isEmpty() }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.isNotEmpty
 */
@Deprecated("Use notToBeEmpty; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToBeEmpty<T>()"))
fun <T : CharSequence> Expect<T>.isNotEmpty(): Expect<T> =
    _logicAppend { isNotEmpty() }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) [CharSequence].[kotlin.text.isNotBlank].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.isNotBlank
 */
@Deprecated("Use notToBeBlank; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToBeBlank<T>()"))
fun <T : CharSequence> Expect<T>.isNotBlank(): Expect<T> =
    _logicAppend { isNotBlank() }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) matches the given [expected] [Regex].
 *
 * In contrast to [containsRegex] it does not look for a partial match but for an entire match.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.matches
 *
 * @since 0.9.0
 */
@Deprecated("Use toMatch; will be removed with 1.0.0 at the latest", ReplaceWith("this.toMatch<T>(expected)"))
fun <T : CharSequence> Expect<T>.matches(expected: Regex): Expect<T> =
    _logicAppend { matches(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) mismatches the given [expected] [Regex].
 *
 * In contrast to `containsNot.regex` it does not look for a partial match but for an entire match.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated.CharSequenceAssertionSamples.mismatches
 *
 * @since 0.9.0
 */
@Deprecated("Use notToMatch; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToMatch<T>(expected)"))
fun <T : CharSequence> Expect<T>.mismatches(expected: Regex): Expect<T> =
    _logicAppend { mismatches(expected) }
