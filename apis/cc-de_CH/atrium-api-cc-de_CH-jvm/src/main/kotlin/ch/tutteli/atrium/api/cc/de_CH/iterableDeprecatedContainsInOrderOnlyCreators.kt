@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableContainsInOrderOnlyCreatorsKt")
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour

@Deprecated("Use the extension fun `wert` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.wert(expected)"))
fun <E, T : Iterable<E>> wert(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = werte(checkerBuilder, expected)

@Deprecated("Use the extension fun `werte` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> werte(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.werte(expected, *otherExpected)

@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `wert(expected)` without adding enough to be a legit alternative.", ReplaceWith("wert(expected)"))
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.objekt(expected: E): AssertionPlant<T>
    = werte(expected)

@Deprecated("Use the extension fun `wert` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.wert(expected)"))
fun <E, T : Iterable<E>> objekt(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E): AssertionPlant<T>
    = werte(checkerBuilder, expected)

@Deprecated("Will be removed with 1.0.0 because it is redundant in terms of `werte(expected, otherExpected)` without adding enough to be a legit alternative.", ReplaceWith("werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.objekte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = werte(expected, *otherExpected)

@Deprecated("Use the extension fun `werte` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.werte(expected, *otherExpected)"))
fun <E, T : Iterable<E>> objekte(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, expected: E, vararg otherExpected: E): AssertionPlant<T>
    = checkerBuilder.werte(expected, *otherExpected)

@Deprecated("Use the extension fun `eintrag` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E>> eintrag(checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>, assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = eintraege(checkerBuilder, assertionCreator)

@Deprecated("Use the extension fun `eintrag` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintrag(assertionCreator)"))
fun <E : Any, T : Iterable<E?>> nullableEintrag(checkerBuilder: IterableContainsBuilder<E?, T, InOrderOnlySearchBehaviour>, assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = nullableEintraege(checkerBuilder, assertionCreator)

@Deprecated("Use the extension fun `eintraege` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E>> eintraege(
    checkerBuilder: IterableContainsBuilder<E, T, InOrderOnlySearchBehaviour>,
    assertionCreator: Assert<E>.() -> Unit,
    vararg otherAssertionCreators: Assert<E>.() -> Unit
): AssertionPlant<T>
    = checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use the extension fun `eintraege` instead. This fun is only here to retain binary compatibility; will be removed with 1.0.0", ReplaceWith("checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> nullableEintraege(
    checkerBuilder: IterableContainsBuilder<E?, T, InOrderOnlySearchBehaviour>,
    assertionCreator: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreators: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = checkerBuilder.eintraege(assertionCreator, *otherAssertionCreators)


@Deprecated("Use `wert` instead; will be removed with 1.0.0", ReplaceWith("wert(expectedOrNull)"))
infix fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.nullableWert(expectedOrNull: E): AssertionPlant<T>
    = wert(expectedOrNull)

@Deprecated("Use `eintrag` instead; will be removed with 1.0.0", ReplaceWith("eintrag(assertionCreatorOrNull)"))
infix fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.nullableEintrag(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = eintrag(assertionCreatorOrNull)

@Deprecated("Use `werte` instead; will be removed with 1.0.0", ReplaceWith("werte(expectedOrNull, *otherExpectedOrNulls)"))
fun <E : Any?, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.nullableWerte(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = werte(expectedOrNull, *otherExpectedOrNulls)

@Deprecated("Use `eintraege` instead; will be removed with 1.0.0", ReplaceWith("eintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)"))
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>.nullableEintraege(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T> = eintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)
