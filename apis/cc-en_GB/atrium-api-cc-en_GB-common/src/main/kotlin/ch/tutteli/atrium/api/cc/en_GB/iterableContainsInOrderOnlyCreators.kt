@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyCreatorsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.kbox.glue
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.value(expected)",
        "ch.tutteli.atrium.api.fluent.en_GB.value"
    )
)
fun <E , T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value as well as the [otherExpected] values
 * (if given) in the specified order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.values(expected, *otherExpected)",
        "ch.tutteli.atrium.api.fluent.en_GB.values"
    )
)
fun <E , T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.values(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInOrderOnly(this, expected glue otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only a
 * single entry which holds all assertions created by the given [assertionCreatorOrNull] or needs to be `null`
 * in case [assertionCreatorOrNull] is defined as `null`.
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
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.entry(asSubExpect(assertionCreatorOrNull))",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.entry"
    )
)
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.entry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only an
 * entry which holds all assertions [assertionCreatorOrNull] might create or is `null` in case [assertionCreatorOrNull]
 * is defined as `null` and likewise a further entry for each
 * [otherAssertionCreatorsOrNulls] (if given) whereas the entries have to appear in the specified order.
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
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
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
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.entries(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInOrderOnlyWithAssert(this, assertionCreatorOrNull glue otherAssertionCreatorsOrNulls))
