//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)
package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.SubjectProvider
import java.math.BigDecimal

/**
 * Defines the minimum set of assertion functions and builders applicable to floating points ([Float], [Double],
 * [BigDecimal]), which an implementation of the domain of Atrium has to provide.
 */
@Deprecated(
    "Use FloatingPointAssertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.FloatingPointAssertions")
)
actual interface FloatingPointAssertions : FloatingPointAssertionsCommon {
    fun <T : BigDecimal> toBeWithErrorTolerance(
        subjectProvider: SubjectProvider<T>,
        expected: T,
        tolerance: T
    ): Assertion
}
