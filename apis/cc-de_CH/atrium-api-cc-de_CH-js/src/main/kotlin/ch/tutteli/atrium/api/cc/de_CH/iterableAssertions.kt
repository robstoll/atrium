package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
actual val <E, T : Iterable<E>> Assert<T>.enthaelt: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    get() = AssertImpl.iterable.containsBuilder(this)

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
actual val <E, T : Iterable<E>> Assert<T>.enthaeltNicht: NotCheckerOption<E, T, NotSearchBehaviour>
    get() = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))

/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected] and the [otherExpected] values (if given).
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).werte(expected, *otherExpected)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [zumindest], [hoechstens] and [genau] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.inBeliebigerReihenfolge.genau(2).wert('a')`
 * instead of:
 *   `enthaelt('a', 'a')`
 *
 * This function will be renamed on a JVM level from `enthaeltNonNullable` to `enthaelt` with 1.0.0
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E>> Assert<T>.enthaelt(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).werte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains the
 * [expected][expectedOrNull] nullable value.
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableWert(expectedOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E: Any?, T: Iterable<E>> Assert<T>.enthaeltNullableWert(expectedOrNull: E): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableWert(expectedOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains the
 * [expected][expectedOrNull] nullable value and the [other expected][otherExpectedOrNulls] nullable values (if given).
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableWerte(expectedOrNull, *otherExpectedOrNulls)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [expectedOrNull] is defined as `'a'` and one of the [otherExpectedOrNulls] is defined as `'a'` as well, then both
 * match, even though they match the same entry. Use an option such as [zumindest], [hoechstens] and [genau] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.inBeliebigerReihenfolge.genau(2).nullableWert('a')`
 * instead of:
 *   `enthaelt.nullableWerte('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E: Any?, T: Iterable<E>> Assert<T>.enthaeltNullableWerte(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableWerte(expectedOrNull, *otherExpectedOrNulls)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator].
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(assertionCreator)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E>> Assert<T>.enthaelt(assertionCreator: Assert<E>.() -> Unit)
    =  enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(assertionCreator)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreators].
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E>> Assert<T>.enthaelt(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintrag(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E: Any, T: Iterable<E?>> Assert<T>.enthaeltNullableEintrag(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintrag(assertionCreatorOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreatorsOrNulls].
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E: Any, T: Iterable<E?>> Assert<T>.enthaeltNullableEintraege(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only [expected] and the [otherExpected] (if given) in
 * the defined order.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.werte(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E>> Assert<T>.enthaeltStrikt(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.werte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains only
 * the [expected][expectedOrNull] nullable value.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.nullableWert(expectedOrNull, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any?, T : Iterable<E>> Assert<T>.enthaeltStriktNullableWert(expectedOrNull: E): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.nullableWert(expectedOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains only
 * the [expected][expectedOrNull] nullable value and the [other expected][otherExpectedOrNulls] nullable values
 * (if given) in the defined order.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.nullableWerte(expectedOrNull, *otherExpectedOrNulls)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any?, T : Iterable<E>> Assert<T>.enthaeltStriktNullableWerte(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.nullableWerte(expectedOrNull, *otherExpectedOrNulls)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if given) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.eintraege(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E>> Assert<T>.enthaeltStrikt(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.eintraege(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or only one entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.nullableEintrag(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltStriktNullableEintrag(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.nullableEintrag(assertionCreatorOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or `null` in case [assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each [otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.nullableEintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltStriktNullableEintraege(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.nullableEintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the [expected] value
 * and neither one of the [otherExpected] values (if given).
 *
 * It is a shortcut for `enthaeltNicht.werte(expected, *otherExpected)`
 *
 * This function will be renamed on a JVM level from `enthaeltNichtNonNullable` to `enthaeltNicht` with 1.0.0
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E: Any, T : Iterable<E>> Assert<T>.enthaeltNicht(expected: E, vararg otherExpected: E)
    = enthaeltNicht.werte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator].
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(assertionCreator)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E>> Assert<T>.irgendEiner(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(assertionCreator)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains an entry holding
 * the assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintrag(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E: Any, T: Iterable<E?>> Assert<T>.irgendEinNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintrag(assertionCreatorOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain a single entry which holds all assertions
 * created by the [assertionCreator].
 *
 *  It is a shortcut for `enthaeltNicht.eintrag(assertionCreator)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E>> Assert<T>.keiner(assertionCreator: (Assert<E>.() -> Unit))
    = enthaeltNicht.eintrag(assertionCreator)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) does not contain a single entry
 * which holds all assertions created by [assertionCreatorOrNull] or does not contain a single entry which is `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `enthaeltNicht.nullableEintrag(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E?>> Assert<T>.keinerDerNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = enthaeltNicht.nullableEintrag(assertionCreatorOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] has at least one element and that every element holds all
 * assertions created by the [assertionCreator].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E>> Assert<T>.alle(assertionCreator: Assert<E>.() -> Unit)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreator))

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) has at least one element and
 * that every element holds all assertions created by the [assertionCreatorOrNull] or that all elements are `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
actual fun <E : Any, T : Iterable<E?>> Assert<T>.alleDerNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreatorOrNull))
