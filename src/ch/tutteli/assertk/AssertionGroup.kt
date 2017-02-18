package ch.tutteli.assertk

open class AssertionGroup(override final val name: String, override final val subject: Any, val assertions: List<IAssertion>) : IAssertionGroup {

    override fun messages() = assertions.flatten { it.messages() }
}
