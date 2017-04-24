package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.reporting.IObjectFormatter
import ch.tutteli.atrium.reporting.IRawString

/**
 * Use this class to represent an [ITranslatable] which shall be translated and
 * then be treated as raw [String] in reporting.
 *
 * @see IObjectFormatter
 *
 * @property translatable The [ITranslatable] who's translation should be treated as raw [String].
 *
 * @constructor
 * @param translatable The [ITranslatable] who's translation should be treated as raw [String]..
 *
 */
data class TranslatableRawString(val translatable: ITranslatable) : IRawString {
    override fun toString(): String {
        return "${translatable.getDefault()} (TranslatableRawString)"
    }
}
