package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Creates an [CharSequenceContainsBuilder] based on this [IAssertionPlant] which allows to define
 * a sophisticated `contains` assertions.
 *
 * @param contain Has to be `contain`.
 *
 * @return The newly created builder.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.to(@Suppress("UNUSED_PARAMETER") contain: contain)
    = _containsBuilder(this)


/**
 * Makes the assertion that [IAssertionPlant.subject] contains the [toString] representation of the given [expected]
 * using a non disjoint search.
 *
 * It is a shortcut for `to contain atLeast 1 value expected`
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.contains(expected: Any)
    = this to contain atLeast 1 value expected

/**
 * Makes the assertion that [IAssertionPlant.subject] contains the given [values] [toString] representation
 * using a non disjoint search.
 *
 * It is a shortcut for `to contain atLeast 1 the Values(...)`
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
 */
infix fun <T : CharSequence> IAssertionPlant<T>.contains(values: Values<Any>): IAssertionPlant<T>
    = this to contain atLeast 1 the values


/**
 * Makes the assertion that [IAssertionPlant.subject] contains the [getDefault][ITranslatable.getDefault]
 * representation of the given [translatable].
 *
 * It is a shortcut for `to contain atLeast 1 defaultTranslationOf translatable)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.containsDefaultTranslationOf(translatable: ITranslatable): IAssertionPlant<T>
    = this to contain atLeast 1 defaultTranslationOf translatable

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [DefaultTranslationsOf.expected]'s
 * [getDefault][ITranslatable.getDefault] representation and the [getDefault][ITranslatable.getDefault] representations
 * of the [DefaultTranslationsOf.otherExpected] (if defined), using a non disjoint search.
 *
 * It is a shortcut for `to contain atLeast 1 the DefaultTranslationsOf(...)`
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [DefaultTranslationsOf.expected] is defined as `'a'` and one default translation of the
 * [DefaultTranslationsOf.otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [zumindest], [hoestens] and [genau] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain exactly 2 defaultTranslationOf IS`
 * instead of:
 *   `to contain atLeast 1 the DefaultTranslationOf(IS, IS)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.contains(defaultTranslationOf: DefaultTranslationsOf): IAssertionPlant<T>
    = this to contain atLeast 1 the defaultTranslationOf


/**
 * Makes the assertion that [IAssertionPlant.subject] does not [expected]'s [toString] representation.
 *
 * Delegates to [containsNot] [Values].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.containsNot(expected: Any)
    = this containsNot Values(expected)

/**
 * Makes the assertion that [IAssertionPlant.subject] does not contain the [toString] representation
 * of the given [values].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.containsNot(values: Values<Any>): IAssertionPlant<T>
    = addAssertion(_containsNot(this, values.expected, values.otherExpected))


/**
 * Makes the assertion that [IAssertionPlant.subject] does  not contain [DefaultTranslationsOf.expected]'s
 * [getDefault][ITranslatable.getDefault] representation and neither one of the [DefaultTranslationsOf.otherExpected]'s
 * [getDefault][ITranslatable.getDefault] representation (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.containsNot(defaultTranslationsOf: DefaultTranslationsOf): IAssertionPlant<T>
    = addAssertion(_containsNotDefaultTranslationOf(this, defaultTranslationsOf.expected, defaultTranslationsOf.otherExpected))


/**
 * Makes the assertion that [IAssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.startsWith(expected: CharSequence): IAssertionPlant<T>
    = addAssertion(_startsWith(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] does not start with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.startsNotWith(expected: CharSequence): IAssertionPlant<T>
    = addAssertion(_startsNotWith(this, expected))


/**
 * Makes the assertion that [IAssertionPlant.subject] ends with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.endsWith(expected: CharSequence): IAssertionPlant<T>
    = addAssertion(_endsWith(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] does not end with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.endsNotWith(expected: CharSequence): IAssertionPlant<T>
    = addAssertion(_endsNotWith(this, expected))


/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @param Empty Has to be `Empty`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.toBe(@Suppress("UNUSED_PARAMETER") Empty: Empty): IAssertionPlant<T>
    = addAssertion(_isEmpty(this))

/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @param onlyEmptyAllowed Has to be `Empty`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <T : CharSequence> IAssertionPlant<T>.notToBe(@Suppress("UNUSED_PARAMETER") onlyEmptyAllowed: Empty): IAssertionPlant<T>
    = addAssertion(_isNotEmpty(this))
