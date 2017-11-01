package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions._objectsInAnyOrderOnly
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsNoOpCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values need to be contained in [Iterable] where it does not matter in which order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlyDecorator>.values(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = objects(expected, *otherExpected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] object as well as the
 * [otherExpected] objects need to be contained in [Iterable] where it does not matter in which order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [IAssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderOnlyDecorator>.objects(expected: E, vararg otherExpected: E): IAssertionPlant<T> {
    val checker = IterableContainsNoOpCheckerBuilder(this)
    return checker.addAssertion(_objectsInAnyOrderOnly(checker, expected, otherExpected))
}

