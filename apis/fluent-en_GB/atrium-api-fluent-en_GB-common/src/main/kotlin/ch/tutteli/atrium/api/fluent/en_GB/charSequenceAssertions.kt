package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.fluent.en_GB.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour

/**
 * Creates a [CharSequenceContains.Builder] based on this [Expect] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> Expect<T>.contains: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    get() = ExpectImpl.charSequence.containsBuilder(this)

/**
 * Creates a [CharSequenceContains.Builder] based on this [Expect] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> Expect<T>.containsNot: NotCheckerOption<T, NotSearchBehaviour>
    get() = NotCheckerOptionImpl(ExpectImpl.charSequence.containsNotBuilder(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if given), using a non disjoint search.
 *
 * It is a shortcut for `contains.atLeast(1).values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use the property `contains` to create a more sophisticated `contains`
 * assertion where you can use options such as [atLeast], [atMost] and [exactly] to control the number of occurrences
 * you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).value('a')`
 * instead of:
 *   `contains('a', 'a')`
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
fun <T : CharSequence> Expect<T>.contains(expected: Any, vararg otherExpected: Any): Expect<T> =
    contains.atLeast(1).values(expected, *otherExpected)

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if given).
 *
 * It is a shortcut for `containsNot.values(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.containsNot(expected: Any, vararg otherExpected: Any) =
    containsNot.values(expected, *otherExpected)


/**
 * Expects that the subject of the assertion (a [CharSequence]) contains a sequence which matches the given regular expression
 * [pattern] as well as the [otherPatterns] (if given), using a non disjoint search.
 *
 * It is a shortcut for `contains.atLeast(1).regex(pattern, *otherPatterns)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).regex('a(b)?')`
 * instead of:
 *   `contains.atLeast(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.containsRegex(pattern: String, vararg otherPatterns: String): Expect<T> =
    contains.atLeast(1).regex(pattern, *otherPatterns)

/**
 * Expects that the subject of the assertion (a [CharSequence]) starts with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.startsWith(expected: CharSequence) =
    addAssertion(ExpectImpl.charSequence.startsWith(this, expected))

/**
 * Expects that the subject of the assertion (a [CharSequence]) starts with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.startsWith(expected: Char) = startsWith(expected.toString())

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not start with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.startsNotWith(expected: CharSequence) =
    addAssertion(ExpectImpl.charSequence.startsNotWith(this, expected))

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not start with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.startsNotWith(expected: Char) = startsNotWith(expected.toString())


/**
 * Expects that the subject of the assertion (a [CharSequence]) ends with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.endsWith(expected: CharSequence) =
    addAssertion(ExpectImpl.charSequence.endsWith(this, expected))

/**
 * Expects that the subject of the assertion (a [CharSequence]) ends with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.endsWith(expected: Char) = endsWith(expected.toString())

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not end with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.endsNotWith(expected: CharSequence) =
    addAssertion(ExpectImpl.charSequence.endsNotWith(this, expected))

/**
 * Expects that the subject of the assertion (a [CharSequence]) does not end with [expected].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.endsNotWith(expected: Char) = endsNotWith(expected.toString())


/**
 * Expects that the subject of the assertion (a [CharSequence]) [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.isEmpty() = addAssertion(ExpectImpl.charSequence.isEmpty(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.isNotEmpty() = addAssertion(ExpectImpl.charSequence.isNotEmpty(this))

/**
 * Expects that the subject of the assertion (a [CharSequence]) [CharSequence].[kotlin.text.isNotBlank].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Expect<T>.isNotBlank() = addAssertion(ExpectImpl.charSequence.isNotBlank(this))
