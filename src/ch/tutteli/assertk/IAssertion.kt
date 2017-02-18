package ch.tutteli.assertk

interface IAssertion {
    fun messages(): List<Message>
}

fun IAssertion.holds(): Boolean = messages().all(Message::holds)
