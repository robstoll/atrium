@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)

package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.kbox.glue
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched,
 * using a non disjoint search.
 *
 * Delegates to `values(expected)`.
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

@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 0.10.0",
    ReplaceWith(
        "this.value(expected)",
        "ch.tutteli.atrium.api.fluent.en_GB.value"
    )
)
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.value(expected: Any): AssertionPlant<T> =
    values(expected)

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
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).value('a')`
 * instead of:
 *   `contains.atLeast(1).values('a', 'a')`
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */

@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 0.10.0",
    ReplaceWith(
        "this.values(expected, *otherExpected)",
        "ch.tutteli.atrium.api.fluent.en_GB.values"
    )
)
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.values(
    expected: Any,
    vararg otherExpected: Any
): AssertionPlant<T> = addAssertion(AssertImpl.charSequence.contains.values(this, expected glue otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search.
 *
 * Delegates to `values(expected)`.
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
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.value(expected)",
        "ch.tutteli.atrium.api.fluent.en_GB.value"
    )
)
@JvmName("valueIgnoringCase")
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.value(expected: Any): AssertionPlant<T> =
    values(expected)

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
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.ignoringCase.exactly(2).value('a')`
 * instead of:
 *   `contains.ignoringCase.atLeast(1).values('a', 'a')`
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.values(expected, *otherExpected)",
        "ch.tutteli.atrium.api.fluent.en_GB.values"
    )
)
@JvmName("valuesIgnoringCase")
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.values(
    expected: Any,
    vararg otherExpected: Any
): AssertionPlant<T> =
    addAssertion(AssertImpl.charSequence.contains.valuesIgnoringCase(this, expected glue otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search where it needs to be contained at least once.
 *
 * Delegates to `atLeast(1).values(expected)`.
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
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.value(expected)",
        "ch.tutteli.atrium.api.fluent.en_GB.value"
    )
)
fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.value(expected: Any): AssertionPlant<T> =
    atLeast(1).value(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as
 * the [otherExpected] values shall be searched (ignoring case), using a non disjoint search
 * where each need to be contained at least once.
 *
 * Delegates to `atLeast(1).values(expected, otherExpected)`
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
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.values(expected, *otherExpected)",
        "ch.tutteli.atrium.api.fluent.en_GB.values"
    )
)
fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.values(
    expected: Any,
    vararg otherExpected: Any
): AssertionPlant<T> = atLeast(1).values(expected, *otherExpected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match, using a non disjoint search.
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
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.regex(pattern, *otherPatterns)",
        "ch.tutteli.atrium.api.fluent.en_GB.regex"
    )
)
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.regex(
    pattern: String,
    vararg otherPatterns: String
): AssertionPlant<T> = addAssertion(AssertImpl.charSequence.contains.regex(this, pattern glue otherPatterns))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.ignoringCase.exactly(2).regex('a(b)?')`
 * instead of:
 *   `contains.ignoringCase.atLeast(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.regex(pattern, *otherPatterns)",
        "ch.tutteli.atrium.api.fluent.en_GB.regex"
    )
)
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.regex(
    pattern: String,
    vararg otherPatterns: String
): AssertionPlant<T> =
    addAssertion(AssertImpl.charSequence.contains.regexIgnoringCase(this, pattern glue otherPatterns))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to to have at least one match (ignoring case),
 * using a non disjoint search.
 *
 * Delegates to `atLeast(1).regex(pattern, otherPatterns)`
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly]
 * to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.ignoringCase.exactly(2).regex('a(b)?')`
 * instead of:
 *   `contains.ignoringCase.atLeast(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.regex(pattern, *otherPatterns)",
        "ch.tutteli.atrium.api.fluent.en_GB.regex"
    )
)
fun <T : CharSequence> CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>.regex(
    pattern: String,
    vararg otherPatterns: String
): AssertionPlant<T> = atLeast(1).regex(pattern, *otherPatterns)

/**
 * Helper method to simplify adding assertions to the plant which itself is stored in
 * [CharSequenceContains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> CharSequenceContains.CheckerOption<T, S>.addAssertion(
    assertion: Assertion
): AssertionPlant<T> = addAssertionForAssert(assertion)
