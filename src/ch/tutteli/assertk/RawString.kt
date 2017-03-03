package ch.tutteli.assertk

/**
 * Use this class to represent a string which should be treated as raw string in reporting.
 */
data class RawString(val string: String){
    companion object{
        val NULL = "null"
    }
}
