package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.getDefaultLocale

/**
 * Formats this [String] based on the given [locale] and using the given [arg] and [otherArgs] for replacements.
 */
expect fun String.format(locale: Locale, arg: Any, vararg otherArgs: Any): String

/**
 * Formats this [String] using the given [arg] and [otherArgs] for replacements where a predefined [Locale] is used.
 *
 * The predefined [Locale] might correspond to [getDefaultLocale] but can also vary (e.g. on JVM platform the default
 * local for formatting is used which corresponds to [getDefaultLocale] if nothing special is defined).
 */
expect fun String.format(arg: Any, vararg otherArgs: Any): String

//TODO remove again before 1.3.0 => move to atrium-specs
/**
 * No backward compatibility guarantees
 */
expect fun String.toRegexSupportingQE(): Regex
