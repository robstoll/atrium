package ch.tutteli.atrium.core.polyfills

actual fun getAtriumProperty(key: String): String? = System.getProperty(key)
actual fun setAtriumProperty(key: String, newValue: String) {
    System.setProperty(key, newValue)
}
