package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.infix.en_GB.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour


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
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a
 *   [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> Expect<T>.contains(expected: Any): Expect<T> =
    this contains o atLeast 1 value expected

/**
 * Expects that the subject of the assertion (a [CharSequence]) contains the [toString] representation of the
 * given [values] using a non disjoint search.
 *
 * It is a shortcut for `contains o atLeast 1 the Values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and
 * [Values.expected] is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same sequence in the input of the search. Use the property `contains` to create
 * a more sophisticated `contains` assertion where you can use options such as [atLeast], [atMost] and [exactly]
 * to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains o exactly 2 value 'a'`
 * instead of:
 *   `contains Values('a', 'a')`
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a
 *   [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> Expect<T>.contains(values: Values<Any>): Expect<T> =
    this contains o atLeast 1 the values

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not contain [expected]'s [toString] representation.
 *
 * It is a shortcut for `contains not value expected`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.containsNot(expected: Any) =
    this containsNot o value expected

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not contain the [toString] representation
 * of the given [values].
 *
 * It is a shortcut for `contains not the Values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.containsNot(values: Values<Any>) =
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
 * It is a shortcut for `contains o atLeast 1 regex pattern`.
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
 * regular expression [patterns], using a non disjoint search.
 *
 * It is a shortcut for `contains o atLeast 1 the RegexPatterns(pattern, *otherPatterns)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and
 * [RegexPatterns.expected] is defined as `'a(b)?'` and one of the [RegexPatterns.otherExpected] is defined
 * as `'a(b)?'` as well, then both match, even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains o exactly 2 regex "a(b)?"`
 * instead of:
 *   `contains o atLeast 1 the RegexPatterns("a(b)?", "a(b)?")`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.containsRegex(patterns: RegexPatterns): Expect<T> =
    this contains o atLeast 1 the patterns

/**
 * Expects that the subject of the assertion (a [CharSequence]) contains a sequence which matches the given
 * regular expression [patterns], using a non disjoint search.
 *
 * It is a shortcut for `contains o atLeast 1 regex All(pattern, *otherPatterns)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and
 * [RegexPatterns.expected] is defined as `'a(b)?'` and one of the [RegexPatterns.otherExpected] is defined
 * as `'a(b)?'` as well, then both match, even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains o exactly 2 regex "a(b)?"`
 * instead of:
 *   `contains o atLeast 1 the RegexPatterns("a(b)?", "a(b)?")`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search.
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
 * @since 0.11.0
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
 * @since 0.11.0
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
 * @since 0.11.0
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
 * @since 0.11.0
 */
infix fun <T : CharSequence> Expect<T>.endsNotWith(expected: Char) =
    it endsNotWith expected.toString()


/**
 * Expects that the subject of the assertion (a [CharSequence]) [CharSequence].[kotlin.text.isEmpty].
 *
 * @param Empty Has to be `Empty`.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.toBe(@Suppress("UNUSED_PARAMETER") Empty: Empty) =
    addAssertion(ExpectImpl.charSequence.isEmpty(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @param Empty Has to be `Empty`.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") Empty: Empty) =
    addAssertion(ExpectImpl.charSequence.isNotEmpty(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) [CharSequence].[kotlin.text.isNotBlank].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Expect<T>.notToBe(@Suppress("UNUSED_PARAMETER") Blank: Blank) =
    addAssertion(ExpectImpl.charSequence.isNotBlank(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) matches the given [expected] [Regex].
 *
 * In contrast to [containsRegex] it does not look for a partial match but for an entire match.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.11.0
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
 * @since 0.11.0
 */
infix fun <T : CharSequence> Expect<T>.mismatches(expected: Regex) =
    addAssertion(ExpectImpl.charSequence.mismatches(this, expected))
