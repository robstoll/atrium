package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertion
import ch.tutteli.atrium.domain.builders.utils.toVarArg
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains.*
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.kbox.glue
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched,
 * using a non disjoint search.
 *
 * Delegates to [values].
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
fun <T : CharSequence> CheckerOption<T, NoOpSearchBehaviour>.value(expected: CharSequenceOrNumberOrChar): Expect<T> =
    values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as
 * the [otherExpected] values shall be searched, using a non disjoint search.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and [expected]
 * is defined as `"a"` and one [otherExpected] is defined as `"a"` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).value("a")`
 * instead of:
 *   `contains.atLeast(1).values("a", "a")`
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
fun <T : CharSequence> CheckerOption<T, NoOpSearchBehaviour>.values(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = addAssertion(ExpectImpl.charSequence.contains.values(this, expected glue otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search.
 *
 * Delegates to `values(expected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
@JvmName("valueIgnoringCase")
fun <T : CharSequence> CheckerOption<T, IgnoringCaseSearchBehaviour>.value(
    expected: CharSequenceOrNumberOrChar
): Expect<T> = values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as
 * the [otherExpected] values shall be searched (ignoring case), using a non disjoint search.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and [expected]
 * is defined as `"a"` and one [otherExpected] is defined as `"a"` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.ignoringCase.exactly(2).value("a")`
 * instead of:
 *   `contains.ignoringCase.atLeast(1).values("a", "a")`
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
@JvmName("valuesIgnoringCase")
fun <T : CharSequence> CheckerOption<T, IgnoringCaseSearchBehaviour>.values(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = addAssertion(ExpectImpl.charSequence.contains.valuesIgnoringCase(this, expected glue otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search where it needs to be contained at least once.
 *
 * Delegates to `atLeast(1).value(expected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
fun <T : CharSequence> Builder<T, IgnoringCaseSearchBehaviour>.value(expected: CharSequenceOrNumberOrChar): Expect<T> =
    atLeast(1).value(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as
 * the [otherExpected] values shall be searched (ignoring case), using a non disjoint search
 * where each need to be contained at least once.
 *
 * Delegates to `atLeast(1).values(expected, otherExpected)`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and [expected]
 * is defined as `"a"` and one [otherExpected] is defined as `"a"` as well, then both match, even though they match the
 * same sequence in the input of the search.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
fun <T : CharSequence> Builder<T, IgnoringCaseSearchBehaviour>.values(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = atLeast(1).values(expected, *otherExpected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match, using a non disjoint search.
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
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> CheckerOption<T, NoOpSearchBehaviour>.regex(
    pattern: String,
    vararg otherPatterns: String
): Expect<T> = addAssertion(ExpectImpl.charSequence.contains.regex(this, pattern glue otherPatterns))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given [Regex] [pattern]
 * as well as the [otherPatterns] are expected to have a match, using a non disjoint search.
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
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
//TODO rename to `matchFor` with 1.0.0
fun <T : CharSequence> CheckerOption<T, NoOpSearchBehaviour>.regex(
    pattern: Regex,
    vararg otherPatterns: Regex
): Expect<T> = addAssertion(ExpectImpl.charSequence.contains.regex(this, pattern glue otherPatterns))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.ignoringCase.exactly(2).regex("a(b)?")`
 * instead of:
 *   `contains.ignoringCase.atLeast(1).regex("a(b)?", "a(b)?")`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
fun <T : CharSequence> CheckerOption<T, IgnoringCaseSearchBehaviour>.regex(
    pattern: String,
    vararg otherPatterns: String
): Expect<T> = addAssertion(ExpectImpl.charSequence.contains.regexIgnoringCase(this, pattern glue otherPatterns))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to to have at least one match (ignoring case),
 * using a non disjoint search.
 *
 * Delegates to `atLeast(1).regex(pattern, otherPatterns)`
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly]
 * to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.ignoringCase.exactly(2).regex("a(b)?")`
 * instead of:
 *   `contains.ignoringCase.atLeast(1).regex("a(b)?", "a(b)?")`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Builder<T, IgnoringCaseSearchBehaviour>.regex(
    pattern: String,
    vararg otherPatterns: String
): Expect<T> = atLeast(1).regex(pattern, *otherPatterns)

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterable]
 * shall be searched, using a non disjoint search.
 *
 * Delegates to `values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [values] for more information).
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expectedIterable The [Iterable] whose elements are expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterable] is not a [CharSequence], [Number] or [Char] or the given
 * [expectedIterable] does not have elements (is empty).
 *
 * @since 0.9.0
 */
fun <T : CharSequence> CheckerOption<T, NoOpSearchBehaviour>.elementsOf(
    expectedIterable: Iterable<CharSequenceOrNumberOrChar>
): Expect<T> {
    val (first, rest) = toVarArg(expectedIterable)
    return values(first, *rest)
}

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterable]
 * shall be searched (ignoring case), using a non disjoint search.
 *
 * Delegates to `values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [values] for more information).
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expectedIterable The [Iterable] whose elements are expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterable] is not a [CharSequence], [Number] or [Char] or the given
 * [expectedIterable] does not have elements (is empty).
 *
 * @since 0.9.0
 */
@JvmName("elementsOfIgnoringCase")
fun <T : CharSequence> CheckerOption<T, IgnoringCaseSearchBehaviour>.elementsOf(
    expectedIterable: Iterable<CharSequenceOrNumberOrChar>
): Expect<T> {
    val (first, rest) = toVarArg(expectedIterable)
    return values(first, *rest)
}

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterable]
 * shall be searched (ignoring case), using a non disjoint search.
 *
 * Delegates to `values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [values] for more information).
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any] for your convenience,
 * so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expectedIterable The [Iterable] whose elements are expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterable] is not a [CharSequence], [Number] or [Char] or the given
 * [expectedIterable] does not have elements (is empty).
 *
 * @since 0.11.0
 */
@JvmName("elementsOfIgnoringCase")
fun <T : CharSequence> Builder<T, IgnoringCaseSearchBehaviour>.elementsOf(
    expectedIterable: Iterable<CharSequenceOrNumberOrChar>
): Expect<T> {
    val (first, rest) = toVarArg(expectedIterable)
    return values(first, *rest)
}
