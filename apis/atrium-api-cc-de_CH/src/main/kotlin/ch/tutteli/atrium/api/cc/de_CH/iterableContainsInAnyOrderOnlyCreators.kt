package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.kbox.glue
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `werte(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.wert(expected: E): AssertionPlant<T>
    = werte(expected)

@Deprecated("Use the extension fun `wert` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.wert(expected)"))
fun <E, T : Iterable<E>> wert(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = werte(checkerBuilder, expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected][expectedOrNull] nullable value.
 *
 * Delegates to `nullableWerte(expectedOrNull)`.
 *
 * @param expectedOrNull The nullable value which is expectedOrNull to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.nullableWert(expectedOrNull: E): AssertionPlant<T>
    = nullableWerte(expectedOrNull)

@JvmName("deprecatedWert")
@Deprecated("Use `nullableWert` instead; will be removed with 1.0.0", ReplaceWith("nullableWert(expected)"))
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.wert(expected: E): AssertionPlant<T>
    = nullableWert(expected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values need to be contained in [Iterable] where it does not matter in which order but only as
 * many entries should be returned by the [Iterable] as values defined.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.werte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(this, expected glue otherExpected))


@Deprecated("Use the extension fun `werte` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> werte(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.werte(expected, *otherExpected)

@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `wert(expected)` without adding enough to be a legit alternative.", ReplaceWith("wert(expected)"))
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.objekt(expected: E): AssertionPlant<T>
    = werte(expected)

@Deprecated("Use the extension fun `wert` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.wert(expected)"))
fun <E, T : Iterable<E>> objekt(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = werte(checkerBuilder, expected)

@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `werte(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.objekte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = werte(expected, *otherExpected)

@Deprecated("Use the extension fun `werte` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> objekte(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.werte(expected, *otherExpected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected][expectedOrNull]
 * nullable value as well as the [other expected][otherExpectedOrNulls] nullable values (if given) need to be
 * contained in [Iterable] where it does not matter in which order but only as
 * many entries should be returned by the [Iterable] as values defined.
 *
 * @param expectedOrNull The nullable value which is expectedOrNull to be contained within the [Iterable].
 * @param otherExpectedOrNulls Additional nullable values which are expectedOrNull to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.nullableWerte(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(this, expectedOrNull glue otherExpectedOrNulls))

@Deprecated("Use `nullableWerte` instead; will be removed with 1.0.0", ReplaceWith("nullableWerte(expected, *otherExpected)"))
@JvmName("deprecatedWerte")
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.werte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = nullableWerte(expected, *otherExpected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator].
 *
 * Delegates to `eintraege(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.eintrag(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = eintraege(assertionCreator)

@Deprecated("Use the extension fun `eintrag` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E>> eintrag(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = eintraege(checkerBuilder, assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreatorOrNull] or is `null` in case
 * [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `nullableEintraege(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.nullableEintrag(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintraege(assertionCreatorOrNull)

@JvmName("deprecatedEintrag")
@Deprecated("Use `nullableEintrag` instead; will be removed with 1.0.0", ReplaceWith("nullableEintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.eintrag(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintrag(assertionCreator)


@Deprecated("Use the extension fun `nullableEintrag` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.nullableEintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E?>> nullableEintrag(checkerBuilder: IterableContainsBuilder<E?, T, InAnyOrderOnlySearchBehaviour>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintraege(checkerBuilder, assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [assertionCreator] might create -- likewise an entry for each
 * [otherAssertionCreators] needs to be contained in the [Iterable] where it does not matter in which order the
 * entries appear but only as many entries should be returned by the [Iterable] as assertion creators are defined.
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `eintraege({ isGreaterThan(0) }, { toBe(1) })` but for `eintraege({ toBe(1) }, { isGreaterThan(0) })` otherwise
 * `isGreaterThan(0)` matches `1` before `toBe(1)` would match it. As a consequence `toBe(1)` could only match the
 * entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param assertionCreator The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 * @param otherAssertionCreators Additional identification lambdas which each kind of identify (separately) an entry
 *   which we are looking for.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.eintraege(
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderOnly(this, assertionCreator glue otherAssertionCreators))

@Deprecated("Use the extension fun `eintraege` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> eintraege(
    checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>,
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry needs to be contained in the
 * [Iterable] which holds all assertions [assertionCreatorOrNull] might create or needs to be `null` in case
 * [assertionCreatorOrNull] is defined as `null` -- likewise an entry for each
 * [otherAssertionCreatorsOrNulls] needs to be contained in the [Iterable] where it does not matter in which order the
 * entries appear but only as many entries should be returned by the [Iterable] as assertion creators are defined.
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `nullableEintraege({ isGreaterThan(0) }, { toBe(1) })` but for
 * `nullableEintraege({ toBe(1) }, { isGreaterThan(0) })` otherwise
 * `isGreaterThan(0)` matches `1` before `toBe(1)` would match it. As a consequence `toBe(1)` could only match the
 * entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each kind of identify (separately)
 *   an entry which we are looking for.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.nullableEintraege(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderOnly(this, assertionCreatorOrNull glue otherAssertionCreatorsOrNulls))

@JvmName("deprecatedEintraege")
@Deprecated("Use `nullableEintraege` instead; will be removed with 1.0.0", ReplaceWith("nullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.eintraege(
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = nullableEintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use the extension fun `nullableEintraege` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.nullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> nullableEintraege(
    checkerBuilder: IterableContainsBuilder<E?, T, InAnyOrderOnlySearchBehaviour>,
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = checkerBuilder.nullableEintraege(assertionCreator, *otherAssertionCreators)
