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
data class StringBasedRawString internal constructor(val string: String) : RawString {

    /**
     * @suppress No need to document this behaviour
     */
    override fun toString(): String {
        return "$string (RawString)"
    }
}
