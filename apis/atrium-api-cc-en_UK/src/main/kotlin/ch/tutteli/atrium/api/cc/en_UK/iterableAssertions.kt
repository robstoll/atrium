package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders.IterableContainsNotCheckerBuilder
import ch.tutteli.atrium.creating._containsBuilder
import ch.tutteli.atrium.creating._containsNotBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Creates an [IterableContainsBuilder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Assert<T>.contains: IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
    get() = _containsBuilder(this)

/**
 * Creates an [IterableContainsBuilder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> Assert<T>.containsNot: IterableContainsNotCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
    get() = IterableContainsNotCheckerBuilder(_containsNotBuilder(this))


/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]
 * and the [otherExpected] (if defined).
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
fun <E, T : Iterable<E>> Assert<T>.contains(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).objects(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if defined) where it does not matter
 * in which order the entries appear.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.contains(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if defined) where it does not matter
 * in which order the entries appear.
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsNullable")
fun <E : Any, T : Iterable<E?>> Assert<T>.contains(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).entries(assertionCreator, *otherAssertionCreators)


/**
 * Makes the assertion that [AssertionPlant.subject] contains only [expected] and the [otherExpected] (if defined) in
 * the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.objects(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> Assert<T>.containsStrictly(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = contains.inOrder.only.objects(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if defined) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `contains.inOrder.only.entries(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = contains.inOrder.only.entries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if defined) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `contains.inOrder.only.entries(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("containsStrictlyNulllable")
fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains.inOrder.only.entries(assertionCreator, *otherAssertionCreators)


/**
 * Makes the assertion that [AssertionPlant.subject] does not contain [expected]
 * and neither one of the [otherExpected] (if defined).
 *
 *  It is a shortcut for `containsNot.objects(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> Assert<T>.containsNot(expected: E, vararg otherExpected: E)
    = containsNot.objects(expected, *otherExpected)
