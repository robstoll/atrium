package ch.tutteli.atrium.assertions

/**
 * Represents an assertion which only wants to give an [explanation]
 */
class ExplanatoryAssertion(override val explanation: Any?) : IExplanatoryAssertion
