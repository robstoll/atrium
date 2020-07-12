//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.FloatingPointAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._toBeWithErrorTolerance

@Deprecated("Will be removed with 1.0.0")
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
}
