package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.creating.AssertImpl
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
infix fun <E, T : Iterable<E>> Assert<T>.to(@Suppress("UNUSED_PARAMETER") contain: contain): IterableContains.Builder<E, T, NoOpSearchBehaviour>
    = AssertImpl.iterable.containsBuilder(this)

@Deprecated("use the extension function `to`, will be removed with 1.0.0", ReplaceWith("plant to contain"))
fun <E, T : Iterable<E>> to(plant: Assert<T>, @Suppress("UNUSED_PARAMETER") contain: contain): DeprecatedBuilder<E, T, NoOpSearchBehaviour>
    = DeprecatedBuilder(plant, (plant to contain).searchBehaviour)


/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Assert<T>.notTo(@Suppress("UNUSED_PARAMETER") contain: contain): NotCheckerOption<E, T, InAnyOrderSearchBehaviour>
    = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))

@Deprecated("use the extension function `notTo`, will be removed with 1.0.0", ReplaceWith("plant notTo contain"))
fun <E, T : Iterable<E>> notTo(plant: Assert<T>, @Suppress("UNUSED_PARAMETER") contain: contain): DeprecatedNotCheckerBuilder<E, T>
    = DeprecatedNotCheckerBuilder(AssertImpl.iterable.containsNotBuilder(plant))


/**
 * Makes the assertion that [AssertionPlant.subject] contains the [expected] value.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.contains(expected: E)
    = this to contain inAny order atLeast 1 value expected

/**
 * Makes the assertion that [AssertionPlant.subject] contains the expected [values].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Values(...)`
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
infix fun <E, T : Iterable<E>> Assert<T>.contains(values: Values<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the values

/**
 * Makes the assertion that [AssertionPlant.subject] contains the expected [objects].
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
infix fun <E, T : Iterable<E>> Assert<T>.contains(objects: Objects<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the objects

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] where it does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this to contain inAny order atLeast 1 entry assertionCreator

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if defined) where it
 * does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(entries: Entries<E, Assert<E>.() -> Unit>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the entries

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] where it does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("contains?")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this to contain inAny order atLeast 1 entry assertionCreator

@Deprecated("use the extension fun `contains` instead, will be removed 1.0.0", ReplaceWith("plant contains assertionCreator"))
fun <E : Any, T : Iterable<E?>> containsNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant contains assertionCreator


/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if defined) where it
 * does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("contains?")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the entries

@Deprecated("use the extension fun `contains` instead, will be removed 1.0.0", ReplaceWith("plant contains entries"))
fun <E : Any, T : Iterable<E?>> containsNullable(plant: Assert<T>, entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = plant contains entries


/**
 * Makes the assertion that [AssertionPlant.subject] contains only the [expected] value.
 *
 * It is a shortcut for `to contain inGiven order but only value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(expected: E): AssertionPlant<T>
    = this to contain inGiven order but only value expected

/**
 * Makes the assertion that [AssertionPlant.subject] contains only the expected [values] in the defined order.
 *
 * It is a shortcut for `to contain inGiven order but only the Values(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(values: Values<E>): AssertionPlant<T>
    = this to contain inGiven order but only the values

/**
 * Makes the assertion that [AssertionPlant.subject] contains only the expected [objects] in the defined order.
 *
 * It is a shortcut for `to contain inGiven order but only the Objects(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(objects: Objects<E>): AssertionPlant<T>
    = this to contain inGiven order but only the objects

/**
 * Makes the assertion that [AssertionPlant.subject] contains only one entry which is holding the assertions created
 * by the [assertionCreator].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this to contain inGiven order but only entry assertionCreator

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if defined) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `to contain inGiven order but only the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(entries: Entries<E, Assert<E>.() -> Unit>): AssertionPlant<T>
    = this to contain inGiven order but only the entries

/**
 * Makes the assertion that [AssertionPlant.subject] contains only one entry which is holding the assertions created
 * by the [assertionCreator].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsStrictly?")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this to contain inGiven order but only entry assertionCreator

@Deprecated("use the extension fun `containsStrictly` instead, will be removed 1.0.0", ReplaceWith("plant containsStrictly assertionCreator"))
fun <E : Any, T : Iterable<E?>> containsStrictlyNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant containsStrictly assertionCreator


/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if defined) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `to contain inGiven order but only the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsStrictly?")
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = this to contain inGiven order but only the entries

@Deprecated("use the extension fun `containsStrictly` instead, will be removed 1.0.0", ReplaceWith("plant containsStrictly entries"))
fun <E : Any, T : Iterable<E?>> containsStrictlyNullable(plant: Assert<T>, entries: Entries<E, (Assert<E>.() -> Unit)?>): AssertionPlant<T>
    = plant containsStrictly entries

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the [expected] value.
 *
 * Delegates to `containsNot Objects(expected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsNot(expected: E): AssertionPlant<T>
    = this containsNot Objects(expected)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the expected [values].
 *
 * Delegates to `containsNot Objects(values)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsNot(values: Values<E>): AssertionPlant<T>
    = this containsNot Objects(values)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the expected [objects].
 *
 * It is a shortcut for `notTo contain the Objects(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsNot(objects: Objects<E>)
    = this notTo contain the objects
