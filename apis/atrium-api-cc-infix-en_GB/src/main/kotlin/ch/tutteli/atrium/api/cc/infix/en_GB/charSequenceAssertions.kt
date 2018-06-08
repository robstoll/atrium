package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * a sophisticated `contains` assertions.
 *
 * @param contain Has to be `contain`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> Assert<T>.to(@Suppress("UNUSED_PARAMETER") contain: contain): CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    = AssertImpl.charSequence.containsBuilder(this)

/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> Assert<T>.notTo(@Suppress("UNUSED_PARAMETER") contain: contain): NotCheckerOption<T, NotSearchBehaviour>
    = NotCheckerOptionImpl(AssertImpl.charSequence.containsNotBuilder(this))

/**
 * Makes the assertion that [AssertionPlant.subject] contains the [toString] representation of the given [expected]
 * using a non disjoint search.
 *
 * It is a shortcut for `to contain atLeast 1 value expected`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> Assert<T>.contains(expected: Any): AssertionPlant<T>
    = this contains Values(expected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains the given [values] [toString] representation
 * using a non disjoint search.
 *
 * It is a shortcut for `to contain atLeast 1 the Values(...)`
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [Values.expected]
 * is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use the property `contains` to create a more sophisticated `contains`
 * assertion where you can use options such as [atLeast], [atMost] and [exactly] to control the number of occurrences
 * you expect.
 *
 * Meaning you might want to use:
 *   `to contain exactly 2 the value 'a'`
 * instead of:
 *   `contains Values('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case one of the [values] is not a [CharSequence], [Number] or [Char].
 */
infix fun <T : CharSequence> Assert<T>.contains(values: Values<Any>): AssertionPlant<T>
    = this to contain atLeast 1 the values


/**
 * Makes the assertion that [AssertionPlant.subject] contains a sequence which matches the given [pattern].
 *
 * It is a shortcut for `to contain atLeast 1 regex pattern`.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Assert<T>.containsRegex(pattern: String): AssertionPlant<T>
    = this contains RegexPatterns(pattern)

/**
 * Makes the assertion that [AssertionPlant.subject] contains a sequence which matches the given [patterns]
 * using a non disjoint search.
 *
 * It is a shortcut for `to contain atLeast 1 the RegexPatterns(...)`.
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
infix fun <T : CharSequence> Assert<T>.contains(patterns: RegexPatterns): AssertionPlant<T>
    = this to contain atLeast 1 the patterns

/**
 * Makes the assertion that [AssertionPlant.subject] does not [expected]'s [toString] representation.
 *
 * Delegates to [containsNot] [Values].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Assert<T>.containsNot(expected: Any)
    = this containsNot Values(expected)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the [toString] representation
 * of the given [values].
 *
 * It is a shortcut for `notTo contain the Values(expected, *otherExpected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Assert<T>.containsNot(values: Values<Any>)
    = this notTo contain the values

/**
 * Makes the assertion that [AssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Assert<T>.startsWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.startsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not start with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Assert<T>.startsNotWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.startsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] ends with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Assert<T>.endsWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.endsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not end with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Assert<T>.endsNotWith(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.endsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @param Empty Has to be `Empty`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Assert<T>.toBe(@Suppress("UNUSED_PARAMETER") Empty: Empty)
    = addAssertion(AssertImpl.charSequence.isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @param onlyEmptyAllowed Has to be `Empty`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> Assert<T>.notToBe(@Suppress("UNUSED_PARAMETER") onlyEmptyAllowed: Empty)
    = addAssertion(AssertImpl.charSequence.isNotEmpty(this))
