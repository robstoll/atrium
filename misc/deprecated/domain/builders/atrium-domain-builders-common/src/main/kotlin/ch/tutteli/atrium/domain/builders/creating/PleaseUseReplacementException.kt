package ch.tutteli.atrium.domain.builders.creating

/**
 * Indicates a problem which was indicated by a `@Deprecated` annotation but was ignored by you ;-)
 */
@Deprecated(
    "Use PleaseUseReplacementException from core; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.creating.PleaseUseReplacementException")
)
class PleaseUseReplacementException
@Deprecated(
    "Use PleaseUseReplacementException from core; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.creating.PleaseUseReplacementException(reason)")
)
constructor(reason: String) : Exception(reason)
