package ch.tutteli.assertk.assertions

/**
 * The base interface for [IAssertion]s which have multiple [Message]s.
 *
 * It provides a default implementation for [IAssertion.holds] which returns true if all its [messages] hold.
 */
interface IMultiMessageAssertion : IAssertion {
    val messages: List<Message>

    /**
     * Holds if all its [messages] hold.
     */
    override fun holds() = messages.all(Message::holds)
}
