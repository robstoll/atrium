package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Creates a [CharSequenceContainsBuilder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> Assert<T>.contains: CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>
    get() = _containsBuilder(this)

/**
 * Creates a [CharSequenceContainsBuilder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> Assert<T>.containsNot: CharSequenceContainsNotCheckerBuilder<T, CharSequenceContainsNotSearchBehaviour>
    get() = CharSequenceContainsNotCheckerBuilder(_containsNotBuilder(this))

/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if defined), using a non disjoint search.
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
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
fun <T : CharSequence> Assert<T>.contains(expected: Any, vararg otherExpected: Any): AssertionPlant<T>
    = contains.atLeast(1).values(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if defined).
 *
 * It is a shortcut for `containsNot.values(expected, *otherExpected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.containsNot(expected: Any, vararg otherExpected: Any)
    = containsNot.values(expected, *otherExpected)


/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]'s [getDefault][Translatable.getDefault]
 * representation and the [getDefault][Translatable.getDefault] representations of the [otherExpected] (if defined),
 * using a non disjoint search.
 *
 * It is a shortcut for `contains.atLeast(1).defaultTranslationOf(expected, *otherExpected)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [expected] is defined as `'a'` and one default translation of the
 * [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [atLeast], [atMost] and [exactly] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.exactly(2).defaultTranslationOf(IS)`
 * instead of:
 *   `containsDefaultTranslationOf(IS, IS)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.containsDefaultTranslationOf(expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
    = contains.atLeast(1).defaultTranslationOf(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] does  not contain [expected]'s
 * [getDefault][Translatable.getDefault] representation and neither one of the [otherExpected]'s
 * [getDefault][Translatable.getDefault] representation (if defined).
 *
 * It is a shortcut for `containsNot.defaultTranslationOf(expected, *otherExpected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.containsNotDefaultTranslationOf(expected: Translatable, vararg otherExpected: Translatable)
    = containsNot.defaultTranslationOf(expected, * otherExpected)


/**
 * Makes the assertion that [AssertionPlant.subject] contains a sequence which matches the given regular expression
 * [pattern] as well as the [otherPatterns] (if defined), using a non disjoint search.
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
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.containsRegex(pattern: String, vararg otherPatterns: String): AssertionPlant<T>
    = contains.atLeast(1).regex(pattern, *otherPatterns)

/**
 * Makes the assertion that [AssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.startsWith(expected: CharSequence)
    = addAssertion(_startsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not start with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.startsNotWith(expected: CharSequence)
    = addAssertion(_startsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] ends with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.endsWith(expected: CharSequence)
    = addAssertion(_endsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not end with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.endsNotWith(expected: CharSequence)
    = addAssertion(_endsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.isEmpty()
    = addAssertion(_isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.isNotEmpty()
    = addAssertion(_isNotEmpty(this))
