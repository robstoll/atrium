package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
import java.time.LocalDateTime

class LocalDateTimeFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.LocalDateTimeFeatureAssertionsSpec(
    property<LocalDateTime, Int>(Expect<LocalDateTime>::year),
    fun1<LocalDateTime, Expect<Int>.() -> Unit>(Expect<LocalDateTime>::year)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<LocalDateTime> = notImplemented()
        var a2: Expect<LocalDateTime> = notImplemented()

        a1.year
        a1 = a1.year { }
        a2.year
        a2 = a2.year { }
    }
}
