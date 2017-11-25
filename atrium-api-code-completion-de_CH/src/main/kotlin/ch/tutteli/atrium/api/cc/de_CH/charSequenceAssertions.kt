package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Creates an [CharSequenceContainsBuilder] based on this [IAssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
val <T : CharSequence> IAssertionPlant<T>.enthaelt
    get(): CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>
    = _containsBuilder(this)

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if defined), using a non disjoint search.
 *
 * It is a shortcut for `enthaelt.zumindest(1).werte(expected, *otherExpected)`.
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
 */
fun <T : CharSequence> IAssertionPlant<T>.enthaelt(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = enthaelt.zumindest(1).werte(expected, *otherExpected)

/**
 * Makes the assertion that [IAssertionPlant.subject] does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> IAssertionPlant<T>.enthaeltNicht(expected: Any, vararg otherExpected: Any): IAssertionPlant<T>
    = addAssertion(_containsNot(this, expected, *otherExpected))

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]'s [getDefault][ITranslatable.getDefault]
 * representation and the [getDefault][ITranslatable.getDefault] representations of the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> IAssertionPlant<T>.enthaeltStandardUebersetzungVon(expected: ITranslatable, vararg otherExpected: ITranslatable): IAssertionPlant<T>
    = enthaelt(expected.getDefault(), *otherExpected.map { it.getDefault() }.toTypedArray())

/**
 * Makes the assertion that [IAssertionPlant.subject] does  not contain [expected]'s
 * [getDefault][ITranslatable.getDefault] representation and neither one of the [otherExpected]'s
 * [getDefault][ITranslatable.getDefault] representation (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> IAssertionPlant<T>.enthaeltNichtDieStandardUebersetzungVon(expected: ITranslatable, vararg otherExpected: ITranslatable): IAssertionPlant<T>
    = addAssertion(_containsNotDefaultTranslationOf(this, expected, *otherExpected))

/**
 * Makes the assertion that [IAssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> IAssertionPlant<T>.beginntMit(expected: CharSequence): IAssertionPlant<T>
    = addAssertion(_startsWith(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] does not start with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> IAssertionPlant<T>.beginntNichtMit(expected: CharSequence): IAssertionPlant<T>
    = addAssertion(_startsNotWith(this, expected))


/**
 * Makes the assertion that [IAssertionPlant.subject] ends with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> IAssertionPlant<T>.endetMit(expected: CharSequence): IAssertionPlant<T>
    = addAssertion(_endsWith(this, expected))

/**
 * Makes the assertion that [IAssertionPlant.subject] does not end with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> IAssertionPlant<T>.endetNichtMit(expected: CharSequence): IAssertionPlant<T>
    = addAssertion(_endsNotWith(this, expected))


/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> IAssertionPlant<T>.istLeer(): IAssertionPlant<T>
    = addAssertion(_isEmpty(this))

/**
 * Makes the assertion that [IAssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <T : CharSequence> IAssertionPlant<T>.istNichtLeer(): IAssertionPlant<T>
    = addAssertion(_isNotEmpty(this))
