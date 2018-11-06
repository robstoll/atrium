package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.kbox.isNotNullAndNotBlank

actual val Throwable.stack: List<String>
    get() {
        val nullableStack: String? = asDynamic().stack as? String
        return if (nullableStack.isNotNullAndNotBlank()) {
            //TODO !! no longer necessary with Kotlin 1.3 and a newer KBox Version
            splitStackLines(nullableStack!!)
        } else {
            listOf("Could not populate the stack, please file a bug report at $BUG_REPORT_URL")
        }
    }

private fun splitStackLines(stack: String): List<String> {
    val searchWord = "  at "
    val firstFrame = stack.indexOf(searchWord)
    return stack.substring(firstFrame + searchWord.length)
        .splitToSequence('\n')
        //TODO remove once https://youtrack.jetbrains.com/issue/KT-27920 is fixed
        .dropWhile { it.contains("init") && it.contains("kotlin.js") }
        .map { it.substringAfter(searchWord) }
        .toList()
}
