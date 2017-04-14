package ch.tutteli.atrium.assertions

/**
 * The base interface for [IAssertion]s which have one [Message].
 *
 * It provides a default implementation for [IAssertion.holds] which delegates to [Message.holds].
 */
interface IOneMessageAssertion : IAssertion {
    /**
     * The message of this assertion.
     */
    val message: Message

    /**
     * Holds if its [message] holds.
     *
     * @return `true` if the [message] holds; `false` otherwise.
     */
    override fun holds() = message.holds
}
