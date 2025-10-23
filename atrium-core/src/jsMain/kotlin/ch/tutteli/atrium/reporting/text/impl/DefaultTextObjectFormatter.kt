package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.reporting.text.TextObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import kotlin.reflect.KClass

external class WeakMap {
    fun set(k: dynamic, v: String)
    fun get(k: dynamic): String?
}

private val pseudoIdentityHash = WeakMap()
private val primitiveIdentityHash = hashMapOf<Any, String?>()
private var count = 0

actual class DefaultTextObjectFormatter actual constructor(
    translator: Translator
) : TextObjectFormatterCommon(translator), TextObjectFormatter {

    actual override fun format(value: Any?): String =
        when (value) {
            is Float -> format(value)
            is Double -> format(value)
            else -> super.format(value)
        }

    override fun format(kClass: KClass<*>): String {
        return "${kClass.simpleName} (${kClass.js.name})"
    }

    private fun format(float: Float): String =
        (if (float % 1.0f == 1.0f) "$float.0" else float.toString()) + classNameAndIdentity(float)

    private fun format(double: Double): String =
        (if (double % 1.0 == 1.0) "$double.0" else double.toString()) + classNameAndIdentity(double)

    override fun identityHash(indent: String, any: Any): String {
        val current = getHash(any)
        val hash = if (current != null) {
            current
        } else {
            val newHash = (++count).toString()
            setHash(any, newHash)
            newHash
        }
        return "$indent<$hash>"
    }

    private fun getHash(any: Any): String? =
        if (jsTypeOf(any) == "object") pseudoIdentityHash.get(any)
        else primitiveIdentityHash[any]

    private fun setHash(any: Any, newHash: String) {
        if (jsTypeOf(any) == "object") pseudoIdentityHash.set(any, newHash)
        else primitiveIdentityHash[any] = newHash
    }
}
