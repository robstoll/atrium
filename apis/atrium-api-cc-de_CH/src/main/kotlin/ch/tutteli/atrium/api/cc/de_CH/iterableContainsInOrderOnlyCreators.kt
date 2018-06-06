package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.kbox.glue
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegate to `werte(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.wert(expected: E): AssertionPlant<T>
    = werte(expected)

@Deprecated("Use the extension fun `wert` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.wert(expected)"))
fun <E, T : Iterable<E>> wert(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = werte(checkerBuilder, expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] nullable value.
 *
 * Delegate to `nullableWerte(expected)`.
 *
 * @param expected The nullable value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.nullableWert(expected: E): AssertionPlant<T>
    = nullableWerte(expected)

@JvmName("deprecatedWert")
@Deprecated("Use `nullableWert` instead, will be removed with 1.0.0", ReplaceWith("nullableWert(expected)"))
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.wert(expected: E): AssertionPlant<T>
    = werte(expected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values (if defined) need to be contained in [Iterable] in the specified order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.werte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInOrderOnly(this, expected glue otherExpected))

@Deprecated("Use the extension fun `werte` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> werte(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.werte(expected, *otherExpected)


@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `wert(expected)` without adding enough to be a legit alternative.", ReplaceWith("wert(expected)"))
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.objekt(expected: E): AssertionPlant<T>
    = werte(expected)

@Deprecated("Use the extension fun `wert` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.wert(expected)"))
fun <E, T : Iterable<E>> objekt(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = werte(checkerBuilder, expected)

@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `werte(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.objekte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = werte(expected, *otherExpected)

@Deprecated("Use the extension fun `werte` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> objekte(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.werte(expected, *otherExpected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] nullable value
 * as well as the [otherExpected] nullable values (if defined) need to be contained in [Iterable] in the specified order.
 *
 * @param expected The nullable value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional nullable values which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.nullableWerte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInOrderOnly(this, expected glue otherExpected))

@Deprecated("Use `nullableWerte` instead, will be removed with 1.0.0", ReplaceWith("nullableWerte(expected, *otherExpected)"))
@JvmName("deprecatedWerte")
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.werte(expected: E, vararg otherExpected: E): AssertionPlant<T>
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
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.eintrag(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = eintraege(assertionCreator)

@Deprecated("Use the extension fun `eintrag` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E>> eintrag(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = eintraege(checkerBuilder, assertionCreator)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator] or needs to be `null` in case
 * [assertionCreator] is `null` as well.
 *
 * Delegates to `nullableEintraege(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.nullableEintrag(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintraege(assertionCreator)

@JvmName("deprecatedEintrag")
@Deprecated("Use `nullableEintrag` instead, will be removed with 1.0.0", ReplaceWith("nullableEintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.eintrag(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintrag(assertionCreator)

@Deprecated("Use the extension fun `nullableEintrag` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.nullableEintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E?>> nullableEintrag(checkerBuilder: IterableContainsBuilder<E?, T, InOrderOnlySearchBehaviour>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintraege(checkerBuilder, assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [assertionCreator] might create -- likewise an entry for each further
 * [otherAssertionCreators], following the specified order, needs to be contained in the [Iterable]
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
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.eintraege(
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInOrderOnly(this, assertionCreator glue otherAssertionCreators))

@Deprecated("Use the extension fun `eintraege` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> eintraege(
    checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>,
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [assertionCreator] might create or needs to be `null` in case
 * [assertionCreator] is `null` as well -- likewise an entry for each further
 * [otherAssertionCreators], following the specified order, needs to be contained in the [Iterable].
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
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.nullableEintraege(
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInOrderOnly(this, assertionCreator glue otherAssertionCreators))

@JvmName("deprecatedEintraege")
@Deprecated("Use `nullableEintraege` instead, will be removed with 1.0.0", ReplaceWith("nullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.eintraege(
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = nullableEintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use the extension fun `nullableEintraege` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.nullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> nullableEintraege(
    checkerBuilder: IterableContainsBuilder<E?, T, InOrderOnlySearchBehaviour>,
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = checkerBuilder.nullableEintraege(assertionCreator, *otherAssertionCreators)

