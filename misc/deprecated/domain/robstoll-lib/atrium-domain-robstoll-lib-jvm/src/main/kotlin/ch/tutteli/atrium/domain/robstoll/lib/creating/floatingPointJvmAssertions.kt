@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)
@file:JvmMultifileClass
@file:JvmName("floatingPointAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.SubjectProvider
import java.math.BigDecimal

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : BigDecimal> _toBeWithErrorTolerance(
    subjectProvider: SubjectProvider<T>,
    expected: T,
    tolerance: T
): Assertion {
    val absDiff = { subject: BigDecimal -> (subject - expected).abs() }
    return toBeWithErrorTolerance(subjectProvider, expected, tolerance, absDiff) { subject ->
        listOf(
            createToBeWithErrorToleranceExplained(subject, expected, absDiff, tolerance)
        )
    }
}
