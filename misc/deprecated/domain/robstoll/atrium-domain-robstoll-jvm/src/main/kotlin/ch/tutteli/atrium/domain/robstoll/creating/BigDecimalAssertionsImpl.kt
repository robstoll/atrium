//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.BigDecimalAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isEqualIncludingScale
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNotEqualIncludingScale
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNotNumericallyEqualTo
import ch.tutteli.atrium.domain.robstoll.lib.creating._isNumericallyEqualTo
import java.math.BigDecimal

@Deprecated("Will be removed with 1.0.0")
class BigDecimalAssertionsImpl : BigDecimalAssertions {

    override fun <T : BigDecimal> isNumericallyEqualTo(subjectProvider: SubjectProvider<T>, expected: T) =
        _isNumericallyEqualTo(subjectProvider, expected)

    override fun <T : BigDecimal> isNotNumericallyEqualTo(subjectProvider: SubjectProvider<T>, expected: T) =
        _isNotNumericallyEqualTo(subjectProvider, expected)

    override fun <T : BigDecimal> isEqualIncludingScale(
        subjectProvider: SubjectProvider<T>,
        expected: T,
        nameOfIsNumericallyEqualTo: String
    ) = _isEqualIncludingScale(subjectProvider, expected, nameOfIsNumericallyEqualTo)

    override fun <T : BigDecimal> isNotEqualIncludingScale(subjectProvider: SubjectProvider<T>, expected: T) =
        _isNotEqualIncludingScale(subjectProvider, expected)
}
