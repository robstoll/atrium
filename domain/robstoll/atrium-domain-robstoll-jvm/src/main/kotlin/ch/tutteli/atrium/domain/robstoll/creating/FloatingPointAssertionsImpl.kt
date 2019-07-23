package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.FloatingPointAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._toBeWithErrorTolerance
import java.math.BigDecimal


class FloatingPointAssertionsImpl : FloatingPointAssertions {

    override fun toBeWithErrorTolerance(
        subjectProvider: SubjectProvider<Float>,
        expected: Float,
        tolerance: Float
    ) = _toBeWithErrorTolerance(subjectProvider, expected, tolerance)

    override fun toBeWithErrorTolerance(
        subjectProvider: SubjectProvider<Double>,
        expected: Double,
        tolerance: Double
    ) = _toBeWithErrorTolerance(subjectProvider, expected, tolerance)

    override fun <T : BigDecimal> toBeWithErrorTolerance(
        subjectProvider: SubjectProvider<T>,
        expected: T,
        tolerance: T
    ) = _toBeWithErrorTolerance(subjectProvider, expected, tolerance)
}
