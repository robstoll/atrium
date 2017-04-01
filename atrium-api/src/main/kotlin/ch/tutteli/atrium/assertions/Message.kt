package ch.tutteli.atrium.assertions

/**
 * Represents a message of an [IAssertion].
 */
data class Message(val description: String, val representation: Any, val holds: Boolean)
