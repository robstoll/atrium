package ch.tutteli.atrium.core.polyfills

private val properties = mutableMapOf<String, String>()

actual fun getAtriumProperty(key: String): String? = properties[key]

actual fun setAtriumProperty(key: String, newValue: String) {
    properties[key] = newValue
}
