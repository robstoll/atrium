package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.CheckerStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.regex
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.regexIgnoringCase
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.values
import ch.tutteli.atrium.logic.creating.charsequence.contains.creators.valuesIgnoringCase
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.CharSequenceOrNumberOrChar
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.utils.toVarArg
import ch.tutteli.kbox.glue
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the [expected] object shall be searched,
 * using a non-disjoint search.
 *
 * Delegates to [values].
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non-disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.value
 *
 */
fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.value(expected: CharSequenceOrNumberOrChar): Expect<T> =
    values(expected)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the [expected] value as well as
 * the [otherExpected] values shall be searched, using a non-disjoint search.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and [expected]
 * is defined as `"a"` and one [otherExpected] is defined as `"a"` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.exactly(2).value("a")`
 * instead of:
 *   `toContain.atLeast(1).values("a", "a")`
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.values
 *
 */
fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.values(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = _logicAppend { values(expected glue otherExpected) }


/**
 * Finishes the specification of the sophisticated `to contain` expectation where the [expected] value shall be searched
 * (ignoring case), using a non-disjoint search.
 *
 * Delegates to `values(expected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non-disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 *
 *  @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.valueIgnoringCaseWithChecker
 *
 */
@JvmName("valueIgnoringCase")
fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.value(
    expected: CharSequenceOrNumberOrChar
): Expect<T> = values(expected)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the [expected] value as well as
 * the [otherExpected] values shall be searched (ignoring case), using a non-disjoint search.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and [expected]
 * is defined as `"a"` and one [otherExpected] is defined as `"a"` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.ignoringCase.exactly(2).value("a")`
 * instead of:
 *   `toContain.ignoringCase.atLeast(1).values("a", "a")`
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.valuesIgnoringCaseWithChecker
 *
 */
@JvmName("valuesIgnoringCase")
fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.values(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = _logicAppend { valuesIgnoringCase(expected glue otherExpected) }


/**
 * Finishes the specification of the sophisticated `to contain` expectation where the [expected] value shall be searched
 * (ignoring case), using a non-disjoint search where it needs to be contained at least once.
 *
 * Delegates to `atLeast(1).value(expected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non-disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.valueIgnoringCase
 *
 */
fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.value(
    expected: CharSequenceOrNumberOrChar
): Expect<T> = atLeast(1).value(expected)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the [expected] value as well as
 * the [otherExpected] values shall be searched (ignoring case), using a non-disjoint search
 * where each need to be contained at least once.
 *
 * Delegates to `atLeast(1).values(expected, otherExpected)`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed.
 * This function expects [CharSequenceOrNumberOrChar] (which is a typealias for [Any]) for your convenience,
 * so that you can mix [String] and [Int] for instance.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"a"` and [expected]
 * is defined as `"a"` and one [otherExpected] is defined as `"a"` as well, then both match, even though they match the
 * same sequence in the input of the search.
 *
 * @param expected The value which is expected to be contained within the input of the search.
 * @param otherExpected Additional values which are expected to be contained within the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.valuesIgnoringCase
 *
 */
fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.values(
    expected: CharSequenceOrNumberOrChar,
    vararg otherExpected: CharSequenceOrNumberOrChar
): Expect<T> = atLeast(1).values(expected, *otherExpected)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match, using a non-disjoint search.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.exactly(2).regex("a(b)?")`
 * instead of:
 *   `toContain.atLeast(1).regex("a(b)?", "a(b)?")`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.regex
 *
 */
fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.regex(
    pattern: String,
    vararg otherPatterns: String
): Expect<T> = _logicAppend { regex(pattern glue otherPatterns) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the given [Regex] [pattern]
 * as well as the [otherPatterns] are expected to have a match, using a non-disjoint search.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.exactly(2).regex(Regex("a(b)?"))`
 * instead of:
 *   `toContain.atLeast(1).regex(Regex("a(b)?"), Regex("a(b)?"))`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.9.0
 */
@Deprecated(
    "Use matchFor instead, will be removed with 2.0.0 at the latest",
    ReplaceWith("this.matchFor(pattern, *otherPatterns)")
)
fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.regex(
    pattern: Regex,
    vararg otherPatterns: Regex
): Expect<T> = _logicAppend { regex(pattern glue otherPatterns) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the given [Regex] [pattern]
 * as well as the [otherPatterns] are expected to have a match, using a non-disjoint search.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.exactly(2).matchFor(Regex("a(b)?"))`
 * instead of:
 *   `toContain.atLeast(1).matchFor(Regex("a(b)?"), Regex("a(b)?"))`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 1.1.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.matchFor
 *
 */
fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.matchFor(
    pattern: Regex,
    vararg otherPatterns: Regex
): Expect<T> = _logicAppend { regex(pattern glue otherPatterns) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to have a match (ignoring case), using a non-disjoint search.
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to
 * control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.ignoringCase.exactly(2).regex("a(b)?")`
 * instead of:
 *   `toContain.ignoringCase.atLeast(1).regex("a(b)?", "a(b)?")`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.regexIgnoringCaseWithChecker
 *
 */
@JvmName("regexIgnoringCase")
fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.regex(
    pattern: String,
    vararg otherPatterns: String
): Expect<T> = _logicAppend { regexIgnoringCase(pattern glue otherPatterns) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where the given regular expression [pattern]
 * as well as the [otherPatterns] are expected to to have at least one match (ignoring case),
 * using a non-disjoint search.
 *
 * Delegates to `atLeast(1).regex(pattern, otherPatterns)`
 *
 * By non-disjoint is meant that `"aa"` in `"aaaa"` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `"ab"` and [pattern]
 * is defined as `"a(b)?"` and one of the [otherPatterns] is defined as `"a(b)?"` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly]
 * to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `toContain.ignoringCase.exactly(2).regex("a(b)?")`
 * instead of:
 *   `toContain.ignoringCase.atLeast(1).regex("a(b)?", "a(b)?")`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.regexIgnoringCase
 *
 */
fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.regex(
    pattern: String,
    vararg otherPatterns: String
): Expect<T> = atLeast(1).regex(pattern, *otherPatterns)

/**
 * Finishes the specification of the sophisticated `to contain` expectation where all elements of the [expectedIterableLike]
 * shall be searched, using a non-disjoint search.
 *
 * Delegates to `values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [values] for more information).
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * By non-disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types or the given
 * [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.13.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.elementsOf
 *
 */
fun <T : CharSequence> CheckerStep<T, NoOpSearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> =
    _logic.toVarArg<CharSequenceOrNumberOrChar>(expectedIterableLike)
        .let { (first, rest) -> values(first, *rest) }


/**
 * Finishes the specification of the sophisticated `to contain` expectation where all elements of the [expectedIterableLike]
 * shall be searched (ignoring case), using a non-disjoint search.
 *
 * Delegates to `values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [values] for more information).
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * By non-disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types or the given
 * [expectedIterableLike] does not have elements (is empty).
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.elementsOfIgnoringCaseWithChecker
 *
 * @since 0.13.0
 */
@JvmName("elementsOfIgnoringCase")
fun <T : CharSequence> CheckerStep<T, IgnoringCaseSearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> =
    _logic.toVarArg<CharSequenceOrNumberOrChar>(expectedIterableLike)
        .let { (first, rest) -> values(first, *rest) }

/**
 * Finishes the specification of the sophisticated `to contain` expectation where all elements of the [expectedIterableLike]
 * shall be searched (ignoring case), using a non-disjoint search.
 *
 * Delegates to `values(expectedIterable.first(), *expectedIterable.drop(1).toTypedArray())`
 * (see [values] for more information).
 *
 * Notice that a runtime check applies which assures that only [Iterable], [Sequence] or one of the [Array] types
 * are passed. This function expects [IterableLike] (which is a typealias for [Any]) to avoid cluttering the API.
 *
 * By non-disjoint is meant that "aa" in "aaaa" is found three times and not only two times.
 *
 * @param expectedIterableLike The [IterableLike] whose elements are expected to be contained within the input of the search.
 *
 * @return an [Expect] for the subject of `this` expectation.
 * @throws IllegalArgumentException in case [expectedIterableLike] is not an [Iterable], [Sequence] or one of the [Array] types or the given
 * [expectedIterableLike] does not have elements (is empty).
 *
 * @since 0.13.0
 *
 * @sample ch.tutteli.atrium.api.fluent.en_GB.samples.CharSequenceToContainCreatorSamples.elementsOfIgnoreCase
 *
 */
@JvmName("elementsOfIgnoringCase")
fun <T : CharSequence> EntryPointStep<T, IgnoringCaseSearchBehaviour>.elementsOf(
    expectedIterableLike: IterableLike
): Expect<T> =
    _logic.toVarArg<CharSequenceOrNumberOrChar>(expectedIterableLike)
        .let { (first, rest) -> values(first, *rest) }
