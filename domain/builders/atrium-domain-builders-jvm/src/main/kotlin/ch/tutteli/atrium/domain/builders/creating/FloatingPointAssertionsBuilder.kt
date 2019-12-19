@file:Suppress(
    "OVERRIDE_BY_INLINE",
    "NOTHING_TO_INLINE",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.FloatingPointAssertions
import ch.tutteli.atrium.domain.creating.floatingPointAssertions
import java.math.BigDecimal

/**
 * Delegates inter alia to the implementation of [FloatingPointAssertions].
 * In detail, it implements [FloatingPointAssertions] by delegating to [floatingPointAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
actual object FloatingPointAssertionsBuilder : FloatingPointAssertions {

    override inline fun toBeWithErrorTolerance(
        subjectProvider: SubjectProvider<Float>,
        expected: Float,
        tolerance: Float
    ) = floatingPointAssertions.toBeWithErrorTolerance(subjectProvider, expected, tolerance)

    override inline fun toBeWithErrorTolerance(
        subjectProvider: SubjectProvider<Double>,
        expected: Double,
        tolerance: Double
    ) = floatingPointAssertions.toBeWithErrorTolerance(subjectProvider, expected, tolerance)

    override inline fun <T : BigDecimal> toBeWithErrorTolerance(
        subjectProvider: SubjectProvider<T>,
        expected: T,
        tolerance: T
    ) = floatingPointAssertions.toBeWithErrorTolerance(subjectProvider, expected, tolerance)
}
