// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyCreatorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.kbox.glue
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegate to `werte(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E , T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.wert(expected: E): AssertionPlant<T>
    = werte(expected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value as well as the [otherExpected] values
 * (if given) in the specified order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E , T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.werte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInOrderOnly(this, expected glue otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only a
 * single entry which holds all assertions created by the given [assertionCreatorOrNull] or needs to be `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `eintraege(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.eintrag(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = eintraege(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only an
 * entry which holds all assertions [assertionCreatorOrNull] might create or is `null` in case [assertionCreatorOrNull]
 * is defined as `null` and likewise a further entry for each
 * [otherAssertionCreatorsOrNulls] (if given) whereas the entries have to appear in the specified order.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("api-cc-de_CH is discontinued, switch to api-fluent-en_GB; will be removed with 1.0.0")
@Suppress("DEPRECATION")
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.eintraege(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInOrderOnlyWithAssert(this, assertionCreatorOrNull glue otherAssertionCreatorsOrNulls))
