package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `the Objects(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = this the Objects(expected)

@Deprecated("Use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder value expected"))
fun <E, T : Iterable<E>> value(builder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = the(builder, Values(expected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * need to be contained in [Iterable] in the specified order.
 *
 * Delegates to `the Objects(values)`.
 *
 * @param values The values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(values: Values<E>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.objectsInOrderOnly(this, values.expected, values.otherExpected))

@Deprecated("Use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder the values"))
fun <E, T : Iterable<E>> the(builder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, values: Values<E>): AssertionPlant<T>
    = builder the values


@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `value expected` without adding enough to be a legit alternative.", ReplaceWith("value expected"))
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.`object`(expected: E): AssertionPlant<T>
    = this the Values(expected)

@Deprecated("Use the extension fun `object` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder.`object`(expected)"))
fun <E, T : Iterable<E>> `object`(builder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = the(builder, Values(expected))

@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `the Values(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("the Values(expected, *otherExpected)"))
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(objects: Objects<E>): AssertionPlant<T>
    = this the Values(objects)

@Deprecated("Use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder the objects"))
fun <E, T : Iterable<E>> the(builder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, objects: Objects<E>): AssertionPlant<T>
    = builder the Values(objects)


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

@Deprecated("Use the extension fun `entry` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder entry assertionCreator"))
fun <E : Any, T : Iterable<E>> entry(builder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = the(builder, Entries(assertionCreator))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [Entries.assertionCreator] might create -- equally an entry for each further
 * [Entries.otherAssertionCreators], following the specified order, needs to be contained in the [Iterable]
 *
 * @param entries The parameter object containing the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(entries: Entries<E, Assert<E>.() -> Unit>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInOrderOnly(this, entries.assertionCreator, entries.otherAssertionCreators))

@Deprecated("Use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder the entries"))
fun <E : Any, T : Iterable<E>> the(builder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, entries: Entries<E, Assert<E>.() -> Unit>): AssertionPlant<T>
    = builder the entries


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator].
 *
 * Delegates to `the Entries(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("entry?")
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.entry(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this the Entries(assertionCreator)

@Deprecated("Use the extension fun `entry` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder entry assertionCreator"))
fun <E : Any, T : Iterable<E?>> nullableEntry(builder: IterableContainsBuilder<E?, T, InOrderOnlySearchBehaviour>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEntries(builder, Entries(assertionCreator))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [Entries.assertionCreator] might create -- equally an entry for each further
 * [Entries.otherAssertionCreators] needs to be contained in the [Iterable]  where it does not matter in which order the
 * entries appear.
 *
 * Notice, that a first-wins strategy applies which means your [Entries.assertionCreator] functions -- which kind of serve as
 * identification functions -- should be ordered in such a way that the most specific identification function appears
 * first, not that a less specific function wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toBe(1) })` but for `entries({ toBe(1) }, { isGreaterThan(0) })` otherwise
 * `isGreaterThan(0)` matches `1` before `toBe(1)` would match it. As a consequence `toBe(1)` could only match the
 * entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param entries The parameter object containing the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("entries?")
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.the(entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.nullableEntriesInOrderOnly(this, entries.assertionCreator, entries.otherAssertionCreators))

@Deprecated("Use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("builder the entries"))
fun <E : Any, T : Iterable<E?>> nullableEntries(builder: IterableContainsBuilder<E?, T, InOrderOnlySearchBehaviour>, entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = builder the entries
