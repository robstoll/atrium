@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
val <E, T : Iterable<E>> Assert<T>.enthaelt: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    get() = AssertImpl.iterable.containsBuilder(this)

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
val <E, T : Iterable<E>> Assert<T>.enthaeltNicht: NotCheckerOption<E, T, NotSearchBehaviour>
    get() = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains the
 * [expected] value and the [otherExpected] values (if given).
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).werte(expected, *otherExpected)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [expected] is defined as `'a'` and one of the [otherExpected] is defined as `'a'` as well, then both
 * match, even though they match the same entry. Use an option such as [zumindest], [hoechstens] and [genau] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.inBeliebigerReihenfolge.genau(2).wert('a')`
 * instead of:
 *   `enthaelt.werte('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E, T: Iterable<E>> Assert<T>.enthaelt(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).werte(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E: Any, T: Iterable<E?>> Assert<T>.enthaelt(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(assertionCreatorOrNull)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreatorsOrNulls].
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E: Any, T: Iterable<E?>> Assert<T>.enthaelt(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only
 * the [expected] value and the [otherExpected] values
 * (if given) in the defined order.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.werte(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E , T : Iterable<E>> Assert<T>.enthaeltExakt(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.werte(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or only one entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.eintrag(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltExakt(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.eintrag(assertionCreatorOrNull)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or `null` in case [assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each [otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.eintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltExakt(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.eintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)


/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not contain the [expected] value
 * and neither one of the [otherExpected] values (if given).
 *
 * It is a shortcut for `enthaeltNicht.werte(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E, T : Iterable<E>> Assert<T>.enthaeltNicht(expected: E, vararg otherExpected: E)
    = enthaeltNicht.werte(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] contains an entry holding
 * the assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E: Any, T: Iterable<E?>> Assert<T>.irgendEiner(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).eintrag(assertionCreatorOrNull)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] does not contain a single entry
 * which holds all assertions created by [assertionCreatorOrNull] or does not contain a single entry which is `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `enthaeltNicht.eintrag(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E : Any, T : Iterable<E?>> Assert<T>.keiner(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = enthaeltNicht.eintrag(assertionCreatorOrNull)

/**
 * Makes the assertion that the [Assert.subject][SubjectProvider.subject] has at least one element and
 * that every element holds all assertions created by the [assertionCreatorOrNull] or that all elements are `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E : Any, T : Iterable<E?>> Assert<T>.alle(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreatorOrNull))
