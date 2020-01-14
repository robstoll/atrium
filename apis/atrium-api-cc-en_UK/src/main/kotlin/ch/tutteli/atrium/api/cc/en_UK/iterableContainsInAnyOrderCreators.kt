@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.kbox.glue
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * within the [Iterable].
 *
 * Delegates to `values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.value(expected)"))
fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.value(expected: E): AssertionPlant<T>
    = values(expected)

@JvmName("deprecatedValue")
@Deprecated("Use `nullableValue` instead; will be removed with 0.10.0", ReplaceWith("nullableValue(expected)"))
fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.value(expected: E): AssertionPlant<T>
    = nullableValue(expected)

@Deprecated("Use the extension fun `value` instead. This fun is only here to retain binary compatibility; will be removed with 0.10.0", ReplaceWith("checkerBuilder.value(expected)"))
fun <E, T : Iterable<E>> value(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E): AssertionPlant<T>
    = values(checkerBuilder, expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.inAnyOrder.exactly(2).values('a')`
 * instead of:
 *   `contains.inAnyOrder.atLeast(1).values('a', 'a')`
 *
 * @param expected The object which is expected to be contained within the [Iterable].
 * @param otherExpected Additional objects which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.values(expected, *otherExpected)"))
fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.values(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, expected glue otherExpected))

@Deprecated("Use `nullableValues` instead; will be removed with 0.10.0", ReplaceWith("nullableValues(expected, *otherExpected)"))
@JvmName("deprecatedValues")
fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.values(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = nullableValues(expected, *otherExpected)

@Deprecated("Use the extension fun `values` instead. This fun is only here to retain binary compatibility; will be removed with 0.10.0", ReplaceWith("checkerBuilder.values(expected, *otherExpected)"))
fun <E, T : Iterable<E>> values(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.values(expected, *otherExpected)


@Deprecated("Will be removed with 0.10.0 because it is redundant in terms of `value(expected)` without adding enough to be a legit alternative.", ReplaceWith("value(expected)"))
fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.`object`(expected: E): AssertionPlant<T>
    = values(expected)

@Deprecated("Use the extension fun `value` instead. This fun is only here to retain binary compatibility; will be removed with 0.10.0", ReplaceWith("checkerBuilder.value(expected)"))
fun <E, T : Iterable<E>> `object`(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E): AssertionPlant<T>
    = values(checkerBuilder, expected)

@Deprecated("Will be removed with 0.10.0 because it is redundant in terms of `values(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("values(expected, *otherExpected)"))
fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.objects(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = values(expected, *otherExpected)

@Deprecated("Use the extension fun `values` instead. This fun is only here to retain binary compatibility; will be removed with 0.10.0", ReplaceWith("checkerBuilder.values(expected, *otherExpected)"))
fun <E, T : Iterable<E>> objects(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.values(expected, *otherExpected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [assertionCreator] might create.
 *
 * Delegates to `entries(expected)`.
 *
 * @param assertionCreator The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.entry(assertionCreator)"))
fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.entry(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = entries(assertionCreator)

@Deprecated("Use the extension fun `entry` instead. This fun is only here to retain binary compatibility; will be removed with 0.10.0", ReplaceWith("checkerBuilder.entry(assertionCreator)"))
fun <E : Any, T : Iterable<E>> entry(checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>, assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = entries(checkerBuilder, assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which holds
 * all assertions [assertionCreator] might create and search for entries which hold (one by one) the assertions
 * created by the [otherAssertionCreators].
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
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.entries(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.entries(
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderWithAssert(this, assertionCreator glue otherAssertionCreators))

@Deprecated("Use the extension fun `entries` instead. This fun is only here to retain binary compatibility; will be removed with 0.10.0", ReplaceWith("checkerBuilder.entries(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> entries(
    checkerBuilder: IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>,
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = checkerBuilder.entries(assertionCreator, *otherAssertionCreators)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value shall be searched
 * within the [Iterable].
 *
 * Delegates to `nullableValues(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.nullableValue(expected)"))
fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.nullableValue(expected: E): AssertionPlant<T>
    = nullableValues(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.inAnyOrder.exactly(2).values('a')`
 * instead of:
 *   `contains.inAnyOrder.atLeast(1).values('a', 'a')`
 *
 * @param expected The object which is expected to be contained within the [Iterable].
 * @param otherExpected Additional objects which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.nullableValues(expected, *otherExpected)"))
fun <E : Any?, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.nullableValues(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, expected glue otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreator] might create or is `null` in case [assertionCreator] is null as well.
 *
 * Delegates to `entries(expected)`.
 *
 * @param assertionCreator The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.entry(assertionCreator)"))
@JvmName("entry?")
fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.entry(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = entries(assertionCreator)

@Deprecated("Use the extension fun `entry` instead. This fun is only here to retain binary compatibility; will be removed with 0.10.0", ReplaceWith("checkerBuilder.entry(assertionCreator)"))
fun <E : Any, T : Iterable<E?>> nullableEntry(checkerBuilder: IterableContainsCheckerBuilder<E?, T, InAnyOrderSearchBehaviour>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEntries(checkerBuilder, assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreator] might create or is `null` in case [assertionCreator] is null as well --
 * likewise an entry (can be the same) is searched for each of the [otherAssertionCreators].
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
@Deprecated("Use pendant from package en_GB; will be removed with 0.10.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.entries(assertionCreator, *otherAssertionCreators)"))
@JvmName("entries?")
fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.entries(
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderWithAssert(this, assertionCreator glue otherAssertionCreators))

@Deprecated("Use the extension fun `entries` instead. This fun is only here to retain binary compatibility; will be removed with 0.10.0", ReplaceWith("checkerBuilder.entries(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> nullableEntries(
    checkerBuilder: IterableContainsCheckerBuilder<E?, T, InAnyOrderSearchBehaviour>,
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = checkerBuilder.entries(assertionCreator, *otherAssertionCreators)

/**
 * Helper method to simplify adding assertions to the plant which itself is stored in
 * [CharSequenceContains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
private fun <E, T : Iterable<E>, S : IterableContains.SearchBehaviour> IterableContains.CheckerOption<E, T, S>.addAssertion(
    assertion: Assertion
): AssertionPlant<T> = addAssertionForAssert(assertion)
