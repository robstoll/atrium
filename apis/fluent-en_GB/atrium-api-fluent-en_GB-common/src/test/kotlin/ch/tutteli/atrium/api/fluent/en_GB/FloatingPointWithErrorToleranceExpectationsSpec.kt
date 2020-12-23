package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.adjustName
import ch.tutteli.atrium.specs.fun2

class FloatingPointWithErrorToleranceAssertionsSpec :
    ch.tutteli.atrium.specs.integration.FloatingPointWithErrorToleranceAssertionsSpec(
        fun2(Expect<Float>::toBeWithErrorTolerance).adjustName { "$it for Float" },
        fun2(Expect<Double>::toBeWithErrorTolerance).adjustName { "$it for Double" }
    )
