@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
@file:JvmMultifileClass
@file:JvmName("ParameterObjectsKt")

package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.kbox.glue

@Deprecated("Use Wert instead, it was opened up to Any? in 0.8.0; will be removed with 1.0.0", ReplaceWith("Wert(expected)"))
data class NullableWert<T : Any?>(val expected: T) : GroupWithNullableEntries<T> {
    override fun toList() = listOf(expected)
}

@Deprecated("Use Werte instead, it was opened up to Any? in 0.8.0; will be removed with 1.0.0", ReplaceWith("Werte(expected, *otherExpected)"))
class NullableWerte<T : Any?>(private val expected: T, vararg val otherExpected: T) : GroupWithNullableEntries<T>{
    override fun toList() = listOf(expected, *otherExpected)
}

@Deprecated("Use Eintrag instead, it was opened up to `(Assert<V>.() -> Unit)?` in 0.8.0; will be removed with 1.0.0", ReplaceWith("Eintrag(assertionCreator)"))
class NullableEintrag<T : Any>(
    val assertionCreator: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = listOf(assertionCreator)
}

@Deprecated("Use Eintraege instead, it was opened up to Any? in 0.8.0; will be removed with 1.0.0", ReplaceWith("Eintraege(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)"))
class NullableEintraege<T : Any>(
    val assertionCreatorOrNull: (Assert<T>.() -> Unit)?,
    vararg val otherAssertionCreatorsOrNulls: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}

@Deprecated("Use KeyValue instead, it was opened up to `(Assert<V>.() -> Unit)?` in 0.8.0; will be removed with 1.0.0", ReplaceWith("KeyValue(key, *valueAssertionCreatorOrNull)"))
data class KeyNullableValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Assert<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Assert<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull
    override fun toString(): String
        = "KeyNullableValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}
