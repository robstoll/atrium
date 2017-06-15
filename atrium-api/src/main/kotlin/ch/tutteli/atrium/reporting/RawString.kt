package ch.tutteli.atrium.reporting

/**
 * Use this class to represent a [String] which should be treated as raw [String] in reporting.
 * @see IObjectFormatter
 *
 * @property string The string which should be treated as raw [String].
 *
 * @constructor
 * @param string The string which should be treated as raw [String].
 *
 */
data class RawString(val string: String) : IRawString {

    /**
     * @suppress No need to document this behaviour
     */
    override fun toString(): String {
        return "$string (RawString)"
    }

    companion object {
        /**
         * The representation for `null`.
         */
        val NULL = RawString("null")
    }
}
