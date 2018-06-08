package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
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
val <E, T : Iterable<E>> Assert<T>.contains: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    get() = AssertImpl.iterable.containsBuilder(this)

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Assert<T>.containsNot: NotCheckerOption<E, T, NotSearchBehaviour>
    get() = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))


/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected] and the [otherExpected] values (if given).
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
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains the
 * [expected][expectedOrNull] nullable value.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).nullableValue(expectedOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E: Any?, T: Iterable<E>> Assert<T>.containsNullableValue(expectedOrNull: E): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).nullableValue(expectedOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains the
 * [expected][expectedOrNull] nullable value and the [other expected][otherExpectedOrNulls] nullable values (if given).
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).nullableValues(expectedOrNull, *otherExpectedOrNulls)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expectedOrNull] is
 * defined as `'a'` and one [otherExpectedOrNulls] is defined as `'a'` as well, then both match, even though they match the
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
fun <E: Any?, T: Iterable<E>> Assert<T>.containsNullableValues(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).nullableValues(expectedOrNull, *otherExpectedOrNulls)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator].
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entry(assertionCreator)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.contains(assertionCreator: Assert<E>.() -> Unit)
    = contains.inAnyOrder.atLeast(1).entry(assertionCreator)

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
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).nullableEntry(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E: Any, T: Iterable<E?>> Assert<T>.containsNullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).nullableEntry(assertionCreatorOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains an entry holding the
 * assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null` -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreatorsOrNulls].
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).nullableEntries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E: Any, T: Iterable<E?>> Assert<T>.containsNullableEntries(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).nullableEntries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)


/**
 * Makes the assertion that [AssertionPlant.subject] contains only [expected] and the [otherExpected] (if given) in
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
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains only
 * the [expected][expectedOrNull] nullable value.
 *
 * It is a shortcut for `contains.inOrder.only.nullableValue(expectedOrNull, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> Assert<T>.containsStrictlyNullableValue(expectedOrNull: E): AssertionPlant<T>
    = contains.inOrder.only.nullableValue(expectedOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains only
 * the [expected][expectedOrNull] nullable value and the [other expected][otherExpectedOrNulls] nullable values
 * (if given) in the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.nullableValues(expectedOrNull, *otherExpectedOrNulls)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> Assert<T>.containsStrictlyNullableValues(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = contains.inOrder.only.nullableValues(expectedOrNull, *otherExpectedOrNulls)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if given) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `contains.inOrder.only.entries(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = contains.inOrder.only.entries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or only one entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inOrder.only.nullableEntry(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictlyNullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inOrder.only.nullableEntry(assertionCreatorOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains only an entry holding
 * the assertions created by [assertionCreatorOrNull] or `null` in case [assertionCreatorOrNull] is defined as `null`
 * and likewise an additional entry for each [otherAssertionCreatorsOrNulls] (if given)
 * whereas the entries have to appear in the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.nullableEntries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictlyNullableEntries(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inOrder.only.nullableEntries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)


/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the [expected] value
 * and neither one of the [otherExpected] values (if given).
 *
 *  It is a shortcut for `containsNot.nullableValues(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.containsNot(expected: E, vararg otherExpected: E)
    = containsNot.nullableValues(expected, *otherExpected)


/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator].
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entry(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.any(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).entry(assertionCreator)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) contains an entry holding
 * the assertions created by [assertionCreatorOrNull] or an entry which is `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).nullableEntry(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Assert<T>.anyOfNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).nullableEntry(assertionCreatorOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain a single entry which holds all assertions
 * created by the [assertionCreator].
 *
 *  It is a shortcut for `containsNot.entry(assertionCreator)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.none(assertionCreator: (Assert<E>.() -> Unit))
    = containsNot.entry(assertionCreator)

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) does not contain a single entry
 * which holds all assertions created by [assertionCreatorOrNull] or does not contain a single entry which is `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 *  It is a shortcut for `containsNot.nullableEntry(assertionCreatorOrNull)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Assert<T>.noneOfNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = containsNot.nullableEntry(assertionCreatorOrNull)

/**
 * Makes the assertion that [AssertionPlant.subject] has at least one element and that every element holds all
 * assertions created by the [assertionCreator].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.all(assertionCreator: Assert<E>.() -> Unit)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreator))

/**
 * Makes the assertion that [AssertionPlant.subject] (which has a nullable entry type) has at least one element and
 * that every element holds all assertions created by the [assertionCreatorOrNull] or that all elements are `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> Assert<T>.allOfNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreatorOrNull))
