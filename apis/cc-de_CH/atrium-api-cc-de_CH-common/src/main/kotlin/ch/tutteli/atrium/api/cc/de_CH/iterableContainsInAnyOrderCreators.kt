@file:JvmMultifileClass
@file:JvmName("IterableContainsInAnyOrderCreatorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.kbox.glue
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.addAssertion
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * within the [Iterable].
 *
 * Delegates to `werte(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.wert(expected: E): AssertionPlant<T>
    = werte(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected][expectedOrNull]
 * nullable value shall be searched within the [Iterable].
 *
 * Delegates to `nullableWerte(expectedOrNull)`.
 *
 * @param expectedOrNull The nullable value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.nullableWert(expectedOrNull: E): AssertionPlant<T>
    = nullableWerte(expectedOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values (if given) shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [zumindest], [hoechstens] and [genau] to control the number of
 * occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.inBeliebigerReihenfolge.genau(2).werte('a')`
 * instead of:
 *   `enthaelt.inBeliebigerReihenfolge.zumindest(1).werte('a', 'a')`
 *
 * @param expected The object which is expected to be contained within the [Iterable].
 * @param otherExpected Additional objects which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.werte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, expected glue otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected][expectedOrNull]
 * nullable value as well as the [other expected][otherExpectedOrNulls] nullable values (if given)
 * shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [expectedOrNull] is defined as `'a'` and one [otherExpectedOrNulls] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [zumindest], [hoechstens] and [genau] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.inBeliebigerReihenfolge.genau(2).nullableWerte('a')`
 * instead of:
 *   `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableWerte('a', 'a')`
 *
 * @param expectedOrNull The object which is expected to be contained within the [Iterable].
 * @param otherExpectedOrNulls Additional objects which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.nullableWerte(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, expectedOrNull glue otherExpectedOrNulls))

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [assertionCreator] might create.
 *
 * Delegates to `eintraege(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.eintrag(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = eintraege(assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreatorOrNull] might create or needs to be `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * Delegates to `nullableEintraege(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.nullableEintrag(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintraege(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [assertionCreator] might create -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreators].
 *
 * @param assertionCreator The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 * @param otherAssertionCreators Additional identification lambdas which each kind of identify (separately) an entry
 *   which we are looking for.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.eintraege(
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrder(this, assertionCreator glue otherAssertionCreators))

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreatorOrNull] might create or needs to be `null` in case
 * [assertionCreatorOrNull] is defined as `null` -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreatorsOrNulls].
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each kind of identify (separately) an entry
 *   which we are looking for.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.nullableEintraege(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrder(this, assertionCreatorOrNull glue otherAssertionCreatorsOrNulls))
