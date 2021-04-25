package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.toEqualWithErrorTolerance
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class FloatingPointExpectationSamples {

    @Test
    fun toEqualWithErrorToleranceFloat() {
        expect(12.001F).toEqualWithErrorTolerance(12.0F, 0.01F)

        fails {
            expect(12.1F).toEqualWithErrorTolerance(12.0F, 0.01F)
        }
    }

    @Test
    fun toEqualWithErrorToleranceDouble() {
        expect(12.001).toEqualWithErrorTolerance(12.0, 0.01)

        fails {
            expect(12.1).toEqualWithErrorTolerance(12.0, 0.01)
        }
    }
}
