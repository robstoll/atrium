@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableContainsInAnyOrderCreatorsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.kbox.glue
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains

import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected]
 * value shall be searched within the [Iterable].
 *
 * Delegates to `values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.value(expected)",
        "ch.tutteli.atrium.api.fluent.en_GB.value"
    )
)
fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.value(expected: E): AssertionPlant<T>
    = values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected]
 * value as well as the [otherExpected] values (if given) shall be searched within the [Iterable].
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and
 * [expected] is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match,
 * even though they match the same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the
 * number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `contains.inAnyOrder.exactly(2).values('a')`
 * instead of:
 *   `contains.inAnyOrder.atLeast(1).values('a', 'a')`
 *
 * @param expected The object which is expected to be contained within the [Iterable].
 * @param otherExpected Additional objects which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.values(expected, *otherExpected)",
        "ch.tutteli.atrium.api.fluent.en_GB.values"
    )
)
fun <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>.values(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrder(this, expected glue otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreatorOrNull] might create or needs to be `null` in case [assertionCreatorOrNull]
 * is defined as `null`.
 *
 * Delegates to `entries(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.entry(asSubExpect(assertionCreatorOrNull))",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.entry"
    )
)
fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.entry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry shall be searched which either
 * holds all assertions [assertionCreatorOrNull] might create or needs to be `null` in case
 * [assertionCreatorOrNull] is defined as `null` -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreatorsOrNulls].
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0",
    ReplaceWith(
        "this.entries(\n" +
            "asSubExpect(assertionCreatorOrNull),\n" +
            "*otherAssertionCreatorsOrNulls.map { asSubExpect(it) }.toTypedArray()\n" +
            ")",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.entries"
    )
)
fun <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>.entries(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderWithAssert(this, assertionCreatorOrNull glue otherAssertionCreatorsOrNulls))

/**
 * Helper method to simplify adding assertions to the plant which itself is stored in
 * [CharSequenceContains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
private fun <E, T : Iterable<E>, S : IterableContains.SearchBehaviour> IterableContains.CheckerOption<E, T, S>.addAssertion(
    assertion: Assertion
): AssertionPlant<T> = addAssertionForAssert(assertion)
