package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.length
import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CharSequenceFeatureExtractorSamples {

    @Test
    fun lengthFeature() {
        expect("Hello").length.toBeGreaterThan(3)

        fails {
            expect("Hi").length.toBeGreaterThan(3)
        }
    }

    @Test
    fun length() {
        expect("Hello").length { toBeGreaterThan(3) }

        fails {
            expect("Hi").length { toBeGreaterThan(3) }
        }
    }
}
