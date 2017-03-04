package ch.tutteli.assertk.assertions

data class AssertionGroup(
    override val name: String,
    override val subject: Any,
    override val assertions: List<IAssertion>) : IAssertionGroup
