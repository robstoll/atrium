@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

@Deprecated("Use `contains` instead; will be removed with 1.0.0", ReplaceWith("contains(expectedOrNull)"))
fun <E: Any?, T: Iterable<E>> Assert<T>.containsNullableValue(expectedOrNull: E): AssertionPlant<T>
    = contains(expectedOrNull)

@Deprecated("Use `contains` instead; will be removed with 1.0.0", ReplaceWith("contains(expectedOrNull, *otherExpectedOrNulls)"))
fun <E: Any?, T: Iterable<E>> Assert<T>.containsNullableValues(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = contains(expectedOrNull, *otherExpectedOrNulls)

@Deprecated("Use `contains` instead; will be removed with 1.0.0", ReplaceWith("contains(assertionCreatorOrNull)"))
fun <E: Any, T: Iterable<E?>> Assert<T>.containsNullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains(assertionCreatorOrNull)

@Deprecated("Use `contains` instead; will be removed with 1.0.0", ReplaceWith("contains(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)"))
fun <E: Any, T: Iterable<E?>> Assert<T>.containsNullableEntries(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = contains(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)

@Deprecated(
   "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(expected, *otherExpected)", "ch.tutteli.atrium.api.cc.en_GB.containsExactly")
)
fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = containsExactly(expected, *otherExpected)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(expectedOrNull)", "ch.tutteli.atrium.api.cc.en_GB.containsExactly")
)
fun <E : Any?, T : Iterable<E>> Assert<T>.containsStrictlyNullableValue(expectedOrNull: E): AssertionPlant<T>
    = containsExactly(expectedOrNull)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(expectedOrNull, *otherExpectedOrNulls)", "ch.tutteli.atrium.api.cc.en_GB.containsExactly")
)
fun <E : Any?, T : Iterable<E>> Assert<T>.containsStrictlyNullableValues(expectedOrNull: E, vararg otherExpectedOrNulls: E): AssertionPlant<T>
    = containsExactly(expectedOrNull, *otherExpectedOrNulls)


@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(assertionCreator, *otherAssertionCreators)", "ch.tutteli.atrium.api.cc.en_GB.containsExactly")
)
fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = containsExactly(assertionCreator, *otherAssertionCreators)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(assertionCreatorOrNull)", "ch.tutteli.atrium.api.cc.en_GB.containsExactly")
)
fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictlyNullableEntry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = containsExactly(assertionCreatorOrNull)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)", "ch.tutteli.atrium.api.cc.en_GB.containsExactly")
)
fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictlyNullableEntries(assertionCreatorOrNull: (Assert<E>.() -> Unit)?, vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = containsExactly(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)


@Deprecated("Use `any` instead; will be removed with 1.0.0", ReplaceWith("any(assertionCreatorOrNull)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.anyOfNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = any(assertionCreatorOrNull)

@Deprecated("Use `none` instead; will be removed with 1.0.0", ReplaceWith("none(assertionCreatorOrNull)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.noneOfNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = none(assertionCreatorOrNull)

@Deprecated("Use `all` instead; will be removed with 1.0.0", ReplaceWith("all(assertionCreatorOrNull)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.allOfNullable(assertionCreatorOrNull: (Assert<E>.() -> Unit)?)
    = all(assertionCreatorOrNull)
