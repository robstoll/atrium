package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.toBeWithErrorTolerance
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class FloatingPointAssertionSamples {

    @Test
    fun toBeWithErrorToleranceFloat() {
        expect(12.001F).toBeWithErrorTolerance(12.0F, 0.01F)

        fails {
            expect(12.1F).toBeWithErrorTolerance(12.0F, 0.01F)
        }
    }

    @Test
    fun toBeWithErrorToleranceDouble() {
        expect(12.001).toBeWithErrorTolerance(12.0, 0.01)

        fails {
            expect(12.1).toBeWithErrorTolerance(12.0, 0.01)
        }
    }
}
