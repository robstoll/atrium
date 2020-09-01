//TODO remove JvmMultifileClass with 1.0.0
@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsCreatersKt")

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.All
import ch.tutteli.atrium.api.infix.en_GB.creating.RegexPatterns
import ch.tutteli.atrium.api.infix.en_GB.creating.Values
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.toVarArg
import ch.tutteli.atrium.domain.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.domain.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.CheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.regex
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.regexIgnoringCase
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.values
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.valuesIgnoringCase
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched,
 * using a non disjoint search.
 *
 * Delegates to `the values(expected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.value(
    expected: CharSequenceOrNumberOrChar
): Expect<T> = this the values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given [values]
 * shall be searched, using a non disjoint search.
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
 *   `contains o exactly 2 the value "a"`
 * instead of:
 *   `contains o atLeast 1 the values("a", "a")`
 *
 * @param values The values which should not be found within the input of the search
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.the(
    values: Values<CharSequenceOrNumberOrChar>
): Expect<T> = _logicAppend { values(values.toList()) }


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search.
 *
 * Delegates to `the values(expected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
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
infix fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.value(
    expected: CharSequenceOrNumberOrChar
): Expect<T> = this the values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [values]
 * shall be searched (ignoring case), using a non disjoint search.
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
 *   `contains o ignoring case exactly 2 the value "a"`
 * instead of:
 *   `contains o ignoring case atLeast 1 the values("a", "a")`
 *
 * @param values The values which are expected to be contained within the input of the search
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
@JvmName("valuesIgnoringCase")
infix fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.the(
    values: Values<CharSequenceOrNumberOrChar>
): Expect<T> = _logicAppend { valuesIgnoringCase(values.toList()) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * (ignoring case), using a non disjoint search where it needs to be contained at least once.
 *
 * Delegates to `atLeast 1 value expected`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.value(
    expected: CharSequenceOrNumberOrChar
): Expect<T> = this atLeast 1 value expected

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [values]
 * shall be searched (ignoring case), using a non disjoint search
 * where each need to be contained at least once.
 *
 * Delegates to `atLeast 1 the value`.
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
 *   `contains o ignoring case exactly 2 the value "a"`
 * instead of:
 *   `contains o ignoring case atLeast 1 the values("a", "a")`
 *
 * @param values The values which are expected to be contained within the input of the search
 *   -- use the function `values(t, ...)` to create a [Values].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.the(
    values: Values<CharSequenceOrNumberOrChar>
): Expect<T> = this atLeast 1 the values

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * is expected to have a match, using a non disjoint search.
 *
 * Delegates to `the regexPatterns(pattern)`.
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.regex(pattern: String): Expect<T> =
    this the regexPatterns(pattern)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given [Regex] [pattern]
 * is expected to have a match.
 *
 * Delegates to `matchFor all(pattern)`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.matchFor(
    pattern: Regex
): Expect<T> = this matchFor all(pattern)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [patterns]
 * are expected to have a match, using a non disjoint search.
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
 * @param patterns The patterns which are expected to have a match against the input of the search
 *   -- use the function `regexPatterns(t, ...)` to create a [RegexPatterns].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.the(patterns: RegexPatterns): Expect<T> =
    _logicAppend { regex(patterns.toList()) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given [Regex] [patterns]
 * are expected to have a match, using a non disjoint search.
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
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.12.0
 */
infix fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.matchFor(patterns: All<Regex>): Expect<T> =
    _logicAppend { regex(patterns.toList()) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * is expected to have a match (ignoring case), using a non disjoint search.
 *
 * Delegates to `the regexPatterns(pattern)`.
 *
 * @param pattern The patterns which is expected to have a match against the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
infix fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.regex(pattern: String): Expect<T> =
    this the regexPatterns(pattern)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [patterns]
 * are expected to have a match (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and
 * [RegexPatterns] is defined as `regexPatterns("a(b)?", "a(b)?")` as well, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains o ignoring case exactly 2 the regex "a(b)?"`
 * instead of:
 *   `contains o ignoring case atLeast 1 the regexPatterns("a(b)?", "a(b)?")`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search
 *   -- use the function `regexPatterns(t, ...)` to create a [RegexPatterns].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("regexIgnoringCase")
infix fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.the(patterns: RegexPatterns): Expect<T> =
    _logicAppend { regexIgnoringCase(patterns.toList()) }

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [pattern]
 * is expected to have at least one match (ignoring case), using a non disjoint search.
 *
 * Delegates to `atLeast 1 regex pattern`.
 *
 * @param pattern The patterns which is expected to have a match against the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.regex(pattern: String): Expect<T> =
    this atLeast 1 regex pattern

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [patterns]
 * are expected to have at least one match (ignoring case), using a non disjoint search.
 *
 * Delegates to `atLeast 1 the patterns`.
 *
 * By non disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and
 * [RegexPatterns] is defined as `regexPatterns("a(b)?", "a(b)?")` as well, then both match,
 * even though they match the same sequence in the input of the search.
 * Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains o ignoring case exactly 2 the regex "a(b)?"`
 * instead of:
 *   `contains o ignoring case atLeast 1 the RegexPatterns("a(b)?", "a(b)?")`
 *
 * @param patterns The patterns which are expected to have a match against the input of the search --
 *   use the function `regexPatterns(t, ...)` to create a [RegexPatterns].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.the(patterns: RegexPatterns): Expect<T> =
    this atLeast 1 the patterns

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterable]
 * shall be searched, using a non disjoint search.
 *
 * Delegates to `the values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [the] for more information).
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
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
 * TODO remove with 1.0.0
 * @since 0.12.0
 */
infix fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.elementsOf(
    expectedIterable: Iterable<CharSequenceOrNumberOrChar>
): Expect<T> {
    val (first, rest) = toVarArg<CharSequenceOrNumberOrChar>(expectedIterable)
    return this the Values(first, rest)
}


/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterable]
 * shall be searched (ignoring case), using a non disjoint search.
 *
 * Delegates to `the values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [the] for more information).
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
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
 * TODO remove with 1.0.0
 * @since 0.12.0
 */
@JvmName("elementsOfIgnoringCase")
infix fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.elementsOf(
    expectedIterable: Iterable<CharSequenceOrNumberOrChar>
): Expect<T> {
    val (first, rest) = toVarArg<CharSequenceOrNumberOrChar>(expectedIterable)
    return this the Values(first, rest)
}

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterable]
 * shall be searched (ignoring case), using a non disjoint search.
 *
 * Delegates to `the values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [the] for more information).
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
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
 * TODO remove with 1.0.0
 * @since 0.12.0
 */
@JvmName("elementsOfIgnoringCase")
infix fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.elementsOf(
    expectedIterable: Iterable<CharSequenceOrNumberOrChar>
): Expect<T> {
    val (first, rest) = toVarArg<CharSequenceOrNumberOrChar>(expectedIterable)
    return this the Values(first, rest)
}

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterableLike]
 * shall be searched, using a non disjoint search.
 *
 * Delegates to `the values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [the] for more information).
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types or the given
 * [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.13.0
 */
infix fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> {
    val (first, rest) = toVarArg<CharSequenceOrNumberOrChar>(expectedIterableLike)
    return this the Values(first, rest)
}


/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterableLike]
 * shall be searched (ignoring case), using a non disjoint search.
 *
 * Delegates to `the values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [the] for more information).
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types or the given
 * [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.13.0
 */
@JvmName("elementsOfIgnoringCase")
infix fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> {
    val (first, rest) = toVarArg<CharSequenceOrNumberOrChar>(expectedIterableLike)
    return this the Values(first, rest)
}

/**
 * Finishes the specification of the sophisticated `contains` assertion where all elements of the [expectedIterableLike]
 * shall be searched (ignoring case), using a non disjoint search.
 *
 * Delegates to `the values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [the] for more information).
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * By non disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within the input of the search.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types or the given
 * [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.13.0
 */
@JvmName("elementsOfIgnoringCase")
infix fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> {
    val (first, rest) = toVarArg<CharSequenceOrNumberOrChar>(expectedIterableLike)
    return this the Values(first, rest)
}
