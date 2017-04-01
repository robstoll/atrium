package ch.tutteli.assertk.assertions

/**
 * Represent a group of [IAssertion] identified by a [name] and an associated [subject].
 */
data class AssertionGroup(
    override val name: String,
    override val subject: Any,
    override val assertions: List<IAssertion>) : IAssertionGroup
