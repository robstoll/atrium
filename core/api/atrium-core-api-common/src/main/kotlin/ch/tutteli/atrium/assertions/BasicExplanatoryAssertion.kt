package ch.tutteli.atrium.assertions

/**
 * A default implementation for [ExplanatoryAssertion] -- an assertion which only wants to give an [explanation].
 */
@Deprecated("Use ExplanatoryAssertion, do not rely on this specific type, will be made internal with 1.0.0")
class BasicExplanatoryAssertion
@Deprecated(
    "Use `AssertImpl.builder.explanatory` instead, will be made `internal` with 1.0.0",
    ReplaceWith(
        "AssertImpl.builder.explanatory.withAssertions(explanation)",
        "ch.tutteli.atrium.domain.builders.AssertImpl"
    )
)
constructor(override val explanation: Any?) : ExplanatoryAssertion
