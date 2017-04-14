package ch.tutteli.atrium.assertions

/**
 * The base interface for [IAssertion]s which have multiple [Message]s.
 *
 * It provides a default implementation for [IAssertion.holds] which returns true if all its [messages] hold.
 */
interface IMultiMessageAssertion : IAssertion {
    /**
     * The messages of this assertion.
     */
    val messages: List<Message>

    /**
     * Holds if all its [messages] hold.
     *
     * @return `true` if all [messages] hold; `false` otherwise.
     */
    override fun holds() = messages.all(Message::holds)
}
