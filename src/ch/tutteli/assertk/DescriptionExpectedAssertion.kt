package ch.tutteli.assertk


class DescriptionExpectedAssertion(description: String, expected: Any?, check: () -> Boolean) : IAssertion {
    private val lazyMessages: List<Message> by lazy {
        listOf(Message(description, expected, check()))
    }

    override fun messages() = lazyMessages
}
