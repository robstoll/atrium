package ch.tutteli.atrium.api.cc.en_UK

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
 * Delegates to `values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.value(expected)"))
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = values(expected)

@JvmName("deprecatedValue")
@Deprecated("Use `nullableValue` instead, will be removed with 1.0.0", ReplaceWith("nullableValue(expected)"))
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = nullableValue(expected)

@Deprecated("Use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.value(expected)"))
fun <E, T : Iterable<E>> value(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = values(checkerBuilder, expected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values need to be contained in [Iterable] where it does not matter in which order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.values(expected, *otherExpected)"))
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.values(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(this, expected glue otherExpected))

@Deprecated("Use `nullableValues` instead, will be removed with 1.0.0", ReplaceWith("nullableValues(expected, *otherExpected)"))
@JvmName("deprecatedValues")
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.values(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = nullableValues(expected, *otherExpected)

@Deprecated("Use the extension fun `values` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.values(expected, *otherExpected)"))
fun <E, T : Iterable<E>> values(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.values(expected, *otherExpected)


@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `value(expected)` without adding enough to be a legit alternative.", ReplaceWith("value(expected)"))
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.`object`(expected: E): AssertionPlant<T>
    = values(expected)

@Deprecated("Use the extension fun `object` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.value(expected)"))
fun <E, T : Iterable<E>> `object`(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = values(checkerBuilder, expected)

@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `values(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("values(expected, *otherExpected)"))
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.objects(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = values(expected, *otherExpected)

@Deprecated("Use the extension fun `values` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.values(expected, *otherExpected)"))
fun <E, T : Iterable<E>> objects(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.values(expected, *otherExpected)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator].
 *
 * Delegates to `entries(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.entry(assertionCreator)"))
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.entry(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = entries(assertionCreator)

@Deprecated("Use the extension fun `entry` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.entry(assertionCreator)"))
fun <E : Any, T : Iterable<E>> entry(checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>, assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = entries(checkerBuilder, assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [assertionCreator] might create -- equally an entry for each further
 * [otherAssertionCreators] needs to be contained in the [Iterable]  where it does not matter in which order the
 * entries appear.
 *
 * Notice, that a first-wins strategy applies which means your [assertionCreator] functions -- which kind of serve as
 * identification functions -- should be ordered in such a way that the most specific identification function appears
 * first, not that a less specific function wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toBe(1) })` but for `entries({ toBe(1) }, { isGreaterThan(0) })` otherwise
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
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.entries(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.entries(
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderOnly(this, assertionCreator glue otherAssertionCreators))

@Deprecated("Use the extension fun `entries` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.entries(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> entries(
    checkerBuilder: IterableContainsBuilder<E, T, InAnyOrderOnlySearchBehaviour>,
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = checkerBuilder.entries(assertionCreator, *otherAssertionCreators)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `nullableValues(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.nullableValue(expected)"))
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.nullableValue(expected: E): AssertionPlant<T>
    = nullableValues(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected] value as well as the
 * [otherExpected] values need to be contained in [Iterable] where it does not matter in which order.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.nullableValues(expected, *otherExpected)"))
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.nullableValues(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(this, expected glue otherExpected))


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreator].
 *
 * Delegates to `entries(assertionCreator)`.
 *
 * @param assertionCreator The identification lambda.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.entry(assertionCreator)"))
@JvmName("entry?")
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.entry(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = entries(assertionCreator)

@Deprecated("Use the extension fun `entry` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.entry(assertionCreator)"))
fun <E : Any, T : Iterable<E?>> nullableEntry(checkerBuilder: IterableContainsBuilder<E?, T, InAnyOrderOnlySearchBehaviour>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEntries(checkerBuilder, assertionCreator)


/**
 * Finishes the specification of the sophisticated `contains` assertion where the entry needs to be contained in the
 * [Iterable] which holds all assertions [assertionCreator] might create -- equally an entry for each further
 * [otherAssertionCreators] needs to be contained in the [Iterable]  where it does not matter in which order the
 * entries appear.
 *
 * Notice, that a first-wins strategy applies which means your [assertionCreator] functions -- which kind of serve as
 * identification functions -- should be ordered in such a way that the most specific identification function appears
 * first, not that a less specific function wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toBe(1) })` but for `entries({ toBe(1) }, { isGreaterThan(0) })` otherwise
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
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.en_GB.entries(assertionCreator, *otherAssertionCreators)"))
@JvmName("entries?")
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.entries(
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = plant.addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderOnly(this, assertionCreator glue otherAssertionCreators))

@Deprecated("Use the extension fun `entries` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("checkerBuilder.entries(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> nullableEntries(
    checkerBuilder: IterableContainsBuilder<E?, T, InAnyOrderOnlySearchBehaviour>,
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = checkerBuilder.entries(assertionCreator, *otherAssertionCreators)
