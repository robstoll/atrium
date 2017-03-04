package ch.tutteli.assertk.assertions

interface IOneMessageAssertion : IAssertion {
    val message: Message
    override fun holds() = message.holds
}
