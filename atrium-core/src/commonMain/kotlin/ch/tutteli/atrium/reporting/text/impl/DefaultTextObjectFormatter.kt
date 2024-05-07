package ch.tutteli.atrium.reporting.text.impl

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.text.TextObjectFormatter
import kotlin.reflect.KClass

expect class DefaultTextObjectFormatter() : TextObjectFormatter{
    //TODO  1.4.0 remove
    @Suppress("DEPRECATION", "UNUSED_PARAMETER")
    @Deprecated(
        "use the overload which does not expect a Translator, it is simply ignored",
        ReplaceWith("this()")
    )
    constructor(translator: ch.tutteli.atrium.reporting.translating.Translator)

    override fun format(value: Any?): String
}

/**
 * Base class for the platform specific implementation of [DefaultTextObjectFormatter].
 *
 * It cannot format Java's `Class`, this has to be done in the JVM module. Moreover, it requires a platform specific
 * implementation of [identityHash] and [format] a [KClass].
 */
abstract class TextObjectFormatterCommon() : TextObjectFormatter {
    //TODO  1.4.0 remove
    @Suppress("DEPRECATION", "UNUSED_PARAMETER")
    @Deprecated(
        "use the overload which does not expect a Translator, it is simply ignored",
        ReplaceWith("TextObjectFormatterCommon()")
    )
    constructor(translator: ch.tutteli.atrium.reporting.translating.Translator) : this()

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
     * - [ch.tutteli.atrium.reporting.translating.Translatable] is represented by its [ch.tutteli.atrium.reporting.translating.Translatable.getDefault]
     * - [KClass]'s format is defined by the concrete platform specific subclass.
     * - [Enum] is represented as "[toString] ([KClass.fullName])
     * - [Throwable] is represented as "[KClass.fullName]"
     * - All other objects are represented as "[toString] ([KClass.fullName] [identityHash])"
     *
     * @param value The value which shall be formatted.
     *
     * @return The formatted [value].
     */
    //TODO 1.3.0 remove suppression once it is clear if we still have an object formatter etc.
    @Suppress("DEPRECATION")
    override fun format(value: Any?): String = when (value) {
        null -> Text.NULL.string
        is LazyRepresentation -> format(value.eval())
        is Char -> "'$value'"
        is Boolean -> value.toString()
        is String -> format(value)
        is CharSequence -> format(value)
        is Text -> limitRepresentation(value.string)
        is ch.tutteli.atrium.reporting.translating.Translatable -> limitRepresentation(value.getDefault())
        is KClass<*> -> format(value)
        is Enum<*> -> format(value)
        is Throwable -> format(value)
        else -> limitRepresentation(value.toString()) + classNameAndIdentity(value)
    }

    private fun format(string: String) = "\"${limitRepresentation(string)}\"" + identityHash(INDENT, string)
    private fun format(charSequence: CharSequence) =
        "\"${limitRepresentation(charSequence.toString())}\"" + classNameAndIdentity(charSequence)

    private fun format(enum: Enum<*>) =
        limitRepresentation(enum.toString()) + INDENT + "(" + (enum::class.fullName) + ")"

    private fun format(throwable: Throwable) = throwable::class.fullName

    private fun classNameAndIdentity(any: Any): String = INDENT + "(${any::class.fullName}${identityHash(" ", any)})"
    private fun limitRepresentation(value: String): String {
        return if (value.length > 10000) "${value.substring(0, 10000)}..." else value
    }

    protected abstract fun format(kClass: KClass<*>): String
    protected abstract fun identityHash(indent: String, any: Any): String

    companion object {
        internal const val INDENT: String = "        "
    }
}
