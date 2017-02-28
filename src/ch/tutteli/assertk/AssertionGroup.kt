package ch.tutteli.assertk

data class AssertionGroup(
    override val name: String,
    override val subject: Any,
    override val assertions: List<IAssertion>) : IAssertionGroup
