package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions._containsEntriesInOrderOnly
import ch.tutteli.atrium.assertions._containsObjectsInOrderOnly
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values need to be contained in [Iterable] in the specified order.
 *
 * Delegates to [objekte].
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.werte(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = objekte(expected, *otherExpected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as the
 * [otherExpected] objects need to be contained in [Iterable] in the specified order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.objekte(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = plant.addAssertion(_containsObjectsInOrderOnly(this, expected, otherExpected))

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
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInOrderOnlySearchBehaviour>.eintraege(assertionCreator: IAssertionPlant<E>.() -> Unit, vararg otherAssertionCreators: IAssertionPlant<E>.() -> Unit): IAssertionPlant<T>
    = plant.addAssertion(_containsEntriesInOrderOnly(this, assertionCreator, otherAssertionCreators))
