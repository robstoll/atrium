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
 * Starts a sophisticated `contains` assertion building process based on this [Expect].
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsBuilder
 */
@Deprecated("Use toContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.toContain<T>(o)"))
infix fun <T : CharSequence> Expect<T>.contains(
    @Suppress("UNUSED_PARAMETER") o: o
): CharSequenceContains.EntryPointStep<T, NoOpSearchBehaviour> = _logic.containsBuilder()

/**
 * Starts a sophisticated `contains` assertion building process based on this [Expect] and already chooses a
 * [NotCheckerStep].
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsNotBuilder
 */
@Deprecated("Use notToContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToContain<T>(o)"))
infix fun <T : CharSequence> Expect<T>.containsNot(
    @Suppress("UNUSED_PARAMETER") o: o
): NotCheckerStep<T, NotSearchBehaviour> = _logic.containsNotBuilder()

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains the [expected]'s [toString] representation.
 *
 * It is a shortcut for `contains o atLeast 1 value expected`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.contains
 */
@Deprecated("Use toContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.toContain<T>(expected)"))
infix fun <T : CharSequence> Expect<T>.contains(expected: CharSequenceOrNumberOrChar): Expect<T> =
    this toContain o atLeast 1 value expected

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains the [toString] representation of the
 * given [values] using a non disjoint search.
 *
 * It is a shortcut for `contains o atLeast 1 the values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and
 * [Values] is defined as `values("a", "a")`, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains o exactly 2 value "a"`
 * instead of:
 *   `contains values("a", "a")`
 *
 * @param values The values which are expected to be contained within the input of the search
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case one of the [values] is not a
 *   [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.contains
 */
@Deprecated("Use toContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.toContain<T>(values)"))
infix fun <T : CharSequence> Expect<T>.contains(values: Values<CharSequenceOrNumberOrChar>): Expect<T> =
    this toContain o atLeast 1 the values

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not contain [expected]'s [toString] representation.
 *
 * It is a shortcut for `contains not value expected`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsNot
 */
@Deprecated("Use notToContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToContain<T>(expected)"))
infix fun <T : CharSequence> Expect<T>.containsNot(expected: CharSequenceOrNumberOrChar): Expect<T> =
    this notToContain o value expected

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not contain the [toString] representation
 * of the given [values].
 *
 * It is a shortcut for `contains not the values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * @param values The values which should not be found -- use the function `values(t, ...)` to create a [Values].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsNot
 */
@Deprecated("Use notToContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToContain<T>(values)"))
infix fun <T : CharSequence> Expect<T>.containsNot(values: Values<CharSequenceOrNumberOrChar>): Expect<T> =
    this notToContain o the values

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern].
 *
 * It is a shortcut for `contains o atLeast 1 regex pattern`.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsRegexStringSingle
 */
@Deprecated(
    "Use toContainRegex; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toContainRegex<T>(pattern)")
)
infix fun <T : CharSequence> Expect<T>.containsRegex(pattern: String): Expect<T> =
    this toContain o atLeast 1 regex pattern

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern].
 *
 * It is a shortcut for `contains o atLeast 1 matchFor pattern`.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsRegexSingle
 */
@Deprecated("Use toContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.toContain<T>(pattern)"))
infix fun <T : CharSequence> Expect<T>.contains(pattern: Regex): Expect<T> =
    this toContain o atLeast 1 matchFor pattern

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [regexPatterns], using a non disjoint search.
 *
 * It is a shortcut for `contains o atLeast 1 the regexPatterns(pattern, *otherPatterns)`.
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and
 * [RegexPatterns] is defined as `regexPatterns("a(b)?", "a(b)?")` as well, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains o exactly 2 regex "a(b)?"`
 * instead of:
 *   `contains o atLeast 1 the regexPatterns("a(b)?", "a(b)?")`
 *
 * @param regexPatterns The patterns which are expected to have a match against the input of the search --
 *   use the function `regexPatterns(t, ...)` to create a [RegexPatterns].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsRegexStringMultiple
 */
@Deprecated("Use toContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.toContain<T>(regexPatterns)"))
infix fun <T : CharSequence> Expect<T>.contains(regexPatterns: RegexPatterns): Expect<T> =
    this toContain o atLeast 1 the regexPatterns


