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
 * [expected] nullable value.
 *
 * Delegates to `the NullableValues(expected)`.
 *
 * @param expected The nullable value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.nullableValue(expected: E): AssertionPlant<T>
    = this the NullableValues(expected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * need to be contained in [Iterable] in the specified order.
 *
 * @param values The values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(values: Values<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInOrderOnly(this, values.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected nullable [values]
 * need to be contained in [Iterable] in the specified order.
 *
 * @param values The nullable values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(values: NullableValues<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInOrderOnly(this, values.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator].
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
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator] or needs to be `null` in case
 * [assertionCreator] is `null` as well.
 *
 * Delegates to `the NullableEntries(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
//TODO remove, if we have a single assertion creator then we can use the overload for a nullable value instead, otherwise we have two fun which do the same.
@JvmName("entry?")
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.entry(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this the NullableEntries(assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [Entries.assertionCreator] might create -- likewise an entry for each further
 * [Entries.otherAssertionCreators], following the specified order, needs to be contained in the [Iterable]
 *
 * @param entries The parameter object containing the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(entries: Entries<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInOrderOnly(this, entries.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [NullableEntries.assertionCreator] might create or needs to be `null` in case
 * [NullableEntries.assertionCreator] is `null` as well -- likewise an entry for each further
 * [NullableEntries.otherAssertionCreators], following the specified order, needs to be contained in the [Iterable].
 *
 * @param entries The parameter object containing the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("entries?")
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.the(entries: NullableEntries<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInOrderOnly(this, entries.toList()))
