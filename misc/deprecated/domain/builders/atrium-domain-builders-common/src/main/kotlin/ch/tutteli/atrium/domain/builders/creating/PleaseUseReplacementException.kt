package ch.tutteli.atrium.domain.builders.creating

/**
 * Indicates a problem which was indicated by a `@Deprecated` annotation but was ignored by you ;-)
 */
//TODO move to logic with 0.16.0
class PleaseUseReplacementException(reason: String) : Exception(reason)
