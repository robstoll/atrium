package ch.tutteli.atrium.reporting.theming.text

// TODO 1.3.0 KDOC
interface TextThemeProvider {
    val supportsAnsiColours: Boolean
    fun render(unstyledString: String, styleId: String): String?
}

