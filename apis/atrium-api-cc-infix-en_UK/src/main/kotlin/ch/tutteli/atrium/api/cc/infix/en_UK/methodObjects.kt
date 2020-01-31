@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0*/)
package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.kbox.glue

/**
 * Parameter object to express `Translatable, vararg Translatable` in the infix-api.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.DefaultTranslationsOf(expected, *otherExpected)"))
class DefaultTranslationsOf(val expected: Translatable, vararg val otherExpected: Translatable) {
    fun toList(): List<Translatable> = expected glue otherExpected
}

/**
 * Parameter object to express `((Assert<T>) -> Unit)?, vararg ((Assert<T>) -> Unit)?` in the infix-api.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.Entries(assertionCreator, *otherAssertionCreators)"))
class Entries<in T : Any, out A : ((Assert<T>) -> Unit)?>(val assertionCreator: A, vararg val otherAssertionCreators: A) {
    fun toList(): List<A> = assertionCreator glue otherAssertionCreators
}

/**
 * Parameter object to express `T, vararg T` in the infix-api.
 */
@Deprecated("Use Values instead; will be removed with 1.0.0 - there is no ReplaceWith defined due to bug KT-10094, please use search & replace instead, watch out for `Values(Values(` afterwards")
class Objects<out T>(val expected: T, vararg val otherExpected: T) {
    @Deprecated("Use Values instead; will be removed with 1.0.0 - there is no ReplaceWith defined due to bug KT-10094, please use search & replace, watch out for `Values(Values(` afterwards")
    constructor(values: Values<T>) : this(values.expected, *values.otherExpected)
}

/**
 * Parameter object to express `String, vararg String` in the infix-api.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.RegexPatterns(pattern, *otherPatterns)"))
class RegexPatterns(val pattern: String, vararg val otherPatterns: String) {
    fun toList(): List<String> = pattern glue otherPatterns
}

/**
 * Parameter object to express `T, vararg T` in the infix-api.
 */
@Deprecated("Use pendant from package en_GB; will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.Values(expected, *otherExpected)"))
class Values<out T>(val expected: T, vararg val otherExpected: T) {

    @Deprecated("Use Values directly instead of wrapping it into Objects in addition; will be removed with 1.0.0")
    constructor(objects: Objects<T>) : this(objects.expected, *objects.otherExpected)

    fun toList(): List<T> = expected glue otherExpected
}
