package ch.tutteli.atrium.reporting.translating

/**
 * Represents a platform independent representation of a locale consisting of a [language], a [script], a [country] and
 * a [variant].
 *
 * @property language consists of at least one letter and only letters
 * @property script can be null or needs to consist of at least one letter and only letters
 * @property country can be null or needs to consist of at least one letter and only letters
 * @property variant can be null or needs at least one character, cannot be blank though (use null instead)
 */
data class Locale(val language: String, val script: String?, val country: String?, val variant: String?) {
    constructor(language: String, country: String) : this(language, null, country, null)
    constructor(language: String) : this(language, null, null, null)

    init {
        requireAtLeastOneAndOnlyLetters(language, "language")
        if (script != null) requireAtLeastOneAndOnlyLetters(script, "script")
        if (country != null) requireAtLeastOneAndOnlyLetters(country, "country")
        if (variant != null) require(variant.isNotBlank()) {
            "variant cannot be blank - use `null` if you do not want to specify it"
        }
    }

    private fun requireAtLeastOneAndOnlyLetters(field: String, name: String) {
        require(field.matches(Regex("[a-zA-Z]+"))) {
            "$name has to match [a-zA-Z]+ was $field"
        }
    }

    /**
     * Returns a string representation of Locale.
     *
     * Do not depend on the structure of the return value, it might change in the future without notice.
     */
    override fun toString(): String {
        val sb = StringBuilder("Locale(").append(language)
        if (script != null) {
            sb.append("-").append(script)
        }
        if (country != null) {
            sb.append("_").append(country)
        }
        if (variant != null) {
            sb.append("_").append(variant)
        }
        sb.append(")")
        return sb.toString()
    }
}

/**
 * Returns the default [Locale] which should be used if not defined otherwise.
 *
 * The actual implementation is platform specific.
 */
expect fun getDefaultLocale(): Locale
