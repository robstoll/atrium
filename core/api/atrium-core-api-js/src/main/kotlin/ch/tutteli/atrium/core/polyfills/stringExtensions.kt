package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.reporting.translating.Locale

/**
 * Replaces the first %s inside this [String] with [arg] and all subsequent with [otherArgs].
 *
 * This implementation neglects the given [locale] for now.
 */
actual fun String.format(locale: Locale, arg: Any, vararg otherArgs: Any): String =
    replacePlaceholdersWithArgs(this, arg, otherArgs)

/**
 * Replaces the first %s inside this [String] with [arg] and all subsequent with [otherArgs].
 *
 * This implementation does not take a default [Locale] into account.
 */
actual fun String.format(arg: Any, vararg otherArgs: Any): String =
    replacePlaceholdersWithArgs(this, arg, otherArgs)

private fun replacePlaceholdersWithArgs(string: String, arg: Any, otherArgs: Array<out Any>): String {
    val placeholder = Regex("%s")
    var tmp = string.replaceFirst(placeholder, arg.toString())
    otherArgs.forEach {
        tmp = tmp.replaceFirst(placeholder, it.toString())
    }
    return tmp
}
