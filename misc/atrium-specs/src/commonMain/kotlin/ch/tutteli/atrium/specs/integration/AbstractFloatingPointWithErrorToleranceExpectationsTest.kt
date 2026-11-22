package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.core.polyfills.formatFloatingPointNumber
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionFloatingPointException.FAILURE_DUE_TO_FLOATING_POINT_NUMBER
import ch.tutteli.atrium.translations.DescriptionFloatingPointException.TO_EQUAL_WITH_ERROR_TOLERANCE
import ch.tutteli.atrium.translations.DescriptionFloatingPointException.TO_EQUAL_WITH_ERROR_TOLERANCE_EXPLAINED
import kotlin.math.absoluteValue

@Suppress("FunctionName")
abstract class AbstractFloatingPointWithErrorToleranceExpectationsTest(
    private val toEqualWithErrorToleranceFloatSpec: Fun2<Float, Float, Float>,
    private val toEqualWithErrorToleranceDoubleSpec: Fun2<Double, Double, Double>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toEqualWithErrorToleranceFloatSpec.forSubjectLessTest(1.0f, 0.01f)
        ),
        SubjectLessTestData(
            toEqualWithErrorToleranceDoubleSpec.forSubjectLessTest(1.0, 0.01)
        )
    )

    @TestFactory
    fun floatCases() = testFactory(toEqualWithErrorToleranceFloatSpec) { toEqualWithErrorToleranceFun ->
        // Keep Float statically typed throughout this factory. In particular, do not route
        // these values through a generic T : Number helper: Kotlin/JS can then treat them as
        // Double and lose the Float rounding behaviour these cases are intended to verify.
        fun Expect<Float>.toEqualWithErrorTolerance(num: Float, tolerance: Float) =
            toEqualWithErrorToleranceFun(num, tolerance)

        val testData = listOf(
            createFloatTestData(
                0.001f,
                0.001f,
                listOf(0.002f, 0.0f, -0.0f, 0.00001f),
                listOf(0.0021f, -0.0000001f)
            ),
            createFloatTestData(
                9.999f,
                0.001f,
                listOf(9.998f, 9.9989f, 9.9988f),
                listOf(1.1f, 1.001f, 1.001f, 9.997f, /* due to precision */ 10.0f)
            ),
            // should give out scientific notation
            createFloatTestData(
                0.000_000_01f,
                0.000_000_002f,
                listOf(0.000_000_011f, 0.000_000_009f),
                listOf(
                    0.000_000_013f,
                    0.000_000_007f,
                    /* due to precision */ 0.000_000_012f,
                    0.000_000_008f
                )
            )
        )

        testData.forEachIndexed { caseIndex, (subject, tolerance, holding, failing) ->
            describe("case ${caseIndex + 1}: $subject / $tolerance") {
                it("subject") {
                    expect(subject).toEqualWithErrorTolerance(subject, tolerance)
                }

                holding.forEachIndexed { numberIndex, num ->
                    it("within ${numberIndex + 1}") {
                        expect(subject).toEqualWithErrorTolerance(num, tolerance)
                    }
                }

                val toEqualInclErrorToleranceDescr = TO_EQUAL_WITH_ERROR_TOLERANCE.getDefault().format(tolerance)
                val failureNoticePrefix = FAILURE_DUE_TO_FLOATING_POINT_NUMBER
                    .getDefault()
                    .format("__TYPE__")
                    .substringBefore("__TYPE__")

                failing.forEachIndexed { numberIndex, num ->
                    it("outside ${numberIndex + 1}") {
                        expect {
                            expect(subject).toEqualWithErrorTolerance(num, tolerance)
                        }.toThrow<AssertionError> {
                            message {
                                val exactCheck = TO_EQUAL_WITH_ERROR_TOLERANCE_EXPLAINED.getDefault().format(
                                    @Suppress("DEPRECATION")
                                    formatFloatingPointNumber(subject),
                                    @Suppress("DEPRECATION")
                                    formatFloatingPointNumber(num),
                                    @Suppress("DEPRECATION")
                                    formatFloatingPointNumber((subject - num).absoluteValue),
                                    @Suppress("DEPRECATION")
                                    formatFloatingPointNumber(tolerance)
                                )
                                toContain(
                                    subject,
                                    "$toEqualInclErrorToleranceDescr: $num",
                                    exactCheck
                                )
                                toContain(failureNoticePrefix)
                            }
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun doubleCases() = testFactory(toEqualWithErrorToleranceDoubleSpec) { toEqualWithErrorToleranceFun ->
        // Keep Double separately typed as well; this avoids widening the Float cases above.
        fun Expect<Double>.toEqualWithErrorTolerance(num: Double, tolerance: Double) =
            toEqualWithErrorToleranceFun(num, tolerance)

        val testData = listOf(
            DoubleTestData(1.0, 0.01, listOf(1.009), listOf(0.98, 1.02, 1.011, /* due to precision */ 1.01, 0.99)),
            DoubleTestData(0.001, 0.001, listOf(0.002, 0.0, -0.0, 0.00001), listOf(0.0021, -0.0000001)),
            DoubleTestData(9.99999, 0.00001, listOf(10.0, 9.99998), listOf(1.1, 1.001, 1.001, 9.99997)),
            // should give out scientific notation
            DoubleTestData(
                0.000_000_01,
                0.000_000_002,
                listOf(0.000_000_011, 0.000_000_012, 0.000_000_009, 0.000_000_008),
                listOf(0.000_000_013, 0.000_000_007)
            )
        )

        testData.forEachIndexed { caseIndex, (subject, tolerance, holding, failing) ->
            describe("case ${caseIndex + 1}: $subject / $tolerance") {
                it("subject") {
                    expect(subject).toEqualWithErrorTolerance(subject, tolerance)
                }

                holding.forEachIndexed { numberIndex, num ->
                    it("within ${numberIndex + 1}") {
                        expect(subject).toEqualWithErrorTolerance(num, tolerance)
                    }
                }

                val toEqualInclErrorToleranceDescr = TO_EQUAL_WITH_ERROR_TOLERANCE.getDefault().format(tolerance)
                val failureNoticePrefix = FAILURE_DUE_TO_FLOATING_POINT_NUMBER
                    .getDefault()
                    .format("__TYPE__")
                    .substringBefore("__TYPE__")

                failing.forEachIndexed { numberIndex, num ->
                    it("outside ${numberIndex + 1}") {
                        expect {
                            expect(subject).toEqualWithErrorTolerance(num, tolerance)
                        }.toThrow<AssertionError> {
                            message {
                                val exactCheck = TO_EQUAL_WITH_ERROR_TOLERANCE_EXPLAINED.getDefault().format(
                                    @Suppress("DEPRECATION")
                                    formatFloatingPointNumber(subject),
                                    @Suppress("DEPRECATION")
                                    formatFloatingPointNumber(num),
                                    @Suppress("DEPRECATION")
                                    formatFloatingPointNumber((subject - num).absoluteValue),
                                    @Suppress("DEPRECATION")
                                    formatFloatingPointNumber(tolerance)
                                )
                                toContain(
                                    subject,
                                    "$toEqualInclErrorToleranceDescr: $num",
                                    exactCheck
                                )
                                toContain(failureNoticePrefix)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun createFloatTestData(
        subject: Float,
        tolerance: Float,
        holding: List<Float>,
        failing: List<Float>
    ) = FloatTestData(
        subject = floatArrayOf(subject)[0],
        tolerance = floatArrayOf(tolerance)[0],
        holding = holding.toFloatArray().toList(),
        failing = failing.toFloatArray().toList()
    )

    private data class FloatTestData(
        val subject: Float,
        val tolerance: Float,
        val holding: List<Float>,
        val failing: List<Float>
    )

    private data class DoubleTestData(
        val subject: Double,
        val tolerance: Double,
        val holding: List<Double>,
        val failing: List<Double>
    )
}
