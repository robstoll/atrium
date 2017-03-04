package ch.tutteli.assertk.assertions


data class OneMessageAssertion(val description: String, val representation: Any, val holds: Boolean) : IOneMessageAssertion {

    constructor(description: String, expected: Any, check: () -> Boolean)
        : this(description, expected, check())

    override val message by lazy {
        Message(description, representation, holds)
    }
}
