//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.SubjectProvider
import java.math.BigDecimal

/**
 * The access point to an implementation of [BigDecimalAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val bigDecimalAssertions by lazy { loadSingleService(BigDecimalAssertions::class) }


/**
 * Defines the minimum set of assertion functions and builders applicable to [BigDecimal],
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated(
    "Use BigDecimalAssertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.BigDecimalAssertions")
)
interface BigDecimalAssertions {
    fun <T : BigDecimal> isNumericallyEqualTo(subjectProvider: SubjectProvider<T>, expected: T): Assertion
    fun <T : BigDecimal> isNotNumericallyEqualTo(subjectProvider: SubjectProvider<T>, expected: T): Assertion
    fun <T : BigDecimal> isEqualIncludingScale(
        subjectProvider: SubjectProvider<T>,
        expected: T,
        nameOfIsNumericallyEqualTo: String
    ): Assertion

    fun <T : BigDecimal> isNotEqualIncludingScale(subjectProvider: SubjectProvider<T>, expected: T): Assertion
}
