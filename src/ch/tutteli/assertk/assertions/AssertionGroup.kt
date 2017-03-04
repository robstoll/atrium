package ch.tutteli.assertk.assertions

import ch.tutteli.assertk.assertions.IAssertion

data class AssertionGroup(
    override val name: String,
    override val subject: Any,
    override val assertions: List<IAssertion>) : IAssertionGroup
