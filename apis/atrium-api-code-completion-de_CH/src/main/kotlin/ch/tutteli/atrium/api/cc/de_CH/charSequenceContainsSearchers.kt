package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions._containsRegex
import ch.tutteli.atrium.assertions._containsRegexIgnoringCase
import ch.tutteli.atrium.assertions._containsValues
import ch.tutteli.atrium.assertions._containsValuesIgnoringCase
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsIgnoringCaseSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched,
 * using a non disjoint search.
 *
 * Delegates to [werte].
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>.wert(expected: Any): IAssertionPlant<T>
    = werte(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as
 * the [otherExpected] objects shall be searched, using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 * @param otherExpected Additional objects which are expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>.werte(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(_containsValues(this, expected, otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched
 * (ignoring case), using a non disjoint search.
 *
 * Delegates to [werte].
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("valueIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>.wert(expected: Any): IAssertionPlant<T>
    = werte(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as
 * the [otherExpected] objects shall be searched (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 * @param otherExpected Additional objects which are expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("valuesIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>.werte(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(_containsValuesIgnoringCase(this, expected, otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match, using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [zumindest], [hoechstens] and [genau]
 * to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.genau(2).regex('a(b)?')`
 * instead of:
 *   `enthaelt.zumindest(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpSearchBehaviour>.regex(pattern: String, vararg otherPatterns: String): IAssertionPlant<T>
    = addAssertion(_containsRegex(this, pattern, otherPatterns))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [zumindest], [hoechstens] and [genau]
 * to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.genau(2).regex('a(b)?')`
 * instead of:
 *   `enthaelt.zumindest(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>.regex(pattern: String, vararg otherPatterns: String): IAssertionPlant<T>
    = addAssertion(_containsRegexIgnoringCase(this, pattern, otherPatterns))
