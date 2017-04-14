package ch.tutteli.atrium.assertions

/**
 * Represents a message of an [IAssertion].
 *
 * It consists of:
 *
 * [description] : [representation] => [holds]
 *
 * @property description Quasi the left hand side of the whole message, e.g., `it starts with`.
 * @property representation The representation of the expected value, e.g., `"hello world"`.
 * @property holds `true` if the assertion holds, `false` otherwise.
 *
 * @constructor  Represents a message of an [IAssertion] which consists of:
 *
 * [description] : [representation] => [holds]
 *
 * @param description Quasi the left hand side of the whole message, e.g., `it starts with`.
 * @param representation The representation of the expected value, e.g., `hello world`.
 * @param holds `true` if the assertion holds, `false` otherwise.
 */
data class Message(val description: String, val representation: Any, val holds: Boolean)
