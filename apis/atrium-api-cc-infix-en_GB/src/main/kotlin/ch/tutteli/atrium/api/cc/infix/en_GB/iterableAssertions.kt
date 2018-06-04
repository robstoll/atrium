package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Assert<T>.to(@Suppress("UNUSED_PARAMETER") contain: contain): IterableContains.Builder<E, T, NoOpSearchBehaviour>
    = AssertImpl.iterable.containsBuilder(this)

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
infix fun <E, T : Iterable<E>> Assert<T>.notTo(@Suppress("UNUSED_PARAMETER") contain: contain): NotCheckerOption<E, T, InAnyOrderSearchBehaviour>
    = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))


/**
 * Makes the assertion that [AssertionPlant.subject] contains the [expected] value.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(expected: E)
    = this to contain inAny order atLeast 1 value expected

/**
 * Makes the assertion that [AssertionPlant.subject] contains the [expected] nullable value.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 nullableValue expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any?, T : Iterable<E>> Assert<T>.contains(expected: NullableValue<E>)
    = this to contain inAny order atLeast 1 nullableValue expected.expected


/**
 * Makes the assertion that [AssertionPlant.subject] contains the expected [values].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Values(...)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [Values.expected] is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 value 'a'`
 * instead of:
 *   `contains Values('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(values: Values<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the values

/**
 * Makes the assertion that [AssertionPlant.subject] contains the expected nullable [values].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the NullableValues(...)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [Values.expected] is defined as `'a'` and one [Values.otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `to contain inAny order exactly 2 nullableValue 'a'`
 * instead of:
 *   `contains NullableValues('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any?, T : Iterable<E>> Assert<T>.contains(values: NullableValues<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the values


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
 * [assertionCreator] where it does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.any(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this to contain inAny order atLeast 1 entry assertionCreator

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [NullableEntry.assertionCreator] or an entry which is `null` in case [NullableEntry.assertionCreator] is `null`
 * as well where it does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 nullableEntry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(entry: NullableEntry<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 nullableEntry entry.assertionCreator

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [NullableEntry.assertionCreator] or an entry which is `null` in case [NullableEntry.assertionCreator] is `null`
 * as well where it does not matter in which order the entries appear.
 *
 * It is a shortcut for `to contain inAny order atLeast 1 nullableEntry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.any(entry: NullableEntry<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 nullableEntry entry.assertionCreator

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [Entries.assertionCreator] -- likewise an entry (can be the same) is searched for each
 * of the [Entries.otherAssertionCreators].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.contains(entries: Entries<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the entries

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [NullableEntries.assertionCreator] or an entry which is `null` in case [NullableEntries.assertionCreator] is `null`
 * as well -- likewise an entry (can be the same) is searched for each
 * of the [NullableEntries.otherAssertionCreators].
 *
 * It is a shortcut for `to contain inAny order atLeast 1 the NullableEntries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(entries: NullableEntries<E>): AssertionPlant<T>
    = this to contain inAny order atLeast 1 the entries


/**
 * Makes the assertion that [AssertionPlant.subject] contains only the [expected] value.
 *
 * It is a shortcut for `to contain inGiven order and only value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(expected: E): AssertionPlant<T>
    = this to contain inGiven order and only value expected

/**
 * Makes the assertion that [AssertionPlant.subject] contains only the [expected] nullable value in the defined order.
 *
 * It is a shortcut for `to contain inGiven order and only nullable expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(expected: NullableValue<E>): AssertionPlant<T>
    = this to contain inGiven order and only nullableValue expected.expected

/**
 * Makes the assertion that [AssertionPlant.subject] contains only the expected [values] in the defined order.
 *
 * It is a shortcut for `to contain inGiven order and only the Values(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(values: Values<E>): AssertionPlant<T>
    = this to contain inGiven order and only the values

/**
 * Makes the assertion that [AssertionPlant.subject] contains only the expected nullable [values] in the defined order.
 *
 * It is a shortcut for `to contain inGiven order and only the NullableValues(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(values: NullableValues<E>): AssertionPlant<T>
    = this to contain inGiven order and only the values

/**
 * Makes the assertion that [AssertionPlant.subject] contains only one entry which is holding the assertions created
 * by the [assertionCreator].
 *
 * It is a shortcut for `to contain inGiven order and only the entry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this to contain inGiven order and only entry assertionCreator

/**
 * Makes the assertion that [AssertionPlant.subject] contains only one entry which is holding the assertions created
 * by the [NullableEntry.assertionCreator].
 *
 * It is a shortcut for `to contain inGiven order and only nullableEntry { ... }`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(entry: NullableEntry<E>): AssertionPlant<T>
    = this to contain inGiven order and only nullableEntry entry.assertionCreator


/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [Entries.assertionCreator] and an additional entry for each [Entries.otherAssertionCreators] (if defined) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `to contain inGiven order and only the Entries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(entries: Entries<E>): AssertionPlant<T>
    = this to contain inGiven order and only the entries

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [NullableEntries.assertionCreator] where the entry needs to be `null` in case [NullableEntries.assertionCreator]
 * is `null` and an additional entry for each [NullableEntries.otherAssertionCreators] (if defined) whereas the entries
 * have to appear in the defined order.
 *
 * It is a shortcut for `to contain inGiven order and only the NullableEntries(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(entries: NullableEntries<E>): AssertionPlant<T>
    = this to contain inGiven order and only the entries


/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the [expected] value.
 *
 * It is a shortcut for `notTo contain value expected`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsNot(expected: E): AssertionPlant<T>
    = this notTo contain value expected

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the expected [values].
 *
 * It is a shortcut for `notTo contain the Values(...)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsNot(values: Values<E>): AssertionPlant<T>
    = this notTo contain the values

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain a single entry which holds all assertions
 * created by the [assertionCreator].
 *
 *  It is a shortcut for `notTo contain entry assertionCreator`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.none(assertionCreator: (Assert<E>.() -> Unit))
    = this notTo contain entry assertionCreator

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain a single entry which holds all assertions
 * created by the [NullableEntry.assertionCreator] or does not contain a single entry which is `null`
 * in case [NullableEntry.assertionCreator] is `null` as well.
 *
 *  It is a shortcut for `notTo contain nullableEntry assertionCreator`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.none(entry: NullableEntry<E>)
    = this notTo contain nullableEntry entry.assertionCreator


/**
 * Makes the assertion that [AssertionPlant.subject] has at least one element and that the elements hold all assertions
 * created by the [assertionCreator].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E>> Assert<T>.all(assertionCreator: Assert<E>.() -> Unit)
    = addAssertion(AssertImpl.iterable.all(this, assertionCreator))

/**
 * Makes the assertion that [AssertionPlant.subject] ahs at least one element and that the elements hold all assertions
 * created by the [NullableEntry.assertionCreator] or are all `null` in case
 * [NullableEntry.assertionCreator] is `null` as well.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> Assert<T>.all(entry: NullableEntry<E>)
    = addAssertion(AssertImpl.iterable.all(this, entry.assertionCreator))
