package ch.tutteli.atrium.reporting

/**
 * Use this class to represent a [String] which should be treated as raw [String] in reporting.
 */
data class RawString(val string: String) {
    companion object {
        val NULL = "null"
    }
}
