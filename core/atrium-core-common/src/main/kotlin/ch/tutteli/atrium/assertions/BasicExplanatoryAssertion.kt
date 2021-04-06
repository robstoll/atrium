package ch.tutteli.atrium.assertions

/**
 * A default implementation for [ExplanatoryAssertion] -- an assertion which only wants to give an [explanation].
 */
internal class BasicExplanatoryAssertion(override val explanation: Any?) : ExplanatoryAssertion {
    override fun toString() = explanation.toString()
}
