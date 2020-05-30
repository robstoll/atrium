// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
@file:JvmMultifileClass
@file:JvmName("ParameterObjectsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.kbox.glue

@Deprecated("Use Value instead, it was opened up to Any? in 0.8.0; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.", ReplaceWith("Value(expected)"))
data class NullableValue<T : Any?>(val expected: T) : GroupWithNullableEntries<T> {
    override fun toList() = listOf(expected)
}

@Deprecated("Use Values instead, it was opened up to Any? in 0.8.0; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.", ReplaceWith("Values(expected, *otherExpected)"))
class NullableValues<T : Any?>(private val expected: T, vararg val otherExpected: T) : GroupWithNullableEntries<T>{
    override fun toList() = listOf(expected, *otherExpected)
}

@Deprecated("Use Entry instead, it was opened up to `(Assert<V>.() -> Unit)?` in 0.8.0; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.", ReplaceWith("Entry(assertionCreator)"))
class NullableEntry<T : Any>(
    val assertionCreator: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = listOf(assertionCreator)
}

@Deprecated("Use Entries instead, it was opened up to Any? in 0.8.0; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.", ReplaceWith("Entries(assertionCreatorOrNull, *otherAssertionCreatorsOrNulls)"))
class NullableEntries<T : Any>(
    val assertionCreatorOrNull: (Assert<T>.() -> Unit)?,
    vararg val otherAssertionCreatorsOrNulls: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = assertionCreatorOrNull glue otherAssertionCreatorsOrNulls
}

@Deprecated("Use KeyValue instead, it was opened up to `(Assert<V>.() -> Unit)?` in 0.8.0; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.", ReplaceWith("KeyValue(key, *valueAssertionCreatorOrNull)"))
data class KeyNullableValue<out K, V : Any>(val key: K, val valueAssertionCreatorOrNull: (Assert<V>.() -> Unit)?) {
    fun toPair(): Pair<K, (Assert<V>.() -> Unit)?> = key to valueAssertionCreatorOrNull
    override fun toString(): String
        = "KeyNullableValue(key=$key, value=${if (valueAssertionCreatorOrNull == null) "null" else "lambda"})"
}
