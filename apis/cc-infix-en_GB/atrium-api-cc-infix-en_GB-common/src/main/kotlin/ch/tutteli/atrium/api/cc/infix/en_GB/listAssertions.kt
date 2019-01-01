package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetNullableOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.list.get.builders.ListGetOption
import ch.tutteli.atrium.creating.Assert

/**
 * Prepares the assertion about the return value of calling [get][List.get] with the given [index].
 *
 * @return A fluent builder to finish the assertion.
 */
infix fun <T : Any> Assert<List<T>>.get(index: Int): ListGetOption<T> = ListGetOption.create(this, index)

/**
 * Prepares the assertion about the return value of calling [get][List.get] with the given [index].
 *
 * Notice, that the corresponding entry of the given [index] can be `null` even if the index is within bounds
 * as the [List] has a nullable entry type.
 *
 * @return A fluent builder to finish the assertion.
 */
infix fun <T> Assert<List<T>>.getNullable(index: Int): ListGetNullableOption<T> =
    ListGetNullableOption.create(this, index)
