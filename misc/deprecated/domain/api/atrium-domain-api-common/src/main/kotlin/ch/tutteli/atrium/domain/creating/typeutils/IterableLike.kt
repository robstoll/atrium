package ch.tutteli.atrium.domain.creating.typeutils

/**
 * Type alias for [Any] but with a better description what is expected at runtime,
 * i.e. such as Iterable, Sequence or one of the other Array types.
 */
@Deprecated(
    "Use the typealias from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.creating.typeutils.IterableLike")
)
typealias IterableLike = Any
