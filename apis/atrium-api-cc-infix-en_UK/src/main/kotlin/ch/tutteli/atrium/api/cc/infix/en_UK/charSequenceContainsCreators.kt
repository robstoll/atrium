package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.addAssertion
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

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
 * @param expected The object which is expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.value(expected)"))
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
 * @param values The objects which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.the(values)"))
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.the(values: Values<Any>): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.values(this, values.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object shall be searched
 * (ignoring case), using a non disjoint search.
 *
 * Delegates to `the Values(expected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @param expected The object which is expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.value(expected)"))
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
 * @param values The objects which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.the(values)"))
@JvmName("valuesIgnoringCase")
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.the(values: Values<Any>): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.valuesIgnoringCase(this, values.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [getDefault][Translatable.getDefault]
 * representation of the given [translatable] shall be searched, using a non disjoint search.
 *
 * Delegates to `the DefaultTranslationsOf(translatable)`.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.defaultTranslationOf(translatable)"))
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.defaultTranslationOf(translatable: Translatable): AssertionPlant<T>
    = this the DefaultTranslationsOf(translatable)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [getDefault][Translatable.getDefault]
 * representation of the given [translatables] shall be searched, using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [DefaultTranslationsOf.expected] is defined as `'a'` and one default translation of the
 * [DefaultTranslationsOf.otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain exactly 2 the defaultTranslationOf IS`
 * instead of:
 *   `to contain atLeast 1 the DefaultTranslationsOf(IS, IS)`
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.the(translatables)"))
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.the(translatables: DefaultTranslationsOf): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.defaultTranslationOf(this, translatables.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [getDefault][Translatable.getDefault]
 * representation of the given [translatable] shall be searched (ignoring case), using a non disjoint search.
 *
 * Delegates to `the DefaultTranslationsOf(translatable)`.
 *
 * By non disjoint is meant that 'aa' in 'aaaa' is found three times and not only two times.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("defaultTranslationOfIgnoringCase")
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.defaultTranslationOf(translatable)"))
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.defaultTranslationOf(translatable: Translatable): AssertionPlant<T>
    = this the DefaultTranslationsOf(translatable)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [getDefault][Translatable.getDefault]
 * representation of the given [translatables] shall be searched (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [Values.expected]
 * is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain ignoring case exactly 2 the defaultTranslatableOf IS`
 * instead of:
 *   `to contain ignoring case atLeast 1 the DefaultTranslationsOf(IS, IS)`
 *
 * @param translatables The objects which are expected to be contained within the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.the(translatables)"))
@JvmName("defaultTranslationsOfIgnoringCase")
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.the(translatables: DefaultTranslationsOf): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.defaultTranslationOfIgnoringCase(this, translatables.toList()))


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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.regex(pattern)"))
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.regex(pattern: String): AssertionPlant<T>
    = this the RegexPatterns(pattern)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [patterns]
 * are expected to have a match, using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [RegexPatterns.pattern]
 * is defined as `'a(b)?'` and one of the [RegexPatterns.otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.the(patterns)"))
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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.regex(pattern)"))
@JvmName("regexIgnoringCase")
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.regex(pattern: String): AssertionPlant<T>
    = this the RegexPatterns(pattern)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the given regular expression [patterns]
 * are expected to have a match (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [RegexPatterns.pattern]
 * is defined as `'a(b)?'` and one of the [RegexPatterns.otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
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
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.the(patterns)"))
@JvmName("regexIgnoringCase")
infix fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.the(patterns: RegexPatterns): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.regexIgnoringCase(this, patterns.toList()))
