package ch.tutteli.assertk

interface IAssertionGroup : IAssertion {
    val name: String
    val subject: Any
}
