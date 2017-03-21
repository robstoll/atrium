package ch.tutteli.assertk.assertions

/**
 * Represent a message for reporting.
 */
data class Message(val description: String, val representation: Any, val holds: Boolean)