/**
 * Expects that the subject of `this` expectation (a [CharSequence]) contains a sequence which matches the given
 * regular expression [patterns], using a non disjoint search.
 *
 * It is a shortcut for `contains o atLeast 1 regex All(pattern, *otherPatterns)`.
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and
 * [All] is defined as `all(Regex("a(b)?"), Regex("a(b)?"))` as well, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains o exactly 2 regex "a(b)?"`
 * instead of:
 *   `contains o atLeast 1 the all(Regex("a(b)?"), Regex("a(b)?"))`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search --
 *   use the function `all(Regex(...), ...)` to create a [All].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.containsRegexMultiple
 */
@Deprecated("Use toContain; will be removed with 1.0.0 at the latest", ReplaceWith("this.toContain<T>(patterns)"))
infix fun <T : CharSequence> Expect<T>.contains(patterns: All<Regex>): Expect<T> =
    this toContain o atLeast 1 matchFor patterns

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) starts with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.startsWith
 */
@Deprecated("Use toStartWith; will be removed with 1.0.0 at the latest", ReplaceWith("this.toStartWith<T>(expected)"))
infix fun <T : CharSequence> Expect<T>.startsWith(expected: CharSequence): Expect<T> =
    _logicAppend { startsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) starts with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.startsWithChar
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use toStartWith which expects a String; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toStartWith<T>(expected.toString())")
)
infix fun <T : CharSequence> Expect<T>.startsWith(expected: Char): Expect<T> =
    it toStartWith expected.toString()

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not start with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.startsNotWith
 */
@Deprecated(
    "Use notToStartWith; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToStartWith<T>(expected)")
)
infix fun <T : CharSequence> Expect<T>.startsNotWith(expected: CharSequence): Expect<T> =
    _logicAppend { startsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not start with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.startsNotWithChar
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use notToStartWith which expects a String; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToStartWith<T>(expected.toString())")
)
infix fun <T : CharSequence> Expect<T>.startsNotWith(expected: Char): Expect<T> =
    it notToStartWith expected.toString()


/**
 * Expects that the subject of `this` expectation (a [CharSequence]) ends with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.endsWith
 */
@Deprecated(
    "Use toEndWith; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toEndWith<T>(expected)")
)
infix fun <T : CharSequence> Expect<T>.endsWith(expected: CharSequence): Expect<T> =
    _logicAppend { endsWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) ends with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.endsWithChar
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use toEndWith which expects a String; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.toEndWith<T>(expected.toString())")
)
infix fun <T : CharSequence> Expect<T>.endsWith(expected: Char): Expect<T> =
    it toEndWith expected.toString()

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not end with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.endsNotWith
 */
@Deprecated(
    "Use notToEndWith; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToEndWith<T>(expected)")
)
infix fun <T : CharSequence> Expect<T>.endsNotWith(expected: CharSequence): Expect<T> =
    _logicAppend { endsNotWith(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) does not end with [expected].
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.endsNotWithChar
 *
 * @since 0.12.0
 */
@Deprecated(
    "Use notToEndWith which expects a String; will be removed with 1.0.0 at the latest",
    ReplaceWith("this.notToEndWith<T>(expected.toString())")
)
infix fun <T : CharSequence> Expect<T>.endsNotWith(expected: Char): Expect<T> =
    it notToEndWith expected.toString()


//TODO move to charSequenceExpectations.kt with 0.18.0
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

//TODO move to charSequenceExpectations.kt with 0.18.0
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

//TODO move to charSequenceExpectations.kt with 0.18.0
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

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) matches the given [expected] [Regex].
 *
 * In contrast to [containsRegex] it does not look for a partial match but for an entire match.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.matches
 *
 * @since 0.12.0
 */
@Deprecated("Use toMatch; will be removed with 1.0.0 at the latest", ReplaceWith("this.toMatch<T>(expected)"))
infix fun <T : CharSequence> Expect<T>.matches(expected: Regex): Expect<T> =
    _logicAppend { matches(expected) }

/**
 * Expects that the subject of `this` expectation (a [CharSequence]) mismatches the given [expected] [Regex].
 *
 * In contrast to `containsNot.regex` it does not look for a partial match but for an entire match.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.deprecated.CharSequenceAssertionSamples.mismatches
 *
 * @since 0.12.0
 */
@Deprecated("Use notToMatch; will be removed with 1.0.0 at the latest", ReplaceWith("this.notToMatch<T>(expected)"))
infix fun <T : CharSequence> Expect<T>.mismatches(expected: Regex): Expect<T> =
    _logicAppend { mismatches(expected) }
