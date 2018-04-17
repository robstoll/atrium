package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.addAssertion
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * within the [Iterable].
 *
 * Delegates to `the Values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.value(expected: E): AssertionPlant<T>
    = this the Values(expected)

@Deprecated("Use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder value expected"))
fun <E, T : Iterable<E>> value(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E): AssertionPlant<T>
    = the(checkerBuilder, Values(expected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [Values.expected] is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 value 'a'`
 * instead of:
 *   `to contain inAny order exactly 1 the Values('a', 'a')`
 *
 * @param values The values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(values: Values<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, values.toList()))

@Deprecated("Use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder the values"))
fun <E, T : Iterable<E>> the(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, values: Values<E>): AssertionPlant<T>
    = checkerBuilder the values


@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `value expected` without adding enough to be a legit alternative.", ReplaceWith("this value expected"))
infix fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.`object`(expected: E): AssertionPlant<T>
    = this the Values(expected)

@Deprecated("Use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder value expected"))
fun <E, T : Iterable<E>> `object`(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E): AssertionPlant<T>
    = the(checkerBuilder, Values(expected))

@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `the Values(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("this the Values(objects)"))
infix fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(objects: Objects<E>): AssertionPlant<T>
    = this the Values(objects)

@Deprecated("Use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder the objects"))
fun <E, T : Iterable<E>> the(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, objects: Objects<E>): AssertionPlant<T>
    = checkerBuilder the Values(objects)


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [assertionCreator] might create.
 *
 * Delegates to `the Entries(assertionCreator)`.
 *
 * @param assertionCreator The lambda function which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.entry(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this the Entries(assertionCreator)

@Deprecated("Use the extension fun `entry` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder entry assertionCreator"))
fun <E : Any, T : Iterable<E>> entry(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = the(checkerBuilder, Entries(assertionCreator))


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [Entries.assertionCreator] might create and search for entries which hold (one by one) the assertions
 * created by the [Entries.otherAssertionCreators].
 *
 * @param entries The parameter object which contains the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.the(entries: Entries<E, Assert<E>.() -> Unit>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrder(this, entries.toList()))

@Deprecated("Use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder the entries"))
fun <E : Any, T : Iterable<E>> the(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, entries: Entries<E, Assert<E>.() -> Unit>): AssertionPlant<T>
    = checkerBuilder the entries


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreator] might create or is `null` in case [assertionCreator] is null as well.
 *
 * Delegates to `entries(expected)`.
 *
 * @param assertionCreator The lambda function which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("entry?")
infix fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.entry(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this the Entries(assertionCreator)

@Deprecated("Use the extension fun `entry` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder entry assertionCreator"))
fun <E : Any, T : Iterable<E?>> nullableEntry(checkerBuilder: IterableContainsCheckerBuilder<E?, T, InAnyOrderSearchBehaviour>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEntries(checkerBuilder, Entries(assertionCreator))


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [Entries.assertionCreator] might create or is `null` in case [Entries.assertionCreator] is null as well --
 * likewise an entry (can be the same) is searched for each of the [Entries.otherAssertionCreators].
 *
 * @param entries The parameter object which contains the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("entries?")
infix fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.the(entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.nullableEntriesInAnyOrder(this, entries.toList()))

@Deprecated("Use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder the entries"))
fun <E : Any, T : Iterable<E?>> nullableEntries(checkerBuilder: IterableContainsCheckerBuilder<E?, T, InAnyOrderSearchBehaviour>, entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = checkerBuilder the entries
