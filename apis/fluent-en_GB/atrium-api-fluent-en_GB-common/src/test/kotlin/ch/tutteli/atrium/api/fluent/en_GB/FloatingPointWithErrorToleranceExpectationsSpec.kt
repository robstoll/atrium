package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.adjustName
import ch.tutteli.atrium.specs.fun2

class FloatingPointWithErrorToleranceExpectationsSpec :
    ch.tutteli.atrium.specs.integration.FloatingPointWithErrorToleranceExpectationsSpec(
        fun2(Expect<Float>::toEqualWithErrorTolerance).adjustName { "$it for Float" },
        fun2(Expect<Double>::toEqualWithErrorTolerance).adjustName { "$it for Double" }
    )
