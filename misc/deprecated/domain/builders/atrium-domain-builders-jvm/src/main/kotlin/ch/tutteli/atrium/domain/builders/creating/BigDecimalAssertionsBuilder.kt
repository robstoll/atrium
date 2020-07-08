//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    "OVERRIDE_BY_INLINE",
    "NOTHING_TO_INLINE",
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.BigDecimalAssertions
import ch.tutteli.atrium.domain.creating.bigDecimalAssertions
import java.math.BigDecimal

/**
 * Delegates inter alia to the implementation of [BigDecimalAssertions].
 * In detail, it implements [BigDecimalAssertions] by delegating to [bigDecimalAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object BigDecimalAssertionsBuilder : BigDecimalAssertions {

    override inline fun <T : BigDecimal> isNumericallyEqualTo(subjectProvider: SubjectProvider<T>, expected: T) =
        bigDecimalAssertions.isNumericallyEqualTo(subjectProvider, expected)

    override inline fun <T : BigDecimal> isNotNumericallyEqualTo(subjectProvider: SubjectProvider<T>, expected: T) =
        bigDecimalAssertions.isNotNumericallyEqualTo(subjectProvider, expected)

    override inline fun <T : BigDecimal> isEqualIncludingScale(
        subjectProvider: SubjectProvider<T>,
        expected: T,
        nameOfIsNumericallyEqualTo: String
    ) = bigDecimalAssertions.isEqualIncludingScale(subjectProvider, expected, nameOfIsNumericallyEqualTo)

    override inline fun <T : BigDecimal> isNotEqualIncludingScale(subjectProvider: SubjectProvider<T>, expected: T) =
        bigDecimalAssertions.isNotEqualIncludingScale(subjectProvider, expected)
}
