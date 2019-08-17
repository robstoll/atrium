@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsCreatorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.kbox.glue
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched,
 * using a non disjoint search.
 *
 * Delegates to `werte(expected)`.
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
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.wert(expected: Any): AssertionPlant<T>
    = werte(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as
 * the [otherExpected] values shall be searched, using a non disjoint search.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.werte(expected: Any, vararg otherExpected: Any): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.values(this, expected glue otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search.
 *
 * Delegates to `werte(expected)`.
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
@JvmName("wertGrossKleinschreibungIgnorierend")
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.wert(expected: Any): AssertionPlant<T>
    = werte(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as
 * the [otherExpected] values shall be searched (ignoring case), using a non disjoint search.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
@JvmName("werteGrossKleinschreibungIgnorierend")
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.werte(expected: Any, vararg otherExpected: Any): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.valuesIgnoringCase(this, expected glue otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search where it needs to be contained at least once.
 *
 * Delegates to `zumindest(1).wert(expected)`.
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
fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.wert(expected: Any): AssertionPlant<T>
    = zumindest(1).wert(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as
 * the [otherExpected] values shall be searched (ignoring case), using a non disjoint search
 * where each need to be contained at least once.
 *
 * Delegates to `zumindest(1).werte(expected, otherExpected)`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.werte(expected: Any, vararg otherExpected: Any): AssertionPlant<T>
    = zumindest(1).werte(expected, *otherExpected)

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
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.regex(pattern: String, vararg otherPatterns: String): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.regex(this, pattern glue otherPatterns))

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
 *   `enthaelt.ignoriereGrossKleinschreibung.genau(2).regex('a(b)?')`
 * instead of:
 *   `enthaelt.ignoriereGrossKleinschreibung.zumindest(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.regex(pattern: String, vararg otherPatterns: String): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.regexIgnoringCase(this, pattern glue otherPatterns))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to to have at least one match (ignoring case),
 * using a non disjoint search.
 *
 * Delegates to `zumindest(1).regex(pattern, otherPatterns)`
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [zumindest], [hoechstens] and [genau]
 * to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.ignoriereGrossKleinschreibung.genau(2).regex('a(b)?')`
 * instead of:
 *   `enthaelt.ignoriereGrossKleinschreibung.zumindest(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.regex(pattern: String, vararg otherPatterns: String): AssertionPlant<T>
    = zumindest(1).regex(pattern, *otherPatterns)

/**
 * Helper method to simplify adding assertions to the plant which itself is stored in
 * [CharSequenceContains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> CharSequenceContains.CheckerOption<T, S>.addAssertion(assertion: Assertion): AssertionPlant<T>
    = addAssertionForAssert(assertion)
