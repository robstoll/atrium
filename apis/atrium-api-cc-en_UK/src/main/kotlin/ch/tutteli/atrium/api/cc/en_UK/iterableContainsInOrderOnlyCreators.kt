package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.creators._containsEntriesInOrderOnly
import ch.tutteli.atrium.assertions.iterable.contains.creators._containsObjectsInOrderOnly
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `objects(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = objects(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values need to be contained in [Iterable] in the specified order.
 *
 * Delegates to `objects(expected, *otherExpected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.values(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = objects(expected, *otherExpected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] object.
 *
 * Delegate to `objects(expected)`.
 *
 * @param expected The object which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.`object`(expected: E): AssertionPlant<T>
    = objects(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as the
 * [otherExpected] objects need to be contained in [Iterable] in the specified order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.objects(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = plant.addAssertion(_containsObjectsInOrderOnly(this, expected, otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator].
 *
 * Delegates to `entries(assertionCreator)`
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.entry(assertionCreator: AssertionPlant<E>.() -> Unit): AssertionPlant<T>
    = entries(assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [assertionCreator] might create -- equally an entry for each further
 * [otherAssertionCreators], following the specified order, needs to be contained in the [Iterable]
 *
 * @param assertionCreator The lambda function which creates the assertions which the entry we are looking for
 *        has to hold; or in other words, the function which defines whether an entry is the one we are looking for.
 * @param otherAssertionCreators Additional lambda functions which each kind of identify (separately) an entry
 *        which we are looking for.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.entries(assertionCreator: AssertionPlant<E>.() -> Unit, vararg otherAssertionCreators: AssertionPlant<E>.() -> Unit): AssertionPlant<T>
    = plant.addAssertion(_containsEntriesInOrderOnly(this, assertionCreator, otherAssertionCreators))
