package ch.tutteli.assertk.assertions

/**
 * Represents a message of an [IAssertion].
 */
data class Message(val description: String, val representation: Any, val holds: Boolean)
