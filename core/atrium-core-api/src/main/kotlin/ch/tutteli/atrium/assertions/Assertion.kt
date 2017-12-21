package ch.tutteli.atrium.assertions

/**
 * The base interface of all assertions, providing the method [holds].
 */
interface Assertion {
    /**
     * Indicates whether the assertion holds or not.
     *
     * @return `true` in case the assertion holds otherwise `false`.
     */
    fun holds(): Boolean
}
