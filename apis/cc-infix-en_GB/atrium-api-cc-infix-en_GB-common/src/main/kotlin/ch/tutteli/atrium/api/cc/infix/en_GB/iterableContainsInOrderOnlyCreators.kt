@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyCreatorsKt")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `the Values(expected)`.
 *
 * @param expected The nullable value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = this the Values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * expected [values] in the specified order.
 *
 * @param values The nullable values which are expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.the(values: Values<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInOrderOnly(this, values.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only a
 * single entry which holds all assertions created by the given [assertionCreatorOrNull] or needs to be `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `the Entries(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.entry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this the Entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only an
 * entry which holds all assertions [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull]
 * might create or is `null` in case [entries].[assertionCreatorOrNull][Entries.otherAssertionCreatorsOrNulls]
 * is defined as `null` and likewise a further entry for each
 * [entries].[otherAssertionCreatorsOrNulls][Entries.otherExpected]
 * (if given) whereas the entries have to appear in the specified order.
 *
 * This function will be renamed on the JVM level to `the` with 1.0.0;
 *
 * @param entries The parameter object containing the identification lambdas.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Suppress("DEPRECATION")
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.the(entries: Entries<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInOrderOnlyWithAssert(this, entries.toList()))
