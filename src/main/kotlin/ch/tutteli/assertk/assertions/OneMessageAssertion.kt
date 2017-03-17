package ch.tutteli.assertk.assertions


class OneMessageAssertion(description: String, representation: Any, check: () -> Boolean) : IOneMessageAssertion {

    constructor(description: String, expected: Any, holds: Boolean)
        : this(description, expected, { holds })

    override val message by lazy {
        Message(description, representation, check())
    }

    override fun toString() = message.toString()
}
