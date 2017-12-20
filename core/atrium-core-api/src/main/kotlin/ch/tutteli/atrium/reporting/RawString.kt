package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableBasedRawString

/**
 * Marker interface for types which provide a raw string functionality
 * and should be treated accordingly in reporting (e.g., in [ObjectFormatter]).
 */
interface RawString {
    companion object {
        /**
         * The representation for `null` as [StringBasedRawString].
         */
        val NULL = StringBasedRawString("null")
        /**
         * An empty string as [RawString].
         */
        val EMPTY = RawString.create("")

        fun create(string: String): RawString = StringBasedRawString(string)
        fun create(translatable: ITranslatable): RawString = TranslatableBasedRawString(translatable)
    }
}
