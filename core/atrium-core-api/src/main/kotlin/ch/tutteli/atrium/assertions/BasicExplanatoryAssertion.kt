package ch.tutteli.atrium.assertions

/**
 * A default implementation for [ExplanatoryAssertion] -- an assertion which only wants to give an [explanation].
 */
@Deprecated("use ExplanatoryAssertion, do not rely on this specific type, will be made internal with 1.0.0")
class BasicExplanatoryAssertion
@Deprecated(
    "use `AssertImpl.builder.explanatory` instead, will be made `internal` with 1.0.0",
    ReplaceWith(
        "AssertImpl.builder.explanatory.create(explanation)",
        "ch.tutteli.atrium.domain.builders.creating.AssertImpl"
    )
)
constructor(override val explanation: Any?) : ExplanatoryAssertion
