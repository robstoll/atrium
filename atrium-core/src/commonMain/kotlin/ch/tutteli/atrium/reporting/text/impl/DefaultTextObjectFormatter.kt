package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.text.TextObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Translator
import kotlin.reflect.KClass

expect class DefaultTextObjectFormatter(translator: Translator) : TextObjectFormatter{
    override fun format(value: Any?): String
}

/**
 * Base class for the platform specific implementation of [DefaultTextObjectFormatter].
 *
 * It cannot format Java's `Class`, this has to be done in the JVM module. Moreover it requires a platform specific
 * implementation of [identityHash] and [format] a [KClass].
 */
abstract class TextObjectFormatterCommon(
    private val translator: Translator
) : TextObjectFormatter {

    /**
     * Returns a formatted version of the given [value].
     *
     * The following rules apply for the representation of an object:
     * - `null` is represented as [Text.NULL].[Text.string]
     * - [LazyRepresentation] is [evaluated][LazyRepresentation.eval] and then again [format]ted
     * - [Char] is put in apostrophes
     * - [Boolean] is represented with its [toString] representation
     * - [String] is put in quotes and its [KClass.fullName] is omitted
     * - [CharSequence] is put in quotes, but [KClass.fullName] is used in contrast to [String]
     * - [Text] is represented as [Text.string]
     * - [Translatable] is represented as result of its translation (by [translator])
     * - [KClass]'s format is defined by the concrete platform specific subclass.
     * - [Enum] is represented as "[toString] ([KClass.fullName])
     * - [Throwable] is represented as "[KClass.fullName]"
     * - All other objects are represented as "[toString] ([KClass.fullName] [identityHash])"
     *
     * @param value The value which shall be formatted.
     *
     * @return The formatted [value].
     */
    override fun format(value: Any?): String = when (value) {
        null -> Text.NULL.string
        is LazyRepresentation -> format(value.eval())
        is Char -> "'$value'"
        is Boolean -> value.toString()
        is String -> format(value)
        is CharSequence -> format(value)
        is Text -> limitRepresentation(value.string)
        is Translatable -> limitRepresentation(translator.translate(value))
        is KClass<*> -> format(value)
        is Enum<*> -> format(value)
        is Throwable -> format(value)
        else -> defaultFormat(value)
    }

    protected fun defaultFormat(value: Any): String = limitRepresentation(value.toString()) + classNameAndIdentity(value)

    private fun format(string: String) = "\"${limitRepresentation(string)}\"" + identityHash(INDENT, string)
    private fun format(charSequence: CharSequence) =
        "\"${limitRepresentation(charSequence.toString())}\"" + classNameAndIdentity(charSequence)

    private fun format(enum: Enum<*>) =
        limitRepresentation(enum.toString()) + INDENT + "(" + (enum::class.fullName) + ")"

    private fun format(throwable: Throwable) = throwable::class.fullName

    protected fun classNameAndIdentity(any: Any): String = INDENT + "(${any::class.fullName}${identityHash(" ", any)})"
    private fun limitRepresentation(value: String): String {
        return if (value.length > 10000) "${value.take(10000)}..." else value
    }

    protected abstract fun format(kClass: KClass<*>): String
    protected abstract fun identityHash(indent: String, any: Any): String

    companion object {
        internal const val INDENT: String = "        "
    }
}
