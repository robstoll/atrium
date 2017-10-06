package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsIgnoringCaseDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIgnoringCaseRegexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsIndexSearcher
import ch.tutteli.atrium.assertions.charsequence.contains.searchers.CharSequenceContainsRegexSearcher
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched,
 * using a non disjoint search.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>.wert(expected: Any): IAssertionPlant<T>
    = werte(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as
 * the [otherExpected] objects shall be searched, using a non disjoint search.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 * @param otherExpected Additional objects which are expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>.werte(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIndexSearcher(), expected, otherExpected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched
 * (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("valueIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>.wert(expected: Any): IAssertionPlant<T>
    = werte(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as
 * the [otherExpected] objects shall be searched (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 * @param otherExpected Additional objects which are expected to be contained within the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("valuesIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>.werte(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIgnoringCaseIndexSearcher(), expected, otherExpected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given [pattern] as well as
 * the [otherPatterns] are expected to have a match, using a non disjoint search.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsNoOpDecorator>.regex(pattern: Any, vararg otherPatterns: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsRegexSearcher(), pattern, otherPatterns)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given [pattern] as well as
 * the [otherPatterns] are expected to have a match (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
fun <T : CharSequence> CharSequenceContainsCheckerBuilder<T, CharSequenceContainsIgnoringCaseDecorator>.regex(pattern: Any, vararg otherPatterns: Any): IAssertionPlant<T>
    = addAssertion(CharSequenceContainsIgnoringCaseRegexSearcher(), pattern, otherPatterns)
