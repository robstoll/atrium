package ch.tutteli.atrium.creating

/**
 * Indicates a problem which was indicated by a `@Deprecated` annotation but was ignored by you ;-)
 */
class PleaseUseReplacementException(reason: String) : Exception(reason)
