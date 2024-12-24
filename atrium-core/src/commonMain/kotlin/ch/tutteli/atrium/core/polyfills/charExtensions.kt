package ch.tutteli.atrium.core.polyfills

/**
 * Indicates if the char is a [High-Surrogate Code Unit](http://www.unicode.org/glossary/#high_surrogate_code_unit).
 * @return true if it is a high-surrogate code unit, false otherwise.
 *
 * @since 1.3.0
 */
expect fun Char.isHighSurrogate(): Boolean
