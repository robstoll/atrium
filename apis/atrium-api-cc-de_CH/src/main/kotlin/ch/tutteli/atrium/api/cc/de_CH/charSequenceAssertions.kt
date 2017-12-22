package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Creates a [CharSequenceContainsBuilder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> Assert<T>.enthaelt
    get(): CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>
    = _containsBuilder(this)

/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if defined), using a non disjoint search.
 *
 * It is a shortcut for `enthaelt.zumindest(1).werte(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use the property `enthaelt` to create a more sophisticated `contains`
 * assertion where you can use options such as [zumindest], [hoechstens] and [genau] to control the number of
 * occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.genau(2).wert('a')`
 * instead of:
 *   `enthaelt('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *         [CharSequence], [Number] or [Char].
 */
fun <T : CharSequence> Assert<T>.enthaelt(expected: Any, vararg otherExpected: Any): AssertionPlant<T>
    = enthaelt.zumindest(1).werte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.enthaeltNicht(expected: Any, vararg otherExpected: Any)
    = addAssertion(_containsNot(this, expected, otherExpected))

/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]'s [getDefault][Translatable.getDefault]
 * representation and the [getDefault][Translatable.getDefault] representations of the [otherExpected] (if defined),
 * using a non disjoint search.
 *
 * It is a shortcut for `enthaelt.zumindest(1).standardUebersetzungVon(expected, *otherExpected)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [expected] is defined as `'a'` and one default translation of the
 * [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [zumindest], [hoestens] and [genau] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.genau(2).standardUebersetzungVon(IS)`
 * instead of:
 *   `enthaeltStandardUebersetzungVon(IS, IS)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.enthaeltStandardUebersetzungVon(expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
    = enthaelt.zumindest(1).standardUebersetzungVon(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] does  not contain [expected]'s
 * [getDefault][Translatable.getDefault] representation and neither one of the [otherExpected]'s
 * [getDefault][Translatable.getDefault] representation (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.enthaeltNichtDieStandardUebersetzungVon(expected: Translatable, vararg otherExpected: Translatable)
    = addAssertion(_containsNotDefaultTranslationOf(this, expected, otherExpected))

/**
 * Makes the assertion that [AssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.beginntMit(expected: CharSequence)
    = addAssertion(_startsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not start with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.beginntNichtMit(expected: CharSequence)
    = addAssertion(_startsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] ends with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.endetMit(expected: CharSequence)
    = addAssertion(_endsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not end with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.endetNichtMit(expected: CharSequence)
    = addAssertion(_endsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.istLeer()
    = addAssertion(_isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> Assert<T>.istNichtLeer()
    = addAssertion(_isNotEmpty(this))
