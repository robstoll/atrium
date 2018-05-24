package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Assert<T>.contains: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    get() = AssertImpl.iterable.containsBuilder(this)

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Assert<T>.containsNot: NotCheckerOption<E, T, InAnyOrderSearchBehaviour>
    get() = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))


/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]
 * and the [otherExpected] (if defined).
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).values(expected, *otherExpected)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.inAnyOrder.exactly(2).value('a')`
 * instead of:
 *   `contains('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.contains(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).values(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains the [expected] nullable value.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).nullableValue(expected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E: Any?, T: Iterable<E>> Assert<T>.containsNullableValue(expected: E): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).nullableValue(expected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains the [expected] nullable value
 * and the [otherExpected] nullable values (if defined).
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).nullableValues(expected, *otherExpected)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.inAnyOrder.exactly(2).nullableValue('a')`
 * instead of:
 *   `contains('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E: Any?, T: Iterable<E>> Assert<T>.containsNullableValues(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).nullableValues(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreators].
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.contains(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] or an entry which is `null` in case [assertionCreator] is `null` as well.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).nullableEntry(assertionCreator)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E: Any, T: Iterable<E?>> Assert<T>.containsNullableEntry(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).nullableEntry(assertionCreator)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] or an entry which is `null` in case [assertionCreator] is `null`
 * as well -- likewise an entry (can be the same) is searched for each of the [otherAssertionCreators].
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).nullableEntries(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E: Any, T: Iterable<E?>> Assert<T>.containsNullableEntries(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).nullableEntries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only [expected] and the [otherExpected] (if defined) in
 * the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.nullableValues(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inOrder.only.nullableValues(expected, *otherExpected)
/**
 * Makes the assertion that [AssertionPlant.subject] contains only [expected] nullable values
 * and the [otherExpected] nullable values (if defined) in the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.nullableValues(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> Assert<T>.containsStrictlyNullable(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inOrder.only.nullableValues(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if defined) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `contains.inOrder.only.entries(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = contains.inOrder.only.entries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] where the entry needs to be `null` in case [assertionCreator]
 * is `null` and an additional entry for each [otherAssertionCreators] (if defined) whereas the entries
 * have to appear in the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.nullableEntries(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsStrictly?")
fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inOrder.only.nullableEntries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the [expected] value
 * and neither one of the [otherExpected] values (if defined).
 *
 *  It is a shortcut for `containsNot.nullableValues(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.containsNot(expected: E, vararg otherExpected: E)
    = containsNot.nullableValues(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the [expected] nullable value
 * and neither one of the [otherExpected] nullable values (if defined).
 *
 *  It is a shortcut for `containsNot.nullableValues(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> Assert<T>.containsNotNullable(expected: E, vararg otherExpected: E)
    = containsNot.nullableValues(expected, *otherExpected)
