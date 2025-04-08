package ch.tutteli.atrium.logic.creating.typeutils

/**
 * Type alias for [Any] but with a better description what is expected at runtime,
 * i.e. either a [CharSequence], a [Number] or a [Char].
 */
@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.creating.typeutils.CharSequenceOrNumberOrChar")
)
typealias CharSequenceOrNumberOrChar = Any
