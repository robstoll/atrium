package ch.tutteli.atrium.core.polyfills

@Deprecated("Will be removed with 0.17.0 without replacement")
actual fun getAtriumProperty(key: String): String? = System.getProperty(key)
@Deprecated("Will be removed with 0.17.0 without replacement")
actual fun setAtriumProperty(key: String, newValue: String) {
    System.setProperty(key, newValue)
}
