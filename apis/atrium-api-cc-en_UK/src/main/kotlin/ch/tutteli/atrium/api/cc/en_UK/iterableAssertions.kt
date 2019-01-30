@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders.IterableContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder as DeprecatedBuilder

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.contains"))
val <E, T : Iterable<E>> Assert<T>.contains: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    get() = AssertImpl.iterable.containsBuilder(this)

@Deprecated("Use the extension fun `contains` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("plant.contains"))
fun <E, T : Iterable<E>> getContains(plant: Assert<T>): DeprecatedBuilder<E, T, NoOpSearchBehaviour>
    = DeprecatedBuilder(plant, plant.contains.searchBehaviour)


/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.containsNot"))
val <E, T : Iterable<E>> Assert<T>.containsNot: NotCheckerOption<E, T, InAnyOrderSearchBehaviour>
    get() = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))

@Deprecated("Use the extension fun `containsNot` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("plant.containsNot"))
fun <E, T : Iterable<E>> getContainsNot(plant: Assert<T>): DeprecatedNotCheckerBuilder<E, T>
    = DeprecatedNotCheckerBuilder(AssertImpl.iterable.containsNotBuilder(plant))


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains [expected]
 * and the [otherExpected] (if given).
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
@Deprecated("Use `containsNullableValues` from package en_GB or `contains` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("containsNullableValues(expected, *otherExpected)", "ch.tutteli.atrium.api.cc.en_GB.containsNullableValues"))
fun <E, T : Iterable<E>> Assert<T>.contains(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).values(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if given) where it does not matter
 * in which order the entries appear.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.contains(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> Assert<T>.contains(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if given) where it does not matter
 * in which order the entries appear.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsDeprecated")
@Deprecated("Use `containsNullableEntries` from package en_GB or `contains` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("containsNullableEntries(assertionCreator, *otherAssertionCreators)", "ch.tutteli.atrium.api.cc.en_GB.containsNullableEntries"))
fun <E : Any, T : Iterable<E?>> Assert<T>.contains(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)

@Deprecated("Use the extension fun `contains` instead; will be removed 1.0.0", ReplaceWith("plant.contains(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> containsNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant.contains(assertionCreator, *otherAssertionCreators)


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only [expected] and the [otherExpected] (if given) in
 * the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.values(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `containsStrictlyNullableValues` from package en_GB or `containsStrictly` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("containsStrictlyNullableValues(expected, *otherExpected)", "ch.tutteli.atrium.api.cc.en_GB.containsStrictlyNullableValues"))
fun <E, T : Iterable<E>> Assert<T>.containsStrictly(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inOrder.only.values(expected, *otherExpected)

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if given) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `contains.inOrder.only.entries(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.containsStrictly(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = contains.inOrder.only.entries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if given) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `contains.inOrder.only.entries(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `containsStrictlyNullableEntries` from package en_GB or `containsStrictly` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("containsStrictlyNullableEntries(assertionCreator, *otherAssertionCreators)", "ch.tutteli.atrium.api.cc.en_GB.containsStrictlyNullableEntries"))
@JvmName("containsStrictly?")
fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inOrder.only.entries(assertionCreator, *otherAssertionCreators)

@Deprecated("Use the extension fun `containsStrictly` instead; will be removed 1.0.0", ReplaceWith("plant.containsStrictly(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> containsStrictlyNulllable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant.containsStrictly(assertionCreator, *otherAssertionCreators)


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] does not contain [expected]
 * and neither one of the [otherExpected] (if given).
 *
 *  It is a shortcut for `containsNot.values(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `containsNot.nullableValues` from package en_GB or `containsNot` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("containsNot.nullableValues(expected, *otherExpected)", "ch.tutteli.atrium.api.cc.en_GB.containsNot", "ch.tutteli.atrium.api.cc.en_GB.nullableValues" ))
fun <E, T : Iterable<E>> Assert<T>.containsNot(expected: E, vararg otherExpected: E)
    = containsNot.values(expected, *otherExpected)
