package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1

object ChronoLocalDateAssertionsSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateAssertionsSpec(
    fun1(Expect<Int>::isAfterOrEquals) // Unresolved reference: isAfterOrEquals
)
