package ch.tutteli.atrium.domain.creating

/**
 * Defines the minimum set of assertion functions and builders applicable to floating points ([Float], [Double]),
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated(
    "Use FloatingPointAssertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.FloatingPointAssertions")
)
actual interface FloatingPointAssertions : FloatingPointAssertionsCommon
