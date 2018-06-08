package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `the Values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = this the Values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected][expectedOrNull] nullable value.
 *
 * Delegates to `the NullableValues(expectedOrNull)`.
 *
 * @param expectedOrNull The nullable value which is expectedOrNull to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.nullableValue(expectedOrNull: E): AssertionPlant<T>
    = this the NullableValues(expectedOrNull)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * expected [values] in the specified order.
 *
 * @param values The values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(values: Values<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInOrderOnly(this, values.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * expected [nullableValues] in the specified order.
 *
 * @param nullableValues The nullable nullableValues which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(nullableValues: NullableValues<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInOrderOnly(this, nullableValues.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only a
 * single entry which holds all assertions created by the given [assertionCreator].
 *
 * Delegates to `the Entries(assertionCreator)`
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.entry(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this the Entries(assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only a
 * single entry which holds all assertions created by the given [assertionCreatorOrNull] or needs to be `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `the NullableEntries(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.nullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this the NullableEntries(assertionCreatorOrNull)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only an
 * entry which holds all assertions [entries].[assertionCreator][Entries.assertionCreator] might create and likewise
 * a further entry for each [entries].[otherAssertionCreators][Entries.otherAssertionCreators] (if defined) whereas the entries
 * have to appear in the specified order.
 *
 * @param entries The parameter object containing the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(entries: Entries<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInOrderOnly(this, entries.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only an
 * entry which holds all assertions [nullableEntries].[assertionCreator][NullableEntries.assertionCreator] might create
 * or is `null` in case [nullableEntries].[assertionCreator][NullableEntries.assertionCreator] is defined as `null`
 * and likewise a further entry for each
 * [nullableEntries].[otherAssertionCreators][NullableEntries.otherAssertionCreators]
 * (if defined) whereas the entries have to appear in the specified order.
 *
 * @param nullableEntries The parameter object containing the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.the(nullableEntries: NullableEntries<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInOrderOnly(this, nullableEntries.toList()))
