@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched,
 * using a non disjoint search.
 *
 * Delegates to `the Values(expected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.value(expected: Any): AssertionPlant<T>
    = this the Values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given [values]
 * shall be searched, using a non disjoint search.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [Values.expected]
 * is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain exactly 2 the value 'a'`
 * instead of:
 *   `to contain atLeast 1 the Values('a', 'a')`
 *
 * @param values The values which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.the(values: Values<Any>): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.values(this, values.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search.
 *
 * Delegates to `the Values(expected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
@JvmName("valueIgnoringCase")
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.value(expected: Any): AssertionPlant<T>
    = this the Values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [values]
 * shall be searched (ignoring case), using a non disjoint search.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [Values.expected]
 * is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain ignoring case exactly 2 the value 'a'`
 * instead of:
 *   `to contain ignoring case atLeast 1 the Values('a', 'a')`
 *
 * @param values The values which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
@JvmName("valuesIgnoringCase")
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.the(values: Values<Any>): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.valuesIgnoringCase(this, values.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search where it needs to be contained at least once.
 *
 * Delegates to `atLeast 1 value expected`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.value(expected: Any): AssertionPlant<T>
    = this atLeast 1 value expected

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [values]
 * shall be searched (ignoring case), using a non disjoint search
 * where each need to be contained at least once.
 *
 * Delegates to `atLeast 1 the value`
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [Values.expected]
 * is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain ignoring case exactly 2 the value 'a'`
 * instead of:
 *   `to contain ignoring case atLeast 1 the Values('a', 'a')`
 *
 * @param values The values which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.the(values: Values<Any>): AssertionPlant<T>
    = this atLeast 1 the values

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * is expected to have a match, using a non disjoint search.
 *
 * Delegates to `the RegexPatterns(pattern)`.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.regex(pattern: String): AssertionPlant<T>
    = this the RegexPatterns(pattern)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [patterns]
 * are expected to have a match, using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and
 * [patterns].[pattern][RegexPatterns.expected] is defined as `'a(b)?'` and one of the
 * [patterns].[otherPatterns][RegexPatterns.otherExpected] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain exactly 2 the regex 'a(b)?'`
 * instead of:
 *   `to contain atLeast 1 the RegexPatterns('a(b)?', 'a(b)?')`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.the(patterns: RegexPatterns): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.regex(this, patterns.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * is expected to have a match (ignoring case), using a non disjoint search.
 *
 * Delegates to `the RegexPatterns(pattern)`.
 *
 * @param pattern The patterns which is expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.regex(pattern: String): AssertionPlant<T>
    = this the RegexPatterns(pattern)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [patterns]
 * are expected to have a match (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and
 * [patterns].[pattern][RegexPatterns.expected] is defined as `'a(b)?'` and one of the
 * [patterns].[otherPatterns][RegexPatterns.otherExpected] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain ignoring case exactly 2 the regex 'a(b)?'`
 * instead of:
 *   `to contain ignoring case atLeast 1 the RegexPatterns('a(b)?', 'a(b)?')`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.the(patterns: RegexPatterns): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.regexIgnoringCase(this, patterns.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * is expected to have at least one match (ignoring case), using a non disjoint search.
 *
 * Delegates to `the RegexPatterns(pattern)`.
 *
 * @param pattern The patterns which is expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.regex(pattern: String): AssertionPlant<T>
    = this atLeast 1 regex pattern

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [patterns]
 * are expected to have at least one match (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and
 * [patterns].[pattern][RegexPatterns.expected] is defined as `'a(b)?'` and one of the
 * [patterns].[otherPatterns][RegexPatterns.otherExpected] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain ignoring case exactly 2 the regex 'a(b)?'`
 * instead of:
 *   `to contain ignoring case atLeast 1 the RegexPatterns('a(b)?', 'a(b)?')`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.the(patterns: RegexPatterns): AssertionPlant<T>
    = this atLeast 1 the patterns

/**
 * Helper method to simplify adding assertions to the plant which itself is stored in
 * [CharSequenceContains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> CharSequenceContains.CheckerOption<T, S>.addAssertion(assertion: Assertion): AssertionPlant<T>
    = addAssertionForAssert(assertion)
