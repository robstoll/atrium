package ch.tutteli.atrium.assertions

/**
 * A default implementation for [ExplanatoryAssertion] -- an assertion which only wants to give an [explanation].
 */
@Deprecated("use ExplanatoryAssertion, do not rely on this specific type, will be made internal with 1.0.0")
class BasicExplanatoryAssertion
@Deprecated("Use AssertionBuilder.explanatory instead, will be made internal with 1.0.0")
constructor(override val explanation: Any?) : ExplanatoryAssertion
