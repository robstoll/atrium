package ch.tutteli.atrium.assertions

/**
 * A default implementation for [ExplanatoryAssertion] -- an assertion which only wants to give an [explanation].
 */
@Deprecated("Use AssertionBuilder.explanatory instead, will be made internal with 1.0.0")
class BasicExplanatoryAssertion(override val explanation: Any?) : ExplanatoryAssertion
