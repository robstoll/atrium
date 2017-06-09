package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

/**
 * Formats an object by using its [toString] representation, its [Class.name] and its [System.identityHashCode].
 *
 * The aim of representing more information than just [toString] is to avoid situations where an assert may fail
 * and [toString] does not distinguish two compared objects.
 * Consider the following error message "error, assert: 1 to be 1" would not be very helpful.
 * "error, assert: 1 (Int <123>) to be 1 (Double <456>)" on the other hand is helpful.
 *
 * @property translator The [ITranslator] used to translate [TranslatableRawString]s.
 *
 * @constructor
 * @param translator The [ITranslator] used to translate [TranslatableRawString]s.
 */
internal class DetailedObjectFormatter(private val translator: ITranslator) : IObjectFormatter {

    /**
     * Returns a formatted version of the given [value].
     *
     * The following rules apply for the representation of an object:
     * - `null` is represented as [RawString.NULL].[RawString.string]
     * - [RawString] is represented as [RawString.string]
     * - [TranslatableRawString] is represented as result of its translation (by [translator])
     * - [String] is put in quotes and its [Class.name] is omitted
     * - [CharSequence] is put in quotes, but [Class.name] is used in contrast to [String]
     * - [Class] is represented as "[Class.getSimpleName] ([Class.name])"
     * - All other objects are represented as "[toString] ([Class.name] <[System.identityHashCode]>)"
     *
     * @param value The value which shall be formatted.
     *
     * @return The formatted [value].
     */
    override fun format(value: Any?): String = when (value) {
        null -> RawString.NULL.string
        is RawString -> value.string
        is TranslatableRawString -> translator.translate(value.translatable)
        is String -> format(value)
        is CharSequence -> format(value)
        is Class<*> -> format(value)
        else -> value.toString() + INDENT + classNameAndIdentity(value)
    }

    private fun format(string: String) = "\"$string\"" + INDENT + identityHash(string)
    private fun format(charSequence: CharSequence) = "\"$charSequence\"" + INDENT + classNameAndIdentity(charSequence)
    private fun <T> format(clazz: Class<T>) = "${clazz.simpleName} (${clazz.name})"

    private fun classNameAndIdentity(any: Any)
        = "(${any::class.java.name} ${identityHash(any)})"

    private fun identityHash(any: Any) = "<${System.identityHashCode(any)}>"

    companion object {
        internal const val INDENT: String = "        "
    }
}
