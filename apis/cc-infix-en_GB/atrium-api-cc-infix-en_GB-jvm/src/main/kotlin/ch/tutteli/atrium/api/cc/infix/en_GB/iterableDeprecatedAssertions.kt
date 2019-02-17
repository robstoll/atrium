@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

@Deprecated("Use `contains` instead; will be removed with 1.0.0", ReplaceWith("contains(nullableValue.expected)"))
infix fun <E : Any?, T : Iterable<E>> Assert<T>.contains(nullableValue: NullableValue<E>)
    = contains(nullableValue.expected)

@Deprecated("Use `contains` instead; will be removed with 1.0.0", ReplaceWith("contains(nullableEntry.assertionCreator)"))
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(nullableEntry: NullableEntry<E>): AssertionPlant<T>
    = contains(nullableEntry.assertionCreator)


@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(expected)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(expected: E): AssertionPlant<T>
    = containsExactly(expected)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(expected.expected)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(expected: NullableValue<E>): AssertionPlant<T>
    = containsExactly(expected.expected)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(values)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(values: Values<E>): AssertionPlant<T>
    = containsExactly(values)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(nullableValues)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(nullableValues: NullableValues<E>): AssertionPlant<T>
    = containsExactly(nullableValues)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(assertionCreator)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = containsExactly(assertionCreator)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(nullableEntry.assertionCreator)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(nullableEntry: NullableEntry<E>): AssertionPlant<T>
    = containsExactly(nullableEntry.assertionCreator)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(entries)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(entries: Entries<E>): AssertionPlant<T>
    = containsExactly(entries)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 1.0.0",
    ReplaceWith("containsExactly(nullableEntries)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(nullableEntries: NullableEntries<E>): AssertionPlant<T>
    = containsExactly(nullableEntries)

@Deprecated("Use `any` instead; will be removed with 1.0.0", ReplaceWith("any(nullableEntry.assertionCreator)"))
infix fun <E : Any, T : Iterable<E?>> Assert<T>.any(nullableEntry: NullableEntry<E>): AssertionPlant<T>
    = any(nullableEntry.assertionCreator)

@Deprecated("Use `none` instead; will be removed with 1.0.0", ReplaceWith("none(nullableEntry.assertionCreator)"))
infix fun <E : Any, T : Iterable<E?>> Assert<T>.none(nullableEntry: NullableEntry<E>)
    = none(nullableEntry.assertionCreator)

@Deprecated("Use `all` instead; will be removed with 1.0.0", ReplaceWith("all(nullableEntry.assertionCreator)"))
infix fun <E : Any, T : Iterable<E?>> Assert<T>.all(nullableEntry: NullableEntry<E>)
    = all(nullableEntry.assertionCreator)
