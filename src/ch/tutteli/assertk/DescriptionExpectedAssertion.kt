package ch.tutteli.assertk


class DescriptionExpectedAssertion(private val description: String,
                                   private val expected: String,
                                   private val check: () -> Boolean) : IAssertion {

    override fun holds(): Boolean = check()
    override fun logMessages(): List<Pair<String, String>> = listOf(description to expected)
}
