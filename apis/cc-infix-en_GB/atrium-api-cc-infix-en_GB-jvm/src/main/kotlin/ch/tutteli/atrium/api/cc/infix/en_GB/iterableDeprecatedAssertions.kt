@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)
@file:JvmMultifileClass
@file:JvmName("IterableAssertionsKt")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant

@Deprecated("Use `contains` instead; will be removed with 0.10.0", ReplaceWith("this contains nullableValue.expected"))
infix fun <E : Any?, T : Iterable<E>> Assert<T>.contains(nullableValue: NullableValue<E>)
    = this contains nullableValue.expected

@Deprecated("Use `contains` instead; will be removed with 0.10.0", ReplaceWith("this contains Values(nullableValues.expected, *nullableValues.otherExpected)"))
infix fun <E : Any?, T : Iterable<E>> Assert<T>.contains(nullableValues: NullableValues<E>): AssertionPlant<T>
    = this contains Values(nullableValues.expected, *nullableValues.otherExpected)

@Deprecated("Use `contains` instead; will be removed with 0.10.0", ReplaceWith("this contains nullableEntry.assertionCreator"))
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(nullableEntry: NullableEntry<E>): AssertionPlant<T>
    = this contains nullableEntry.assertionCreator

@Deprecated("Use `contains` instead; will be removed with 0.10.0", ReplaceWith("this contains Entries(nullableEntries.assertionCreatorOrNull, *nullableEntries.otherAssertionCreatorsOrNulls)"))
infix fun <E : Any, T : Iterable<E?>> Assert<T>.contains(nullableEntries: NullableEntries<E>): AssertionPlant<T>
    = this contains Entries(nullableEntries.assertionCreatorOrNull, *nullableEntries.otherAssertionCreatorsOrNulls)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 0.10.0",
    ReplaceWith("this containsExactly expected", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(expected: E): AssertionPlant<T>
    = this containsExactly expected

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 0.10.0",
    ReplaceWith("this containsExactly expected.expected", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(expected: NullableValue<E>): AssertionPlant<T>
    = this containsExactly expected.expected

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 0.10.0",
    ReplaceWith("this containsExactly values", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(values: Values<E>): AssertionPlant<T>
    = this containsExactly values

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 0.10.0",
    ReplaceWith("this containsExactly Values(nullableValues.expected, *nullableValues.otherExpected)", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E, T : Iterable<E>> Assert<T>.containsStrictly(nullableValues: NullableValues<E>): AssertionPlant<T>
    = this containsExactly Values(nullableValues.expected, *nullableValues.otherExpected)

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 0.10.0",
    ReplaceWith(" this containsExactly assertionCreator", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(assertionCreator: Assert<E>.() -> Unit): AssertionPlant<T>
    = this containsExactly assertionCreator

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 0.10.0",
    ReplaceWith("this containsExactly nullableEntry.assertionCreator", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(nullableEntry: NullableEntry<E>): AssertionPlant<T>
    = this containsExactly nullableEntry.assertionCreator

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 0.10.0",
    ReplaceWith("this containsExactly entries", "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly")
)
infix fun <E : Any, T : Iterable<E>> Assert<T>.containsStrictly(entries: Entries<E>): AssertionPlant<T>
    = this containsExactly entries

@Deprecated(
    "Replaced with containsExactly for clearer naming; will be removed with 0.10.0",
    ReplaceWith(
        "this containsExactly Entries(nullableEntries.assertionCreatorOrNull, *nullableEntries.otherAssertionCreatorsOrNulls)",
        "ch.tutteli.atrium.api.cc.infix.en_GB.containsExactly"
    )
)
infix fun <E : Any, T : Iterable<E?>> Assert<T>.containsStrictly(nullableEntries: NullableEntries<E>): AssertionPlant<T>
    = this containsExactly Entries(nullableEntries.assertionCreatorOrNull, *nullableEntries.otherAssertionCreatorsOrNulls)

@Deprecated("Use `any` instead; will be removed with 0.10.0", ReplaceWith("this any nullableEntry.assertionCreator"))
infix fun <E : Any, T : Iterable<E?>> Assert<T>.any(nullableEntry: NullableEntry<E>): AssertionPlant<T>
    = this any nullableEntry.assertionCreator

@Deprecated("Use `none` instead; will be removed with 0.10.0", ReplaceWith("this none nullableEntry.assertionCreator"))
infix fun <E : Any, T : Iterable<E?>> Assert<T>.none(nullableEntry: NullableEntry<E>)
    = this none nullableEntry.assertionCreator

@Deprecated("Use `all` instead; will be removed with 0.10.0", ReplaceWith("this all nullableEntry.assertionCreator"))
infix fun <E : Any, T : Iterable<E?>> Assert<T>.all(nullableEntry: NullableEntry<E>)
    = this all nullableEntry.assertionCreator

