package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions._containsBuilder
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
val <E, T : Iterable<E>> IAssertionPlant<T>.enthaelt
    get(): IterableContainsBuilder<E, T, IterableContainsNoOpDecorator>
    = _containsBuilder(this)

/**
 * Makes the assertion that [IAssertionPlant.subject] contains [expected]
 * and the [otherExpected] (if defined).
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest.werte(expected, *otherExpected)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [zumindest], [hoechstens] and [genau] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.inBeliebigerReihenfolge.genau(2).wert('a')`
 * instead of:
 *   `enthaelt('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IAssertionPlant<T>.enthaelt(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).objekte(expected, *otherExpected)

/**
 * Makes the assertion that [IAssertionPlant.subject] does not contain [expected]
 * and neither one of the [otherExpected] (if defined).
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E, T : Iterable<E>> IAssertionPlant<T>.enthaeltNicht(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = addAssertion(_containsNot(this, expected, *otherExpected))
