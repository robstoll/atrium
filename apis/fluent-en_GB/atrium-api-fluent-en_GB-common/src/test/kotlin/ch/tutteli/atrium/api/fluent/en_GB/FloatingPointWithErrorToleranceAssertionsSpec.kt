package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2

class FloatingPointWithErrorToleranceAssertionsSpec :
    ch.tutteli.atrium.specs.integration.FloatingPointWithErrorToleranceAssertionsSpec(
        AssertionVerbFactory,
        fun2(Expect<Float>::toBeWithErrorTolerance, suffix = " for Float"),
        fun2(Expect<Double>::toBeWithErrorTolerance, suffix = " for Double")
    )
