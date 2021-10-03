package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.formatFloatingPointNumber
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.style.specification.describe
import kotlin.math.absoluteValue

abstract class FloatingPointWithErrorToleranceExpectationsSpec(
    toEqualWithErrorToleranceFloat: Fun2<Float, Float, Float>,
    toEqualWithErrorToleranceDouble: Fun2<Double, Double, Double>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Float>(
        "$describePrefix[Float] ",
        toEqualWithErrorToleranceFloat.forSubjectLess(1.0f, 0.01f)
    ) {})
    include(object : SubjectLessSpec<Double>(
        "$describePrefix[Double] ",
        toEqualWithErrorToleranceDouble.forSubjectLess(1.0, 0.01)
    ) {})

    fun <T : Number> Root.describeFun(
        pair: Fun2<T, T, T>,
        withFailureNotice: Boolean,
        absDiff: (T, T) -> T,
        testData: List<TestData<T>>
    ) = checkFloatingPoint(describePrefix, pair, withFailureNotice, absDiff, testData)

    //@formatter:off
    describeFun(toEqualWithErrorToleranceFloat, true, { a: Float, b: Float -> (a - b).absoluteValue }, listOf(
        TestData(0.001f, 0.001f, listOf(0.002f, 0.0f, -0.0f, 0.00001f), listOf(0.0021f, -0.0000001f)),
        TestData(9.999f, 0.001f, listOf(9.998f, 9.9989f, 9.9988f), listOf(1.1f, 1.001f, 1.001f, 9.997f, /* due to precision */ 10.0f)),
        //should give out scientific notation
        TestData(0.000_000_01f, 0.000_000_002f, listOf(0.000_000_011f, 0.000_000_009f), listOf(0.000_000_013f, 0.000_000_007f, /* due to precision */ 0.000_000_012f, 0.000_000_008f))
    ))
    describeFun(toEqualWithErrorToleranceDouble, true, { a, b -> (a - b).absoluteValue }, listOf(
        TestData(1.0, 0.01, listOf(1.009), listOf(0.98, 1.02, 1.011, /* due to precision */ 1.01, 0.99)),
        TestData(0.001, 0.001, listOf(0.002, 0.0, -0.0, 0.00001), listOf(0.0021, -0.0000001)),
        TestData(9.99999, 0.00001, listOf(10.0, 9.99998), listOf(1.1, 1.001, 1.001, 9.99997)),
        //should give out scientific notation
        TestData(0.000_000_01, 0.000_000_002, listOf(0.000_000_011, 0.000_000_012, 0.000_000_009, 0.000_000_008), listOf(0.000_000_013, 0.000_000_007))
    ))
    //@formatter:on

}) {
    data class TestData<out T : Number>(val subject: T, val tolerance: T, val holding: List<T>, val failing: List<T>)
}

fun <T : Number> Root.checkFloatingPoint(
    describePrefix: String,
    pair: Fun2<T, T, T>,
    withFailureNotice: Boolean,
    absDiff: (T, T) -> T,
    testData: List<FloatingPointWithErrorToleranceExpectationsSpec.TestData<T>>
) {
    val (name, toEqualWithErrorTolerance) = pair

    describe("$describePrefix $name") {
        testData.forEach { (subject, tolerance, holding, failing) ->
            context("tolerance is $tolerance and subject is $subject ...") {
                it("... compare to $subject does not throw") {
                    expect(subject).toEqualWithErrorTolerance(subject, tolerance)
                }

                holding.forEach { num ->
                    it("... compare to $num does not throw") {
                        expect(subject).toEqualWithErrorTolerance(num, tolerance)
                    }
                }

                val toEqualInclErrorToleranceDescr =
                    String.format(DescriptionFloatingPointAssertion.TO_EQUAL_WITH_ERROR_TOLERANCE.getDefault(), tolerance)
                val failureNotice = String.format(
                    DescriptionFloatingPointAssertion.FAILURE_DUE_TO_FLOATING_POINT_NUMBER.getDefault(),
                    subject::class.fullName
                )
                failing.forEach { num ->
                    it("... compare to $num throws AssertionError") {
                        expect {
                            expect(subject).toEqualWithErrorTolerance(num, tolerance)
                        }.toThrow<AssertionError> {
                            message {
                                @Suppress("DEPRECATION")
                                val exactCheck = String.format(
                                    DescriptionFloatingPointAssertion.TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED.getDefault(),
                                    formatFloatingPointNumber(subject),
                                    formatFloatingPointNumber(num),
                                    formatFloatingPointNumber(absDiff(subject, num)),
                                    formatFloatingPointNumber(tolerance)
                                )
                                toContain(
                                    subject,
                                    "$toEqualInclErrorToleranceDescr: $num",
                                    exactCheck
                                )
                                if (withFailureNotice) {
                                    toContain(failureNotice)
                                } else {
                                    notToContain(failureNotice)
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
