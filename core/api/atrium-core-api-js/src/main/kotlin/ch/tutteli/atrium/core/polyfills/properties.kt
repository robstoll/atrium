package ch.tutteli.atrium.core.polyfills

private val properties = mutableMapOf<String, String>()

@Deprecated("Will be removed with 0.17.0 without replacement")
actual fun getAtriumProperty(key: String): String? = properties[key]

@Deprecated("Will be removed with 0.17.0 without replacement")
actual fun setAtriumProperty(key: String, newValue: String) {
    properties[key] = newValue
}
