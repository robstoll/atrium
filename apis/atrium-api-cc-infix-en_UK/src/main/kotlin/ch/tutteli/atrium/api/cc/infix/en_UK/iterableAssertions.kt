package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.assertions._containsBuilder
import ch.tutteli.atrium.assertions._containsNot
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Creates an [IterableContainsBuilder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Assert<T>.to(@Suppress("UNUSED_PARAMETER") contain: contain)
    = _containsBuilder(this)

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
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(assertionCreator: AssertionPlant<E>.() -> Unit): AssertionPlant<T>
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
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(entries: Entries<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the entries


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
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: AssertionPlant<E>.() -> Unit): AssertionPlant<T>
    = this to contain inGiven order but only entry assertionCreator

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if defined) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for ``to contain inGiven order but only the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(entries: Entries<E>): AssertionPlant<T>
    = this to contain inGiven order but only the entries


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
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsNot(objects: Objects<E>): AssertionPlant<T>
    = addAssertion(_containsNot(this, objects.expected, objects.otherExpected))
