@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.withFailureHint
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionBigDecimalAssertion.*
import java.math.BigDecimal

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : BigDecimal> _isNumericallyEqualTo(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(subjectProvider, IS_NUMERICALLY_EQUAL_TO, expected) {
        isNumericallyEqualTo(it, expected)
    }

private fun <T : BigDecimal> isNumericallyEqualTo(subject: T, expected: T) =
    subject.compareTo(expected) == 0

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : BigDecimal> _isNotNumericallyEqualTo(subjectProvider: SubjectProvider<T>, expected: T) =
    ExpectImpl.builder.createDescriptive(
        subjectProvider,
        IS_NOT_NUMERICALLY_EQUAL_TO,
        expected
    ) { !isNumericallyEqualTo(it, expected) }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : BigDecimal> _isEqualIncludingScale(
    subjectProvider: SubjectProvider<T>,
    expected: T,
    nameOfIsNumericallyEqualTo: String
): Assertion = ExpectImpl.builder.descriptive
    .withTest(subjectProvider) { it == expected }
    .withFailureHint {
        ExpectImpl.builder.explanatoryGroup
            .withDefaultType
            .withExplanatoryAssertion(
                FAILURE_IS_EQUAL_INCLUDING_SCALE_BUT_NUMERICALLY_EQUAL,
                nameOfIsNumericallyEqualTo
            )
            .build()
    }
    .showBasedOnDefinedSubjectOnlyIf(subjectProvider) {
        isNumericallyEqualTo(it, expected)
    }
    .withDescriptionAndRepresentation(IS_EQUAL_INCLUDING_SCALE, expected)
    .build()

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : BigDecimal> _isNotEqualIncludingScale(subjectProvider: SubjectProvider<T>, expected: T): Assertion {
    // unfortunately we cannot give a hint about isNotNumericallyEqualTo, because <<10 is not 10.0>> holds
    // so we do not get to the point where we can detect that using this function instead of isNotNumericallyEqualTo
    // might not be the intention of the user
    return ExpectImpl.builder.createDescriptive(subjectProvider, IS_NOT_EQUAL_INCLUDING_SCALE, expected) {
        it != expected
    }
}
