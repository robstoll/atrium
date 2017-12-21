package ch.tutteli.atrium.assertions

/**
 * A default implementation for [ExplanatoryAssertion] -- an assertion which only wants to give an [explanation].
 */
class BasicExplanatoryAssertion(override val explanation: Any?) : ExplanatoryAssertion
