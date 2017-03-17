package ch.tutteli.assertk.assertions

interface IMultiMessageAssertion : IAssertion {
    val messages: List<Message>
    override fun holds() = messages.all(Message::holds)
}
