package ch.tutteli.assertk


class DescriptionExpectedAssertion(private val description: String,
                                   private val expected: Any?,
                                   private val check: () -> Boolean) : IAssertion {

    override fun messages() = listOf(Message(description, expected, check()))
}
