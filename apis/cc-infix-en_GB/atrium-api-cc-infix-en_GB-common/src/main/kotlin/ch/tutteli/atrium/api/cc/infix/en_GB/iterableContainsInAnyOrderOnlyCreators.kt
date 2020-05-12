// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
@file:JvmMultifileClass
@file:JvmName("IterableContainsInAnyOrderOnlyCreatorsKt")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] must contain only the
 * [expected] value.
 *
 * Delegates to `the Values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.value(expected).asAssert()",
        "ch.tutteli.atrium.api.infix.en_GB.value",
        "ch.tutteli.atrium.domain.builders.migration.asAssert"
    )
)
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = this the Values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the expected [values]
 * must be contained in [Iterable] but it does not matter in which order.
 *
 * @param values The values which are expected to be contained within the [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.the(ch.tutteli.atrium.api.infix.en_GB.values(values.expected, *values.otherExpected)).asAssert()",
        "ch.tutteli.atrium.api.infix.en_GB.the",
        "ch.tutteli.atrium.api.infix.en_GB.values",
        "ch.tutteli.atrium.domain.builders.migration.asAssert"
    )
)
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.the(values: Values<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(this, values.toList()))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] must contain only one
 * entry which holds all assertions created by the given [assertionCreatorOrNull] or has to be `null` in case
 * [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `the Entries(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.entry(asSubExpect(assertionCreatorOrNull)).asAssert()",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.infix.en_GB.entry"
    )
)
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.entry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = this the Entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry needs to be contained in the
 * [Iterable] which holds all assertions [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull]
 * might create or it needs to be `null` in case
 * [entries].[assertionCreatorOrNull][Entries.assertionCreatorOrNull] is defined as `null` -- likewise an
 * entry for each [entries].[otherAssertionCreatorsOrNulls][Entries.otherAssertionCreatorsOrNulls] needs to
 * be contained in the [Iterable] where it does not matter in which order the entries appear but only as many entries
 * should be returned by the [Iterable] as assertion creators are defined.
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `Entries({ isGreaterThan(0) }, { toBe(1) })` but for `Entries({ toBe(1) }, { isGreaterThan(0) })`
 * otherwise `isGreaterThan(0)` matches `1` before `toBe(1)` would match it. As a consequence `toBe(1)` could
 * only match the entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param entries The parameter object containing the identification lambdas.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Suppress("DEPRECATION")
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.the(entries.mapArguments.to { asSubExpect(it) }.let { (first, rest) -> ch.tutteli.atrium.api.infix.en_GB.entries(first, *rest) }).asAssert()",
        "ch.tutteli.atrium.api.infix.en_GB.the",
        "ch.tutteli.atrium.domain.builders.migration.asAssert",
        "ch.tutteli.atrium.domain.builders.utils.mapArguments",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect"
    )
)
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.the(entries: Entries<E>): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderOnlyWithAssert(this, entries.toList()))

/**
 * Helper method to simplify adding assertions to the plant which itself is stored in
 * [CharSequenceContains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
internal fun <E, T : Iterable<E>, S : IterableContains.SearchBehaviour> IterableContains.Builder<E, T, S>.addAssertion(
    assertion: Assertion
): AssertionPlant<T> = addAssertionForAssert(assertion)
