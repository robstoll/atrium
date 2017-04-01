package ch.tutteli.atrium.assertions

/**
 * The base interface for [IAssertion]s which have one [Message].
 *
 * It provides a default implementation for [IAssertion.holds] which delegates to [Message.holds].
 */
interface IOneMessageAssertion : IAssertion {
    val message: Message

    /**
     * Holds if its [message] holds.
     */
    override fun holds() = message.holds
}
