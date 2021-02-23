package ch.tutteli.atrium.core.polyfills

/**
 * Returns the specified value for the property with the given [key] or `null` in case it is not defined.
 *
 * @return The value of the property with the specified [key] or `null` in case it is not defined.
 */
@Deprecated("Will be removed with 0.17.0 without replacement")
expect fun getAtriumProperty(key: String): String?

/**
 * Sets the property with the given [key] to the given [newValue].
 */
@Deprecated("Will be removed with 0.17.0 without replacement")
expect fun setAtriumProperty(key: String, newValue: String)
