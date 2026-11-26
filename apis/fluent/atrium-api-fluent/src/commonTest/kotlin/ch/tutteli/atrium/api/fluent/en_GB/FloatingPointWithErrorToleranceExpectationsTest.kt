package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.adjustName
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.integration.AbstractFloatingPointWithErrorToleranceExpectationsTest
import kotlin.test.Test

class FloatingPointWithErrorToleranceExpectationsTest :
    AbstractFloatingPointWithErrorToleranceExpectationsTest(
        fun2(Expect<Float>::toEqualWithErrorTolerance).adjustName { "$it for Float" },
        fun2(Expect<Double>::toEqualWithErrorTolerance).adjustName { "$it for Double" }
    ) {
    @Test
    fun trigger_run_gutter() = Unit
}
