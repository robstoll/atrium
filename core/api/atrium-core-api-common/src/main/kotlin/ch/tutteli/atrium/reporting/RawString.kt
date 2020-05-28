@file:Suppress(/* TODO remove file with 1.0.0 */"DEPRECATION")

package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableBasedRawString

/**
 * Marker interface for types which provide a raw string functionality
 * and should be treated accordingly in reporting (e.g., in [ObjectFormatter]).
 */
@Deprecated("Use Text instead; will be removed with 1.0.0", ReplaceWith("Text"))
interface RawString {
    companion object {
        /**
         * The representation for `null` as [StringBasedRawString].
         */
        @Deprecated(
            "use Text.NULL instead; will be removed with 1.0.0",
            ReplaceWith("Text.NULL", "ch.tutteli.atrium.reporting.Text")
        )
        val NULL = StringBasedRawString("null")

        /**
         * An empty string as [RawString].
         */
        @Deprecated(
            "use Text.EMPTY instead; will be removed with 1.0.0",
            ReplaceWith("Text.EMPTY", "ch.tutteli.atrium.reporting.Text")
        )
        val EMPTY = StringBasedRawString("")

        @Deprecated(
            "Use Text.create instead; will be removed with 1.0.0",
            ReplaceWith("Text(string)", "ch.tutteli.atrium.reporting.Text")
        )
        fun create(string: String): RawString = StringBasedRawString(string)

        @Deprecated("Translatable are always treated as Text since 0.13.0", ReplaceWith("translatable"))
        fun create(translatable: Translatable): RawString = TranslatableBasedRawString(translatable)
    }
}
