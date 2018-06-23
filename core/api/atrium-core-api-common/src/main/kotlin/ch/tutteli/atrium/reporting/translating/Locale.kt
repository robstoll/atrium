package ch.tutteli.atrium.reporting.translating

data class Locale(val language: String, val script: String?, val country: String?, val variant: String?) {
    constructor(language: String, country: String) : this(language, null, country, null)
    constructor(language: String) : this(language, null, null, null)

    init {
        requireOnlyLetters(language, "language")
        if (script != null) requireOnlyLetters(script, "script")
        if (country != null) require(country.matches(Regex("[a-zA-Z]*"))) {
            "country has to match [a-zA-Z]* was $country"
        }
        if (variant != null) require(variant.isNotBlank()) {
            "variant cannot be blank - use `null` if you do not want to specify it"
        }
    }

    private fun requireOnlyLetters(field: String, name: String) {
        require(field.matches(Regex("[a-zA-Z]+"))) {
            "$name has to match [a-zA-Z]+ was $field"
        }
    }

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

expect fun getDefaultLocale(): Locale
