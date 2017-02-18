package ch.tutteli.assertk

open class AssertionGroup(override final val name: String, override final val subject: Any, assertions: List<IAssertion>) : IAssertionGroup {
    private val lazyMessages: List<Message> by lazy {
        assertions.flatten { it.messages() }
    }

    override fun messages() = lazyMessages
}
