@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("CharSequenceContainsCreatorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert
import ch.tutteli.kbox.glue
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected]'s
 * [getDefault][Translatable.getDefault] representation as well as the [getDefault][Translatable.getDefault]
 * representations of the [otherExpected] (if given) shall be searched, using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [expected] is defined as `'a'` and one default translation of the
 * [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [zumindest], [hoechstens] and [genau] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.genau(2).standardUebersetzungVon(IS)`
 * instead of:
 *   `enthaelt.zumindest(1).standardUebersetzungVon(IS, IS)`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Most probably only used by Atrium itself; will be made internal with 1.0.0", ReplaceWith("please open an issue on GitHub if you use it as well"))
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>.standardUebersetzungVon(expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.defaultTranslationOf(this, expected glue otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected]'s
 * [getDefault][Translatable.getDefault] representation as well as the [getDefault][Translatable.getDefault]
 * representations of the [otherExpected] (if given) shall be searched (ignoring case), using a non disjoint search.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [expected] is defined as `'a'` and one default translation of the
 * [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [zumindest], [hoechstens] and [genau] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.genau(2).standardUebersetzungVon(IS)`
 * instead of:
 *   `enthaelt.zumindest(1).standardUebersetzungVon(IS, IS)`
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Most probably only used by Atrium itself; will be made internal with 1.0.0", ReplaceWith("please open an issue on GitHub if you use it as well"))
@JvmName("standardUebersetzungVonGrossKleinschreibungIgnorierend")
fun <T : CharSequence> CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>.standardUebersetzungVon(expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
    = addAssertion(AssertImpl.charSequence.contains.defaultTranslationOfIgnoringCase(this, expected glue otherExpected))

/**
 * Helper method to simplify adding assertions to the plant which itself is stored in
 * [CharSequenceContains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> CharSequenceContains.CheckerOption<T, S>.addAssertion(assertion: Assertion): AssertionPlant<T>
    = addAssertionForAssert(assertion)
