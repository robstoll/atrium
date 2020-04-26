package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.All
import ch.tutteli.atrium.api.infix.en_GB.creating.RegexPatterns
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.domain.creating.typeutils.CharSequenceOrNumberOrChar


/**
 * Creates a [CharSequenceContains.Builder] based on this [Expect] which allows to define
 * a sophisticated `contains` assertion.
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> Expect<T>.contains(
    @Suppress("UNUSED_PARAMETER") o: o
): CharSequenceContains.Builder<T, NoOpSearchBehaviour> = ExpectImpl.charSequence.containsBuilder(this)

/**
 * Creates a [CharSequenceContains.Builder] based on this [Expect] which allows to define
 * more sophisticated `contains not` assertion.
 *
 * @param o The filler object [o].
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> Expect<T>.containsNot(
    @Suppress("UNUSED_PARAMETER") o: o
): NotCheckerOption<T, NotSearchBehaviour> = NotCheckerOptionImpl(ExpectImpl.charSequence.containsNotBuilder(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) contains the [expected]'s [toString] representation.
 *
 * It is a shortcut for `contains o atLeast 1 value expected`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> Expect<T>.contains(expected: CharSequenceOrNumberOrChar): Expect<T> =
    this contains o atLeast 1 value expected

/**
 * Expects that the subject of the assertion (a [CharSequence]) contains the [toString] representation of the
 * given [values] using a non disjoint search.
 *
 * It is a shortcut for `contains o atLeast 1 the values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
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
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a
 *   [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> Expect<T>.contains(values: Values<CharSequenceOrNumberOrChar>): Expect<T> =
    this contains o atLeast 1 the values

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not contain [expected]'s [toString] representation.
 *
 * It is a shortcut for `contains not value expected`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.containsNot(expected: CharSequenceOrNumberOrChar) =
    this containsNot o value expected

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not contain the [toString] representation
 * of the given [values].
 *
 * It is a shortcut for `contains not the values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * @param values The values which should not be found -- use the function `values(t, ...)` to create a [Values].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.containsNot(values: Values<CharSequenceOrNumberOrChar>) =
    this containsNot o the values

/**
 * Expects that the subject of the assertion (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern].
 *
 * It is a shortcut for `contains o atLeast 1 regex pattern`.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.containsRegex(pattern: String): Expect<T> =
    this contains o atLeast 1 regex pattern

/**
 * Expects that the subject of the assertion (a [CharSequence]) contains a sequence which matches the given
 * regular expression [pattern].
 *
 * It is a shortcut for `contains o atLeast 1 matchFor pattern`.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.contains(pattern: Regex): Expect<T> =
    this contains o atLeast 1 matchFor pattern

/**
 * Expects that the subject of the assertion (a [CharSequence]) contains a sequence which matches the given
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
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.contains(regexPatterns: RegexPatterns): Expect<T> =
    this contains o atLeast 1 the regexPatterns


/**
 * Expects that the subject of the assertion (a [CharSequence]) contains a sequence which matches the given
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
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.contains(patterns: All<Regex>): Expect<T> =
    this contains o atLeast 1 matchFor patterns
/**
 * Expects that the subject of the assertion (a [CharSequence]) starts with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.startsWith(expected: CharSequence) =
    addAssertion(ExpectImpl.charSequence.startsWith(this, expected))

/**
 * Expects that the subject of the assertion (a [CharSequence]) starts with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : CharSequence> Expect<T>.startsWith(expected: Char) =
    it startsWith expected.toString()

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not start with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.startsNotWith(expected: CharSequence) =
    addAssertion(ExpectImpl.charSequence.startsNotWith(this, expected))

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not start with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : CharSequence> Expect<T>.startsNotWith(expected: Char) =
    it startsNotWith expected.toString()


/**
 * Expects that the subject of the assertion (a [CharSequence]) ends with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.endsWith(expected: CharSequence) =
    addAssertion(ExpectImpl.charSequence.endsWith(this, expected))

/**
 * Expects that the subject of the assertion (a [CharSequence]) ends with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : CharSequence> Expect<T>.endsWith(expected: Char) =
    it endsWith expected.toString()

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not end with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.endsNotWith(expected: CharSequence) =
    addAssertion(ExpectImpl.charSequence.endsNotWith(this, expected))

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not end with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : CharSequence> Expect<T>.endsNotWith(expected: Char) =
    it endsNotWith expected.toString()


/**
 * Expects that the subject of the assertion (a [CharSequence]) [CharSequence].[kotlin.text.isEmpty].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") empty: empty) =
    addAssertion(ExpectImpl.charSequence.isEmpty(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @param empty Use the pseudo-keyword `empty`.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") empty: empty) =
    addAssertion(ExpectImpl.charSequence.isNotEmpty(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) [CharSequence].[kotlin.text.isNotBlank].
 *
 * @param blank Use the pseudo-keyword `blank`.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") blank: blank) =
    addAssertion(ExpectImpl.charSequence.isNotBlank(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) matches the given [expected] [Regex].
 *
 * In contrast to [containsRegex] it does not look for a partial match but for an entire match.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : CharSequence> Expect<T>.matches(expected: Regex) =
    addAssertion(ExpectImpl.charSequence.matches(this, expected))

/**
 * Expects that the subject of the assertion (a [CharSequence]) mismatches the given [expected] [Regex].
 *
 * In contrast to `containsNot.regex` it does not look for a partial match but for an entire match.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : CharSequence> Expect<T>.mismatches(expected: Regex) =
    addAssertion(ExpectImpl.charSequence.mismatches(this, expected))
