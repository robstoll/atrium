package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions._containsNot
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsNoOpDecorator
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * Creates an [IterableContainsBuilder] based on this [IAssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
val <E, T : Iterable<E>> IAssertionPlant<T>.contains
    get(): IterableContainsBuilder<E, T, IterableContainsNoOpDecorator>
    = IterableContainsBuilder(this, IterableContainsNoOpDecorator)

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]
 * and the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IAssertionPlant<T>.contains(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = contains.inAnyOrder.atLeast(1).objects(expected, *otherExpected)

/**
 * Makes the assertion that [IAssertionPlant.subject] does not contain [expected]
 * and neither one of the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IAssertionPlant<T>.containsNot(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = addAssertion(_containsNot(this, expected, *otherExpected))
