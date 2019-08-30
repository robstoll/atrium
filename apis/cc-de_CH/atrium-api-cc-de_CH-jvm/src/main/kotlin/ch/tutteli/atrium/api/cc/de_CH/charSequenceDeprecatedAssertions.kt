@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("CharSequenceAssertionsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder as DeprecatedBuilder

@Deprecated("Use the extension fun `enthaelt` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("plant.enthaelt"))
fun <T : CharSequence> getEnthaelt(plant: Assert<T>): DeprecatedBuilder<T, NoOpSearchBehaviour>
    = DeprecatedBuilder(plant, plant.enthaelt.searchBehaviour)

@Deprecated("Use the extension fun `enthaeltNicht` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("plant.enthaeltNicht"))
fun <T : CharSequence> getEnthaeltNicht(plant:  Assert<T>): DeprecatedNotCheckerBuilder<T, NotSearchBehaviour>
    = DeprecatedNotCheckerBuilder(AssertImpl.charSequence.containsNotBuilder(plant))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains [expected]'s [getDefault][Translatable.getDefault]
 * representation and the [getDefault][Translatable.getDefault] representations of the [otherExpected] (if given),
 * using a non disjoint search.
 *
 * It is a shortcut for `enthaelt.zumindest(1).standardUebersetzungVon(expected, *otherExpected)`.
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
 *   `enthaeltStandardUebersetzungVon(IS, IS)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Most probably only used by Atrium itself; will be made internal with 1.0.0", ReplaceWith("please open an issue on GitHub if you use it as well"))
fun <T : CharSequence> Assert<T>.enthaeltStandardUebersetzungVon(
    expected: Translatable,
    vararg otherExpected: Translatable
) = enthaelt.zumindest(1).standardUebersetzungVon(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does  not contain [expected]'s
 * [getDefault][Translatable.getDefault] representation and neither one of the [otherExpected]'s
 * [getDefault][Translatable.getDefault] representation (if given).
 *
 * It is a shortcut for `enthaeltNicht.standardUebersetzungVon(expected, *otherExpected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Most probably only used by Atrium itself; will be made internal with 1.0.0", ReplaceWith("please open an issue on GitHub if you use it as well"))
fun <T : CharSequence> Assert<T>.enthaeltNichtDieStandardUebersetzungVon(
    expected: Translatable,
    vararg otherExpected: Translatable
) = enthaeltNicht.standardUebersetzungVon(expected, *otherExpected)
