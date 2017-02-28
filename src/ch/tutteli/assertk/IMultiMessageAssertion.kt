package ch.tutteli.assertk

interface IMultiMessageAssertion : IAssertion {
    val messages: List<Message>
    override fun holds() = messages.all(Message::holds)
}
