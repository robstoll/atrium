package ch.tutteli.assertk

interface IOneMessageAssertion : IAssertion {
    val message : Message
    override fun holds() = message.holds
}
