package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions._containsBuilder
import ch.tutteli.atrium.assertions._containsNot
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Creates an [IterableContainsBuilder] based on this [IAssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IAssertionPlant<T>.contains
    get(): IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
    = _containsBuilder(this)

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]
 * and the [otherExpected] (if defined).
 *
 * It is a shortcut for `contains.inAnyOrder.atLeast.values(expected, *otherExpected)`
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
fun <E, T : Iterable<E>> IAssertionPlant<T>.contains(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).objects(expected, *otherExpected)

/**
 * Makes the assertion that [IAssertionPlant.subject] contains only [expected] and the [otherExpected] (if defined) in
 * the defined order.
 *
 * It is a shortcut for `contains.inOrder.only.objects(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IAssertionPlant<T>.containsStrictly(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = contains.inOrder.only.objects(expected, *otherExpected)

/**
 * Makes the assertion that [IAssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if defined) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `contains.inOrder.only.entries(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IAssertionPlant<T>.containsStrictly(assertionCreator: IAssertionPlant<E>.() -> Unit, vararg otherAssertionCreators: IAssertionPlant<E>.() -> Unit): IAssertionPlant<T>
    = contains.inOrder.only.entries(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [IAssertionPlant.subject] does not contain [expected]
 * and neither one of the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IAssertionPlant<T>.containsNot(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = addAssertion(_containsNot(this, expected, *otherExpected))
