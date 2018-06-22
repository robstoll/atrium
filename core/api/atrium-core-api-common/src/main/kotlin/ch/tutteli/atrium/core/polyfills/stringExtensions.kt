package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.reporting.translating.Locale

expect fun String.format(locale: Locale, arg: Any, vararg otherArgs: Any): String

expect fun String.format(arg: Any, vararg otherArgs: Any): String
