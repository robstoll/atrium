//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting

/**
 * Use this class to represent a [String] which should be treated as raw [String] in reporting.
 * @see ObjectFormatter
 *
 * @property string The string which should be treated as raw [String].
 *
 * @constructor Use [RawString.create] to create a [String] based [RawString].
 * @param string The string which should be treated as raw [String].
 */
@Deprecated("Switch from StringBasedRawString to Text; will be removed with 1.0.0", ReplaceWith("Text(string)"))
data class StringBasedRawString internal constructor(val string: String) : RawString {

    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return "$string (RawString)"
    }
}
