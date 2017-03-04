package ch.tutteli.assertk.assertions

import ch.tutteli.assertk.assertions.Message
import ch.tutteli.assertk.assertions.IAssertion

interface IOneMessageAssertion : IAssertion {
    val message : Message
    override fun holds() = message.holds
}
