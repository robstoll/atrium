package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.kbox.isNotNullAndNotBlank

actual val Throwable.stackBacktrace: List<String>
    get() {
        val nullableStack: String? = asDynamic().stack as? String
        return if (nullableStack.isNotNullAndNotBlank()) {
            splitStackLines(nullableStack)
        } else {
            listOf("Could not populate the stackBacktrace, please file a bug report at $BUG_REPORT_URL")
        }
    }

private fun splitStackLines(stack: String): List<String> {
    val searchWord = "  at "
    val firstFrame = stack.indexOf(searchWord)
    val nonFilteredStack = stack.substring(firstFrame + searchWord.length)
        .splitToSequence('\n')

    val filteredStack = nonFilteredStack
        //TODO remove once https://youtrack.jetbrains.com/issue/KT-27920 is fixed
        .dropWhile {
            //kotlin 1.6
            it.contains("packages_imported/kotlin/") ||
                it.contains("packages_imported\\kotlin\\") ||
                // kotlin 1.4
                it.contains("node_modules/kotlin/") ||
                it.contains("node_modules\\kotlin\\") ||
                // kotlin 1.4 + kotlin 1.3
                it.contains("_init_0")
        }
        .map { it.substringAfter(searchWord) }

    // include check for KotlinVersion.CURRENT once https://youtrack.jetbrains.com/issue/KT-64188 is fixed
    // although, it might also be that it is intellij related, if so, try to find out if we can get the intellij
    // version in use
    val hasCutColumnNumberOffBug = stack.contains("KotlinTestTeamCityConsoleAdapter")

    val cleanedStack = filteredStack.map { stackFrame ->
        if (stackFrame.startsWith("/")) {
            // in order that intellij renders the link correctly we prefix it with the file name
            stackFrame.substringAfterLast("/").substringBeforeLast(".kt") + " (" + stackFrame + ")"
        } else {
            stackFrame
        }
    }.map { stackFrame ->
        nameRegex.matchEntire(stackFrame)?.let {
            // looks like we use KotlinJs version which includes the .protoOf. and function name mangling
            // they don't add much value and might only confuse people
            // note that this only works because in the next map we also remove `kt` or `js` which would come after `columNumber)`
            "${it.groupValues[1]}${it.groupValues[2]}${it.groupValues[3]}"
        } ?: stackFrame
    }.map { stackFrame ->
        Unit.takeIf { hasCutColumnNumberOffBug }?.let {
            numberRegex.matchEntire(stackFrame)?.let {
                // due to a Kotlin bug (https://youtrack.jetbrains.com/issue/KT-64188) we add an imaginary third
                // number at the end because it seems that the KotlinTestTeamCityConsoleAdapter cuts one number off
                "${it.groupValues[1]}:0)"
            }
        } ?: stackFrame
    }

    return cleanedStack.toList()
}

private val nameRegex = Regex("""(.*\.)protoOf\.(.*?)_[0-9a-z]+(?:_[a-z]\$)?(\s+\(.*)""")
private val numberRegex = Regex("""(.*\.(kt|js):\d+:\d+)\)(.*)""")
