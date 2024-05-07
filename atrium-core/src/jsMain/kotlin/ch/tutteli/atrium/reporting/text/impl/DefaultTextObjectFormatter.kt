//TODO 1.3.0 remove once we use StringFormatter
@file:Suppress("DEPRECATION")

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

actual class DefaultTextObjectFormatter actual constructor() : TextObjectFormatterCommon(), TextObjectFormatter {
    //TODO  1.4.0 remove
    @Suppress("DEPRECATION", "UNUSED_PARAMETER")
    @Deprecated(
        "use the overload which does not expect a Translator, it is simply ignored",
        ReplaceWith("this()")
    )
    actual constructor(translator: ch.tutteli.atrium.reporting.translating.Translator) : this()

    override fun format(kClass: KClass<*>): String {
        return "${kClass.simpleName} (${kClass.js.name})"
    }

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
