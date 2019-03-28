@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders.IterableContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder as DeprecatedBuilder

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.to(contain)"))
infix fun <E, T : Iterable<E>> Assert<T>.to(@Suppress("UNUSED_PARAMETER") contain: contain): IterableContains.Builder<E, T, NoOpSearchBehaviour>
    = AssertImpl.iterable.containsBuilder(this)

@Deprecated("Use the extension function `to`; will be removed with 1.0.0", ReplaceWith("plant to contain"))
fun <E, T : Iterable<E>> to(plant: Assert<T>, @Suppress("UNUSED_PARAMETER") contain: contain): DeprecatedBuilder<E, T, NoOpSearchBehaviour>
    = DeprecatedBuilder(plant, (plant to contain).searchBehaviour)


/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.notTo(contain)"))
infix fun <E, T : Iterable<E>> Assert<T>.notTo(@Suppress("UNUSED_PARAMETER") contain: contain): NotCheckerOption<E, T, InAnyOrderSearchBehaviour>
    = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))

@Deprecated("Use the extension function `notTo`; will be removed with 1.0.0", ReplaceWith("plant notTo contain"))
fun <E, T : Iterable<E>> notTo(plant: Assert<T>, @Suppress("UNUSED_PARAMETER") contain: contain): DeprecatedNotCheckerBuilder<E, T>
    = DeprecatedNotCheckerBuilder(AssertImpl.iterable.containsNotBuilder(plant))


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains the [expected] value.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `contains NullableValue` from package en_GB or `contains` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("this contains NullableValue(expected)", "ch.tutteli.atrium.api.cc.infix.en_GB.contains", "ch.tutteli.atrium.api.cc.infix.en_GB.NullableValue"))
infix fun <E, T : Iterable<E>> Assert<T>.contains(expected: E)
    = this to contain inAny order atLeast 1 value expected

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains the expected [values].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Values(...)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [Values.expected] is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 value('a')`
 * instead of:
 *   `contains Values('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `contains NullableValues` from package en_GB or `contains Values` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("this contains NullableValues(values.expected, *values.otherExpected)", "ch.tutteli.atrium.api.cc.infix.en_GB.contains", "ch.tutteli.atrium.api.cc.infix.en_GB.NullableValues"))
infix fun <E, T : Iterable<E>> Assert<T>.contains(values: Values<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the values

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains the expected [objects].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Objects(...)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [Objects.expected] is
 * defined as `'a'` and one [Objects.otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 value('a')`
 * instead of:
 *   `contains Objects('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `contains Values(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("this contains Values(objects)"))
infix fun <E, T : Iterable<E>> Assert<T>.contains(objects: Objects<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the objects

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] where it does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.contains(assertionCreator)"))
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this to contain inAny order atLeast 1 entry assertionCreator

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if given) where it
 * does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.contains(entries)"))
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(entries: Entries<E, Assert<E>.() -> Unit>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the entries

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] where it does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `contains NullableEntry` from package en_GB; will be removed with 1.0.0", ReplaceWith("this contains NullableEntry(expected)", "ch.tutteli.atrium.api.cc.infix.en_GB.contains", "ch.tutteli.atrium.api.cc.infix.en_GB.NullableEntry"))
@JvmName("containsDeprecated")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this to contain inAny order atLeast 1 entry assertionCreator

@Deprecated("Use the extension fun `contains` instead; will be removed 1.0.0", ReplaceWith("plant contains assertionCreator"))
fun <E : Any, T : Iterable<E?>> containsNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant contains assertionCreator


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if given) where it
 * does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `contains NullableEntries` from package en_GB; will be removed with 1.0.0", ReplaceWith("this contains NullableEntries(entries.assertionCreator, *entries.otherAssertionCreators)", "ch.tutteli.atrium.api.cc.infix.en_GB.contains", "ch.tutteli.atrium.api.cc.infix.en_GB.NullableEntries"))
@JvmName("containsDeprecated")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the entries

@Deprecated("Use the extension fun `contains` instead; will be removed 1.0.0", ReplaceWith("plant contains entries"))
fun <E : Any, T : Iterable<E?>> containsNullable(plant: Assert<T>, entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = plant contains entries


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only the [expected] value.
 *
 * It is a shortcut for `to contain inGiven order but only value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `containsStrictly NullableValue` from package en_GB or `containsStrictly` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("this contains NullableValue(expected)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsStrictly", "ch.tutteli.atrium.api.cc.infix.en_GB.NullableValue"))
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(expected: E): AssertionPlant<T>
    = this to contain inGiven order but only value expected

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only the expected [values] in the defined order.
 *
 * It is a shortcut for `to contain inGiven order but only the Values(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `containsStrictly NullableValues` from package en_GB or `containsStrictly` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("this contains NullableValues(value.expected, *value.otherExpected)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsStrictly", "ch.tutteli.atrium.api.cc.infix.en_GB.NullableValues"))
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(values: Values<E>): AssertionPlant<T>
    = this to contain inGiven order but only the values

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only the expected [objects] in the defined order.
 *
 * It is a shortcut for `to contain inGiven order but only the Objects(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `containsStrictly Values(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("this containsStrictly Values(objects)"))
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(objects: Objects<E>): AssertionPlant<T>
    = this to contain inGiven order but only the objects

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only one entry which is holding the assertions created
 * by the [assertionCreator].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.containsStrictly(assertionCreator)"))
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this to contain inGiven order but only entry assertionCreator

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if given) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `to contain inGiven order but only the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.containsStrictly(entries)"))
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(entries: Entries<E, Assert<E>.() -> Unit>): AssertionPlant<T>
    = this to contain inGiven order but only the entries

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only one entry which is holding the assertions created
 * by the [assertionCreator].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `contains NullableEntry` from package en_GB; will be removed with 1.0.0", ReplaceWith("this contains NullableEntry(expected)", "ch.tutteli.atrium.api.cc.infix.en_GB.contains", "ch.tutteli.atrium.api.cc.infix.en_GB.NullableEntry"))
@JvmName("containsStrictly?")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this to contain inGiven order but only entry assertionCreator

@Deprecated("Use the extension fun `containsStrictly` instead; will be removed 1.0.0", ReplaceWith("plant containsStrictly assertionCreator"))
fun <E : Any, T : Iterable<E?>> containsStrictlyNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant containsStrictly assertionCreator


/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if given) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `to contain inGiven order but only the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `containsStrictly NullableEntries` from package en_GB; will be removed with 1.0.0", ReplaceWith("this containsStrictly NullableEntries(entries.assertionCreator, *entries.otherAssertionCreators)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsStrictly", "ch.tutteli.atrium.api.cc.infix.en_GB.NullableEntries"))
@JvmName("containsStrictly?")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = this to contain inGiven order but only the entries

@Deprecated("Use the extension fun `containsStrictly` instead; will be removed 1.0.0", ReplaceWith("plant containsStrictly entries"))
fun <E : Any, T : Iterable<E?>> containsStrictlyNullable(plant: Assert<T>, entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = plant containsStrictly entries

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] does not contain the [expected] value.
 *
 * Delegates to `containsNot Values(expected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.containsNot(expected)"))
infix fun <E, T : Iterable<E>> Assert<T>.containsNot(expected: E): AssertionPlant<T>
    = this containsNot Values(expected)

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] does not contain the expected [values].
 *
 * It is a shortcut for `notTo contain the Values(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use `notTo contain the NullableValues` from package en_GB or `containsNot` from package en_GB in case you do not deal with nullable elements; will be removed with 1.0.0", ReplaceWith("notTo contain the NullableValues(values.expected, *values.otherExpected)", "ch.tutteli.atrium.api.cc.infix.en_GB.notTo", "ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain", "ch.tutteli.atrium.api.cc.infix.en_GB.the", "ch.tutteli.atrium.api.cc.infix.en_GB.NullableValues"))
infix fun <E, T : Iterable<E>> Assert<T>.containsNot(values: Values<E>): AssertionPlant<T>
    = this notTo contain the values

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] does not contain the expected [objects].
 *
 * It is a shortcut for `notTo contain the Objects(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `containsNot Values(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("this containsNot Values(objects)"))
infix fun <E, T : Iterable<E>> Assert<T>.containsNot(objects: Objects<E>)
    = this containsNot Values(objects)
