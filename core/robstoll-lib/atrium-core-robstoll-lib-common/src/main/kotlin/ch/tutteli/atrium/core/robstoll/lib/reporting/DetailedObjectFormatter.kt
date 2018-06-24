package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.StringBasedRawString
import ch.tutteli.atrium.reporting.translating.TranslatableBasedRawString
import ch.tutteli.atrium.reporting.translating.Translator
import kotlin.reflect.KClass

expect class DetailedObjectFormatter(translator: Translator) : ObjectFormatter

/**
 * Base class for the platform specific implementation of [DetailedObjectFormatter].
 *
 * It cannot format Java's `Class`, this has to be done in the JVM module. Moreover it requires a platform specific
 * implementation of [identityHash] and [format] a [KClass].
 */
abstract class DetailedObjectFormatterCommon(
    private val translator: Translator
) : ObjectFormatter {

    /**
     * Returns a formatted version of the given [value].
     *
     * The following rules apply for the representation of an object:
     * - `null` is represented as [RawString.NULL].[StringBasedRawString.string]
     * - [LazyRepresentation] is [evaluated][LazyRepresentation.eval] and then again [format]ted
     * - [Char] is put in apostrophes
     * - [Boolean] is represented with its [toString] representation
     * - [String] is put in quotes and its [KClass.fullName] is omitted
     * - [CharSequence] is put in quotes, but [KClass.fullName] is used in contrast to [String]
     * - [StringBasedRawString] is represented as [StringBasedRawString.string]
     * - [TranslatableBasedRawString] is represented as result of its translation (by [translator])
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
        null -> RawString.NULL.string
        is LazyRepresentation -> format(value.eval())
        is Char -> "'$value'"
        is Boolean -> value.toString()
        is String -> format(value)
        is CharSequence -> format(value)
        is StringBasedRawString -> value.string
        is TranslatableBasedRawString -> translator.translate(value.translatable)
        is KClass<*> -> format(value)
        is Enum<*> -> format(value)
        is Throwable -> format(value)
        else -> value.toString() + classNameAndIdentity(value)
    }

    private fun format(string: String) = "\"$string\"" + identityHash(INDENT, string)
    private fun format(charSequence: CharSequence) = "\"$charSequence\"" + classNameAndIdentity(charSequence)
    private fun format(enum: Enum<*>) =
        enum.toString() + INDENT + "(" + (enum::class.fullName) + ")"

    private fun format(throwable: Throwable) = throwable::class.fullName

    private fun classNameAndIdentity(any: Any): String = INDENT + "(${any::class.fullName}${identityHash(" ", any)})"

    protected abstract fun format(kClass: KClass<*>): String
    protected abstract fun identityHash(indent: String, any: Any): String

    companion object {
        internal const val INDENT: String = "        "
    }
}
