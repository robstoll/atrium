package ch.tutteli.atrium.core.polyfills

actual val Throwable.stack: List<String>
    get() {
        val nullableStack: String? = asDynamic().stack as? String
        return if (nullableStack != null) {
            splitStackLines(this, nullableStack)
        } else {
            //TODO or should we at least return class + message? => listOf("${this::class.js.name}:$message")
            listOf()
        }
    }

private fun splitStackLines(t: Throwable, nullableStack: String): List<String> {
    return when {
        nullableStack.startsWith("captureStack") -> {
            val firstNewLine = nullableStack.indexOf('\n')
            withoutEndingNewLine(nullableStack).substring(firstNewLine + 1).split('\n')
        }
        nullableStack.isBlank() -> t.toString().split('\n')
        else -> nullableStack.split('\n')
    }
}

private fun withoutEndingNewLine(text: String?): String {
    if (text == null) return ""
    return if (text.endsWith("\n")) text.substringBeforeLast("\n") else text
}
