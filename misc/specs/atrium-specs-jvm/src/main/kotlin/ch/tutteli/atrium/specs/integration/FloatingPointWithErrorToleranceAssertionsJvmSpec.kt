package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.specs.Fun2
import ch.tutteli.atrium.specs.SubjectLessSpec
import ch.tutteli.atrium.specs.forSubjectLess
import ch.tutteli.atrium.specs.include
import ch.tutteli.atrium.specs.integration.FloatingPointWithErrorToleranceAssertionsSpec.TestData
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root
import java.math.BigDecimal

abstract class FloatingPointWithErrorToleranceAssertionsJvmSpec(
    toBeWithErrorToleranceBigDecimalPair: Fun2<BigDecimal, BigDecimal, BigDecimal>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<BigDecimal>(
        "$describePrefix[Float] ",
        toBeWithErrorToleranceBigDecimalPair.forSubjectLess(BigDecimal.TEN, BigDecimal("0.00001"))
    ) {})

    fun <T : Number> Root.describeFun(
        pair: Fun2<T, T, T>,
        withFailureNotice: Boolean,
        absDiff: (T, T) -> T,
        testData: List<TestData<T>>
    ) = checkFloatingPoint(describePrefix, pair, withFailureNotice, absDiff, testData)

    //@formatter:off
    describeFun(toBeWithErrorToleranceBigDecimalPair, false, { a, b -> (a - b).abs() }, listOf(
        TestData(BigDecimal("9.99999999999999"), BigDecimal("0.00000000000001"),
            listOf(BigDecimal.TEN, BigDecimal("9.999999999999999999999999"), BigDecimal("9.99999999999998")),
            listOf(BigDecimal("10.0000000000000000001"), BigDecimal("9.99999999999997"), BigDecimal("9.9999999999999799999999999999999999"))),
        TestData(BigDecimal("10.0"), BigDecimal("0.001"),
            listOf(BigDecimal.TEN, BigDecimal("10"), BigDecimal("10.000"), BigDecimal("10.001"), BigDecimal("10.0000000000000000001")),
            listOf(BigDecimal("10.001000000001"), BigDecimal("9.99899999")))
    ))
    //@formatter:on
})
