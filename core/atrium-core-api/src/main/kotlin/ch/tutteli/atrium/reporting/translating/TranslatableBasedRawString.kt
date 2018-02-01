package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString

/**
 * Use this class to represent a [Translatable] which shall be translated and
 * then be treated as raw [String] in reporting.
 *
 * @see ObjectFormatter
 *
 * @property translatable The [Translatable] who's translation should be treated as raw [String].
 *
 * @constructor Use [RawString.create] to create a [Translatable] based [RawString].
 * @param translatable The [Translatable] who's translation should be treated as raw [String].
 *
 */
data class TranslatableBasedRawString internal constructor(val translatable: Translatable) : RawString {
    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return "${translatable.getDefault()} (TranslatableRawString)"
    }
}
