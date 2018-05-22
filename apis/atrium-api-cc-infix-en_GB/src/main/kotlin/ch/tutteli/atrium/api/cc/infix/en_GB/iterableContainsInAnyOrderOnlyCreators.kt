package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] must contain only the
 * [expected] value.
 *
 * Delegates to `the Values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = this the Values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] must contain only the
 * [expected] nullable value.
 *
 * Delegates to `the NullableValues(expected)`.
 *
 * @param expected The nullable value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.nullableValue(expected: E): AssertionPlant<T>
    = this the NullableValues(expected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * must be contained in the [Iterable] but it does not matter in which order.
 *
 * @param values The values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.the(values: Values<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(this, values.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected nullable [values]
 * must be contained in [Iterable] but it does not matter in which order.
 *
 * @param values The nullable values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.the(values: NullableValues<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(this, values.toList()))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] must contain only one
 * entry which holds all assertions created by the given [assertionCreator].
 *
 * Delegates to `the Entries(assertionCreator)`
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.entry(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this the Entries(assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator] or is `null` in which case
 * [assertionCreator] has to be defined as `null` as well.
 *
 * Delegates to `the NullableEntries(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.nullableEntry(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this the NullableEntries(assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry needs to be contained in the
 * [Iterable] which holds all assertions [Entries.assertionCreator] might create -- equally an entry for each further
 * [Entries.otherAssertionCreators] needs to be contained in the [Iterable] where it does not matter in which order the
 * entries appear.
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toBe(1) })` but for `entries({ toBe(1) }, { isGreaterThan(0) })` otherwise
 * `isGreaterThan(0)` matches `1` before `toBe(1)` would match it. As a consequence `toBe(1)` could only match the
 * entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param entries The parameter object containing the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.the(entries: Entries<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderOnly(this, entries.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry needs to be contained in the
 * [Iterable] which holds all assertions [NullableEntries.assertionCreator] might create or it needs to be `null` in
 * case [NullableEntries.assertionCreator] is `null` -- likewise an entry for each
 * further [NullableEntries.otherAssertionCreators] needs to be contained in the [Iterable]
 * where it does not matter in which order the entries appear but only as many entries should be returned by the
 * [Iterable] as assertion creators are defined.
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toBe(1) })` but for `entries({ toBe(1) }, { isGreaterThan(0) })` otherwise
 * `isGreaterThan(0)` matches `1` before `toBe(1)` would match it. As a consequence `toBe(1)` could only match the
 * entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param entries The parameter object containing the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.the(entries: NullableEntries<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderOnly(this, entries.toList()))
