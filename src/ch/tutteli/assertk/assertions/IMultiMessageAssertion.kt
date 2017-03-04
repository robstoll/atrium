package ch.tutteli.assertk.assertions

import ch.tutteli.assertk.assertions.Message
import ch.tutteli.assertk.assertions.IAssertion

interface IMultiMessageAssertion : IAssertion {
    val messages: List<Message>
    override fun holds() = messages.all(Message::holds)
}
