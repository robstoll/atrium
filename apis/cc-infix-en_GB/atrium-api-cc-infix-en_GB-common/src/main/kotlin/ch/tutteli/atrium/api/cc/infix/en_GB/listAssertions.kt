package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetNullableOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetOption
import ch.tutteli.atrium.creating.Assert

/**
 * Prepares the assertion about the return value of calling [get][List.get] with the given [index].
 *
 * @return A fluent builder to finish the assertion.
 */
infix fun <E : Any, T: List<E>> Assert<T>.get(index: Int): ListGetOption<E, T> = ListGetOption.create(this, index)

/**
 * Prepares the assertion about the nullable return value of calling [get][List.get] with the given [index].
 *
 * Notice, that the corresponding entry of the given [index] can be `null` even if the index is within bounds
 * as the [List] has a nullable entry type.
 *
 * @return A fluent builder to finish the assertion.
 */
infix fun <E, T: List<E>> Assert<T>.getNullable(index: Int): ListGetNullableOption<E, T> =
    ListGetNullableOption.create(this, index)
